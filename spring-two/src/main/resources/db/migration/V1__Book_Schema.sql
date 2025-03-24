CREATE TABLE book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    year_of_publish INT,
    price DECIMAL(10, 2),
    classification VARCHAR(255),
    is_available BOOLEAN
);

CREATE INDEX idx_books_title ON book(title);
CREATE INDEX idx_books_author ON book(author);

ALTER TABLE book ALTER COLUMN is_available SET DEFAULT FALSE;

INSERT INTO book (title, author, year_of_publish, price, classification, is_available) VALUES
('Shadow Hunters City Of Bones', 'Cassandra Clare', 2007, 15.99, 'Fiction', true),
('Shadow Hunters City Of Glass', 'Cassandra Clare', 2009, 16.99, 'Fiction', true),
('Prodigy', 'Marie Lu', 2013, 22.99, 'Fiction', true),
('Legend', 'Marie Lu', 2011, 22.99, 'Fiction', true),
('Champion', 'Marie Lu', 2013, 22.99, 'Fiction', true),
('The Boys Of Riverside', 'Thomas Fuller', 2024, 11.25, 'NonFiction', true),
('King: A Life', 'Jonathan Eig', 2023, 11.25, 'Biography', false);