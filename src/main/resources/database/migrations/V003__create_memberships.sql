CREATE TABLE memberships
(
    id         BIGINT,
    member_id  BIGINT,
    package_id BIGINT,
    startDate  DATE,
    endDate    DATE,

    CONSTRAINT pk_memberships PRIMARY KEY (id),
    CONSTRAINT fk_member_id FOREIGN KEY (member_id) REFERENCES members(id),
    CONSTRAINT fk_package_id FOREIGN KEY (package_id) REFERENCES packages(id)
)