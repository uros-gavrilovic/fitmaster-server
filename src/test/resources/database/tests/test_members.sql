SELECT plan(19);

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

SELECT has_unique('member', 'Table "member" should have a unique constraint(s)');
SELECT * FROM col_is_unique('member', 'email');
SELECT * FROM col_is_unique('member', 'username');

SELECT finish();
