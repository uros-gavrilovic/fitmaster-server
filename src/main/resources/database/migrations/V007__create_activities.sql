CREATE TABLE activities
(
    ordinalNumber BIGINT,
    plan_id       BIGINT,
    exercise_id   BIGINT,
    reps          INTEGER,
    sets          INTEGER,
    comment       VARCHAR(255),

    CONSTRAINT pk_activities PRIMARY KEY (ordinalNumber),
    CONSTRAINT fk_plan_id FOREIGN KEY (plan_id) REFERENCES plans(id),
    CONSTRAINT fk_exercise_id FOREIGN KEY (exercise_id) REFERENCES packages(id)
)