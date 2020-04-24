/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/24 17:15
 */
public class RpcClient {

    public static void main(String[] args) {
        Client client = new Client();
        CalService calService = client.getProxy(CalService.class);
        int r1 = calService.add(1, 2);
        int r2 = calService.minus(2, 1);
    }
}
