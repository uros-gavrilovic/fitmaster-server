BEGIN;
    SELECT plan(26);

    SELECT has_table('member', 'Table "member" should exist');

    SELECT has_column('member', 'id', 'Column "id" should exist');
    SELECT has_column('member', 'first_name', 'Column "first_name" should exist');
    SELECT has_column('member', 'last_name', 'Column "last_name" should exist');
    SELECT has_column('member', 'gender', 'Column "gender" should exist');
    SELECT has_column('member', 'address', 'Column "address" should exist');
    SELECT has_column('member', 'phone_number', 'Column "phone_number" should exist');
    SELECT has_column('member', 'birth_date', 'Column "birth_date" should exist');
    SELECT has_column('member', 'is_banned', 'Column "is_banned" should exist');
    SELECT has_column('member', 'email', 'Column "email" should exist');
    SELECT has_column('member', 'email_verified', 'Column "email_verified" should exist');
    SELECT has_column('member', 'avatar', 'Column "avatar" should exist');
    SELECT has_column('member', 'username', 'Column "username" should exist');
    SELECT has_column('member', 'password', 'Column "password" should exist');

    SELECT col_is_pk('member', 'id', 'Column "id" should be a primary key');

    SELECT col_not_null('member', 'id', 'Column "id" should not allow NULL values');
    SELECT col_not_null('member', 'is_banned', 'Column "is_banned" should not allow NULL values');
    SELECT col_not_null('member', 'email_verified', 'Column "email_verified" should not allow NULL values');

    SELECT has_unique('member', 'Table "member" should have a unique constraint(s)');
    SELECT * FROM col_is_unique('member', 'email');
    SELECT * FROM col_is_unique('member', 'username');

    PREPARE valid_query AS
        INSERT INTO member (first_name, username, password)
        VALUES ('John', 'johnd', '$2a$10$ZS2qaf7sHNokF2pHb5xGNedQ6NrRhM6GfkB1LFXE7vgkSl4ofL38G');
    SELECT lives_ok('valid_query', 'Inserting a valid member should succeed');


    PREPARE invalid_query_password_regex AS
        INSERT INTO member (first_name, username, password)
        VALUES ('James', 'jamesb', 'invalid-password');
    SELECT throws_ok('invalid_query_password_regex',
                     '23514',
                     'new row for relation "member" violates check constraint "password_check"',
                     'Inserting a member with an invalid password should fail');

    PREPARE invalid_query_required_field_null AS
        INSERT INTO member (first_name, username, password)
        VALUES ('John', NULL, '$2a$10$ZS2qaf7sHNokF2pHb5xGNedQ6NrRhM6GfkB1LFXE7vgkSl4ofL38G');
    SELECT throws_ok('invalid_query_required_field_null',
                     '23502',
                     'null value in column "username" of relation "member" violates not-null constraint',
                     'Inserting a member with a required field as NULL should fail');

    PREPARE invalid_query_email_unique AS
        INSERT INTO member (first_name, username, email, password)
        VALUES ('Jim', 'jimb', 'jim.beam@gmail.com', '$2a$10$ZS2qaf7sHNokF2pHb5xGNedQ6NrRhM6GfkB1LFXE7vgkSl4ofL38G'),
               ('Jack', 'jackd', 'jim.beam@gmail.com', '$2a$10$ZS2qaf7sHNokF2pHb5xGNedQ6NrRhM6GfkB1LFXE7vgkSl4ofL38G');
    SELECT throws_ok('invalid_query_email_unique',
                     '23505',
                     'duplicate key value violates unique constraint "member_email_key"',
                     'Inserting a member with a non-unique email should fail');

    PREPARE invalid_query_username_unique AS
        INSERT INTO member (first_name, username, password)
        VALUES ('Jim', 'jimb', '$2a$10$ZS2qaf7sHNokF2pHb5xGNedQ6NrRhM6GfkB1LFXE7vgkSl4ofL38G'),
               ('Jack', 'jimb', '$2a$10$ZS2qaf7sHNokF2pHb5xGNedQ6NrRhM6GfkB1LFXE7vgkSl4ofL38G');
    SELECT throws_ok('invalid_query_username_unique',
                     '23505',
                     'duplicate key value violates unique constraint "member_username_key"',
                     'Inserting a member with a non-unique username should fail');
ROLLBACK;

