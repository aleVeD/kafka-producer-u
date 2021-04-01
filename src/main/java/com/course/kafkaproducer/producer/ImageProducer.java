package com.course.kafkaproducer.producer;

import com.course.kafkaproducer.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ImageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    public void send(Image image) throws JsonProcessingException {
        String json = mapper.writeValueAsString(image);
        kafkaTemplate.send("t_image", image.getType(), json);
    }
}
