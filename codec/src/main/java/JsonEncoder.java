import com.alibaba.fastjson.JSON;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/20 16:13
 */
public class JsonEncoder implements Encoder {

    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
