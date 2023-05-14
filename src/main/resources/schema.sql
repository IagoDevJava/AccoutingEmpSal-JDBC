CREATE TABLE IF NOT EXISTS DEPARTMENT
(
    ID      int4 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME    varchar NOT NULL UNIQUE,
    HEAD_ID int4,
    PHONE   varchar,
    EMAIL   varchar not null,
    ADDRESS varchar
);

CREATE TABLE IF NOT EXISTS GRADE
(
    ID   int4 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS POSITION
(
    ID            int4 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    POST          varchar NOT NULL,
    DEPARTMENT_ID int4    NOT NULL REFERENCES DEPARTMENT (ID),
    GRADE_ID      int4    NOT NULL REFERENCES GRADE (ID)
);

CREATE TABLE IF NOT EXISTS SALARIES_DATA
(
    ID           int4 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    GRADE_ID     int4 NOT NULL REFERENCES grade (ID),
    WAGE         int4 NOT NULL,
    BONUS        int4 NOT NULL,
    PERSONAL_KPI float4,
    TEAM_KPI     float4,
    COMMON_KPI   float4
);

CREATE TABLE IF NOT EXISTS work_schedules
(
    id        int4 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    work_days int4 NOT NULL,
    week_days int4 NOT NULL,
    hours     int4 NOT NULL
);

CREATE TABLE IF NOT EXISTS EMPLOYEES
(
--     //Сотрудник: 1, Иванов, Иван, Иванович, мужчина, бухгалтер, бухгалтерия,
--     зп30000, гр5/2, дп2022-11-28, null, null
    ID                int4 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    SURNAME           varchar   NOT NULL,
    FIRSTNAME         varchar   NOT NULL,
    LASTNAME          varchar,
    GENDER            varchar   NOT NULL,
    DEPARTMENT_ID     int4 REFERENCES DEPARTMENT (ID) ON DELETE CASCADE,
    PHONE             varchar,
    EMAIL             varchar   NOT NULL,
    POSITION_ID       int4 REFERENCES POSITION (ID) ON DELETE CASCADE,
    WORK_SCHEDULE_ID  int4 REFERENCES work_schedules (ID) ON DELETE CASCADE,
    DATE_OF_ADMISSION timestamp NOT NULL,
    JOB_STATUS        varchar,
    DATE_OF_DISMISSAL timestamp
);

ALTER TABLE DEPARTMENT
    ADD CONSTRAINT fk_depHead_to_Employee FOREIGN KEY (HEAD_ID) REFERENCES EMPLOYEES (ID);

CREATE TABLE IF NOT EXISTS ATTENDANCE_DATA
(
    ID          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    DATE_ATT    date    NOT NULL,
    EMPLOYEE_ID int4    NOT NULL REFERENCES employees (id),
    STATUS      varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS SALARY
(
    ID            BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    DEPARTMENT_ID int4    NOT NULL REFERENCES DEPARTMENT (ID),
    EMPLOYEE_ID   int4    NOT NULL REFERENCES employees (id),
    MONTH         varchar NOT NULL,
    YEAR          varchar NOT NULL,
    SALARY        float4  NOT NULL
);

-- INSERT INTO department(name)
-- VALUES ('Управление'),
--        ('Отдел кадров'),
--        ('Бухгалтерия'),
--        ('Разработка');
--
-- INSERT INTO grade(grade)
-- VALUES ('Стажер'),
--        ('Младший специалист'),
--        ('Средний специалист'),
--        ('Старший специалист'),
--        ('Руководитель команды'),
--        ('Руководитель отдела'),
--        ('Руководитель направления'),
--        ('Директор сферы'),
--        ('Генеральный директор');
--
-- INSERT INTO position(post, department_id, grade_id)
-- VALUES ('Генеральный директор', '1', '9'),
--        ('Руководитель ОК', '2', '4'),
--        ('HR-специалист', '2', '3'),
--        ('Старший бухгалтер', '3', '6'),
--        ('Mладший бухгалтер', '3', '2'),
--        ('Team Lead', '4', '5'),
--        ('Middle developer', '4', '3');
--
-- INSERT INTO work_schedule(work_day, week_day, HOURS)
-- VALUES ('5', '2', '8'),
--        ('2', '2', '12');
--
-- INSERT INTO salaries_data(grade_id, wage, personal_kpi, team_kpi, common_kpi)
-- VALUES ('1', '18000', '0', '0', '0'),
--        ('2', '22500', '0.7', '0.2', '0.1'),
--        ('3', '45000', '0.6', '0.25', '0.15'),
--        ('4', '60000', '0.5', '0.4', '0.10'),
--        ('5', '81000', '0.4', '0.5', '0.10'),
--        ('6', '109350', '0.4', '0.5', '0.10'),
--        ('7', '147622', '0.4', '0.5', '0.10'),
--        ('8', '221433', '0.4', '0.5', '0.10'),
--        ('9', '332150', '0.4', '0.5', '0.10');