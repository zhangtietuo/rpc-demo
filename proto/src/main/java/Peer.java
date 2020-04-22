import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther: zhangtietuo
 * @Description: 表示网络传输的一个端点
 * @Date: 2020/4/20 15:19
 */
@Data
@AllArgsConstructor
public class Peer {

    private String host;

    private int port;
}
