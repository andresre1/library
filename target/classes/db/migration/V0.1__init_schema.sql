CREATE TABLE book
(
    id    UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn  VARCHAR(13)  NOT NULL
);