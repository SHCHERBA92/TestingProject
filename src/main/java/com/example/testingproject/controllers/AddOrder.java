package com.example.testingproject.controllers;

import com.example.testingproject.models.Orderr;
import com.example.testingproject.services.OrderLinesService;
import com.example.testingproject.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class AddOrder {
    private final OrderService orderService;
    private final OrderLinesService orderLinesService;

    public AddOrder(OrderService orderService, OrderLinesService orderLinesService) {
        this.orderService = orderService;
        this.orderLinesService = orderLinesService;
    }

    @GetMapping("/add_order")
    public String addOrder(Model model)
    {
        return "add_order";
    }

    @PostMapping("/addOrderAction")
    public String addOrderPost(@RequestParam String nameOfUser,
                               @RequestParam String addressOfUser)
    {
        Orderr orderr = new Orderr();
        orderr.setClient(nameOfUser);
        orderr.setAddress(addressOfUser);
        orderr.setLocalDate(LocalDate.now());

        orderService.addNewOrder(orderr);

        orderLinesService.addDefaultOrder(orderr);

        return "redirect:/home";
    }
}
