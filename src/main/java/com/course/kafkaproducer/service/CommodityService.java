package com.course.kafkaproducer.service;
import com.course.kafkaproducer.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CommodityService {

    private static final Map<String, Commodity> COMMODITY_BASE = new HashMap<String, Commodity>();
    private static final String COPPER = "copper";
    private static final String GOLD = "gold";
    private static final double MAX_ADJUSTMENT = 1.05;
    private static final double MIN_ADJUSTMENT = 0.95;
    static {
        Long timestamp = System.currentTimeMillis();
        COMMODITY_BASE.put(GOLD, new Commodity(GOLD, 125.06, "ounce", timestamp));
        COMMODITY_BASE.put(COPPER, new Commodity(COPPER, 189.28, "tonne", timestamp));
    }

    public Commodity createCommodity(String name){
        if(!COMMODITY_BASE.keySet().contains(name)){
            throw new IllegalArgumentException("Invalid commodity "+ name);
        }
        Commodity commodity = COMMODITY_BASE.get(name);
        double basePrice = commodity.getPrice();
        double newPrice = basePrice * ThreadLocalRandom.current().nextDouble(MIN_ADJUSTMENT, MAX_ADJUSTMENT);
        commodity.setPrice(newPrice);
        commodity.setTimestamp(System.currentTimeMillis());
        return commodity;
    }

    public List<Commodity> getList(){
        List<Commodity> result = new ArrayList<Commodity>();
        COMMODITY_BASE.keySet().forEach(c -> result.add(createCommodity(c)));
        return result;
    }
}
