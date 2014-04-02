package dummyTypes;

import simpleDI.annotations.Injectable;

/**
 * Created by sleiman on 31/03/2014.
 */

public class FooHasBar {

    @Injectable
    private BarWithinFoo barWithinFoo;

    public BarWithinFoo getBarWithinFoo(){
        return this.barWithinFoo;
    }
}
