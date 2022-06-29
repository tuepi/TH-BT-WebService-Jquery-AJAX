package com.example.demoboot.repository;

import com.example.demoboot.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHomeRepository extends JpaRepository<Home, Long> {
}
