-- DELETE
-- FROM FILM_GENRE;
-- DELETE
-- FROM FILM_MPA;
-- DELETE
-- from REVIEW_LIKES;
-- DELETE
-- from REVIEWS;
-- DELETE
-- FROM FILM_DIRECTOR;
-- DELETE
-- FROM LIKES;
--
-- DELETE
-- FROM MPA;
-- DELETE
-- FROM GENRE;
-- DELETE
-- FROM DIRECTORS;
-- DELETE
-- FROM FRIENDS;
--
-- DELETE FROM EVENT_TYPE;
-- DELETE FROM OPERATION;
-- DELETE FROM FEED;
--
-- DELETE
-- FROM USERS;
--
-- DELETE
-- FROM FILMS;

INSERT INTO department(name)
VALUES ('Управление'),
       ('Отдел кадров'),
       ('Бухгалтерия'),
       ('Разработка');

INSERT INTO grade(grade)
VALUES ('Стажер'),
       ('Младший специалист'),
       ('Средний специалист'),
       ('Старший специалист'),
       ('Руководитель команды'),
       ('Руководитель отдела'),
       ('Руководитель направления'),
       ('Директор сферы'),
       ('Генеральный директор');

INSERT INTO position(post, department_id, grade_id)
VALUES ('Генеральный директор', '1', '9'),
       ('Руководитель ОК', '2', '4'),
       ('HR-специалист', '2', '3'),
       ('Старший бухгалтер', '3', '6'),
       ('Mладший бухгалтер', '3', '2'),
       ('Team Leed', '4', '5'),
       ('Middle developer', '4', '3');

INSERT INTO work_schedule(work_day, week_day, HOURS)
VALUES ('5', '2', '8'),
       ('2', '2', '12');

INSERT INTO salary(grade_id, wage, personal_kpi, team_kpi, common_kpi)
VALUES ('1', '18000', '0', '0', '0'),
       ('2', '22500', '0.7', '0.2', '0.1'),
       ('3', '45000', '0.6', '0.25', '0.15'),
       ('4', '60000', '0.5', '0.4', '0.10'),
       ('5', '81000', '0.4', '0.5', '0.10'),
       ('6', '109350', '0.4', '0.5', '0.10'),
       ('7', '147622', '0.4', '0.5', '0.10'),
       ('8', '221433', '0.4', '0.5', '0.10'),
       ('9', '332150', '0.4', '0.5', '0.10');