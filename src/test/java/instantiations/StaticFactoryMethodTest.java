package instantiations;

import dummyTypes.FooWithStaticFactory;
import junit.framework.Assert;
import org.junit.Test;
import simpleDI.instantiations.FactoryMethod;
import simpleDI.instantiations.InstantiationMethod;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by sleimanjneidi on 05/04/2014.
 */
public class StaticFactoryMethodTest {

    @Test
    public void testStaticFactoryInit() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        InstantiationMethod instantiationMethod = new FactoryMethod("getObject");
        Object object = instantiationMethod.getInstance(FooWithStaticFactory.class);
        Assert.assertEquals(object.getClass(),FooWithStaticFactory.class);
    }

}
