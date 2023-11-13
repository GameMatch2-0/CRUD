CREATE DATABASE IF NOT EXISTS match_making;
USE match_making;

CREATE TABLE IF NOT EXISTS usuario (
  id_usuario INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45) ,
  sobrenome VARCHAR(100) ,
  email VARCHAR(100) UNIQUE ,
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
  id_usuario INT PRIMARY KEY,
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
  CONSTRAINT fk_perfil_usuario
    FOREIGN KEY perfil(id_usuario)
    REFERENCES usuario(id_usuario),
  CONSTRAINT fk_perfil_plano
    FOREIGN KEY perfil(id_plano)
    REFERENCES plano(id_plano));

CREATE UNIQUE INDEX perfil_id_usuario_UNIQUE ON perfil(id_usuario);

CREATE TABLE IF NOT EXISTS avaliacao (
  id_usuario_avaliado INT,
  id_usuario_avaliador INT,
  avaliacao FLOAT ,
  descricao VARCHAR(255) ,
  nota_anterior FLOAT ,
  nota_nova FLOAT ,
  horario DATETIME ,
  ativa TINYINT NOT NULL DEFAULT TRUE,
  PRIMARY KEY(id_usuario_avaliado, id_usuario_avaliador),
  CONSTRAINT fk_avaliacao_perfil1
    FOREIGN KEY avaliacao(id_usuario_avaliado)
    REFERENCES perfil(id_usuario),
  CONSTRAINT fk_avaliacao_perfil2
    FOREIGN KEY avaliacao(id_usuario_avaliador)
    REFERENCES perfil(id_usuario));
    
CREATE UNIQUE INDEX avaliacao_id_usuario1_UNIQUE ON avaliacao(id_usuario_avaliado);

CREATE UNIQUE INDEX avaliacao_id_usuario2_UNIQUE ON avaliacao(id_usuario_avaliador);

CREATE TABLE IF NOT EXISTS tipo_midia (
  id_tipo INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45),
  extensao CHAR(4));

CREATE TABLE IF NOT EXISTS midia (
  id_midia INT PRIMARY KEY AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  id_tipo INT NOT NULL,
  link VARCHAR(255),
  visivel TINYINT DEFAULT TRUE,
  CONSTRAINT fk_midia_perfil
    FOREIGN KEY midia(id_usuario)
    REFERENCES perfil(id_usuario),
  CONSTRAINT fk_midia_tipo_midia
    FOREIGN KEY midia(id_tipo)
    REFERENCES tipo_midia(id_tipo));

CREATE INDEX fk_midia_perfil_idx ON midia(id_usuario);

CREATE INDEX fk_midia_tipo_midia_idx ON midia(id_tipo);

CREATE TABLE IF NOT EXISTS curtidas_log (
  horario DATETIME,
  id_usuario1 INT ,
  id_midia INT ,
  id_usuario2 INT ,
  PRIMARY KEY(horario, id_usuario1),
  CONSTRAINT fk_curtidas_log_perfil1
    FOREIGN KEY curtidas_log(id_usuario1)
    REFERENCES perfil(id_usuario),
  CONSTRAINT fk_curtidas_log_midia1
    FOREIGN KEY curtidas_log(id_midia)
    REFERENCES midia(id_midia),
  CONSTRAINT fk_curtidas_log_perfil2
    FOREIGN KEY curtidas_log(id_usuario2)
    REFERENCES perfil(id_usuario));

CREATE INDEX fk_curtidas_log_perfil1_idx ON curtidas_log(id_usuario1);

CREATE INDEX fk_curtidas_log_midia1_idx ON curtidas_log(id_midia);

CREATE INDEX fk_curtidas_log_perfil2_idx ON curtidas_log(id_usuario2);

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
  id_usuario INT ,
  is_visivel TINYINT ,
  PRIMARY KEY(id_console, id_usuario),
  CONSTRAINT fk_console_perfil
    FOREIGN KEY console_perfil(id_console)
    REFERENCES console(id_console),
  CONSTRAINT fk_console_perfil_perfil
    FOREIGN KEY console_perfil(id_usuario)
    REFERENCES perfil(id_usuario));

CREATE INDEX fk_console_perfil_perfil_idx ON console_perfil(id_console);

CREATE INDEX fk_console_perfil_console_idx ON console_perfil(id_usuario);

CREATE TABLE IF NOT EXISTS interesse (
  id_interesses INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45) NULL,
  descricao VARCHAR(100) NULL);

CREATE TABLE IF NOT EXISTS genero_jogos (
  id_genero_jogos INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45),
  descricao VARCHAR(100));

CREATE TABLE IF NOT EXISTS interesse_perfil (
  id_usuario INT ,
  id_interesses INT,
  is_visivel TINYINT DEFAULT TRUE,
  PRIMARY KEY(id_usuario, id_interesses),
  CONSTRAINT fk_interesse_perfil
    FOREIGN KEY interesse_perfil(id_usuario)
    REFERENCES perfil(id_usuario),
  CONSTRAINT fk_interesse_perfil_interesses
    FOREIGN KEY interesse_perfil(id_interesses)
    REFERENCES interesse(id_interesses));

CREATE INDEX fk_interesse_perfil_interesse_idx ON interesse_perfil(id_interesses);

CREATE INDEX fk_interesse_perfil_perfil_idx ON interesse_perfil(id_usuario);

CREATE TABLE IF NOT EXISTS genero_jogos_perfil (
  id_usuario INT,
  id_genero_jogos INT,
  is_visivel TINYINT DEFAULT TRUE,
  PRIMARY KEY(id_usuario, id_genero_jogos),
  CONSTRAINT fk_genero_jogos_perfil
    FOREIGN KEY genero_jogos_perfil(id_usuario)
    REFERENCES perfil(id_usuario),
  CONSTRAINT fk_genero_jogos_perfil_genero_jogos
    FOREIGN KEY genero_jogos_perfil(id_genero_jogos)
    REFERENCES genero_jogos(id_genero_jogos));

CREATE INDEX fk_genero_jogos_perfil_genero_jogos_idx ON genero_jogos_perfil(id_genero_jogos);

CREATE INDEX fk_genero_jogos_perfil1_idx ON genero_jogos_perfil(id_usuario);

CREATE TABLE IF NOT EXISTS conversa (
  id_conversa INT AUTO_INCREMENT,
  id_usuario1 INT ,
  id_usuario2 INT ,
  notificacoes INT ,
  alerta_notificacao TINYINT DEFAULT TRUE,
  PRIMARY KEY(id_conversa, id_usuario1, id_usuario2),
  CONSTRAINT fk_conversa_perfil1
    FOREIGN KEY conversa(id_usuario1)
    REFERENCES perfil(id_usuario),
  CONSTRAINT fk_conversa_perfil2
    FOREIGN KEY conversa(id_usuario2)
    REFERENCES perfil(id_usuario));

CREATE INDEX fk_conversa_perfil1_idx ON conversa(id_usuario1);

CREATE INDEX fk_conversa_perfil2_idx ON conversa(id_usuario2);

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
    REFERENCES perfil(id_usuario));

CREATE INDEX fk_mensagem_conversa_idx ON mensagem(id_conversa);

CREATE INDEX fk_mensagem_perfil_idx ON mensagem(relator);

CREATE TABLE IF NOT EXISTS usuarios_priorizados (
  id_priorizacao INT AUTO_INCREMENT,
  id_usuario_priorizado INT,
  id_usuario_fila INT,
  is_valido TINYINT DEFAULT FALSE,
  PRIMARY KEY(id_priorizacao, id_usuario_priorizado, id_usuario_fila),
  CONSTRAINT fk_priorizacao_perfil1
    FOREIGN KEY usuarios_priorizados(id_usuario_priorizado)
    REFERENCES perfil(id_usuario),
  CONSTRAINT fk_priorizacao_perfil2
    FOREIGN KEY usuarios_priorizados(id_usuario_fila)
    REFERENCES perfil(id_usuario));

DELIMITER $$
CREATE PROCEDURE SP_curtir_usuario(
  IN var_id_usuario1 INT,
  IN var_id_usuario2 INT,
  IN var_horario DATETIME
)
BEGIN
  DECLARE total INT;

  INSERT INTO curtidas_log (horario, id_usuario1, id_usuario2) 
    VALUES (var_horario, var_id_usuario1, var_id_usuario2);
  
  SELECT COUNT(*) 
    INTO total 
    FROM curtidas_log AS c 
    WHERE c.id_usuario1 = var_id_usuario2 AND c.id_usuario2 = var_id_usuario1;   
    
  IF total >= 1 THEN
    INSERT INTO conversa (id_usuario1, id_usuario2) VALUES (var_id_usuario1, var_id_usuario2);
  ELSE
    INSERT INTO usuarios_priorizados (id_usuario_priorizado, id_usuario_fila) VALUES (var_id_usuario1, var_id_usuario2);
  END IF;
END $$
DELIMITER ;
