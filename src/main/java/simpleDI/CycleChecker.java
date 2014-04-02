package simpleDI;

import simpleDI.annotations.Injectable;
import simpleDI.exceptions.CyclicDependencyException;
import simpleDI.instantiations.InstantiationMethod;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by sleiman on 31/03/2014.
 */
public class CycleChecker {

    Set<DIBinding> bindingSet;

    public CycleChecker(Set<DIBinding> bindingSet) {
        this.bindingSet = bindingSet;
    }

    public void checkForCycles() throws CyclicDependencyException {
        for(DIBinding binding: bindingSet){
            checkForCycles(binding.getBindingType(), new HashSet<Class>());
        }
    }

    void checkForCycles(Class clazz, HashSet<Class> visited) throws CyclicDependencyException {
        if(visited.contains(clazz)){
            throw new CyclicDependencyException(String.format("Cyclic Dependency Detected: %s", clazz));
        }
        visited.add(clazz);
        for(Field innerField: clazz.getDeclaredFields()){
            if(innerField.isAnnotationPresent(Injectable.class)){
                this.checkForCycles(innerField.getType(), visited);
            }
        }
    }

}
