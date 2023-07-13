package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.ActivityAdapter;
import com.mastercode.fitmaster.dto.ActivityDTO;
import com.mastercode.fitmaster.model.Activity;
import com.mastercode.fitmaster.repository.ActivityRepository;
import com.mastercode.fitmaster.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService implements AbstractService<Activity, ActivityDTO> {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityAdapter activityAdapter;

    @Autowired
    private MemberRepository memberRepository;

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
        return activityRepository.saveAndFlush(entity);
    }

    @Override
    public Activity update(Activity entity) {
        return null; // TODO
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
