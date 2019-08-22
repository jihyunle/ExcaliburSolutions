package com.example.demo.Repositories;

import com.example.demo.OrderCombined;
import org.springframework.data.repository.CrudRepository;

public interface OrderCombinedRepository extends CrudRepository<OrderCombined, Long> {
    Iterable<OrderCombined> findAllByDateAfter(String date);
    Iterable<OrderCombined> findAllByDateBefore(String date);
    Iterable<OrderCombined> findAllByDateBetween(String from, String to);
    Iterable<OrderCombined> findAllByAmountEquals(float amount);
    Iterable<OrderCombined> findAllByAmountIsLessThanEqual(float amount);
    Iterable<OrderCombined> findAllByAmountGreaterThanEqual(float amount);
    Iterable<OrderCombined> findAllByAmountBetween(float from, float to);
    Iterable<OrderCombined> findAllByDescriptionContains(String keyword);

}
