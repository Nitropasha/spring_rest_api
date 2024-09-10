package spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test4 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("aplicationconf2.xml");
        Dog myDog = context.getBean("myPet", Dog.class);
        myDog.setName("Балка");
        Dog yourDog = context.getBean("myPet", Dog.class);
        yourDog.setName("Стрелка");
        System.out.println(myDog.getName());
        System.out.println(yourDog.getName());
       System.out.println("Переменные ссылаются на один и тот же обьект?" + (myDog==yourDog));
       System.out.println(myDog);
       System.out.println(yourDog);
context.close();


    }

}
