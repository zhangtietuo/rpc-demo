
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/24 16:37
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {

    private Class clazz;

    private Encoder encoder;

    private Decoder decoder;

    private TransportSelector transportSelector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.transportSelector = selector;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);
        Response response = invokeRemote(request);
        if(null == response ||  0 != response.getCode()) {
            throw new IllegalStateException("" + response);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        Response response = null;
        TransportClient client = null;
        try {
            client = transportSelector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream receive = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(receive, receive.available());
            response = decoder.decode(inBytes, Response.class);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            response.setCode(1);
            response.setMessage(e.getMessage());
        } finally {
            if(null != client) {
                transportSelector.release(client);
            }

        }
        return response;
    }
}
