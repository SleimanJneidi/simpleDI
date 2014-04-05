package dummyTypes;

/**
 * Created by sleiman on 05/04/2014.
 */
public class FooWithStaticFactory {

    private FooWithStaticFactory(){
    }

    public static FooWithStaticFactory getObject(){
        return new FooWithStaticFactory();
    }

}
