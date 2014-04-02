package dummyTypes;

import simpleDI.annotations.Injectable;

/**
 * Created by sleiman on 03/04/2014.
 */
public class CyclicFoo {
    @Injectable
    private CyclicFoo cyclicFoo;
}
