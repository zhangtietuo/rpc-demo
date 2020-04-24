/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/23 15:55
 */
public class ServiceInvoker {

    public Object invoke(ServiceInstance serviceInstance, Request request) {
        return ReflectionUtils.invoke(serviceInstance.getTarget(), serviceInstance.getMethod(), request.getParameters());
    }
}
