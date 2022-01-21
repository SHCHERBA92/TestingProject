package com.example.testingproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class OrderLines {
    private static final String SEQ_ORDER_LINE = "seq_order_line";
    private static final String SEQ_ORDER_LINE_CUP = "SEQ_ORDER_LINE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = SEQ_ORDER_LINE)
    @SequenceGenerator(name = SEQ_ORDER_LINE, sequenceName = SEQ_ORDER_LINE_CUP, allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orderr order;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;
}
