package bindings;

import dummyTypes.*;
import junit.framework.Assert;
import org.junit.Test;
import simpleDI.DIBinding;
import simpleDI.DIContext;
import simpleDI.exceptions.CyclicDependencyException;
import simpleDI.exceptions.InvalidDIBindingException;

/**
 * Created by sleiman on 31/03/2014.
 */
public class ServiceWithDepTest {

    @Test
    public void testDeepDep() throws InvalidDIBindingException {
        DIBinding fooBinding = new DIBinding.BindingBuilder(FooHasBar.class).toSelf().build();
        DIBinding barBinding = new DIBinding.BindingBuilder(BarWithinFoo.class).toSelf().build();
        DIBinding iFooToBarBinding = new DIBinding.BindingBuilder(IFoo.class).to(Bar.class).build();

        DIContext.INSTANCE.addBinding(fooBinding);
        DIContext.INSTANCE.addBinding(barBinding);
        DIContext.INSTANCE.addBinding(iFooToBarBinding);

        DIContext.INSTANCE.injectDependencies();

        ServiceWithDeepDep serviceWithDeepDep = new ServiceWithDeepDep();

        Assert.assertEquals(serviceWithDeepDep.getFoo().getClass(),Bar.class);
        Assert.assertEquals(serviceWithDeepDep.getFooHasBar().getBarWithinFoo().getClass(),BarWithinFoo.class);
    }
    @Test(expected = CyclicDependencyException.class)
    public void testCycles() throws InvalidDIBindingException {
        DIBinding fooBinding = new DIBinding.BindingBuilder(CyclicFoo.class).toSelf().build();

        DIContext.INSTANCE.addBinding(fooBinding);

        DIContext.INSTANCE.injectDependencies();

    }
}
