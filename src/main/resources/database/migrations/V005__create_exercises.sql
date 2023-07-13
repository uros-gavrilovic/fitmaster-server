CREATE TABLE exercises
(
    id           BIGINT,
    name         VARCHAR(255),
    instructions VARCHAR(255),

    CONSTRAINT pk_exercises PRIMARY KEY (id)
)