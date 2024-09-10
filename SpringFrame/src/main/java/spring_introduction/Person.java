package spring_introduction;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component("personBean")
public class Person {
//@Autowired
//@Qualifier("DogBean")
    private Pet pet;
    @Value("${person.surname}")
    private String surname;
    @Value("${person.age}")
    private int age;



//    @Autowired
    public Person(Pet pet) {
        System.out.println("Person bean is crated");
        this.pet = pet;
    }
//public Person(@Qualifier("catBean") Pet pet) {
//    System.out.println("Person bean is crated");
//    this.pet = pet;

//}
//конвертация pet -> setPet
//@Autowired
//@Qualifier("DogBean")
    public void setPet(Pet pet) {
        System.out.println("Class Person: set pet.. any method");
        this.pet = pet;
    }
    public void callYourPet (){
        System.out.println("Hello my Pet");
        pet.say();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
    System.out.println("Class Person - УСТАНОВКА ФАМИЛИИ");
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("Class Person - SET AGE");
        this.age = age;
    }
}
