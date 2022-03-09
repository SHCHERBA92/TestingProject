package com.example.testingproject.controllers;

import com.example.testingproject.services.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class AllGoodController {

    private final GoodService goodService;

    public AllGoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping("/all_goods")
    String allGoods(Model model)
    {
        model.addAttribute("AllGoods", goodService.getAllGoods());
        return "all_goods";
    }

    @PostMapping("/deleteGoods/{id}")
    String deleteGoods(@PathVariable Long id)
    {
        goodService.deleteOrder(id);
        return "redirect:/all_goods";
    }
}
