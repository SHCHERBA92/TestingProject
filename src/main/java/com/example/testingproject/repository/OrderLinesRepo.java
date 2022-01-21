package com.example.testingproject.repository;

import com.example.testingproject.models.OrderLines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLinesRepo extends JpaRepository<OrderLines, Long> {
}
