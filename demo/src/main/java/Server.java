/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/24 17:14
 */
public class Server {

    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(CalService.class, new CalServiceImpl());
        rpcServer.start();
    }
}
