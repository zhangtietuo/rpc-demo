/**
 * @Auther: zhangtietuo
 * @Description: 1.启动，监听端口 2.接收请求处理 3.关闭监听
 * @Date: 2020/4/23 13:56
 */
public interface TransportServer {

    void init(int port, RequestHandler requestHandler);


    void start();



    void stop();
}
