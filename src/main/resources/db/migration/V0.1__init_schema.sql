CREATE TABLE book
(
    id    UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn  VARCHAR(13)  NOT NULL
);

CREATE TABLE copy
(
    id        UUID PRIMARY KEY,
    book_id   UUID         NOT NULL,
    bar_code  VARCHAR(255) NOT NULL,
    available BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE loan
(
    loan_id              UUID PRIMARY KEY,
    copy_id              UUID      NOT NULL,
    user_id              UUID      NOT NULL,
    created_at           TIMESTAMP NOT NULL,
    expected_return_date DATE      NOT NULL,
    returned_at          TIMESTAMP NULL,
    version              BIGINT    NOT NULL DEFAULT 0
);
