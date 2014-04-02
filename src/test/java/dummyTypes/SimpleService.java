package dummyTypes;

import simpleDI.annotations.Injectable;
import simpleDI.annotations.Service;

/**
 * Created by sleiman on 30/03/2014.
 */

@Service
public class SimpleService {

    @Injectable
    private Bar bar;

    @Injectable
    private IFoo foo;

    public IFoo getFoo(){
        return this.foo;
    }

    public Bar getBar(){
        return this.bar;
    }
}
