CREATE TABLE payment
(
    id     BIGINT(20) not null auto_increment,
    serial VARCHAR(200) DEFAULT '',
    PRIMARY key (id)
) DEFAULT CHARSET = utf8;
