package simpleDI.instantiations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by sleimanjneidi on 05/04/2014.
 */
public class FactoryMethod implements InstantiationMethod {
    private String methodName;

    public FactoryMethod(String methodName){
        this.methodName = methodName;
    }

    @Override
    public Object getInstance(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Method method = clazz.getDeclaredMethod(this.methodName);
        return method.invoke(null);
    }
}
