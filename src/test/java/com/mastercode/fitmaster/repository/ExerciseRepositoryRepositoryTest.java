package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.ExerciseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mastercode.fitmaster.data.ExerciseData.EXERCISE_ENTITY;
import static com.mastercode.fitmaster.data.ExerciseData.UPDATED_EXERCISE_ENTITY;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ExerciseRepositoryRepositoryTest implements CRUDRepositoryTest {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    @Test
    public void testSave() {
        ExerciseEntity savedExercise = exerciseRepository.save(EXERCISE_ENTITY);

        assertEquals(EXERCISE_ENTITY.getName(), savedExercise.getName());
        assertEquals(EXERCISE_ENTITY.getInstructions(), savedExercise.getInstructions());
        assertEquals(EXERCISE_ENTITY.getBodyPart(), savedExercise.getBodyPart());
        assertEquals(EXERCISE_ENTITY.getCategory(), savedExercise.getCategory());
    }

    @Override
    @Test
    public void testUpdate() {
        Long savedExerciseID = exerciseRepository.save(EXERCISE_ENTITY).getExerciseID();
        ExerciseEntity updatedExercise = exerciseRepository.save(
                UPDATED_EXERCISE_ENTITY.toBuilder().exerciseID(savedExerciseID).build()
        );

        assertEquals(UPDATED_EXERCISE_ENTITY.getName(), updatedExercise.getName());
        assertEquals(UPDATED_EXERCISE_ENTITY.getInstructions(), updatedExercise.getInstructions());
        assertEquals(UPDATED_EXERCISE_ENTITY.getBodyPart(), updatedExercise.getBodyPart());
        assertEquals(UPDATED_EXERCISE_ENTITY.getCategory(), updatedExercise.getCategory());
    }

    @Override
    @Test
    public void testFind() {
        Long savedExerciseID = exerciseRepository.save(EXERCISE_ENTITY).getExerciseID();
        ExerciseEntity foundExercise = exerciseRepository.findById(savedExerciseID).get();

        assertNotNull(foundExercise);

        assertEquals(EXERCISE_ENTITY.getName(), foundExercise.getName());
        assertEquals(EXERCISE_ENTITY.getInstructions(), foundExercise.getInstructions());
        assertEquals(EXERCISE_ENTITY.getBodyPart(), foundExercise.getBodyPart());
        assertEquals(EXERCISE_ENTITY.getCategory(), foundExercise.getCategory());
    }

    @Override
    @Test
    public void testDelete() {
        Long savedExerciseID = exerciseRepository.save(EXERCISE_ENTITY).getExerciseID();
        exerciseRepository.deleteById(savedExerciseID);

        ExerciseEntity foundExercise = exerciseRepository.findById(savedExerciseID).orElse(null);

        assertEquals(null, foundExercise);
    }
}