package com.example.testingproject.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Goods {
    private static final String GOODS_SEQ_NAME = "goods_seq";
    private static final String GOODS_SEQ_NAME_CAP = "GOODS_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GOODS_SEQ_NAME)
    @SequenceGenerator(name = GOODS_SEQ_NAME, sequenceName = GOODS_SEQ_NAME,allocationSize = 1)
    private Long id;

    @Column(name = "good_name")
    private String name;

    @Column(name = "good_price")
    private BigDecimal price;

    @OneToMany
    private List<OrderLines> orderLinesList;
}
