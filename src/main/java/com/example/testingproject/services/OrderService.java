package com.example.testingproject.services;

import com.example.testingproject.models.Goods;
import com.example.testingproject.models.OrderLines;
import com.example.testingproject.models.Orderr;
import com.example.testingproject.repository.OrderLinesRepo;
import com.example.testingproject.repository.OrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderLinesRepo orderLinesRepo;


    public OrderService(OrderRepo orderRepo, OrderLinesRepo orderLinesRepo) {
        this.orderRepo = orderRepo;
        this.orderLinesRepo = orderLinesRepo;
    }

    // Добавление нового заказа
    public void addNewOrder(Orderr order)
    {
//        order.setId(this.getAllOrders().size() + 1l);

//        order.setOrderLinesList(orderLinesRepo.findAll().
//                add(new OrderLines(orderLinesRepo.findAll().size()+1l,order,)));

        orderRepo.save(order);

    }

    //Изменение существуещого заказа
    public void exchangeCurrentOrder(Long id, Orderr order)
    {
        Optional<Orderr> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isEmpty())
        {
            this.addNewOrder(order);
        }else {
            Orderr currentOrder = optionalOrder.get();

            currentOrder.setAddress(order.getAddress());
            currentOrder.setClient(order.getClient());
//            currentOrder.setLocalDate(order.getLocalDate());
//            currentOrder.setOrderLinesList(order.getOrderLinesList());

            orderRepo.save(currentOrder);
        }
    }

    // Удаление заказа
    public void deleteOrder(Orderr order)
    {
        if (orderRepo.findById(order.getId()).isEmpty())
        {
            throw new NullPointerException("Такого заказа нет");
        }else {
            orderRepo.deleteById(order.getId());
        }

    }

    // Получение всех заказов
    public List<Orderr> getAllOrders()
    {
        return orderRepo.findAll();
    }

    //Получение определённого заказа по id
    public Orderr getOrderById(Long id)
    {
        return orderRepo.getById(id);
    }



    //Получение списка товаров в заказе
    public List<Goods> getAllGoodsOfCurrentOrder(Long id)
    {
        List<OrderLines> orderLinesList = orderRepo.findById(id).get().getOrderLinesList();
        List<Goods> orderList = new ArrayList<>();

        for(int i = 0; i < orderLinesList.size(); i++)
        {
            orderList.add(orderLinesList.get(i).getGoods());
        }

        return orderList;
    }


//
//    //Получение общей стоимости заказа
//    public List<BigDecimal> getAllPrice()
//    {
//        List<BigDecimal> bigDecimals = new ArrayList<>();
//
//        List<Orderr> orderList = this.getAllOrders();
//
//        Map<Orderr,List<OrderLines>> orderListMap = new HashMap<>();
//
//        for (int i = 0; i < orderList.size(); i++)
//        {
//            orderListMap.put(orderList.get(i), orderList.get(i).getOrderLinesList());
//        }
//
//        for (int i = 0; i < orderListMap.size(); i++) {
//
//            bigDecimals.add(
//                    orderListMap.get(orderList.get(i))
//                    .stream().map(orderLines -> orderLines.getGoods())
//                    .map(goods -> goods.getPrice())
//                    .reduce((bigDecimal, bigDecimal2) -> bigDecimal.add(bigDecimal2))
//                    .get()
//            );
//
//        }
//
////        BigDecimal result = goods.stream().map(goods1 -> goods1.getPrice()).reduce((bigDecimal, bigDecimal2) -> bigDecimal.add(bigDecimal2)).get();
//
//        return bigDecimals;
//    }



    // Исключение для отсутствие заказа
    @ExceptionHandler(NullPointerException.class)
    public String handle(NullPointerException e)
    {
        return e.getMessage();
    }

}
