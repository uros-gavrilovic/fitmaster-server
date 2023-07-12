package com.mastercode.fitmaster.model;

import com.mastercode.fitmaster.trainer.model.entity.TrainerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long planID;

    @OneToOne(optional = false)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private TrainerEntity trainerEntity;

    private LocalDateTime dateTime;

    private String comment;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private HashSet<Activity> activities;
}
