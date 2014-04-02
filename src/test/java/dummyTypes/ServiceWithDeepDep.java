package dummyTypes;

import simpleDI.annotations.Injectable;
import simpleDI.annotations.Service;

@Service
public class ServiceWithDeepDep {
    @Injectable
    private FooHasBar fooHasBar;

    @Injectable
    private IFoo foo;

    public FooHasBar getFooHasBar(){
        return this.fooHasBar;
    }

    public IFoo getFoo(){
        return this.foo;
    }
}
