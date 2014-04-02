package instantiations;

import dummyTypes.DummyFoo;
import junit.framework.Assert;
import org.junit.Test;
import simpleDI.instantiations.ConstructorMethod;

import java.lang.reflect.InvocationTargetException;

public class ConstructorMethodTest {

    @Test
    public void testGetConstructorMethodInstance() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ConstructorMethod constructorMethod = ConstructorMethod.createMethod();
        DummyFoo foo = (DummyFoo)constructorMethod.getInstance(DummyFoo.class);
        Assert.assertEquals(foo.name,"DummyFoo");

    }
}
