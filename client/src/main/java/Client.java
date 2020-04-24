import java.lang.reflect.Proxy;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/24 16:27
 */
public class Client {

    private ClientConfig clientConfig;

    private Encoder encoder;

    private Decoder decoder;

    private TransportSelector transportSelector;

    public Client() {
        this(new ClientConfig());
    }

    public Client(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        this.encoder = ReflectionUtils.newInstance(this.clientConfig.getEncoderClass());
        this.encoder = ReflectionUtils.newInstance(this.clientConfig.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.clientConfig.getDecoderClass());
        this.transportSelector = ReflectionUtils.newInstance(this.clientConfig.getSelectorClass());
        this.transportSelector.init(this.clientConfig.getServers(), this.clientConfig.getConnectCount(), this.clientConfig.getTransportClass());

    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}, new RemoteInvoker(clazz, encoder, decoder,transportSelector));
    }


}
