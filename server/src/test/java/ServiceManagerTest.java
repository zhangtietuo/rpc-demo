import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/23 15:30
 */
public class ServiceManagerTest {

    ServiceManager sm;

    @Before
    public void init() {
        sm = new ServiceManager();
        TestInterface bean = new TestInterfaceImpl();
        sm.register(TestInterface.class, bean);
    }


    @Test
    public void register() {
        TestInterface bean = new TestInterfaceImpl();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void lookup() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestInterface.class);
        for (Method method: methods) {
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface.class, method);
            Request request = new Request();
            request.setServiceDescriptor(serviceDescriptor);
            ServiceInstance sis = sm.lookup(request);
            assertNotNull(sis);
        }

    }
}