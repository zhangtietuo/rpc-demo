import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @Auther: zhangtietuo
 * @Description: 一个具体服务
 * @Date: 2020/4/23 14:52
 */
@Data
@AllArgsConstructor
public class ServiceInstance {

    /**
     * 服务有哪个对象提供
     */
    private Object target;

    /**
     * 对象哪个方法暴漏提供服务
     */
    private Method method;
}

