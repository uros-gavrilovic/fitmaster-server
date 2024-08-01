SELECT plan(13);

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

SELECT finish();
