import com.alibaba.fastjson.JSON;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/20 16:15
 */
public class JsonDecoder implements Decoder {

    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
