BEGIN;
    SELECT plan(19);

    SELECT has_table('package', 'Table "package" should exist');

    SELECT has_column('package', 'id', 'Column "id" should exist');
    SELECT has_column('package', 'name', 'Column "name" should exist');
    SELECT has_column('package', 'duration', 'Column "duration" should exist');
    SELECT has_column('package', 'price', 'Column "price" should exist');
    SELECT has_column('package', 'currency', 'Column "currency" should exist');

    SELECT col_is_pk('package', 'id', 'Column "id" should be a primary key');

    SELECT col_not_null('package', 'id', 'Column "id" should not allow NULL values');
    SELECT col_not_null('package', 'name', 'Column "name" should not allow NULL values');
    SELECT col_not_null('package', 'price', 'Column "price" should not allow NULL values');
    SELECT col_not_null('package', 'currency', 'Column "currency" should not allow NULL values');

    SELECT has_unique('package', 'Table "package" should have a unique constraint(s)');
    SELECT * FROM col_is_unique('package', 'name');

    PREPARE valid_query AS
        INSERT INTO package (name, duration, price, currency)
        VALUES ('Fitness 30', 30, 3000, 'RSD');
    SELECT lives_ok('valid_query', 'Inserting a valid package should succeed');

    PREPARE invalid_query_required_field_name_is_null AS
        INSERT INTO package (name) VALUES (NULL);
    SELECT throws_ok('invalid_query_required_field_name_is_null',
                     '23502',
                     'null value in column "name" of relation "package" violates not-null constraint',
                     'Inserting a package with a required field "name" as NULL should fail');

    PREPARE invalid_query_field_name_is_not_unique AS
        INSERT INTO package (name) VALUES ('Fitness 30'),
                                          ('Fitness 30');
    SELECT throws_ok('invalid_query_field_name_is_not_unique',
                        '23505',
                        'duplicate key value violates unique constraint "package_name_key"',
                        'Inserting a package with a non-unique name should fail');

    PREPARE invalid_query_invalid_duration AS
        INSERT INTO package (name, duration) VALUES ('Fitness 30', -30);
    SELECT throws_ok('invalid_query_invalid_duration',
                     '23514',
                     'new row for relation "package" violates check constraint "package_duration_check"',
                     'Inserting a package with an invalid duration should fail');

    PREPARE invalid_query_negative_price AS
        INSERT INTO package (name, price) VALUES ('Fitness 30', -100);
    SELECT throws_ok('invalid_query_negative_price',
                     '23514',
                     'new row for relation "package" violates check constraint "package_price_check"',
                     'Inserting a package with an invalid price should fail');

    PREPARE invalid_query_currency_char_overflow AS
        INSERT INTO package (name, currency) VALUES ('Fitness 30', 'DINAR');
    SELECT throws_ok('invalid_query_currency_char_overflow',
                     '22001',
                     'value too long for type character varying(3)',
                     'Inserting a package with an invalid currency should fail');

    SELECT finish();
ROLLBACK;
