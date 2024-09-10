package spring_introduction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("aplicationconf.xml");
        Pet pet = context.getBean("myPet", Pet.class);
       pet.say();
       context.close();
System.out.println("ddd");
    }
}
