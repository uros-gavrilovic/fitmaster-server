package com.mastercode.fitmaster.dataloader;

import com.mastercode.fitmaster.repository.MemberRepository;
import com.mastercode.fitmaster.repository.MembershipRepository;
import com.mastercode.fitmaster.repository.PackageRepository;
import com.mastercode.fitmaster.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DataLoader {
    @Autowired
    protected PackageRepository packageRepository;
    @Autowired
    protected MembershipRepository membershipRepository;
    @Autowired
    protected MemberRepository memberRepository;
    @Autowired
    protected TrainerRepository trainerRepository;

    abstract void loadTestData();
}
