package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.ActivityAdapter;
import com.mastercode.fitmaster.dto.ActivityDTO;
import com.mastercode.fitmaster.model.Activity;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService implements AbstractService<Activity, ActivityDTO> {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityAdapter activityAdapter;

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    @Override
    public Activity findByID(Long id) {
        return activityRepository.getByOrdinalNumber(Math.toIntExact(id));
    }

    @Override
    public List<ActivityDTO> getAllDTOs() {
        return activityAdapter.entitiesToDTOs(activityRepository.findAll());
    }

    @Override
    public Activity create(Activity entity) {
        return null;
    }

    @Override
    public Activity update(Activity entity) {
        return null; // TODO
    }

    @Override
    public void delete(Long id) {
        Optional<Activity> optionalActivity = activityRepository.findById(Math.toIntExact(id));
        if (optionalActivity.isPresent()) activityRepository.delete(optionalActivity.get());
    }
}
