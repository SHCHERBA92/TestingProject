package com.example.testingproject.controllers;

import com.example.testingproject.models.Goods;
import com.example.testingproject.services.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class AddGood {
    private final GoodService goodService;

    public AddGood(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping("/add_good")
    public String addGoodGet()
    {
        return "add_goods";
    }

    @PostMapping("/addGood")
    public String addGoodPost(@RequestParam String nameOfGoods,
                              @RequestParam int priceOfGoods)
    {
        Goods goods = new Goods();

        goods.setName(nameOfGoods);
        goods.setPrice(BigDecimal.valueOf(priceOfGoods));

        goodService.addNewGood(goods);
        return "redirect:/all_goods";
    }
}
