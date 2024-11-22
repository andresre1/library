INSERT INTO book (id, title, isbn)
VALUES ('d9b1d7e3-54e1-48d8-9bb4-70af9d7f9f94', 'Effective Java', '9780134685991'),
       ('4efc9ba2-22f3-49e1-bff0-5a26bfdf27b7', 'Clean Code', '9780132350884'),
       ('9c9f8823-f2fc-4e7f-8a8a-7bf7d41d3c49', 'Design Patterns', '9780201633610');

INSERT INTO copy (id, book_id, bar_code, available)
VALUES ('e1a7f82c-cb4f-4a42-8a2f-50a76d7a0348', 'd9b1d7e3-54e1-48d8-9bb4-70af9d7f9f94', 'BC9780134685991-1', true),
       ('f2d6a3b7-e67a-44b7-8a6e-07bf2e7d3930', 'd9b1d7e3-54e1-48d8-9bb4-70af9d7f9f94', 'BC9780134685991-2', true),
       ('13f2e7b1-c123-4c97-b7a8-913fd678a950', '4efc9ba2-22f3-49e1-bff0-5a26bfdf27b7', 'BC9780132350884-1', true),
       ('5c9a8723-a7bd-4f7a-b9c7-913f423d8390', '4efc9ba2-22f3-49e1-bff0-5a26bfdf27b7', 'BC9780132350884-2', false),
       ('729f6823-b0d9-4a7e-b8a7-723fd423d390', '9c9f8823-f2fc-4e7f-8a8a-7bf7d41d3c49', 'BC9780201633610-1', false),
       ('9b7c8732-a1fd-4e3c-b3d9-673fd982d380', '9c9f8823-f2fc-4e7f-8a8a-7bf7d41d3c49', 'BC9780201633610-2', true);

INSERT INTO loan (loan_id, copy_id, user_id, created_at, expected_return_date, returned_at, version)
VALUES ('f3a7e812-3d9b-4f3a-a1b7-9bf5d7d8f9a1', 'e1a7f82c-cb4f-4a42-8a2f-50a76d7a0348',
        'a8c12345-f7d9-48a8-b7f9-98a76d3a047b', '2024-11-10T10:30:00', '2024-12-10', NULL, 1),
       ('b5c9d321-f3b7-4f2a-a7b8-1cf5d6d8a912', '5c9a8723-a7bd-4f7a-b9c7-913f423d8390',
        'b9a23456-c9d8-42a8-a7d9-87a56d3a048c', '2024-10-25T14:00:00', '2024-11-25', '2024-11-15T16:00:00', 2),
       ('d8b9c721-a7d9-4c2f-b3a8-7cf5d2d8f931', '729f6823-b0d9-4a7e-b8a7-723fd423d390',
        'dcfd2d62-1868-438f-9e45-c0996355ed1f', '2024-11-05T09:45:00', '2024-12-05', NULL, 1),
       ('e6a7f823-b1d7-4e2a-b7c8-1bf6d3d8f9a4', '9b7c8732-a1fd-4e3c-b3d9-673fd982d380',
        'cccf3110-4600-4785-ba15-adebfd7a6e82', '2024-11-01T11:15:00', '2024-11-30', '2024-11-20T12:00:00', 3);