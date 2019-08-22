package com.example.demo.Repositories;

import com.example.demo.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
    Iterable<OrderDetail> findByAmountBetween(float from, float to);
    Iterable<OrderDetail> findAllByAmountGreaterThanEqual(float amount);
    Iterable<OrderDetail> findAllByAmountIsLessThanEqual(float amount);
    Iterable<OrderDetail> findAllByAmount(float amount);
    Iterable<OrderDetail> findDistinctByDescription(String description);
}
