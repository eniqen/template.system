CREATE TABLE IF NOT EXISTS TEMPLATE (
  ID    INT AUTO_INCREMENT,
  NAME  VARCHAR(255),
  STATE VARCHAR(36),
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS DOCUMENT (
  ID          INT AUTO_INCREMENT,
  NAME        VARCHAR(255),
  STATE       VARCHAR(36),
  TEMPLATE_ID INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (TEMPLATE_ID) REFERENCES TEMPLATE (ID)
);

CREATE TABLE IF NOT EXISTS FIELD (
  ID    INT AUTO_INCREMENT,
  NAME  VARCHAR(255),
  TYPE  VARCHAR(36),
  STATE VARCHAR(36),
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS TEMPLATE_FIELDS (
  ID            INT AUTO_INCREMENT,
  TEMPLATE_ID   INT,
  FIELD_ID      INT,
  NAME_FIELD    VARCHAR(255),
  DESC_FIELD    VARCHAR(255),
  ORDINAL_FIELD INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (TEMPLATE_ID) REFERENCES TEMPLATE (ID),
  FOREIGN KEY (FIELD_ID) REFERENCES FIELD (ID)
);

CREATE TABLE IF NOT EXISTS DOCUMENT_FIELDS (
  ID                 INT AUTO_INCREMENT,
  DOCUMENT_ID        INT,
  TEMPLATE_FIELDS_ID INT,
  VALUE_FIELD        VARCHAR(255),
  PRIMARY KEY (ID),
  FOREIGN KEY (DOCUMENT_ID) REFERENCES DOCUMENT (ID),
  FOREIGN KEY (TEMPLATE_FIELDS_ID) REFERENCES TEMPLATE_FIELDS (ID)
);


INSERT INTO FIELD (NAME, TYPE, STATE)
VALUES ('ВВОД', 'INPUT', 'active'),
  ('Чекбокс', 'CHECKBOX', 'active'),
  ('Текст', 'TEXTAREA', 'active');

INSERT INTO TEMPLATE (NAME, STATE)
VALUES ('Справка', 'active'),
  ('Договор', 'active'),
  ('Текст', 'active');

INSERT INTO DOCUMENT (NAME, STATE, TEMPLATE_ID)
VALUES ('Справка1', 'active', 1),
  ('Справка2', 'active', 1),
  ('Договор1', 'active', 2),
  ('Текст1', 'active', 3);

INSERT INTO TEMPLATE_FIELDS (TEMPLATE_ID,
                             FIELD_ID,
                             NAME_FIELD,
                             DESC_FIELD,
                             ORDINAL_FIELD)
VALUES (1, 1, 'Surname', 'Фамилия', 0),
  (1, 1, 'Name', 'Имя', 1),
  (1, 3, 'Description', 'Описание', 2),
  (1, 2, 'Active', 'Активен', 3),

  (2, 1, 'Surname', 'Фамилия_Договор', 0),
  (2, 1, 'Description', 'Описание_Договора', 1),
  (2, 2, 'Active', 'Активен', 2),

  (3, 1, 'Name', 'Имя', 0),
  (3, 3, 'Description', 'Описание', 1),
  (3, 2, 'Active', 'Активен', 2);

INSERT INTO DOCUMENT_FIELDS (DOCUMENT_ID,
                             TEMPLATE_FIELDS_ID,
                             VALUE_FIELD)
VALUES (1, 1, 'Иванов'),
  (1, 2, 'Вася'),
  (1, 3, 'тест'),
  (1, 4, 'Активен'),

  (2, 1, 'Иванов'),
  (2, 2, 'Вася'),
  (2, 3, 'тест'),
  (2, 4, 'Активен'),

  (3, 5, 'Петров'),
  (3, 6, 'Описание_Договора'),
  (3, 7, 'Активен'),

  (4, 8, 'Сидоров'),
  (4, 9, 'Описание_справки'),
  (4, 10, 'Активен');
