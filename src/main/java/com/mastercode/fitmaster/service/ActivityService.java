package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.ActivityAdapter;
import com.mastercode.fitmaster.dto.ActivityDTO;
import com.mastercode.fitmaster.dto.activity.ActivityFilter;
import com.mastercode.fitmaster.dto.activity.ActivitySearchItem;
import com.mastercode.fitmaster.dto.activity.ActivitySingleView;
import com.mastercode.fitmaster.model.ActivityEntity;
import com.mastercode.fitmaster.repository.ActivityRepository;
import com.mastercode.fitmaster.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService implements AbstractService<ActivityEntity,
                                                        ActivityDTO,
                                                        ActivitySearchItem,
                                                        ActivityFilter,
                                                        ActivitySingleView> {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityAdapter activityAdapter;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<ActivityEntity> getAll() {
        return activityRepository.findAll();
    }

    @Override
    public ActivityEntity findByID(Long id) {
        return activityRepository.getByActivityID(id);
    }

    @Override
    public List<ActivityDTO> getAllDTOs() {
        return activityAdapter.entitiesToDTOs(activityRepository.findAll());
    }

    @Override
    public ActivityDTO create(ActivityEntity entity) {
        return activityAdapter.entityToDTO(activityRepository.saveAndFlush(entity));
    }

    @Override
    public ActivityDTO update(ActivityEntity entity) {
        return null; // TODO
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
