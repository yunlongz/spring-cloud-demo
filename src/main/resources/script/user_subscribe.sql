CREATE TABLE user_subscribe ( id NUMBER ( 20 ) primary key,
    template_id VARCHAR2(50),
    openid VARCHAR2(30),
    subscribe_status NUMBER(1),
    send_status NUMBER(1),
    host_ip VARCHAR2(30),
    appid VARCHAR2 (30),
    create_time timestamp,
    update_time timestamp
);

CREATE SEQUENCE user_subscribe_sequence
MAXVALUE 9999999999999
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 1000;