package spring_introduction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test6 {
    public static void main(String[] args) {

    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(MyConfig.class);

//        Pet cat1 = context.getBean("catBean", Pet.class);
//        Pet cat2 = context.getBean("dogBean", Pet.class);
//        cat1.say();
//        cat2.say();

        Person person = context.getBean("personBean", Person.class);
        System.out.println(
        person.getSurname());
        System.out.println(
                person.getAge());
//        Person person2 = context.getBean("personBean", Person.class);
//        person.callYourPet();
//        person2.callYourPet();


    }
}
