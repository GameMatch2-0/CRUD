CREATE DATABASE IF NOT EXISTS match_making;
USE match_making;

CREATE TABLE IF NOT EXISTS usuario (
  id_usuario VARCHAR(255) PRIMARY KEY,
  nome VARCHAR(45) ,
  sobrenome VARCHAR(100) ,
  email VARCHAR(100),
  celular CHAR(11) ,
  senha VARCHAR(45) ,
  dt_nascimento DATE ,
  identidade_genero VARCHAR(45) ,
  dt_cadastro DATETIME ,
  is_deleted TINYINT DEFAULT FALSE);

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
  is_deleted TINYINT DEFAULT FALSE,
  PRIMARY KEY(id_perfil, id_usuario),
  CONSTRAINT fk_perfil_usuario
    FOREIGN KEY perfil(id_usuario)
    REFERENCES usuario(id_usuario),
  CONSTRAINT fk_perfil_plano
    FOREIGN KEY perfil(id_plano)
    REFERENCES plano(id_plano));

CREATE UNIQUE INDEX perfil_id_usuario_idx ON perfil(id_usuario);

CREATE TABLE IF NOT EXISTS avaliacao (
  id_avaliacao INT,
  id_perfil_avaliado INT,
  id_perfil_avaliador INT,
  avaliacao FLOAT ,
  descricao VARCHAR(255) ,
  nota_anterior FLOAT ,
  nota_nova FLOAT ,
  horario DATETIME ,
  is_ativa TINYINT NOT NULL DEFAULT TRUE,
  PRIMARY KEY(id_avaliacao, id_perfil_avaliado, id_perfil_avaliador),
  CONSTRAINT fk_avaliacao_perfil1
    FOREIGN KEY avaliacao(id_perfil_avaliado)
    REFERENCES perfil(id_perfil),
  CONSTRAINT fk_avaliacao_perfil2
    FOREIGN KEY avaliacao(id_perfil_avaliador)
    REFERENCES perfil(id_perfil));
    
CREATE UNIQUE INDEX avaliacao_id_perfil1_idx ON avaliacao(id_perfil_avaliado);

CREATE UNIQUE INDEX avaliacao_id_perfil2_idx ON avaliacao(id_perfil_avaliador);

CREATE TABLE IF NOT EXISTS tipo_midia (
  id_tipo INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45),
  extensao CHAR(4));

CREATE TABLE IF NOT EXISTS midia (
  id_midia INT PRIMARY KEY AUTO_INCREMENT,
  id_perfil INT NOT NULL,
  id_tipo INT NOT NULL,
  link VARCHAR(255),
  visivel TINYINT DEFAULT TRUE,
  CONSTRAINT fk_midia_perfil
    FOREIGN KEY midia(id_perfil)
    REFERENCES perfil(id_perfil),
  CONSTRAINT fk_midia_tipo_midia
    FOREIGN KEY midia(id_tipo)
    REFERENCES tipo_midia(id_tipo));

CREATE INDEX fk_midia_perfil_idx ON midia(id_perfil);

CREATE INDEX fk_midia_tipo_midia_idx ON midia(id_tipo);

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
  id_tipo_midia INT ,
  CONSTRAINT fk_console_midia1
    FOREIGN KEY console(id_imagem)
    REFERENCES midia(id_midia),
  CONSTRAINT fk_console_midia2
    FOREIGN KEY console(id_tipo_midia)
    REFERENCES midia(id_tipo));

CREATE INDEX fk_console_midia1_idx ON console(id_imagem);

CREATE INDEX fk_console_midia2_idx ON console(id_tipo_midia);

CREATE TABLE IF NOT EXISTS console_perfil (
  id_console INT AUTO_INCREMENT,
  id_perfil INT ,
  is_visivel TINYINT ,
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
  id_interesse INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45),
  descricao VARCHAR(100));

CREATE TABLE IF NOT EXISTS genero_jogos (
  id_genero_jogos INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45),
  descricao VARCHAR(100));

CREATE TABLE IF NOT EXISTS interesse_perfil (
  id_perfil INT ,
  id_interesses INT,
  is_visivel TINYINT DEFAULT TRUE,
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
  is_visivel TINYINT DEFAULT TRUE,
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
  is_visualizada TINYINT DEFAULT FALSE,
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
  is_valido TINYINT DEFAULT TRUE,
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
is_valido TINYINT DEFAULT TRUE,
primary key(id_perfil, id_perfil_descurtido)
);

CREATE INDEX fk_perfis_descurtidos_perfil_idx ON perfis_descurtidos(id_perfil);

CREATE INDEX fk_perfis_descurtidos_perfil_descurtido_idx ON perfis_descurtidos(id_perfis_descurtidos);

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
 WHERE a.id_perfil_avaliado=var_id_perfil_avaliado AND a.is_ativa = 1;
  
UPDATE avaliacao as a set a.nota_nova = var_nota_nova 
WHERE a.id_perfil_avaliado =var_id_perfil_avaliado AND a.id_perfil_avaliador = var_id_perfil_avaliador AND a.nota_nova = null;

UPDATE perfil as p set p.nota = var_nota_nova
where p.id_perfil = var_id_perfil_avaliado;
END $$
DELIMITER ;
