import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Auther: zhangtietuo
 * @Description: 处理网络请求得handler
 * @Date: 2020/4/23 13:58
 */
public interface RequestHandler {

    void onRequest(InputStream receive, OutputStream response);
}
