/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/20 16:08
 */
public interface Decoder {

    <T> T decode(byte[] bytes, Class<T> clazz);
}
