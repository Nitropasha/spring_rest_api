package spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test5 {
    public static void main(String[] args) {


    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("aplicationconf2.xml");
            Cat myCat = context.getBean("myPet", Cat.class);
            myCat.say();
            context.close();


    }

}
