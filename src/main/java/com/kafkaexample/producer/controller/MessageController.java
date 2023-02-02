package com.kafkaexample.producer.controller;

import com.kafkaexample.producer.model.StockHistoryModel;
import com.kafkaexample.producer.schema.StockHistory;
import com.kafkaexample.producer.service.SpringAvroProducer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@RequestMapping("api")
public class MessageController {

    private SpringAvroProducer springAvroProducer;

    @PostMapping
    public void publish(@RequestBody StockHistoryModel request) throws ExecutionException, InterruptedException {
        StockHistory stockHistory = StockHistory.newBuilder().build();
        stockHistory.setStockName(request.getStockName());
        stockHistory.setTradeType(request.getTradeType());
        stockHistory.setPrice(request.getPrice());
        stockHistory.setAmount(request.getAmount());
        stockHistory.setTradeId(new Random(1000).nextInt());
        stockHistory.setTradeMarket(request.getTradeMarket());
        stockHistory.setTradeQuantity(request.getTradeQuantity());
        springAvroProducer.send(stockHistory);
    }
}
