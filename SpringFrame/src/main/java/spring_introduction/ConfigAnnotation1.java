package spring_introduction;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigAnnotation1 {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("application3.xml");
//        Cat myCat = context.getBean("cat", Cat.class);
//        myCat.say();

        Person person = context.getBean("personBean", Person.class);
        person.callYourPet();
        System.out.println(person.getAge() + " " + person.getSurname());
        context.close();



    }
}