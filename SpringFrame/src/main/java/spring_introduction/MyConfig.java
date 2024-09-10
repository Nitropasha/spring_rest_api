package spring_introduction;

import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:myApp.properties")
public class MyConfig {
    @Bean
    @Scope("singleton")
    public Pet catBean() {
        return  new Cat();
    }
    @Bean
    @Scope("singleton")
    public Pet dogBean() {
        return  new Dog();
    }
    @Bean
    @Scope("singleton")
    public Person personBean() {
        return new Person(catBean());
    }



}
