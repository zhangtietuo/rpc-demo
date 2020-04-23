import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Auther: zhangtietuo
 * @Description: 表示服务
 * @Date: 2020/4/20 15:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {

    private String clazz;

    private String method;

    private String returnType;

    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method ethod) {
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClazz(clazz.getName());
        serviceDescriptor.setMethod(ethod.getName());
        serviceDescriptor.setReturnType(ethod.getReturnType().getName());
        Class[] parameterClasses = ethod.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for(int i=0;i<parameterClasses.length;i++) {
            parameterTypes[i] = parameterClasses[i].getName();
        }
        serviceDescriptor.setParameterTypes(parameterTypes);
        return serviceDescriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) o;
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        return clazz + method + returnType + Arrays.toString(parameterTypes);
    }


}
