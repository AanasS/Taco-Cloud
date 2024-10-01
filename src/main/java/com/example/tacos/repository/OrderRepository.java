package com.example.tacos.repository;

import com.example.tacos.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {
//    TacoOrder save(TacoOrder tacoOrder);
}
