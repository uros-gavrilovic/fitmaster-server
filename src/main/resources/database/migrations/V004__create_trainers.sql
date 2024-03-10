CREATE TABLE trainer
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name     VARCHAR(255),
    last_name      VARCHAR(255),
    gender         VARCHAR(32),
    phone_number   VARCHAR(255),
    address        VARCHAR(255),
    hire_date      DATE,
    email          VARCHAR(255),
    email_verified BOOLEAN,
    avatar         BYTEA,
    username       VARCHAR(255),
    password       VARCHAR(255),

    CONSTRAINT pk_trainer PRIMARY KEY (id)
)