package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.TrainerAdapter;
import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.dto.UserDTO;
import com.mastercode.fitmaster.exception.LoginException;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TrainerService implements AbstractService<Trainer, TrainerDTO> {
    
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TrainerAdapter trainerAdapter;

    private final PasswordEncoder passwordEncoder;

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

    @Override
    public Trainer create(Trainer entity) {
        return trainerRepository.saveAndFlush(entity);
    }

    @Override
    public Trainer update(Trainer entity) {
        return null; // TODO
    }

    @Override
    public void delete(Long id) {
        trainerRepository.deleteById(id);
    }

    public TrainerDTO login(TrainerDTO dto) {
        Trainer trainer = trainerRepository.findByUsername(dto.getUsername()).
                orElseThrow(() -> new LoginException("Unknown trainer"));

        if(passwordEncoder.matches(CharBuffer.wrap(dto.getPassword()), trainer.getPassword()))
            return trainerAdapter.entityToDTO(trainer);
        throw new LoginException("Invalid password");
    }

    public TrainerDTO register(TrainerDTO dto) {
        Optional<Trainer> optionalTrainer = trainerRepository.findByUsername(dto.getUsername());

        if(!optionalTrainer.isEmpty())
            throw new LoginException("Login already exists");

        Trainer trainer = trainerAdapter.dtoToEntity(dto);
        trainer.setPassword(passwordEncoder.encode(CharBuffer.wrap(dto.getPassword())));

        Trainer saved = trainerRepository.save(trainer);

        return trainerAdapter.entityToDTO(saved);
    }

    public UserDTO findByUsername(String username) {
        Trainer trainer = trainerRepository.findByUsername(username).
                orElseThrow(() -> new LoginException("Unknown trainer"));
        return trainerAdapter.entityToDTO(trainer);
    }
}
