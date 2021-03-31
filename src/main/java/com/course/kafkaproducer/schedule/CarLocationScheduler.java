package com.course.kafkaproducer.schedule;

import com.course.kafkaproducer.entity.CarLocation;
import com.course.kafkaproducer.producer.CarLocationProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CarLocationScheduler {

    private static final Logger log = LoggerFactory.getLogger(CarLocationScheduler.class);
    private CarLocation carOne;
    private CarLocation carTwo;
    private CarLocation carThree;

    @Autowired
    private CarLocationProducer carLocationProducer;

    public CarLocationScheduler() {

        long now = System.currentTimeMillis();
        this.carOne = new CarLocation("car_one", now, 0);
        this.carTwo = new CarLocation("car_two", now, 110);
        this.carThree = new CarLocation("car_three", now, 95);
    }

    @Scheduled(fixedRate = 10000)
    public void generateCarLocation(){

        long now = System.currentTimeMillis();
        carOne.setTimestamp(now);
        carTwo.setTimestamp(now);
        carThree.setTimestamp(now);
        carOne.setDistance(carOne.getDistance() + 1);
        carTwo.setDistance(carTwo.getDistance() - 1);
        carThree.setDistance(carThree.getDistance() + 1);
        try {
           carLocationProducer.send(carOne);
           log.info("sending : {}", carOne);
           carLocationProducer.send(carTwo);
            log.info("sending : {}", carTwo);
           carLocationProducer.send(carThree);
            log.info("sending : {}", carThree);
        }catch(Exception e){
            log.warn("Exception: {}", e);
        }
    }
}
