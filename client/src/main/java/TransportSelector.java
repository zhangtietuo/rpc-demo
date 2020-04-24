import java.util.List;

/**
 * @Auther: zhangtietuo
 * @Description: 表示选择哪个server去连接
 * @Date: 2020/4/23 16:43
 */
public interface TransportSelector {


    /**
     * @date:2020/4/23 16:48
     * @author: tietuo.zhang
     * @description: 初始化selector
     * @param peerList 可以连接得server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peerList, int count, Class<? extends TransportClient> clazz);

    /**
     * @date:2020/4/23 16:45
     * @author: tietuo.zhang
     * @description: 选择一个transport与server做交互
     * @param
     */
    TransportClient select();

    void release(TransportClient client);

    void close();





}
