import lombok.Data;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/20 15:24
 */
@Data
public class Response {

    private int code = 0;

    private String message;

    private Object data;
}
