package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.TrainerAdapter;
import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.dto.UserDTO;
import com.mastercode.fitmaster.exception.LoginException;
import com.mastercode.fitmaster.exception.RegisterException;
import com.mastercode.fitmaster.model.TrainerEntity;
import com.mastercode.fitmaster.repository.TrainerRepository;
import com.mastercode.fitmaster.util.DescriptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TrainerService implements AbstractService<TrainerEntity, TrainerDTO> {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TrainerAdapter trainerAdapter;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<TrainerEntity> getAll() {
        return trainerRepository.findAll();
    }

    @Override
    public TrainerEntity findByID(Long id) {
        return trainerRepository.getByTrainerID(id);
    }

    @Override
    public List<TrainerDTO> getAllDTOs() {
        return trainerAdapter.entitiesToDTOs(trainerRepository.findAll());
    }

    @Override
    public TrainerEntity create(TrainerEntity entity) {
        return trainerRepository.saveAndFlush(entity);
    }

    @Override
    public TrainerEntity update(TrainerEntity entity) {
        return trainerRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
        trainerRepository.deleteById(id);
    }

    public TrainerEntity login(UserDTO userDTO) {
        TrainerEntity trainerEntity = trainerRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(
                        () -> new LoginException(DescriptionUtils.getErrorDescription("WRONG_USERNAME_OR_PASSWORD"),
                                HttpStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(CharBuffer.wrap(userDTO.getPassword()), trainerEntity.getPassword())) {
            throw new LoginException(DescriptionUtils.getErrorDescription("WRONG_USERNAME_OR_PASSWORD"),
                    HttpStatus.UNAUTHORIZED);
        }

        if (!trainerEntity.isEmailVerified()) {
            throw new LoginException(DescriptionUtils.getErrorDescription("EMAIL_NOT_VERIFIED"), HttpStatus.FORBIDDEN);
        }

        return trainerEntity;
    }

    public TrainerEntity registerTrainer(TrainerEntity trainerEntity) {
        Optional<TrainerEntity> optionalTrainer = trainerRepository.findByUsername(trainerEntity.getUsername());

        if (optionalTrainer.isPresent())
            throw new RegisterException(DescriptionUtils.getErrorDescription("USERNAME_TAKEN"), HttpStatus.CONFLICT);

        trainerEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(trainerEntity.getPassword())));

        TrainerEntity savedTrainerEntity = trainerRepository.saveAndFlush(trainerEntity);

        String encodedID =
                new String(Base64.getUrlEncoder().encode(trainerEntity.getTrainerID().toString().getBytes()));
        savedTrainerEntity.setVerificationToken(encodedID);

        return savedTrainerEntity;
    }

    public UserDTO findByUsername(String username) {
        TrainerEntity trainerEntity = trainerRepository.findByUsername(username)
                .orElseThrow(() -> new LoginException("Unknown trainerEntity", HttpStatus.NOT_FOUND));
        return trainerAdapter.entityToDTO(trainerEntity);
    }

    public TrainerEntity changePassword(Long trainerID, String oldPassword, String newPassword) {
        TrainerEntity trainerEntity = trainerRepository.getByTrainerID(trainerID);

        if (passwordEncoder.matches(CharBuffer.wrap(oldPassword), trainerEntity.getPassword())) {
            trainerEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(newPassword)));
            return trainerRepository.saveAndFlush(trainerEntity);
        }

        throw new LoginException(DescriptionUtils.getErrorDescription("WRONG_PASSWORD"), HttpStatus.UNAUTHORIZED);
    }

    public boolean verifyTrainerAccount(String token) {
        String decodedID = new String(Base64.getUrlDecoder().decode(token));
        TrainerEntity trainerEntity = trainerRepository.getByTrainerID(Long.parseLong(decodedID));

        if (trainerEntity != null) {
            trainerEntity.setEmailVerified(true);
            trainerRepository.saveAndFlush(trainerEntity);
            return true;
        }

        return false;
    }
}
