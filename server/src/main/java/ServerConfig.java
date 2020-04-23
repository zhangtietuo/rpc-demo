import lombok.Data;

import javax.servlet.http.HttpServlet;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/23 14:45
 */
@Data
public class ServerConfig {

    private Class<? extends  TransportServer> transprotServerClass = HttpTransportServer.class;

    private Class<? extends Encoder> encoderClass = JsonEncoder.class;

    private Class<? extends Decoder> dncoderClass = JsonDecoder.class;

    private int prot = 3000;

}
