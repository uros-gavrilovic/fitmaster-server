SELECT plan(12);

SELECT has_table('membership', 'Table "membership" should exist');

SELECT has_column('membership', 'id', 'Column "id" should exist');
SELECT has_column('membership', 'member_id', 'Column "member_id" should exist');
SELECT has_column('membership', 'package_id', 'Column "package_id" should exist');
SELECT has_column('membership', 'start_date', 'Column "start_date" should exist');
SELECT has_column('membership', 'end_date', 'Column "end_date" should exist');

SELECT col_is_pk('membership', 'id', 'Column "id" should be a primary key');
SELECT col_is_fk('membership', 'member_id', 'Column "member_id" should be a foreign key');
SELECT col_is_fk('membership', 'package_id', 'Column "package_id" should be a foreign key');

SELECT col_not_null('membership', 'id', 'Column "id" should not allow NULL values');
SELECT col_not_null('membership', 'member_id', 'Column "member_id" should not allow NULL values');
SELECT col_not_null('membership', 'package_id', 'Column "package_id" should not allow NULL values');

SELECT finish();
