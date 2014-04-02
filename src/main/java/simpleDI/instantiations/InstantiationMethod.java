package simpleDI.instantiations;

import java.lang.reflect.InvocationTargetException;

public interface InstantiationMethod {
    Object getInstance(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
