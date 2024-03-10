package com.mastercode.fitmaster.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mastercode.fitmaster.adapter.ExerciseAdapter;
import com.mastercode.fitmaster.dto.ExerciseDTO;
import com.mastercode.fitmaster.model.ExerciseEntity;
import com.mastercode.fitmaster.model.enums.BodyPart;
import com.mastercode.fitmaster.model.enums.Category;
import com.mastercode.fitmaster.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService implements AbstractService<ExerciseEntity, ExerciseDTO> {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseAdapter exerciseAdapter;

    @Override
    public List<ExerciseEntity> getAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public ExerciseEntity findByID(Long id) {
        return exerciseRepository.getByExerciseID(id);
    }

    @Override
    public List<ExerciseDTO> getAllDTOs() {
        return exerciseAdapter.entitiesToDTOs(exerciseRepository.findAll());
    }

    @Override
    public ExerciseEntity create(ExerciseEntity entity) {
        return exerciseRepository.saveAndFlush(entity);
    }

    @Override
    public ExerciseEntity update(ExerciseEntity entity) {
        return exerciseRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
        exerciseRepository.deleteById(id);
    }

    public ObjectNode getAllFilters() {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode bodyParts = objectMapper.createArrayNode();
        for (BodyPart bodyPart : BodyPart.values()) {
            bodyParts.add(bodyPart.toString());
        }
        ArrayNode categories = objectMapper.createArrayNode();
        for (Category category : Category.values()) {
            categories.add(category.toString());
        }

        // Create JSON object
        ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.putArray("bodyParts").addAll(bodyParts);
        jsonObject.putArray("categories").addAll(categories);

        return jsonObject;
    }
}