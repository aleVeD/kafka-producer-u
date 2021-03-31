package com.course.kafkaproducer.producer;

import com.course.kafkaproducer.entity.CarLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CarLocationProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    ObjectMapper objectMapper = new ObjectMapper();
    public void send(CarLocation car) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(car);
        kafkaTemplate.send("t_location", car.getCarId(), json);
    }

}
