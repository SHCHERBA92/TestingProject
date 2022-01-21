package com.example.testingproject.repository;

import com.example.testingproject.models.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orderr, Long> {

}
