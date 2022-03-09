package com.example.testingproject.controllers;

import com.example.testingproject.services.GoodService;
import com.example.testingproject.services.OrderLinesService;
import com.example.testingproject.services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@Api(description = "список товаров и заказов")
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

    @GetMapping()
    @ApiOperation("Gjkextybt dctuj")
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
