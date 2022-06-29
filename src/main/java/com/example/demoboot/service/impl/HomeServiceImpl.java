package com.example.demoboot.service.impl;

import com.example.demoboot.model.Home;
import com.example.demoboot.repository.IHomeRepository;
import com.example.demoboot.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeServiceImpl implements IHomeService {
    @Autowired
    IHomeRepository homeRepository;
    @Override
    public Page<Home> findAll(Pageable pageable) {
        return homeRepository.findAll(pageable);
    }

    @Override
    public Optional<Home> findById(Long id) {
        return homeRepository.findById(id);
    }

    @Override
    public Home save(Home home) {
        return homeRepository.save(home);
    }

    @Override
    public void remove(Long id) {
        homeRepository.deleteById(id);
    }
}
