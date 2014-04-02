package simpleDI;


import simpleDI.annotations.Injectable;
import simpleDI.annotations.Service;
import simpleDI.exceptions.CyclicDependencyException;
import simpleDI.instantiations.InstantiationMethod;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sleiman on 31/03/2014.
 */
public enum DIContext {
    INSTANCE;

    private final Set<DIBinding> allBindings;

    public void addBinding(DIBinding binding){
        allBindings.add(binding);
    }

    public void addBinding(Set<DIBinding> bindings){
        allBindings.addAll(bindings);
    }

    public  Set<DIBinding> getAllBindings(){
        return allBindings;
    }

    public  DIBinding getBindingForType(Class clazz){
        for(DIBinding binding: allBindings){
            if(binding.getType().equals(clazz)){
                return binding;

            }
        }
        return null;
    }

    private DIContext(){
        allBindings = new HashSet<DIBinding>();
    }


    public void injectDependencies() throws CyclicDependencyException {
        CycleChecker cycleChecker = new CycleChecker(this.getAllBindings());
        cycleChecker.checkForCycles();
    }

    public Object initService(Object service) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if(!service.getClass().isAnnotationPresent(Service.class)){
            throw new IllegalArgumentException("The passed object is not a service, use "+Service.class.getName() +" annotation");
        }
        for(Field field: service.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Injectable.class)){
                recursiveFieldBinding(service, field);
            }
        }

        return service;
    }

    private void recursiveFieldBinding(Object root, Field field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class fieldType = field.getType();
        field.setAccessible(true);
        DIBinding binding = DIContext.INSTANCE.getBindingForType(fieldType);
        InstantiationMethod method = binding.getInstantiationMethod();
        Object instance = method.getInstance(binding.getBindingType());

        field.set(root,instance);

        for(Field innerField: fieldType.getDeclaredFields()){
            if(innerField.isAnnotationPresent(Injectable.class)){
                this.recursiveFieldBinding(instance, innerField);
            }
        }


    }

}
