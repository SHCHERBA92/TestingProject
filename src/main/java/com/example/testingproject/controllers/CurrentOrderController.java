package com.example.testingproject.controllers;

import com.example.testingproject.models.Orderr;
import com.example.testingproject.services.GoodService;
import com.example.testingproject.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentOrderController {

    private final GoodService goodService;
    private final OrderService orderService;

    public CurrentOrderController(GoodService goodService, OrderService orderService) {
        this.goodService = goodService;
        this.orderService = orderService;
    }

    @GetMapping("/currentOrder/{id}")
//    @ResponseBody
    String getCurrentOrder(@PathVariable Long id)
    {
        Orderr orderr = new Orderr();
        orderr = orderService.getOrderById(id);
        return null;
    }
}
