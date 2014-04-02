package bindings;

import dummyTypes.Bar;
import dummyTypes.DummyFoo;
import dummyTypes.IFoo;
import dummyTypes.SimpleService;
import junit.framework.Assert;
import org.junit.Test;
import simpleDI.DIBinding;
import simpleDI.DIContext;
import simpleDI.exceptions.InvalidDIBindingException;

/**
 * Created by sleiman on 30/03/2014.
 */
public class AbstractToConcreteTest{


    @Test
    public void testBindingToSubclass() throws InvalidDIBindingException {
        DIBinding fooDIBinding = new DIBinding.BindingBuilder(IFoo.class).to(Bar.class).build();
        DIBinding barDIBinding = new DIBinding.BindingBuilder(Bar.class).toSelf().build();

        DIContext.INSTANCE.addBinding(fooDIBinding);
        DIContext.INSTANCE.addBinding(barDIBinding);
        DIContext.INSTANCE.injectDependencies();
        SimpleService simpleService = new SimpleService();
        Assert.assertEquals(simpleService.getFoo().getClass(),Bar.class);
    }


    @Test
    public void testBindingToSelf() throws InvalidDIBindingException {

        DIBinding fooDIBinding = new DIBinding.BindingBuilder(IFoo.class).to(Bar.class).build();
        DIBinding barDIBinding = new DIBinding.BindingBuilder(Bar.class).toSelf().build();
        DIContext.INSTANCE.addBinding(fooDIBinding);
        DIContext.INSTANCE.addBinding(barDIBinding);
        DIContext.INSTANCE.injectDependencies();
        SimpleService simpleService = new SimpleService();
        Assert.assertEquals(simpleService.getBar().getClass(),Bar.class);

    }

    @Test(expected = InvalidDIBindingException.class)
    public void testIrrelevantBinding() throws InvalidDIBindingException{
        DIBinding fooDIBinding = new DIBinding.BindingBuilder(IFoo.class).to(DummyFoo.class).build(); //should not be possible, DummyFoo does not extend or implement IFOO
        DIContext.INSTANCE.addBinding(fooDIBinding);
        DIContext.INSTANCE.injectDependencies();
    }


}
