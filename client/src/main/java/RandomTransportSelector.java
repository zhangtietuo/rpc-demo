import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/23 17:42
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {

    //已经连接好得client
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        this.clients = new ArrayList<TransportClient>();
    }

    public synchronized void init(List<Peer> peerList, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for(Peer peer: peerList) {
            for(int i =0; i<count;i++) {
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect server: {}", peer);
        }
    }

    public TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    public void release(TransportClient client) {
        clients.add(client);
    }

    public void close() {
        for(TransportClient client: clients) {
            client.close();
        }
        clients.clear();
    }
}
