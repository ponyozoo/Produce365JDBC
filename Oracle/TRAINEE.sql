CREATE TABLE TRAINEE (
    ID NUMBER PRIMARY KEY,
    NAME VARCHAR2(20) NOT NULL,
    BIRTH DATE NOT NULL,
    SEX VARCHAR2(1) CHECK (SEX IN ('F', 'M')) NOT NULL,
    HEIGHT NUMBER NOT NULL,
    WEIGHT NUMBER NOT NULL,
    NATIONALITY VARCHAR2(20) NOT NULL,
    HIRE_DATE DATE DEFAULT ON NULL SYSDATE
);

ALTER TABLE TRAINEE MODIFY HIRE_DATE DATE DEFAULT ON NULL SYSDATE;

COMMIT;
