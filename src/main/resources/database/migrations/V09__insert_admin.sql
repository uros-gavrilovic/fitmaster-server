DELETE FROM trainer WHERE id = 0;

INSERT INTO trainer(id, first_name, last_name, username, email, email_verified, password)
VALUES (0, 'Admin', 'Admin', 'admin', 'admin@fitmaster.com', true,
        '$2a$10$DckRmrkx7D6nwVpB6juCB..ekyqwfqRMHdN0Kx.11UWbGndMacrUa');
