CREATE OR REPLACE PROCEDURE create_package(
    p_name VARCHAR(255),
    p_duration INT,
    p_price NUMERIC,
    p_currency VARCHAR(32),
    OUT o_package package_dto
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO package (name, duration, price, currency)
        VALUES (p_name, p_duration, p_price, p_currency)
    RETURNING id, name, duration, price, currency
        INTO o_package.id, o_package.name, o_package.duration, o_package.price, o_package.currency;
END;
$$;

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE update_package(
    p_package_id BIGINT,
    p_name VARCHAR(255),
    p_duration INT,
    p_price NUMERIC,
    p_currency VARCHAR(32),
    OUT o_package package_dto
)
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM package WHERE id = p_package_id) THEN
        RAISE EXCEPTION 'Package not found with ID: %', p_package_id USING ERRCODE = 'P0002';
    END IF;

    UPDATE package
    SET name     = p_name,
        duration = p_duration,
        price    = p_price,
        currency = p_currency
    WHERE id = p_package_id
    RETURNING id, name, duration, price, currency
        INTO o_package.id, o_package.name, o_package.duration, o_package.price, o_package.currency;
END;
$$;

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE delete_package(
    IN p_package_id BIGINT,
    OUT o_package_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM package WHERE id = p_package_id) THEN
        RAISE EXCEPTION 'Package not found with ID: %', p_package_id USING ERRCODE = 'P0002';
    END IF;

    DELETE FROM package
    WHERE id = p_package_id
    RETURNING id INTO o_package_id;
END;
$$;

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION search_packages(
    p_page_size INT,
    p_offset BIGINT,
    p_name VARCHAR DEFAULT NULL,
    p_duration INT[] DEFAULT NULL,
    p_price NUMERIC[] DEFAULT NULL,
    p_currency VARCHAR DEFAULT NULL,
    p_sort_field VARCHAR DEFAULT 'id',
    p_sort_direction VARCHAR DEFAULT 'ASC'
)
RETURNS TABLE (
    package_id BIGINT,
    name VARCHAR(255),
    duration INT,
    price NUMERIC,
    currency VARCHAR(32)
)
LANGUAGE plpgsql
AS $$
DECLARE
v_query TEXT;
BEGIN
    v_query := 'SELECT p.id AS package_id,
                       p.name,
                       p.duration,
                       p.price,
                       p.currency
                FROM package p
                WHERE 1 = 1';

    IF p_name IS NOT NULL THEN
        v_query := v_query || ' AND p.name ILIKE ' || quote_literal('%' || p_name || '%');
    END IF;

    IF p_duration IS NOT NULL THEN
       IF array_length(p_duration, 1) = 1 THEN
           v_query := v_query || ' AND p.duration = ' || p_duration[1];
       ELSE
           v_query := v_query || ' AND p.duration BETWEEN ' || p_duration[1] || ' AND ' || p_duration[2];
       END IF;
    END IF;

    IF p_price IS NOT NULL THEN
        IF array_length(p_price, 1) = 1 THEN
            v_query := v_query || ' AND p.price = ' || p_price[1];
        ELSE
            v_query := v_query || ' AND p.price BETWEEN ' || p_price[1] || ' AND ' || p_price[2];
        END IF;
    END IF;

    IF p_currency IS NOT NULL THEN
        v_query := v_query || ' AND p.currency ILIKE ' || quote_literal(p_currency);
    END IF;

    v_query := v_query || ' ORDER BY ' || quote_ident(p_sort_field) || ' ' || p_sort_direction;
    v_query := v_query || ' LIMIT ' || p_page_size || ' OFFSET ' || p_offset;

    RETURN QUERY EXECUTE v_query;
END;
$$;
