CREATE TABLE packages
(
    id    BIGINT,
    name  VARCHAR(255),
    price DECIMAL,

    CONSTRAINT pk_packages PRIMARY KEY (id)
)