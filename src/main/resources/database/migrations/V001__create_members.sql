CREATE TABLE members
(
    id          BIGINT,
    firstName   VARCHAR(255),
    lastName    VARCHAR(255),
    gender      VARCHAR(32),
    address     VARCHAR(255),
    phoneNumber VARCHAR(255),
    birthDate   DATE,

    CONSTRAINT pk_members PRIMARY KEY (id)
)