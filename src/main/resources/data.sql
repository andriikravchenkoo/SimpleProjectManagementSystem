CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO users (email, password, role)
VALUES ('john.doe@example.com', crypt('johndoe123', gen_salt('bf')), 'DEVELOPER'),
       ('jane.smith@example.com', crypt('janesmith456', gen_salt('bf')), 'DEVELOPER'),
       ('bob.jackson@example.com', crypt('password789', gen_salt('bf')), 'TESTER'),
       ('sarah.white@example.com', crypt('password123', gen_salt('bf')), 'PROJECT_MANAGER');

INSERT INTO projects (name, description)
VALUES ('Website Redesign', 'Redesigning the company website to improve user experience'),
       ('Mobile App Development', 'Developing a new mobile application for iOS and Android'),
       ('Database Migration', 'Migrating the database to a new platform for better performance');

INSERT INTO tasks (title, description, status, project_id, assigned_user_id)
VALUES ('Design homepage layout', 'Create wireframes and mockups for the new homepage design', 'TODO', 1, 1),
       ('Implement user authentication', 'Develop login and registration functionality', 'IN_PROGRESS', 1, 2),
       ('Test mobile app on iOS devices', 'Conduct testing on various iOS devices to identify bugs', 'DONE', 2, 3),
       ('Optimize database queries', 'Identify and optimize slow-performing queries', 'TODO', 3, 3),
       ('Update API documentation', 'Document changes made to the API for future reference', 'IN_PROGRESS', 3, 4);




