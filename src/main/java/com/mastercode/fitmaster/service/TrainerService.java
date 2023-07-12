package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public List<Trainer> getAll() {
        Trainer t = new Trainer();
        t.setTrainerID(123L);
        t.setFirstName("Uros");
        t.setLastName("Gavrilvoci");
        ArrayList<Trainer> trainers = new ArrayList<>();
        trainers.add(t);
        return trainers;
    }
}
