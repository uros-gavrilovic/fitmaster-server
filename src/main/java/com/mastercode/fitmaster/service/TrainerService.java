package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.TrainerAdapter;
import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.dto.UserDTO;
import com.mastercode.fitmaster.exception.LoginException;
import com.mastercode.fitmaster.exception.RegisterException;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.repository.TrainerRepository;
import com.mastercode.fitmaster.util.DescriptionUtils;
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
        return trainerRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
        trainerRepository.deleteById(id);
    }

    public Trainer login(UserDTO userDTO) {
        Trainer trainer = trainerRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new LoginException(DescriptionUtils.getErrorDescription("WRONG_USERNAME_OR_PASSWORD"), HttpStatus.UNAUTHORIZED));

        if (passwordEncoder.matches(CharBuffer.wrap(userDTO.getPassword()), trainer.getPassword())) return trainer;

        throw new LoginException(DescriptionUtils.getErrorDescription("WRONG_USERNAME_OR_PASSWORD"), HttpStatus.UNAUTHORIZED);
    }

    public Trainer registerTrainer(Trainer trainer) {
        Optional<Trainer> optionalTrainer = trainerRepository.findByUsername(trainer.getUsername());

        if (optionalTrainer.isPresent())
            throw new RegisterException(DescriptionUtils.getErrorDescription("USERNAME_TAKEN"), HttpStatus.CONFLICT);

        trainer.setPassword(passwordEncoder.encode(CharBuffer.wrap(trainer.getPassword())));
        return trainerRepository.save(trainer);
    }

    public UserDTO findByUsername(String username) {
        Trainer trainer = trainerRepository.findByUsername(username)
                .orElseThrow(() -> new LoginException("Unknown trainer", HttpStatus.NOT_FOUND));
        return trainerAdapter.entityToDTO(trainer);
    }

    public Trainer changePassword(Long trainerID, String oldPassword, String newPassword) {
        Trainer trainer = trainerRepository.getByTrainerID(trainerID);

        if (passwordEncoder.matches(CharBuffer.wrap(oldPassword), trainer.getPassword())) {
            trainer.setPassword(passwordEncoder.encode(CharBuffer.wrap(newPassword)));
            return trainerRepository.saveAndFlush(trainer);
        }

        throw new LoginException(DescriptionUtils.getErrorDescription("WRONG_PASSWORD"), HttpStatus.UNAUTHORIZED);
    }
}
