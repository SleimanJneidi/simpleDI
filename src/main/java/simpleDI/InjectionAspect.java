package simpleDI;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import simpleDI.annotations.Injectable;
import simpleDI.instantiations.InstantiationMethod;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Aspect
public class InjectionAspect{

    @Pointcut("execution ( (@simpleDI.annotations.Service *).new(..))")
    public void serviceInitPointcut(){}

    @Before("serviceInitPointcut()")
    public void serviceInitAdvice(JoinPoint joinPoint) throws InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object target =  joinPoint.getTarget();
        DIContext.INSTANCE.initService(target);
    }

}

