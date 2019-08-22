package com.example.demo.dataloader;

import com.example.demo.Repositories.OrderCombinedRepository;
import com.example.demo.Repositories.OrderDateRepository;
import com.example.demo.Repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    OrderCombinedRepository orderCombinedRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderDateRepository orderDateRepository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading data...");

    }
}
