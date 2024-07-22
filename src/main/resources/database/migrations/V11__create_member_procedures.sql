CREATE OR REPLACE PROCEDURE create_member(
    p_first_name VARCHAR(255),
    p_last_name VARCHAR(255),
    p_gender VARCHAR(32),
    p_address VARCHAR(255),
    p_phone_number VARCHAR(255),
    p_birth_date DATE,
    p_email VARCHAR(255),
    p_username VARCHAR(255),
    p_password VARCHAR(255),
    OUT p_member_id BIGINT
) AS
$$
BEGIN
    INSERT INTO member (first_name, last_name, gender, address, phone_number, birth_date, email, email_verified,
                        username, password, is_banned)
    VALUES (p_first_name, p_last_name, p_gender, p_address, p_phone_number, p_birth_date, p_email, false, p_username,
            p_password, false)
    RETURNING id INTO p_member_id;
END;
$$ LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION get_members()
    RETURNS TABLE
            (
                member_id    BIGINT,
                first_name   VARCHAR(255),
                last_name    VARCHAR(255),
                gender       VARCHAR(32),
                address      VARCHAR(255),
                phone_number VARCHAR(255),
                birth_date   DATE,
                status       VARCHAR(32)
            )
    LANGUAGE plpgsql
AS
$$
BEGIN
    RETURN QUERY
        SELECT m.id    AS member_id,
               m.first_name,
               m.last_name,
               m.gender,
               m.address,
               m.phone_number,
               m.birth_date,
               CASE
                   WHEN EXISTS (SELECT 1
                                FROM membership ms
                                WHERE ms.member_id = m.id
                                  AND ms.start_date <= CURRENT_DATE
                                  AND ms.end_date >= CURRENT_DATE) THEN 'ACTIVE'::VARCHAR(32)
                   WHEN NOT EXISTS (SELECT 1
                                    FROM membership ms
                                    WHERE ms.member_id = m.id) THEN 'PENDING'::VARCHAR(32)
                   WHEN m.is_banned = TRUE THEN 'BANNED'::VARCHAR(32)
                   ELSE 'INACTIVE'::VARCHAR(32)
                   END AS status
        FROM member m;
END;
$$;

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION search_members(
    IN p_page_size INT,
    IN p_offset INT,
    IN p_first_name VARCHAR DEFAULT NULL,
    IN p_last_name VARCHAR DEFAULT NULL,
    IN p_gender VARCHAR DEFAULT NULL,
    IN p_status VARCHAR DEFAULT NULL
)
    RETURNS TABLE
            (
                member_id    BIGINT,
                first_name   VARCHAR(255),
                last_name    VARCHAR(255),
                gender       VARCHAR(32),
                address      VARCHAR(255),
                phone_number VARCHAR(255),
                birth_date   DATE,
                status       VARCHAR(32)
            )
    LANGUAGE plpgsql
AS
$$
DECLARE
    v_query TEXT;
BEGIN
    v_query := '
        SELECT m.id AS member_id,
               m.first_name,
               m.last_name,
               m.gender,
               m.address,
               m.phone_number,
               m.birth_date,
               CASE
                   WHEN EXISTS (
                       SELECT 1
                       FROM membership ms
                       WHERE ms.member_id = m.id
                         AND ms.start_date <= CURRENT_DATE
                         AND ms.end_date >= CURRENT_DATE
                   ) THEN ''ACTIVE''::VARCHAR(32)
                   WHEN NOT EXISTS (
                       SELECT 1
                       FROM membership ms
                       WHERE ms.member_id = m.id
                   ) THEN ''PENDING''::VARCHAR(32)
                   WHEN m.is_banned = TRUE THEN ''BANNED''::VARCHAR(32)
                   ELSE ''INACTIVE''::VARCHAR(32)
               END AS status
        FROM member m
        WHERE 1 = 1';


    IF p_first_name IS NOT NULL THEN
        v_query := v_query || ' AND m.first_name ILIKE ' || quote_literal('%' || p_first_name || '%');
    END IF;

    IF p_last_name IS NOT NULL THEN
        v_query := v_query || ' AND m.last_name ILIKE ' || quote_literal('%' || p_last_name || '%');
    END IF;

    IF p_gender IS NOT NULL THEN
        v_query := v_query || ' AND m.gender = ' || quote_literal(p_gender);
    END IF;

    IF p_status IS NOT NULL THEN
        v_query := v_query || ' AND (
            CASE
                WHEN EXISTS (
                    SELECT 1
                    FROM membership ms
                    WHERE ms.member_id = m.id
                      AND ms.start_date <= CURRENT_DATE
                      AND ms.end_date >= CURRENT_DATE
                ) THEN ''ACTIVE''::VARCHAR(32)
                WHEN NOT EXISTS (
                    SELECT 1
                    FROM membership ms
                    WHERE ms.member_id = m.id
                ) THEN ''PENDING''::VARCHAR(32)
                WHEN m.is_banned = TRUE THEN ''BANNED''::VARCHAR(32)
                ELSE ''INACTIVE''::VARCHAR(32)
            END = ' || quote_literal(p_status) || ')';
    END IF;

    v_query := v_query || '
        ORDER BY m.id
        LIMIT ' || p_page_size || '
        OFFSET ' || p_offset;

    RETURN QUERY EXECUTE v_query;
END;
$$;

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_member(
    p_member_id BIGINT,
    p_first_name VARCHAR,
    p_last_name VARCHAR,
    p_gender VARCHAR,
    p_address VARCHAR,
    p_phone_number VARCHAR,
    p_birth_date DATE,
    p_email VARCHAR,
    p_username VARCHAR,
    OUT o_member_id BIGINT
)
    LANGUAGE plpgsql
AS
$$
BEGIN
    UPDATE member
    SET first_name   = p_first_name,
        last_name    = p_last_name,
        gender       = p_gender,
        address      = p_address,
        phone_number = p_phone_number,
        birth_date   = p_birth_date,
        email        = p_email,
        username     = p_username
    WHERE id = p_member_id;
END;
$$;

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE delete_member(
    p_member_id BIGINT
)
    LANGUAGE plpgsql
AS
$$
BEGIN
    DELETE FROM member WHERE id = p_member_id;
END;
$$;
