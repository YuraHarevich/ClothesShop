CREATE TABLE IF NOT EXISTS Clothes
(
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    size VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    available_quantity INTEGER NOT NULL
);
create sequence if not exists clothes_seq increment by 1;