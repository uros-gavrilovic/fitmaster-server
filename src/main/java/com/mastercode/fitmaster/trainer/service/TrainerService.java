package com.mastercode.fitmaster.trainer.service;

import com.mastercode.fitmaster.trainer.adapter.TrainerAdapter;
import com.mastercode.fitmaster.trainer.repository.TrainerRepository;
import org.springframework.stereotype.Component;

@Component
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainerAdapter trainerAdapter;

    public TrainerService(TrainerRepository trainerRepository, TrainerAdapter trainerAdapter) {
        this.trainerRepository = trainerRepository;
        this.trainerAdapter = trainerAdapter;
    }

}
