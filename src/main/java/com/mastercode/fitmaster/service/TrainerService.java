package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.TrainerAdapter;
import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService implements AbstractService<Trainer, TrainerDTO> {
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TrainerAdapter trainerAdapter;

    @Override
    public List<Trainer> getAll() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer findByID(Long id) {
        return trainerRepository.getByTrainerID(id);
    }

    @Override
    public List<TrainerDTO> getAllDTOs() {
        return trainerAdapter.entitiesToDTOs(trainerRepository.findAll());
    }
}
