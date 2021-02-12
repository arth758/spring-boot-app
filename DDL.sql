CREATE schema anotacao;

USE anotacao;

CREATE user 'user' @'localhost' IDENTIFIED by 'pass123';

GRANT
SELECT
,
INSERT
,
    DELETE,
UPDATE
    ON anotacao.* TO user @'localhost';

CREATE TABLE usr_usuario (
    usr_id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
    usr_nome varchar(20) NOT NULL,
    usr_senha varchar(50) NOT NULL,
    PRIMARY KEY (usr_id),
    UNIQUE KEY uni_usuario_nome (usr_nome)
);

CREATE TABLE aut_autorizacao (
    aut_id bigint UNSIGNED NOT NULL auto_increemnt,
    aut_nome varchar(20) NOT NULL,
    PRIMARY KEY (aut_id),
    UNIQUE KEY uni_aut_nome (aut_nome)
);

CREATE TABLE usu_usuario_autorizacao (
    usr_id bigint UNSIGNED NOT NULL,
    aut_id bigint UNSIGNED NOT NULL,
    PRIMARY KEY(usr_id, aut_id),
    FOREIGN KEY aut_usuario_fk (usr_id) REFERENCES usr_usuario (usr_id),
    FOREIGN KEY aut_autorizacao_fk (aut_id) REFERENCES aut_autorizacao (aut_id)
);