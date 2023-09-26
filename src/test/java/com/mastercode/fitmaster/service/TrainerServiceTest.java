package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.TrainerAdapter;
import com.mastercode.fitmaster.exception.RegisterException;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TrainerServiceTest {

    @InjectMocks
    private TrainerService trainerService;

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private TrainerAdapter trainerAdapter;

    @Mock
    private PasswordEncoder passwordEncoder;

    private Trainer trainer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        trainer = new Trainer();
        trainer.setTrainerID(1L);
        trainer.setUsername("testUsername");
        trainer.setPassword("testPassword");
        trainer.setFirstName("testFirstname");
        trainer.setLastName("testLastname");
    }

    @Test
    public void testRegisterTrainer() {
        Trainer createdTrainer = new Trainer();
        createdTrainer.setUsername("testUsername");
        createdTrainer.setPassword("testEncodedPassword");

        when(trainerRepository.findByUsername(trainer.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(trainer.getPassword())).thenReturn("testEncodedPassword");
        when(trainerRepository.save(any(Trainer.class))).thenReturn(createdTrainer);

        Trainer result = trainerService.registerTrainer(trainer);

        assertEquals(createdTrainer, result);
        verify(trainerRepository, times(1)).findByUsername(trainer.getUsername());
        verify(trainerRepository, times(1)).save(any(Trainer.class));
    }


    @Test
    public void testRegisterTrainer_DuplicateUsername() {
        Trainer trainer = new Trainer(); // Trainer object received as a request body
        trainer.setUsername("TEST_USERNAME");
        trainer.setPassword("TEST_PASSWORD");

        Trainer existingTrainer = new Trainer();
        existingTrainer.setUsername("TEST_USERNAME");

        when(trainerRepository.findByUsername(trainer.getUsername())).thenReturn(Optional.of(existingTrainer));

        RegisterException exception = assertThrows(RegisterException.class, () -> {
            trainerService.registerTrainer(trainer);
        });

        assertEquals("Registration Error", exception.getTitle());
        assertEquals("Username is already taken", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
    }

    @Test
    public void testUpdateTrainer() {
        Trainer trainerWithChanges = new Trainer();
        trainerWithChanges.setTrainerID(1L);
        trainerWithChanges.setUsername("TEST_NEW_USERNAME");

        when(trainerRepository.saveAndFlush(trainerWithChanges)).thenReturn(trainerWithChanges);

        Trainer updatedTrainer = trainerService.update(trainerWithChanges);

        assertEquals(trainerWithChanges, updatedTrainer);
        verify(trainerRepository, times(1)).saveAndFlush(trainerWithChanges);
    }

    @Test
    public void testDeleteTrainer() {
        Trainer trainerToDelete = new Trainer();
        trainerToDelete.setTrainerID(1L);

        doNothing().when(trainerRepository).deleteById(1L);

        trainerService.delete(1L);

        verify(trainerRepository, times(1)).deleteById(1L);
    }
}
