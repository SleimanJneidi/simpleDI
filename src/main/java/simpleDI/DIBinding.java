package simpleDI;


import simpleDI.exceptions.InvalidDIBindingException;
import simpleDI.instantiations.ConstructorMethod;
import simpleDI.instantiations.InstantiationMethod;

import java.util.ArrayList;
import java.util.List;

public class DIBinding{

    private Class type;
    private Class bindingType;
    private InstantiationMethod instantiationMethod;

    public static class BindingBuilder {
        private Class type;
        private Class binding;
        private InstantiationMethod instantiationMethod;

        public BindingBuilder(Class type){
            this.type = type;
            this.instantiationMethod = new ConstructorMethod(type);
        }

        public BindingBuilder to(Class clazz) {
            this.binding = clazz;
            return this;
        }

        public BindingBuilder toSelf(){
            this.binding = this.type;
            return this;
        }
        public BindingBuilder initMethod(InstantiationMethod instantiationMethod){
            this.instantiationMethod = instantiationMethod;
            return this;
        }

        public DIBinding build() throws InvalidDIBindingException {
            if(null == this.binding){
                throw new InvalidDIBindingException("No Binding specified for the type: "+ this.type);
            }
            if(! this.type.isAssignableFrom(this.binding)){
                throw new InvalidDIBindingException(this.binding.getName()+" cannot be assigned to "+this.type);
            }
            return new DIBinding(this);
        }

    }
    public Class getType(){
        return this.type;
    }
    public  Class getBindingType(){
        return this.bindingType;
    }
    public InstantiationMethod getInstantiationMethod(){
        return this.instantiationMethod;
    }

    private DIBinding(BindingBuilder builder) {
        this.type = builder.type;
        this.bindingType = builder.binding;
        this.instantiationMethod = builder.instantiationMethod;
    }
}
