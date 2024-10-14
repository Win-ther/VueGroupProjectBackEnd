package se.iths.vuegroupprojectbackend;

import org.springframework.boot.SpringApplication;

public class TestVueGroupProjectBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.from(VueGroupProjectBackEndApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
