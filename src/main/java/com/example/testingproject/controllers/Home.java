package com.example.testingproject.controllers;

import com.example.testingproject.services.GoodService;
import com.example.testingproject.services.OrderLinesService;
import com.example.testingproject.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    private final GoodService goodService;
    private final OrderService orderService;
    private final OrderLinesService orderLinesService;

    private boolean flagFirstIdGoods;

    public Home(GoodService goodService, OrderService orderService, OrderLinesService orderLinesService) {
        this.goodService = goodService;
        this.orderService = orderService;
        this.orderLinesService = orderLinesService;
        this.flagFirstIdGoods = true;
    }

    @GetMapping("/home")
    public String home(Model model)
    {
        if (this.flagFirstIdGoods)
        {
            goodService.checkGoodsWithFirstId();
            flagFirstIdGoods = false;
        }

        model.addAttribute("Orders", orderLinesService.getAllGoodsFromOrder());
        model.addAttribute("Good1", orderLinesService.getListGoodsOfOrder());
        return "home";
    }


}
