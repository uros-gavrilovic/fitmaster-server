package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.TrainerAdapter;
import com.mastercode.fitmaster.exception.RegisterException;
import com.mastercode.fitmaster.model.TrainerEntity;
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
public class TrainerEntityServiceTest {

    @InjectMocks
    private TrainerService trainerService;

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private TrainerAdapter trainerAdapter;

    @Mock
    private PasswordEncoder passwordEncoder;

    private TrainerEntity trainerEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        trainerEntity = new TrainerEntity();
        trainerEntity.setTrainerID(1L);
        trainerEntity.setUsername("testUsername");
        trainerEntity.setPassword("testPassword");
        trainerEntity.setFirstName("testFirstname");
        trainerEntity.setLastName("testLastname");
    }

    @Test
    public void testRegisterTrainer() {
        TrainerEntity createdTrainerEntity = new TrainerEntity();
        createdTrainerEntity.setUsername("testUsername");
        createdTrainerEntity.setPassword("testEncodedPassword");

        when(trainerRepository.findByUsername(trainerEntity.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(trainerEntity.getPassword())).thenReturn("testEncodedPassword");
        when(trainerRepository.save(any(TrainerEntity.class))).thenReturn(createdTrainerEntity);

        TrainerEntity result = trainerService.registerTrainer(trainerEntity);

        assertEquals(createdTrainerEntity, result);
        verify(trainerRepository, times(1)).findByUsername(trainerEntity.getUsername());
        verify(trainerRepository, times(1)).save(any(TrainerEntity.class));
    }


    @Test
    public void testRegisterTrainer_DuplicateUsername() {
        TrainerEntity trainerEntity = new TrainerEntity(); // TrainerEntity object received as a request body
        trainerEntity.setUsername("TEST_USERNAME");
        trainerEntity.setPassword("TEST_PASSWORD");

        TrainerEntity existingTrainerEntity = new TrainerEntity();
        existingTrainerEntity.setUsername("TEST_USERNAME");

        when(trainerRepository.findByUsername(trainerEntity.getUsername())).thenReturn(
                Optional.of(existingTrainerEntity));

        RegisterException exception = assertThrows(RegisterException.class, () -> {
            trainerService.registerTrainer(trainerEntity);
        });

        assertEquals("Registration Error", exception.getTitle());
        assertEquals("Username is already taken", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
    }

    @Test
    public void testUpdateTrainer() {
        TrainerEntity trainerEntityWithChanges = new TrainerEntity();
        trainerEntityWithChanges.setTrainerID(1L);
        trainerEntityWithChanges.setUsername("TEST_NEW_USERNAME");

        when(trainerRepository.saveAndFlush(trainerEntityWithChanges)).thenReturn(trainerEntityWithChanges);

        TrainerEntity updatedTrainerEntity = trainerService.update(trainerEntityWithChanges);

        assertEquals(trainerEntityWithChanges, updatedTrainerEntity);
        verify(trainerRepository, times(1)).saveAndFlush(trainerEntityWithChanges);
    }

    @Test
    public void testDeleteTrainer() {
        TrainerEntity trainerEntityToDelete = new TrainerEntity();
        trainerEntityToDelete.setTrainerID(1L);

        doNothing().when(trainerRepository).deleteById(1L);

        trainerService.delete(1L);

        verify(trainerRepository, times(1)).deleteById(1L);
    }
}
