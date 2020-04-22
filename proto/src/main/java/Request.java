import lombok.Data;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/20 15:23
 */
@Data
public class Request {

    private ServiceDescriptor serviceDescriptor;

    private Object[] parameters;
}
