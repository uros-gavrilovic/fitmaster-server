package com.mastercode.fitmaster.dataloader;

import com.mastercode.fitmaster.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DataLoader {

    @Autowired
    TrainerRepository trainerRepository;

    abstract void loadTestData();
}
