package com.example.testingproject.models;



import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Entity
public class Orderr {

    private static final String SEQ_NAME = "order_seq";
    private static final String SEQ_NAME_CAP = "ORDER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME,sequenceName = SEQ_NAME_CAP, allocationSize = 1)
    private Long id;

    @Column(name = "client")
    private String client;

    @DateTimeFormat
    @Column(name = "date")
    private LocalDate localDate;

    @Column(name = "address")
    private String address;

    @OneToMany
    private List<OrderLines> orderLinesList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<OrderLines> getOrderLinesList() {
        return orderLinesList;
    }

    public void setOrderLinesList(List<OrderLines> orderLinesList) {
        this.orderLinesList = orderLinesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderr orderr = (Orderr) o;
        return Objects.equals(id, orderr.id) && Objects.equals(client, orderr.client) && Objects.equals(localDate, orderr.localDate) && Objects.equals(address, orderr.address) && Objects.equals(orderLinesList, orderr.orderLinesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, localDate, address, orderLinesList);
    }
}
