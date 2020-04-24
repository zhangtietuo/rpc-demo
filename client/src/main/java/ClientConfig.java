import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/24 16:17
 */
@Data
public class ClientConfig {

    private Class<? extends  TransportClient> transportClass = HttpTransportClient.class;

    private Class<? extends Encoder> encoderClass = JsonEncoder.class;

    private Class<? extends Decoder> decoderClass = JsonDecoder.class;

    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    private int connectCount = 1;

    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));
}
