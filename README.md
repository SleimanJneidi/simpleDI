# simpleDI

SimpleDI is a simple dependency injection implementation for java, simpleDI ia a proof of concept
rather than a real framework.SimpleDI uses AspectJ to hijack objects' instantiations in order
to instantiate their dependencies.

## Examples
```java
public interface IFoo {
    String fn();
}

public class Bar implements IFoo{

    @Override
    public String fn() {
        return "Bar";
    }
}
@Service
public class SimpleService {

    @Injectable
    private IFoo foo;
}
// Inject Bar to IFoo
DIBinding fooDIBinding = new DIBinding.BindingBuilder(IFoo.class).to(Bar.class).build();
DIContext.INSTANCE.addBinding(fooDIBinding);
DIContext.INSTANCE.injectDependencies();
```
## Todo
* Support lazy initialisation of dependencies