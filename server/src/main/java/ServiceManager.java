import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: zhangtietuo
 * @Description: 管理暴露得服务
 * @Date: 2020/4/23 14:54
 */
@Slf4j
public class ServiceManager {

    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<ServiceDescriptor, ServiceInstance>();
    }

    /**
     * @date:2020/4/23 14:57
     * @author: tietuo.zhang
     * @description:
     * @param interfaceClass 接口类
     * @param bean 服务提供对象
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for(Method method: methods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(interfaceClass, method);
            services.put(serviceDescriptor, sis);
            log.info("register service: {} {}", serviceDescriptor.getClazz(), serviceDescriptor.getMethod());
        }
    }

    public ServiceInstance lookup(Request request) {

        ServiceDescriptor serviceDescriptor = request.getServiceDescriptor();
        return services.get(serviceDescriptor);


    }
}
