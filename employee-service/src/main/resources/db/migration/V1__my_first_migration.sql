CREATE TABLE IF NOT EXISTS employee (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(20),
    email varchar(50)
);

INSERT INTO employee(name, email)
SELECT
    'F' || LPAD(i::text, 6, '0'),
    'F' || LPAD(i::text, 6, '0') || '@bnb.gov.br'
FROM generate_series(1, 99) s(i);
