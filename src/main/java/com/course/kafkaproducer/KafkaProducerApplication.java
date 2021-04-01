package com.course.kafkaproducer;

import com.course.kafkaproducer.entity.Employee;
import com.course.kafkaproducer.entity.FoodOrder;
import com.course.kafkaproducer.producer.EMployeeJsonProducer;
import com.course.kafkaproducer.producer.FoodOrderProducer;
import com.course.kafkaproducer.producer.HelloKafkaProducer;
import com.course.kafkaproducer.producer.KafkaKeyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner {

    //@Autowired
    //private KafkaKeyProducer kafkaKeyProducer;
    //private HelloKafkaProducer helloKafkaProducer;

    @Autowired
    private EMployeeJsonProducer producer;

    @Autowired
    private FoodOrderProducer food;
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        FoodOrder chickenOrder = new FoodOrder(3, "chicken");
        FoodOrder fishOrder = new FoodOrder(12, "fish");
        FoodOrder pizzaOrder = new FoodOrder(7, "pizza");
        food.send(chickenOrder);
        food.send(fishOrder);
        food.send(pizzaOrder);

        /*for (int i = 0; i < 5; i++) {
            Employee employee = new Employee("emp-" + i, "Employee " + i, LocalDate.now());
            producer.sendMessage(employee);
        }*/
    }
/*
    @Override
    public void run(String... args) throws Exception {
       // helloKafkaProducer.sendHello("Ale: "+Math.random());
        for(int i =0; i < 1000; i++){
            String key = "key-"+ i%4;
            String data = "data " + i + " with key " +key;
            kafkaKeyProducer.send(key, data);

        }
    }*/
}
