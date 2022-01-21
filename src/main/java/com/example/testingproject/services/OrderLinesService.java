package com.example.testingproject.services;

import com.example.testingproject.models.Goods;
import com.example.testingproject.models.OrderLines;
import com.example.testingproject.models.Orderr;
import com.example.testingproject.repository.GoodRepo;
import com.example.testingproject.repository.OrderLinesRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderLinesService {

    private final OrderLinesRepo orderLinesRepo;
    private final GoodRepo goodRepo;

    long countOrderr = 0;

    public OrderLinesService(OrderLinesRepo orderLinesRepo, GoodRepo goodRepo) {
        this.orderLinesRepo = orderLinesRepo;
        this.goodRepo = goodRepo;
    }

    // Получение общей стоимости всех товаров в заказе
    public Map<Orderr, BigDecimal> getAllGoodsFromOrder()
    {
        Map<Orderr, BigDecimal> map = new HashMap<>();
        List<OrderLines> orderLinesList = orderLinesRepo.findAll();
        Orderr orderr = null;
        BigDecimal bigDecimal, oldDecimal;

        for (int i = 0; i < orderLinesList.size(); i++) {

            orderr = orderLinesList.get(i).getOrder();
            bigDecimal = orderLinesList.get(i).getGoods().getPrice();

            if (map.get(orderr) == null)
            {
                map.put(orderr,(BigDecimal.ZERO).add(bigDecimal));
            }else
            {
                map.put(orderr,map.get(orderr).add(bigDecimal));
            }

        }
        return map;
    }

    // получения списка товаров для заказа
    public Map<Orderr, List<Goods>> getListGoodsOfOrder()
    {
        Map<Orderr, List<Goods>> map = new HashMap<>();
        List<OrderLines> orderLinesList = orderLinesRepo.findAll();

        Orderr orderr = null;
        List<Goods> goods = null;

        for (int i = 0; i < orderLinesList.size(); i++) {

            orderr = orderLinesList.get(i).getOrder();
            if (map.get(orderr)==null)
            {
                goods = new ArrayList<>();
            }
            goods.add(orderLinesList.get(i).getGoods());

            map.put(orderr, goods);
        }
        return map;
    }

    ///
    public void addDefaultOrder(Orderr orderr)
    {
//        countOrderr = orderLinesRepo.findAll().size();
//        if (countOrderr!=orderLinesRepo.findAll().size())

        OrderLines orderLines = new OrderLines();

        orderLines.setOrder(orderr);
        orderLines.setGoods(goodRepo.getById(1l));
//        orderLines.setId(orderLinesRepo.findAll().size() + 1l);

        orderLinesRepo.save(orderLines);
    }
}
