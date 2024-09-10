package spring_introduction;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component("DogBean")
//@Scope ("prototype")
public class Dog implements Pet{
    private String name;
    public Dog() {
        System.out.println("Dog bean is created");
    }
    @Override
    public void say(){
        System.out.println("Gav - Gav - Gav");
    }

    public void init() {
        System.out.println("INIT METOD");
    }
    public void destroy() {
        System.out.println("INIT DESTROY");
    }

    public void setName(String name) {
        System.out.println("установка имени пса");
        this.name = name;
    }

    public String getName() {

        return name;

    }
}
