package com.course.kafkaproducer.schedule;

import com.course.kafkaproducer.entity.Commodity;
import com.course.kafkaproducer.producer.EMployeeJsonProducer;
import com.course.kafkaproducer.service.CommodityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class CommoditySchedule {
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private EMployeeJsonProducer eMployeeJsonProducer;
    @Scheduled(fixedRate = 5000)
    public void fetchCommodity(){
        List<Commodity> commodities = restTemplate.exchange("http://localhost:8080/api/commodity/all",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Commodity>>() {
                }).getBody();
        commodities.forEach(c -> {
            try {
                eMployeeJsonProducer.sendMessage(c);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
