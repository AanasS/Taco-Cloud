package com.example.tacos.repository;

import com.example.tacos.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
}
