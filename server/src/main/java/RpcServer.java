import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/23 15:57
 */
@Slf4j
public class RpcServer {

    private ServerConfig serverConfig;

    private TransportServer transportServer;

    private Encoder encoder;

    private Decoder decoder;

    private ServiceManager serviceManager;

    private ServiceInvoker serviceInvoker;

    public RpcServer() {

    }

    public RpcServer(ServerConfig config) {
        this.serverConfig = config;
        this.transportServer = ReflectionUtils.newInstance(config.getTransprotServerClass());
        this.transportServer.init(config.getProt(), handler);
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();


    }

    private  RequestHandler handler = new RequestHandler() {
        public void onRequest(InputStream receive, OutputStream response) {
           Response resp = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request: {}", request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis, request);
                resp.setData(ret);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                resp.setCode(1);
                resp.setMessage("rpc server got error:" + e.getMessage());

            } finally {
              byte[] outBytes = encoder.encode(resp);
                try {
                    response.write(outBytes);
                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);


    }

    public void start() {
        this.transportServer.start();

    }

    public void stop() {
        this.transportServer.stop();

    }
}
