import java.io.InputStream;

/**
 * @Auther: zhangtietuo
 * @Description: 1.创建链接 2.发送数据等待响应 3.关闭链接
 * @Date: 2020/4/22 17:18
 */
public interface TransportClient {

    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
