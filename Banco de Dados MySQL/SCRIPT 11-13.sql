DROP DATABASE match_making;
CREATE DATABASE IF NOT EXISTS match_making;
USE match_making;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario VARCHAR(255) PRIMARY KEY,
    nome VARCHAR(45) ,
    sobrenome VARCHAR(100) ,
    email VARCHAR(100),
    celular CHAR(11) ,
    senha VARCHAR(255) ,
    dt_nascimento DATE ,
    identidade_genero VARCHAR(45) ,
    dt_cadastro DATETIME ,
    deleted TINYINT DEFAULT FALSE);

CREATE UNIQUE INDEX id_usuario_UNIQUE ON usuario(id_usuario);

CREATE TABLE IF NOT EXISTS plano (
                                     id_plano INT PRIMARY KEY AUTO_INCREMENT,
                                     nome VARCHAR(45) ,
    descricao VARCHAR(255) ,
    preco FLOAT);

CREATE TABLE IF NOT EXISTS perfil (
                                      id_perfil INT AUTO_INCREMENT,
                                      id_usuario VARCHAR(255),
    username VARCHAR(45) ,
    biografia VARCHAR(255) ,
    nota FLOAT ,
    orientacao_sexual VARCHAR(45) ,
    procura_amizade TINYINT ,
    procura_namoro TINYINT ,
    procura_player2 TINYINT ,
    is_premium TINYINT DEFAULT FALSE,
    id_plano INT ,
    deleted TINYINT DEFAULT FALSE,
    PRIMARY KEY(id_perfil, id_usuario),
    CONSTRAINT fk_perfil_usuario
    FOREIGN KEY perfil(id_usuario)
    REFERENCES usuario(id_usuario),
    CONSTRAINT fk_perfil_plano
    FOREIGN KEY perfil(id_plano)
    REFERENCES plano(id_plano));

CREATE UNIQUE INDEX perfil_id_usuario_idx ON perfil(id_usuario);

CREATE TABLE IF NOT EXISTS avaliacao (
                                         id_avaliacao INT auto_increment,
                                         id_perfil_avaliado INT,
                                         id_perfil_avaliador INT,
                                         avaliacao FLOAT ,
                                         descricao VARCHAR(255) ,
    nota_anterior FLOAT ,
    nota_nova FLOAT ,
    horario DATETIME ,
    ativa TINYINT NOT NULL DEFAULT TRUE,
    PRIMARY KEY(id_avaliacao, id_perfil_avaliado, id_perfil_avaliador),
    CONSTRAINT fk_avaliacao_perfil1
    FOREIGN KEY avaliacao(id_perfil_avaliado)
    REFERENCES perfil(id_perfil),
    CONSTRAINT fk_avaliacao_perfil2
    FOREIGN KEY avaliacao(id_perfil_avaliador)
    REFERENCES perfil(id_perfil));

CREATE UNIQUE INDEX avaliacao_unique_idx ON avaliacao(id_avaliacao);


CREATE TABLE IF NOT EXISTS midia (
                                     id_midia INT PRIMARY KEY AUTO_INCREMENT,
                                     id_perfil INT NOT NULL,
                                     link VARCHAR(255),
    visivel TINYINT DEFAULT TRUE,
    CONSTRAINT fk_midia_perfil
    FOREIGN KEY midia(id_perfil)
    REFERENCES perfil(id_perfil));

CREATE INDEX fk_midia_perfil_idx ON midia(id_perfil);

CREATE TABLE IF NOT EXISTS curtidas_log (
                                            horario DATETIME,
                                            id_perfil1 INT ,
                                            id_midia INT ,
                                            id_perfil2 INT ,
                                            PRIMARY KEY(horario, id_perfil1),
    CONSTRAINT fk_curtidas_log_perfil1
    FOREIGN KEY curtidas_log(id_perfil1)
    REFERENCES perfil(id_perfil),
    CONSTRAINT fk_curtidas_log_midia1
    FOREIGN KEY curtidas_log(id_midia)
    REFERENCES midia(id_midia),
    CONSTRAINT fk_curtidas_log_perfil2
    FOREIGN KEY curtidas_log(id_perfil2)
    REFERENCES perfil(id_perfil));

CREATE INDEX fk_curtidas_log_perfil1_idx ON curtidas_log(id_perfil1);

CREATE INDEX fk_curtidas_log_midia1_idx ON curtidas_log(id_midia);

CREATE INDEX fk_curtidas_log_perfil2_idx ON curtidas_log(id_perfil2);

CREATE TABLE IF NOT EXISTS console (
                                       id_console INT PRIMARY KEY AUTO_INCREMENT,
                                       nome VARCHAR(60) ,
    id_imagem INT ,
    CONSTRAINT fk_console_midia1
    FOREIGN KEY console(id_imagem)
    REFERENCES midia(id_midia));

CREATE INDEX fk_console_midia1_idx ON console(id_imagem);


CREATE TABLE IF NOT EXISTS console_perfil (
                                              id_console INT AUTO_INCREMENT,
                                              id_perfil INT ,
                                              visivel TINYINT ,
                                              PRIMARY KEY(id_console, id_perfil),
    CONSTRAINT fk_console_perfil
    FOREIGN KEY console_perfil(id_console)
    REFERENCES console(id_console),
    CONSTRAINT fk_console_perfil_perfil
    FOREIGN KEY console_perfil(id_perfil)
    REFERENCES perfil(id_perfil));

CREATE INDEX fk_console_perfil_perfil_idx ON console_perfil(id_console);

CREATE INDEX fk_console_perfil_console_idx ON console_perfil(id_perfil);

CREATE TABLE IF NOT EXISTS interesse (
                                         id_interesses INT PRIMARY KEY AUTO_INCREMENT,
                                         nome VARCHAR(45),
    descricao VARCHAR(100));

CREATE TABLE IF NOT EXISTS genero_jogos (
                                            id_genero_jogos INT PRIMARY KEY AUTO_INCREMENT,
                                            nome VARCHAR(45),
    descricao VARCHAR(100));

CREATE TABLE IF NOT EXISTS interesse_perfil (
                                                id_perfil INT ,
                                                id_interesses INT,
                                                visivel TINYINT DEFAULT TRUE,
                                                PRIMARY KEY(id_perfil, id_interesses),
    CONSTRAINT fk_interesse_perfil
    FOREIGN KEY interesse_perfil(id_perfil)
    REFERENCES perfil(id_perfil),
    CONSTRAINT fk_interesse_perfil_interesses
    FOREIGN KEY interesse_perfil(id_interesses)
    REFERENCES interesse(id_interesses));

CREATE INDEX fk_interesse_perfil_interesse_idx ON interesse_perfil(id_interesses);

CREATE INDEX fk_interesse_perfil_perfil_idx ON interesse_perfil(id_perfil);

CREATE TABLE IF NOT EXISTS genero_jogos_perfil (
                                                   id_perfil INT,
                                                   id_genero_jogos INT,
                                                   visivel TINYINT DEFAULT TRUE,
                                                   PRIMARY KEY(id_perfil, id_genero_jogos),
    CONSTRAINT fk_genero_jogos_perfil
    FOREIGN KEY genero_jogos_perfil(id_perfil)
    REFERENCES perfil(id_perfil),
    CONSTRAINT fk_genero_jogos_perfil_genero_jogos
    FOREIGN KEY genero_jogos_perfil(id_genero_jogos)
    REFERENCES genero_jogos(id_genero_jogos));

CREATE INDEX fk_genero_jogos_perfil_genero_jogos_idx ON genero_jogos_perfil(id_genero_jogos);

CREATE INDEX fk_genero_jogos_perfil1_idx ON genero_jogos_perfil(id_perfil);

CREATE TABLE IF NOT EXISTS conversa (
                                        id_conversa INT AUTO_INCREMENT,
                                        id_perfil1 INT ,
                                        id_perfil2 INT ,
                                        notificacoes INT ,
                                        alerta_notificacao TINYINT DEFAULT TRUE,
                                        PRIMARY KEY(id_conversa, id_perfil1, id_perfil2),
    CONSTRAINT fk_conversa_perfil1
    FOREIGN KEY conversa(id_perfil1)
    REFERENCES perfil(id_perfil),
    CONSTRAINT fk_conversa_perfil2
    FOREIGN KEY conversa(id_perfil2)
    REFERENCES perfil(id_perfil));

CREATE INDEX fk_conversa_perfil1_idx ON conversa(id_perfil1);

CREATE INDEX fk_conversa_perfil2_idx ON conversa(id_perfil2);

CREATE TABLE IF NOT EXISTS mensagem (
                                        id_mensagem BIGINT AUTO_INCREMENT,
                                        id_conversa INT ,
                                        horario DATETIME ,
                                        relator INT ,
                                        corpo_mensagem VARCHAR(255),
    visualizada TINYINT DEFAULT FALSE,
    PRIMARY KEY(id_mensagem, id_conversa, horario, relator),
    CONSTRAINT fk_mensagem_conversa
    FOREIGN KEY mensagem(id_conversa)
    REFERENCES conversa(id_conversa),
    CONSTRAINT fk_mensagem_perfil
    FOREIGN KEY mensagem(relator)
    REFERENCES perfil(id_perfil));

CREATE INDEX fk_mensagem_conversa_idx ON mensagem(id_conversa);

CREATE INDEX fk_mensagem_perfil_idx ON mensagem(relator);

CREATE TABLE IF NOT EXISTS perfis_priorizados (
                                                  id_priorizacao INT AUTO_INCREMENT,
                                                  id_perfil_priorizado INT,
                                                  id_perfil_fila INT,
                                                  valido TINYINT DEFAULT TRUE,
                                                  PRIMARY KEY(id_priorizacao, id_perfil_priorizado, id_perfil_fila),
    CONSTRAINT fk_priorizacao_perfil1
    FOREIGN KEY perfis_priorizados(id_perfil_priorizado)
    REFERENCES perfil(id_perfil),
    CONSTRAINT fk_priorizacao_perfil2
    FOREIGN KEY perfis_priorizados(id_perfil_fila)
    REFERENCES perfil(id_perfil));

CREATE INDEX id_perfis_priorizados_idx ON perfis_priorizados(id_priorizacao);

CREATE INDEX fk_perfis_priorizados_perfil_priorizado_idx ON perfis_priorizados(id_perfil_priorizado);

CREATE INDEX fk_perfis_priorizados_perfil_fila_idx ON perfis_priorizados(id_perfil_fila);

CREATE TABLE IF NOT EXISTS perfis_descurtidos(
                                                 id_perfil INT,
                                                 id_perfil_descurtido INT,
                                                 valido TINYINT DEFAULT TRUE,
                                                 primary key(id_perfil, id_perfil_descurtido)
    );

CREATE INDEX fk_perfis_descurtidos_perfil_idx ON perfis_descurtidos(id_perfil);

CREATE INDEX fk_perfis_descurtidos_perfil_descurtido_idx ON perfis_descurtidos(id_perfil_descurtido);

DELIMITER $$
CREATE PROCEDURE SP_buscar_amigos(
    IN var_id_perfil INT
)
BEGIN
SELECT p.username from perfil p
                           JOIN conversa as c on c.id_perfil1 = p.id_perfil
WHERE c.id_perfil2 = var_id_perfil AND c.id_perfil1 != var_id_perfil
UNION
SELECT p.username from perfil p
                           JOIN conversa as c on c.id_perfil2 = p.id_perfil
WHERE c.id_perfil1 = var_id_perfil AND c.id_perfil2 != var_id_perfil;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SP_curtir_perfil(
    IN var_id_perfil1 INT,
    IN var_id_perfil2 INT,
    IN var_horario DATETIME
)
BEGIN
  DECLARE total INT;

INSERT INTO curtidas_log (horario, id_perfil1, id_perfil2)
VALUES (var_horario, var_id_perfil1, var_id_perfil2);

SELECT COUNT(*)
INTO total
FROM curtidas_log AS c
WHERE c.id_perfil1 = var_id_perfil2 AND c.id_perfil2 = var_id_perfil1;

IF total >= 1 THEN
    INSERT INTO conversa (id_perfil1, id_perfil2) VALUES (var_id_perfil1, var_id_perfil2);
ELSE
    INSERT INTO perfis_priorizados (id_perfil_priorizado, id_perfil_fila) VALUES (var_id_perfil1, var_id_perfil2);
END IF;
END $$
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE SP_descurtir_perfil(
    IN var_id_perfil1 INT,
    IN var_id_perfil2 INT
)
BEGIN
    -- Adicione um tratamento para evitar duplicatas
    IF NOT EXISTS (SELECT 1 FROM perfis_descurtidos WHERE id_perfil = var_id_perfil1 AND id_perfil_descurtido = var_id_perfil2) THEN
        INSERT INTO perfis_descurtidos (id_perfil, id_perfil_descurtido) VALUES (var_id_perfil1, var_id_perfil2);
END IF;
    -- Adicione um COMMIT se necessário
    -- COMMIT;
END $$

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SP_nova_avaliacao(
    IN var_id_perfil_avaliado INT,
    IN var_id_perfil_avaliador INT,
    IN var_avaliacao FLOAT,  -- essa avaliação já tem que vir afetada pela própria avaliacao do cara
    -- nota anterior é uma variavel com select e  nota nova é a media
    IN var_descricao VARCHAR(255),
    IN var_horario DATETIME
)
BEGIN
 DECLARE var_nota_nova FLOAT;
 DECLARE var_nota_anterior FLOAT;

SELECT p.nota
INTO var_nota_anterior
FROM perfil as p
WHERE p.id_perfil= var_id_perfil_avaliado;

INSERT INTO avaliacao (
    id_perfil_avaliado,
    id_perfil_avaliador,
    avaliacao ,
    descricao ,
    nota_anterior,
    horario)
VALUES (var_id_perfil_avaliado, var_id_perfil_avaliador, var_avaliacao, var_descricao, var_nota_anterior ,var_horario);

SELECT AVG(a.avaliacao)
INTO var_nota_nova
FROM avaliacao AS a
WHERE a.id_perfil_avaliado=var_id_perfil_avaliado AND a.ativa = 1;


UPDATE avaliacao as a set a.nota_nova = var_nota_nova
WHERE a.id_perfil_avaliado =var_id_perfil_avaliado AND a.id_perfil_avaliador = var_id_perfil_avaliador AND a.nota_nova = null;

UPDATE perfil as p set p.nota = var_nota_nova
where p.id_perfil = var_id_perfil_avaliado;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SP_remover_avaliacao(
    IN var_id_perfil_avaliado INT,
    IN var_id_perfil_avaliador INT
)
BEGIN
 DECLARE var_id_avaliacao FLOAT;
 DECLARE var_nota_nova FLOAT;

SELECT id_avaliacao
INTO var_id_avaliacao
From avaliacao
WHERE id_perfil_avaliado = var_id_perfil_avaliado
  AND id_perfil_avaliador = var_id_perfil_avaliador;

UPDATE avaliacao as a set a.ativa = false
WHERE a.id_avaliacao = var_id_avaliacao;



SELECT AVG(a.avaliacao)
INTO var_nota_nova
FROM avaliacao AS a
WHERE a.id_perfil_avaliado=var_id_perfil_avaliado AND a.ativa = 1;

UPDATE perfil as p set p.nota = var_nota_nova
where p.id_perfil = var_id_perfil_avaliado;

END $$
DELIMITER ;

-- criado procedure cardapio
DELIMITER $$
CREATE PROCEDURE SP_busca_carrossel(
-- parametros
    IN var_id_perfil INT
)
BEGIN

-- declarar variaveis boleanas para oq o user procura
DECLARE var_procura_amizade TINYINT;
DECLARE var_procura_namoro TINYINT;
DECLARE var_procura_player2 TINYINT;

DROP VIEW IF EXISTS VW_perfis_compativeis;

-- select do perfil para atrubuir valor as boleanas
SELECT p.procura_amizade into var_procura_amizade from perfil as p where p.id_perfil = var_id_perfil;
SELECT p.procura_namoro into var_procura_namoro from perfil as p where p.id_perfil = var_id_perfil;
SELECT p.procura_player2 into var_procura_player2 from perfil as p where p.id_perfil = var_id_perfil;

IF var_procura_amizade =1 AND var_procura_namoro in (null, 0) AND var_procura_player2 in (null, 0) THEN
SELECT p.* FROM perfis_priorizados as pp
                    JOIN perfil as p ON p.id_perfil = pp.id_perfil_priorizado
WHERE pp.id_perfil_fila = var_id_perfil
UNION DISTINCT
SELECT p.* FROM perfil as p
                    JOIN interesse_perfil ip ON p.id_perfil = ip.id_perfil
                    JOIN interesse_perfil ip2 ON ip.id_interesses = ip2.id_interesses AND ip.id_perfil <> ip2.id_perfil
                    JOIN genero_jogos_perfil gjp ON p.id_perfil = gjp.id_perfil
                    JOIN genero_jogos_perfil gjp2 ON gjp.id_genero_jogos = gjp2.id_genero_jogos AND gjp.id_perfil <> gjp2.id_perfil
WHERE ip2.id_perfil = var_id_perfil AND ip.visivel = 1 AND ip2.visivel = 1 AND
        gjp2.id_perfil = var_id_perfil AND gjp.visivel = 1 AND gjp2.visivel = 1 AND p.procura_amizade = 1
  AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil = var_id_perfil AND pd.id_perfil_descurtido = p.id_perfil AND pd.valido = 1
    ) AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil_descurtido = var_id_perfil AND pd.id_perfil = p.id_perfil AND pd.valido = 1
    )
GROUP BY
    p.id_perfil, p.id_usuario, p.username, p.biografia, p.nota, p.orientacao_sexual,
    p.procura_amizade, p.procura_namoro, p.procura_player2, p.is_premium, p.id_plano
HAVING
        COUNT(ip.id_interesses) >= 1 AND COUNT(gjp.id_genero_jogos) >= 2 LIMIT 50;
ELSEIF var_procura_amizade in (null,0) AND var_procura_namoro in (null, 0) AND var_procura_player2=1 THEN
SELECT p.* FROM perfis_priorizados as pp
                    JOIN perfil as p ON p.id_perfil = pp.id_perfil_priorizado
WHERE pp.id_perfil_fila = var_id_perfil
UNION DISTINCT
SELECT p.* FROM perfil as p
                    JOIN interesse_perfil ip ON p.id_perfil = ip.id_perfil
                    JOIN interesse_perfil ip2 ON ip.id_interesses = ip2.id_interesses AND ip.id_perfil <> ip2.id_perfil
                    JOIN genero_jogos_perfil gjp ON p.id_perfil = gjp.id_perfil
                    JOIN genero_jogos_perfil gjp2 ON gjp.id_genero_jogos = gjp2.id_genero_jogos AND gjp.id_perfil <> gjp2.id_perfil
WHERE ip2.id_perfil = var_id_perfil AND ip.visivel = 1 AND ip2.visivel = 1 AND
        gjp2.id_perfil = var_id_perfil AND gjp.visivel = 1 AND gjp2.visivel = 1 AND p.procura_player2 = 1
  AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil = var_id_perfil AND pd.id_perfil_descurtido = p.id_perfil AND pd.valido = 1
    ) AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil_descurtido = var_id_perfil AND pd.id_perfil = p.id_perfil AND pd.valido = 1
    )
GROUP BY
    p.id_perfil, p.id_usuario, p.username, p.biografia, p.nota, p.orientacao_sexual,
    p.procura_amizade, p.procura_namoro, p.procura_player2, p.is_premium, p.id_plano
HAVING
        COUNT(ip.id_interesses) >= 1 AND COUNT(gjp.id_genero_jogos) >= 2 LIMIT 50;
ELSEIF var_procura_amizade in (null, 0) AND var_procura_namoro =1  AND var_procura_player2 in (null, 0) THEN
SELECT p.* FROM perfis_priorizados as pp
                    JOIN perfil as p ON p.id_perfil = pp.id_perfil_priorizado
WHERE pp.id_perfil_fila = var_id_perfil
UNION DISTINCT
SELECT p.* FROM perfil as p
                    JOIN interesse_perfil ip ON p.id_perfil = ip.id_perfil
                    JOIN interesse_perfil ip2 ON ip.id_interesses = ip2.id_interesses AND ip.id_perfil <> ip2.id_perfil
                    JOIN genero_jogos_perfil gjp ON p.id_perfil = gjp.id_perfil
                    JOIN genero_jogos_perfil gjp2 ON gjp.id_genero_jogos = gjp2.id_genero_jogos AND gjp.id_perfil <> gjp2.id_perfil
WHERE ip2.id_perfil = var_id_perfil AND ip.visivel = 1 AND ip2.visivel = 1 AND
        gjp2.id_perfil = var_id_perfil AND gjp.visivel = 1 AND gjp2.visivel = 1 AND p.procura_namoro = 1
  AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil = var_id_perfil AND pd.id_perfil_descurtido = p.id_perfil AND pd.valido = 1
    ) AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil_descurtido = var_id_perfil AND pd.id_perfil = p.id_perfil AND pd.valido = 1
    )
GROUP BY
    p.id_perfil, p.id_usuario, p.username, p.biografia, p.nota, p.orientacao_sexual,
    p.procura_amizade, p.procura_namoro, p.procura_player2, p.is_premium, p.id_plano
HAVING
        COUNT(ip.id_interesses) >= 1 AND COUNT(gjp.id_genero_jogos) >= 2 LIMIT 50;
ELSEIF var_procura_amizade =1 AND var_procura_namoro=1 AND var_procura_player2 in (null, 0) THEN
SELECT p.* FROM perfis_priorizados as pp
                    JOIN perfil as p ON p.id_perfil = pp.id_perfil_priorizado
WHERE pp.id_perfil_fila = var_id_perfil
UNION DISTINCT
SELECT p.* FROM perfil as p
                    JOIN interesse_perfil ip ON p.id_perfil = ip.id_perfil
                    JOIN interesse_perfil ip2 ON ip.id_interesses = ip2.id_interesses AND ip.id_perfil <> ip2.id_perfil
                    JOIN genero_jogos_perfil gjp ON p.id_perfil = gjp.id_perfil
                    JOIN genero_jogos_perfil gjp2 ON gjp.id_genero_jogos = gjp2.id_genero_jogos AND gjp.id_perfil <> gjp2.id_perfil
WHERE ip2.id_perfil = var_id_perfil AND ip.visivel = 1 AND ip2.visivel = 1 AND
        gjp2.id_perfil = var_id_perfil AND gjp.visivel = 1 AND gjp2.visivel = 1 AND (p.procura_amizade = 1 OR p.procura_namoro = 1)
  AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil = var_id_perfil AND pd.id_perfil_descurtido = p.id_perfil AND pd.valido = 1
    ) AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil_descurtido = var_id_perfil AND pd.id_perfil = p.id_perfil AND pd.valido = 1
    )
GROUP BY
    p.id_perfil, p.id_usuario, p.username, p.biografia, p.nota, p.orientacao_sexual,
    p.procura_amizade, p.procura_namoro, p.procura_player2, p.is_premium, p.id_plano
HAVING
        COUNT(ip.id_interesses) >= 1 AND COUNT(gjp.id_genero_jogos) >= 2 LIMIT 50;
ELSEIF var_procura_amizade in (null, 0) AND var_procura_namoro = 1 AND var_procura_player2 = 1 THEN
SELECT p.* FROM perfis_priorizados as pp
                    JOIN perfil as p ON p.id_perfil = pp.id_perfil_priorizado
WHERE pp.id_perfil_fila = var_id_perfil
UNION DISTINCT
SELECT p.* FROM perfil as p
                    JOIN interesse_perfil ip ON p.id_perfil = ip.id_perfil
                    JOIN interesse_perfil ip2 ON ip.id_interesses = ip2.id_interesses AND ip.id_perfil <> ip2.id_perfil
                    JOIN genero_jogos_perfil gjp ON p.id_perfil = gjp.id_perfil
                    JOIN genero_jogos_perfil gjp2 ON gjp.id_genero_jogos = gjp2.id_genero_jogos AND gjp.id_perfil <> gjp2.id_perfil
WHERE ip2.id_perfil = var_id_perfil AND ip.visivel = 1 AND ip2.visivel = 1 AND
        gjp2.id_perfil = var_id_perfil AND gjp.visivel = 1 AND gjp2.visivel = 1 AND (p.procura_player2 = 1 OR p.procura_namoro = 1)
  AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil = var_id_perfil AND pd.id_perfil_descurtido = p.id_perfil AND pd.valido = 1
    ) AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil_descurtido = var_id_perfil AND pd.id_perfil = p.id_perfil AND pd.valido = 1
    )
GROUP BY
    p.id_perfil, p.id_usuario, p.username, p.biografia, p.nota, p.orientacao_sexual,
    p.procura_amizade, p.procura_namoro, p.procura_player2, p.is_premium, p.id_plano
HAVING
        COUNT(ip.id_interesses) >= 1 AND COUNT(gjp.id_genero_jogos) >= 2  LIMIT 50;
ELSEIF var_procura_amizade =1 AND var_procura_namoro in (null, 0) AND var_procura_player2 = 1 THEN
SELECT p.* FROM perfis_priorizados as pp
                    JOIN perfil as p ON p.id_perfil = pp.id_perfil_priorizado
WHERE pp.id_perfil_fila = var_id_perfil
UNION DISTINCT
SELECT p.* FROM perfil as p
                    JOIN interesse_perfil ip ON p.id_perfil = ip.id_perfil
                    JOIN interesse_perfil ip2 ON ip.id_interesses = ip2.id_interesses AND ip.id_perfil <> ip2.id_perfil
                    JOIN genero_jogos_perfil gjp ON p.id_perfil = gjp.id_perfil
                    JOIN genero_jogos_perfil gjp2 ON gjp.id_genero_jogos = gjp2.id_genero_jogos AND gjp.id_perfil <> gjp2.id_perfil
WHERE ip2.id_perfil = var_id_perfil AND ip.visivel = 1 AND ip2.visivel = 1 AND
        gjp2.id_perfil = var_id_perfil AND gjp.visivel = 1 AND gjp2.visivel = 1 AND (p.procura_amizade = 1 OR p.procura_player2 = 1)
  AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil = var_id_perfil AND pd.id_perfil_descurtido = p.id_perfil AND pd.valido = 1
    ) AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil_descurtido = var_id_perfil AND pd.id_perfil = p.id_perfil AND pd.valido = 1
    )
GROUP BY
    p.id_perfil, p.id_usuario, p.username, p.biografia, p.nota, p.orientacao_sexual,
    p.procura_amizade, p.procura_namoro, p.procura_player2, p.is_premium, p.id_plano
HAVING
        COUNT(ip.id_interesses) >= 1 AND COUNT(gjp.id_genero_jogos) >= 2 LIMIT 50;
ELSE SELECT p.* FROM perfis_priorizados as pp
                         JOIN perfil as p ON p.id_perfil = pp.id_perfil_priorizado
     WHERE pp.id_perfil_fila = var_id_perfil
     UNION DISTINCT
SELECT p.* FROM perfil as p
                    JOIN interesse_perfil ip ON p.id_perfil = ip.id_perfil
                    JOIN interesse_perfil ip2 ON ip.id_interesses = ip2.id_interesses AND ip.id_perfil <> ip2.id_perfil
                    JOIN genero_jogos_perfil gjp ON p.id_perfil = gjp.id_perfil
                    JOIN genero_jogos_perfil gjp2 ON gjp.id_genero_jogos = gjp2.id_genero_jogos AND gjp.id_perfil <> gjp2.id_perfil
WHERE ip2.id_perfil = var_id_perfil AND ip.visivel = 1 AND ip2.visivel = 1 AND
        gjp2.id_perfil = var_id_perfil AND gjp.visivel = 1 AND gjp2.visivel = 1 AND p.procura_amizade = 1
  AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil = var_id_perfil AND pd.id_perfil_descurtido = p.id_perfil AND pd.valido = 1
    ) AND NOT EXISTS (
        SELECT 1
        FROM perfis_descurtidos pd
        WHERE pd.id_perfil_descurtido = var_id_perfil AND pd.id_perfil = p.id_perfil AND pd.valido = 1
    )
GROUP BY
    p.id_perfil, p.id_usuario, p.username, p.biografia, p.nota, p.orientacao_sexual,
    p.procura_amizade, p.procura_namoro, p.procura_player2, p.is_premium, p.id_plano
HAVING
        COUNT(ip.id_interesses) >= 1 AND COUNT(gjp.id_genero_jogos) >= 2
    LIMIT 50;
END IF;

END$$
DELIMITER ;

-- TESTES E INSERTS
-- Inserts para a tabela usuario
INSERT INTO usuario (id_usuario, nome, sobrenome, email, celular, senha, dt_nascimento, identidade_genero, dt_cadastro)
VALUES
    ('1', 'João', 'Silva', 'joao@example.com', '987654321', 'senha123', '1990-01-01', 'Masculino', NOW()),
    ('2', 'Maria', 'Santos', 'maria@example.com', '987654322', 'senha456', '1985-05-15', 'Feminino', NOW()),
    ('3', 'Carlos', 'Oliveira', 'carlos@example.com', '987654323', 'senha789', '1995-08-20', 'Masculino', NOW()),
    ('4', 'Ana', 'Costa', 'ana@example.com', '987654324', 'senhaabc', '1988-11-10', 'Feminino', NOW()),
    ('5', 'Pedro', 'Ribeiro', 'pedro@example.com', '987654325', 'senhaxyz', '1992-03-25', 'Masculino', NOW()),
    ('8121ca69-ed18-4c1c-a606-a9f3b0a0ec6c','John', 'Doe', 'john@doe.com', '91234-5678', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '2000-01-01', 'Homem', NOW());
-- Inserts para a tabela plano
INSERT INTO plano (nome, descricao, preco)
VALUES
    ('Básico', 'Plano de recursos básicos', 9.99),
    ('Premium', 'Plano premium com recursos adicionais', 19.99),
    ('Gold', 'Plano com todos os recursos', 29.99),
    ('Free', 'Plano gratuito com recursos limitados', 0.00),
    ('Teste', 'Plano de teste', 5.99);

-- Inserts para a tabela perfil
INSERT INTO perfil (id_usuario, username, biografia, nota, orientacao_sexual, procura_amizade, procura_namoro, procura_player2, is_premium, id_plano)
VALUES
    ('1', 'joao_silva', 'Olá, sou o João!', 4.5, 'Heterossexual', 1, 1, 1, 1, 3),
    ('2', 'maria_santos', 'Oi, eu sou a Maria!', 4.8, 'Heterossexual', 1, 1, 1, 1, 2),
    ('3', 'carlos_oliveira', 'E aí, sou o Carlos!', 3.9, 'Homossexual', 1, 1, 1, 0, 1),
    ('4', 'ana_costa', 'Oi, sou a Ana!', 4.2, 'Homossexual', 1, 1, 1, 1, 4),
    ('5', 'pedro_ribeiro', 'Oi, aqui é o Pedro!', 4.1, 'Heterossexual', 1, 1, 1, 0, 5);

-- Inserts para a tabela interesse
INSERT INTO interesse (nome, descricao)
VALUES
    ('Esportes', 'Interesse em praticar e assistir esportes'),
    ('Música', 'Interesse por diversos gêneros musicais'),
    ('Tecnologia', 'Interesse em gadgets e novas tecnologias'),
    ('Viagens', 'Interesse em explorar novos lugares'),
    ('Leitura', 'Interesse em livros e literatura');

-- Inserts para a tabela genero_jogos
INSERT INTO genero_jogos (nome, descricao)
VALUES
    ('Ação', 'Jogos de ação com muita adrenalina'),
    ('RPG', 'Jogos de interpretação de papéis'),
    ('Estratégia', 'Jogos que exigem estratégia'),
    ('Esportes', 'Jogos de esportes virtuais'),
    ('Aventura', 'Jogos de aventura e exploração');

INSERT into genero_jogos_perfil (id_perfil, id_genero_jogos, visivel) VALUES
                                                                          (1, 1,1),
                                                                          (1, 2,1),
                                                                          (2, 2,1),
                                                                          (2, 3,1),
                                                                          (2, 1,1),
                                                                          (5, 4,1),
                                                                          (5, 5,1),
                                                                          (3, 1,1),
                                                                          (3, 4,1),
                                                                          (4, 4,1),
                                                                          (4, 3,1);

INSERT into interesse_perfil (id_perfil, id_interesses, visivel) VALUES
                                                                     (1, 1,1),
                                                                     (1, 2,1),
                                                                     (2, 2,1),
                                                                     (2, 3,1),
                                                                     (2, 1,1),
                                                                     (5, 4,1),
                                                                     (5, 5,1),
                                                                     (3, 1,1),
                                                                     (3, 4,1),
                                                                     (4, 4,1),
                                                                     (4, 3,1);


-- Inserções para a tabela 'plano'
INSERT INTO plano (nome, descricao, preco) VALUES
    ('Plano A', 'Descrição do Plano A', 19.99);

INSERT INTO plano (nome, descricao, preco) VALUES
    ('Plano B', 'Descrição do Plano B', 29.99);

insert into console values 
(null,'PS4', null),
(null,'PS5', null),
(null,'Nintendo Switch', null),
(null,'PC', null),
(null,'Mobile', null);