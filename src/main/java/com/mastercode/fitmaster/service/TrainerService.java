package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public List<Trainer> getAll() {
        return trainerRepository.findAll();
    }
}
