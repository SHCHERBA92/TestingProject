package com.example.testingproject.repository;

import com.example.testingproject.models.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepo extends JpaRepository<Goods, Long> {
}
