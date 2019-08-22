package com.example.demo.Repositories;

import com.example.demo.OrderDate;
import org.springframework.data.repository.CrudRepository;

public interface OrderDateRepository extends CrudRepository<OrderDate, Long> {
    Iterable<OrderDate> findByDateBetween(String from, String to);
    Iterable<OrderDate> findAllByDateBefore(String date);
    Iterable<OrderDate> findAllByDateAfter(String date);
}
