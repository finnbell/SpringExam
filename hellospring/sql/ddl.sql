DROP TABLE IF EXITS MEMBER CASCADE;
CREATE TABLE MEMBER
(
  ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
  NAME VARCHAR(255),
  PRIMARY KEY (ID)
);
