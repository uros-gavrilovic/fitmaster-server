SELECT plan(7);

SELECT has_table('package', 'Table "package" should exist');

SELECT has_column('package', 'id', 'Column "id" should exist');
SELECT has_column('package', 'name', 'Column "name" should exist');
SELECT has_column('package', 'duration', 'Column "duration" should exist');
SELECT has_column('package', 'price', 'Column "price" should exist');
SELECT has_column('package', 'currency', 'Column "currency" should exist');

SELECT col_is_pk('package', 'id', 'Column "id" should be a primary key');

SELECT finish();
