CREATE TABLE exercise
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name         VARCHAR(255),
    category     VARCHAR(32),
    body_part    VARCHAR(32),
    instructions VARCHAR(512),

    CONSTRAINT pk_exercise PRIMARY KEY (id)
)