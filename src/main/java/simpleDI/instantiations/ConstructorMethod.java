package simpleDI.instantiations;



import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstructorMethod implements InstantiationMethod {

    private Object[] params;

    @Override
    public Object getInstance(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return clazz.newInstance();
    }

    public static ConstructorMethod createMethod(Object... params) {
        return new ConstructorMethod(params);
    }

    public ConstructorMethod(Object... params) {
        this.params = params;
    }



}
