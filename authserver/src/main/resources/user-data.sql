---------------------DDL Start------------------------------------------
alter table if exists user_authorities 
   drop constraint if exists FKnfeejqie0sy3gxxil7dn3plsw

alter table if exists user_authorities 
   drop constraint if exists FKhiiib540jf74gksgb87oofni

drop table if exists authorities cascade

drop table if exists authorization_consent cascade

drop table if exists client cascade

drop table if exists sb_authorization cascade

drop table if exists user_authorities cascade

drop table if exists users cascade

drop sequence if exists authorities_seq

drop sequence if exists users_seq

create sequence authorities_seq start with 1 increment by 50

create sequence users_seq start with 1 increment by 50

create table authorities (
    id integer not null,
    authority varchar(255) unique,
    primary key (id)
)

create table authorization_consent (
    authorities varchar(1000),
    principal_name varchar(255) not null,
    registered_client_id varchar(255) not null,
    primary key (principal_name, registered_client_id)
)

create table client (
    client_id_issued_at timestamp(6) with time zone,
    client_secret_expires_at timestamp(6) with time zone,
    authorization_grant_types varchar(1000),
    client_authentication_methods varchar(1000),
    redirect_uris varchar(1000),
    scopes varchar(1000),
    client_settings varchar(2000),
    token_settings varchar(2000),
    client_id varchar(255),
    client_name varchar(255),
    client_secret varchar(255),
    id varchar(255) not null,
    primary key (id)
)

create table sb_authorization (
    access_token_expires_at timestamp(6) with time zone,
    access_token_issued_at timestamp(6) with time zone,
    authorization_code_expires_at timestamp(6) with time zone,
    authorization_code_issued_at timestamp(6) with time zone,
    oidc_id_token_expires_at timestamp(6) with time zone,
    oidc_id_token_issued_at timestamp(6) with time zone,
    refresh_token_expires_at timestamp(6) with time zone,
    refresh_token_issued_at timestamp(6) with time zone,
    state varchar(500),
    access_token_scopes varchar(1000),
    authorized_scopes varchar(1000),
    access_token_metadata text,
    oidc_id_token_claims text,
    oidc_id_token_metadata text,
    refresh_token_metadata text,
    access_token_value text,
    attributes text,
    authorization_code_value text,
    oidc_id_token_value text,
    refresh_token_value text,
    access_token_type varchar(255),
    authorization_code_metadata varchar(255),
    authorization_grant_type varchar(255),
    id varchar(255) not null,
    principal_name varchar(255),
    registered_client_id varchar(255),
    primary key (id)
)

create table user_authorities (
    authorities_id integer not null,
    user_id bigint not null,
    primary key (authorities_id, user_id)
)

create table users (
    account_non_expired boolean,
    account_non_locked boolean,
    credentials_non_expired boolean,
    enabled boolean,
    id bigint not null,
    password varchar(255),
    username varchar(255) unique,
    primary key (id)
)

alter table if exists user_authorities 
   add constraint FKnfeejqie0sy3gxxil7dn3plsw 
   foreign key (authorities_id) 
   references authorities

alter table if exists user_authorities 
   add constraint FKhiiib540jf74gksgb87oofni 
   foreign key (user_id) 
   references users
       
---------------------DDL End------------------------------------------

   ---------------------DML Start------------------------------------------
SELECT * FROM users;
SELECT * FROM authorities;
SELECT * FROM users_authorities;

INSERT INTO authorities(id, authority) VALUES(1,'ROLE_USER');
INSERT INTO authorities(id, authority) VALUES(2,'ROLE_ADMIN');
INSERT INTO authorities(id, authority) VALUES(3,'ROLE_DEVELOPER');

INSERT INTO users(id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) 
VALUES (1, 'Developer', '$2a$12$2yOChyhSuJm/naTBUjGZb.6d6mu1NsXS8XWRFousQfRTwzy0ZQtWW', true, true, true, true);
INSERT INTO users(id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) 
VALUES (2,'Admin', '$2a$12$2yOChyhSuJm/naTBUjGZb.6d6mu1NsXS8XWRFousQfRTwzy0ZQtWW', true, true, true, true);
INSERT INTO users(id,username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) 
VALUES (3,'User', '$2a$12$2yOChyhSuJm/naTBUjGZb.6d6mu1NsXS8XWRFousQfRTwzy0ZQtWW', true, true, true, true);

INSERT INTO user_authorities(user_id, authorities_id) VALUES (1, 1);
INSERT INTO user_authorities(user_id, authorities_id) VALUES (1, 2);
INSERT INTO user_authorities(user_id, authorities_id) VALUES (1, 3);
INSERT INTO user_authorities(user_id, authorities_id) VALUES (2, 1);
INSERT INTO user_authorities(user_id, authorities_id) VALUES (2, 2);
INSERT INTO user_authorities(user_id, authorities_id) VALUES (3, 1);


SELECT * FROM client;

INSERT INTO client(id, authorization_grant_types, client_authentication_methods, client_id, client_id_issued_at, client_name, 
client_secret, client_secret_expires_at, client_settings, redirect_uris, scopes, token_settings) 
VALUES('abbc70f1-fb59-4b42-b1e4-c52fa0080bea', 'refresh_token,client_credentials,authorization_code', 'client_secret_basic',
'client', null, 'abbc70f1-fb59-4b42-b1e4-c52fa0080bea', '$2a$10$lcGI9Fp6GLfk7wjyOK0VqORQqMtsQRoC3J7i/V023SgQv9JZLZ01K', null, 
'{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":true,"settings.client.require-authorization-consent":true}', 
'http://insomnia,http://127.0.0.1:8080/login/oauth2/code/client', 'read,openid,profile', 
'{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,
"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],
"settings.token.access-token-time-to-live":["java.time.Duration",86400.000000000],
"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat",
"value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],
"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

---------------------DML End------------------------------------------