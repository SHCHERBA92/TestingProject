package com.example.testingproject.services;

import com.example.testingproject.models.Goods;
import com.example.testingproject.repository.GoodRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class GoodService {
    private final GoodRepo goodRepo;

    public GoodService(GoodRepo goodRepo) {
        this.goodRepo = goodRepo;
    }

    // добавление нового товара
    public void addNewGood(Goods goods)
    {
        /*
            Добавить проверку существует ли уже такой товар
        * */
//        goods.setId(this.getAllGoods().size() + 1L);
        // не пойму почему id стартует со второго номера, вот такой костыль приделал.

        goodRepo.save(goods);
    }

    //измененме тавара
    public void exchangeCurrentGood(Long id, Goods goods)
    {
        Optional<Goods> optionalOrder = goodRepo.findById(id);
        if (optionalOrder.isEmpty())
        {
            this.addNewGood(goods);
        }else {
            Goods currentGood = optionalOrder.get();

            currentGood.setName(goods.getName());
            currentGood.setPrice(goods.getPrice());

            goodRepo.save(currentGood);
        }
    }

    // Удаление товара
    public void deleteOrder(Goods goods)
    {
        if (goodRepo.findById(goods.getId()).isEmpty())
        {
            throw new NullPointerException("Такого заказа нет");
        }else {
            goodRepo.deleteById(goods.getId());
        }

    }

    // Удаление товара по id
    public void deleteOrder(Long id)
    {
        if (goodRepo.findById(id).isEmpty())
        {
            throw new NullPointerException("Такого заказа нет");
        }else {
            goodRepo.deleteById(id);
        }

    }

    // Получение всех товаров
    public List<Goods> getAllGoods()
    {
        return goodRepo.findAll();
    }

    //Получение определённого товара по id
    public Goods getGoodById(Long id)
    {
        if (goodRepo.findById(id).isPresent())
        {
            return goodRepo.getById(id);
        }else {
            return null;
        }

    }

    //Проверка первого пустого товара
    public void checkGoodsWithFirstId()
    {
        Goods good = this.getGoodById(1l);
        Goods tempGoods = new Goods();
        boolean equalGoodsAndTempGoods;

        tempGoods.setId(1l);
        tempGoods.setName(" ");
        tempGoods.setPrice(BigDecimal.ZERO);

        if (good != null)
        {
            equalGoodsAndTempGoods = good.equals(tempGoods);
            if (!equalGoodsAndTempGoods)
            {
                this.addNewGood(tempGoods);
            }
        }else {
            this.addNewGood(tempGoods);
        }
    }

}
