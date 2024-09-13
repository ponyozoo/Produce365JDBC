CREATE TABLE TRAINEE (
    ID NUMBER PRIMARY KEY,
    NAME VARCHAR2(20) NOT NULL,
    BIRTH DATE NOT NULL,
    SEX VARCHAR2(1) CHECK (SEX IN ('F', 'M')) NOT NULL,
    HEIGHT NUMBER NOT NULL,
    WEIGHT NUMBER NOT NULL,
    NATIONALITY VARCHAR2(50) NOT NULL,
    HIRE_DATE DATE DEFAULT ON NULL SYSDATE
);

CREATE TABLE RATING (
    CATEGORY VARCHAR2(20) CHECK (CATEGORY IN ('VOCAL', 'RAP', 'DANCE', 'TOTAL')),
    TRAINEE_ID NUMBER REFERENCES TRAINEE(ID),
    GRADE VARCHAR2(1) DEFAULT ON NULL 'F' CHECK (GRADE BETWEEN 'A' AND 'F'),
    CONSTRAINT RATING_CONSTRAINT PRIMARY KEY (CATEGORY, TRAINEE_ID)
);

CREATE TABLE DEBUT (
    ID NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME VARCHAR2(50) NOT NULL UNIQUE,
    MEMBER_COUNT NUMBER NOT NULL,
    CONCEPT VARCHAR2(50) NOT NULL,
    GRADE VARCHAR2(1) DEFAULT ON NULL 'F' CHECK (GRADE BETWEEN 'A' AND 'F'),
    DEBUT_DATE DATE NOT NULL
);

CREATE TABLE DEBUT_MEMBER(
    IDX NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    GROUP_ID NUMBER REFERENCES DEBUT(ID) NOT NULL,
    TRAINEE_ID NUMBER REFERENCES TRAINEE(ID) NOT NULL UNIQUE
);

CREATE TABLE CARE (
    ID NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    CATEGORY VARCHAR2(50) NOT NULL,
    COST NUMBER DEFAULT ON NULL 0
);

CREATE TABLE CARE_HISTORY (
    IDX NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    CARE_DATE DATE DEFAULT ON NULL SYSDATE,
    CARE_ID NUMBER REFERENCES CARE(ID) NOT NULL,
    TRAINEE_ID NUMBER REFERENCES TRAINEE(ID)NOT NULL
);

CREATE TABLE LESSON(
    ID NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    TRAINER VARCHAR2(20) NOT NULL,
    SUBJECT VARCHAR2(50) NOT NULL,
    TIME NUMBER(2, 1) NOT NULL
);

CREATE TABLE LESSON_HISTORY (
    IDX NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    LESSON_DATE DATE DEFAULT ON NULL SYSDATE,
    LESSON_ID NUMBER REFERENCES LESSON (ID) NOT NULL,
    TRAINEE_ID NUMBER REFERENCES TRAINEE (ID) NOT NULL
);

INSERT INTO TRAINEE VALUES (32174380, '������', '2002-09-07', 'M', 183, 62, 'REPUBLIC OF KOREA', '2019-05-04');
INSERT INTO TRAINEE VALUES (20589425, '����Ÿ �ĸ�', '1998-06-29', 'M', 180, 70, 'JAPAN', '2018-05-14');
INSERT INTO TRAINEE VALUES (26468421, '�ڰ� ������', '1997-10-21', 'M', 186, 66, 'JAPAN', '2019-01-25');
INSERT INTO TRAINEE VALUES (32574685, '���̼�', '2002-07-09', 'M', 179, 68, 'TAIWAN', '2019-03-21');
INSERT INTO TRAINEE VALUES (32215468, '��īŰŸ ����', '2004-02-07', 'M', 174, 60, 'JAPAN', '2020-08-07');
INSERT INTO TRAINEE VALUES (32175094, '�ƻ����� ��', '2004-07-08', 'M', 184, 63, 'JAPAN', '2020-12-17');
INSERT INTO TRAINEE VALUES (54135879, '�ð�Ÿ �Ϸ��', '2005-05-01', 'M', 173, 60, 'JAPAN', '2021-12-03');
INSERT INTO TRAINEE VALUES (12548798, 'Ÿī�߸� ��Ű', '2005-05-04', 'M', 176, 65, 'JAPAN', '2017-11-13');
INSERT INTO TRAINEE VALUES (45621587, '���콺 ��Ű', '2006-02-17', 'M', 181, 68, 'JAPAN', '2021-05-07');

INSERT INTO RATING VALUES ('TOTAL', 32174380, 'A');
INSERT INTO RATING VALUES ('VOCAL', 26468421, 'A');
INSERT INTO RATING VALUES ('RAP', 32574685, 'B');
INSERT INTO RATING VALUES ('DANCE', 32215468, 'C');
INSERT INTO RATING VALUES ('TOTAL', 32175094, 'A');

INSERT INTO DEBUT (NAME, MEMBER_COUNT, CONCEPT, GRADE, DEBUT_DATE) VALUES ('ANDTEAM', 9, 'WOLF', 'A', '2022-12-07');
INSERT INTO DEBUT (NAME, MEMBER_COUNT, CONCEPT, GRADE, DEBUT_DATE) VALUES ('BOYNEXTDOOR', 6, 'HIPHOP', 'B', '2023-05-30');
INSERT INTO DEBUT (NAME, MEMBER_COUNT, CONCEPT, GRADE, DEBUT_DATE) VALUES ('TXT', 5, 'THE LITTLE PRINCE', 'F', '2019-03-04');

INSERT INTO DEBUT_MEMBER (GROUP_ID, TRAINEE_ID) VALUES (1, 32174380);
INSERT INTO DEBUT_MEMBER (GROUP_ID, TRAINEE_ID) VALUES (1, 32574685);
INSERT INTO DEBUT_MEMBER (GROUP_ID, TRAINEE_ID) VALUES (1, 32175094);
INSERT INTO DEBUT_MEMBER (GROUP_ID, TRAINEE_ID) VALUES (1, 32215468);
INSERT INTO DEBUT_MEMBER (GROUP_ID, TRAINEE_ID) VALUES (2, 54135879);
INSERT INTO DEBUT_MEMBER (GROUP_ID, TRAINEE_ID) VALUES (2, 12548798);
INSERT INTO DEBUT_MEMBER (GROUP_ID, TRAINEE_ID) VALUES (3, 26468421);

INSERT INTO CARE (CATEGORY, COST) VALUES ('�Ǻΰ� ���� 1ȸ', 100000);
INSERT INTO CARE (CATEGORY, COST) VALUES ('��̳���Ʈ 1��', 370000);
INSERT INTO CARE (CATEGORY, COST) VALUES ('����ũ�� 1ȸ', 300000);
INSERT INTO CARE (CATEGORY, COST) VALUES ('Ż�� 1ȸ', 150000);
INSERT INTO CARE (CATEGORY, COST) VALUES ('��ĿǮ ����', 800000);

INSERT INTO CARE_HISTORY (CARE_DATE, CARE_ID, TRAINEE_ID) VALUES ('2024-03-08', 3, 32174380);
INSERT INTO CARE_HISTORY (CARE_DATE, CARE_ID, TRAINEE_ID) VALUES ('2023-12-19', 4, 20589425);
INSERT INTO CARE_HISTORY (CARE_DATE, CARE_ID, TRAINEE_ID) VALUES ('2022-04-21', 1, 45621587);

INSERT INTO LESSON (TRAINER, SUBJECT, TIME) VALUES ('�̳�쿡', '�Ϻ��� ����', '4');
INSERT INTO LESSON (TRAINER, SUBJECT, TIME) VALUES ('������', '�ȹ� â��', '2');
INSERT INTO LESSON (TRAINER, SUBJECT, TIME) VALUES ('�Ҹ�', '���� Ʈ���̴�', '1.5');
INSERT INTO LESSON (TRAINER, SUBJECT, TIME) VALUES ('�����', '��Ÿ ����', '3');
INSERT INTO LESSON (TRAINER, SUBJECT, TIME) VALUES ('�ҿ�', '�ѱ��� ����', '2.5');

INSERT INTO LESSON_HISTORY (LESSON_DATE, LESSON_ID, TRAINEE_ID) VALUES ('2023-05-11', 1, 32174380);
INSERT INTO LESSON_HISTORY (LESSON_DATE, LESSON_ID, TRAINEE_ID) VALUES ('2024-09-01', 5, 32175094);
INSERT INTO LESSON_HISTORY (LESSON_DATE, LESSON_ID, TRAINEE_ID) VALUES ('2021-10-23', 2, 26468421);