package com.course.kafkaproducer;

import com.course.kafkaproducer.entity.Employee;
import com.course.kafkaproducer.entity.FoodOrder;
import com.course.kafkaproducer.entity.Image;
import com.course.kafkaproducer.entity.SimpleNumber;
import com.course.kafkaproducer.producer.*;
import com.course.kafkaproducer.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
//@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner {

    //@Autowired
    //private KafkaKeyProducer kafkaKeyProducer;
    //private HelloKafkaProducer helloKafkaProducer;

    @Autowired
    private EMployeeJsonProducer producer;

    @Autowired
    private FoodOrderProducer food;

    @Autowired
    private SimpleNumberProducer simpleNumberProducer;

    @Autowired
    private ImageProducer imageProducer;

    @Autowired
    private ImageService imageService;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Image img1 = imageService.generateImage("jpg");
        Image img2 = imageService.generateImage("png");
        Image img3 = imageService.generateImage("svg");
        imageProducer.send(img1);
        imageProducer.send(img2);
        imageProducer.send(img3);
      /*  FoodOrder chickenOrder = new FoodOrder(3, "chicken");
        FoodOrder fishOrder = new FoodOrder(12, "fish");
        FoodOrder pizzaOrder = new FoodOrder(7, "pizza");
        food.send(chickenOrder);
        food.send(fishOrder);
        food.send(pizzaOrder);*/
/*
        for(int i = 100; i < 103; i++){
            SimpleNumber simpleNumber = new SimpleNumber(i);
            simpleNumberProducer.send(simpleNumber);
        }*/

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
