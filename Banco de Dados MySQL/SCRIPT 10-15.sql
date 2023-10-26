CREATE DATABASE IF NOT EXISTS MatchMaking;
USE MatchMaking;

CREATE TABLE IF NOT EXISTS Usuario (
  idUsuario INT PRIMARY KEY auto_increment,
  nome VARCHAR(45) ,
  sobrenome VARCHAR(100) ,
  email VARCHAR(100) unique ,
  celular CHAR(11) ,
  senha VARCHAR(45) ,
  dtnascimento DATE ,
  identidadeGenero VARCHAR(45) ,
  dtCadastro DATETIME ,
  isDeleted TINYINT default false);

CREATE UNIQUE INDEX idusuario_UNIQUE on Usuario(idUsuario);

CREATE TABLE IF NOT EXISTS Plano (
  idPlano INT PRIMARY KEY auto_increment,
  nome VARCHAR(45) ,
  descricao VARCHAR(255) ,
  preco FLOAT);

CREATE TABLE IF NOT EXISTS Perfil(
  idUsuario INT primary key,
  username VARCHAR(45) ,
  biografia VARCHAR(255) ,
  nota FLOAT ,
  orientacaoSexual VARCHAR(45) ,
  procuraAmizade TINYINT ,
  procuraNamoro TINYINT ,
  procuraPlayer2 TINYINT ,
  isPremium TINYINT default false,
  idPlano INT ,
  isDeleted TINYINT default false,
  CONSTRAINT fk_perfil_usuario
    FOREIGN KEY Perfil(idUsuario)
    REFERENCES Usuario(idUsuario),
  CONSTRAINT fk_perfil_plano
    FOREIGN KEY Perfil(idPlano)
    REFERENCES Plano(idPlano));

CREATE UNIQUE INDEX perfil_idusuario_UNIQUE on Perfil(idUsuario);


CREATE TABLE IF NOT EXISTS Avaliacao (
  idUsuarioAvaliado INT,
  idUsuarioAvaliador INT,
  avaliacao FLOAT ,
  descricao VARCHAR(255) ,
  notaAnterior FLOAT ,
  notaNova FLOAT ,
  horario DATETIME ,
  ativa TINYINT NOT NULL default true,
  primary key(idUsuarioAvaliado, idUsuarioAvaliador),
  CONSTRAINT fk_alaviacao_perfil1
    FOREIGN KEY Avaliacao(idUsuarioAvaliado)
    REFERENCES Perfil(idUsuario),
  CONSTRAINT fk_avaliacao_perfil2
    FOREIGN KEY Avaliacao(idUsuarioAvaliador)
    REFERENCES Perfil(idUsuario));
    
CREATE UNIQUE INDEX avaliacao_idusuario1_UNIQUE on Avaliacao(idUsuarioAvaliado);

CREATE UNIQUE INDEX avaliacao_idusuario2_UNIQUE on Avaliacao(idUsuarioAvaliador);


CREATE TABLE IF NOT EXISTS TipoMidia (
  idTipo INT primary key auto_increment,
  nome VARCHAR(45),
  extensao char(4));

CREATE TABLE IF NOT EXISTS Midia (
  idMidia INT primary key auto_increment,
  idUsuario INT NOT NULL,
  idTipo INT NOT NULL,
  link VARCHAR(255),
  visivel TINYINT default true,
  CONSTRAINT fk_Midia_perfil
    FOREIGN KEY Midia(idUsuario)
    REFERENCES Perfil(idUsuario),
  CONSTRAINT fk_Midia_tipoMidia
    FOREIGN KEY Midia(idTipo)
    REFERENCES TipoMidia(idTipo));

CREATE INDEX fk_Midia_perfil_idx ON Midia(idUsuario);

CREATE INDEX fk_Midia_tipoMidia_idx ON Midia(idTipo);

CREATE TABLE IF NOT EXISTS curtidasLog (
  horario DATETIME,
  idUsuario1 INT ,
  idMidia INT ,
  idUsuario2 INT ,
  primary key(horario, idUsuario1),
  CONSTRAINT fk_curtidasLog_perfil1
    FOREIGN KEY curtidasLog(idUsuario1)
    REFERENCES Perfil(idUsuario),
  CONSTRAINT fk_curtidasLog_Midia1
    FOREIGN KEY curtidasLog(idMidia)
    REFERENCES Midia(idMidia),
  CONSTRAINT fk_curtidasLog_perfil2
    FOREIGN KEY curtidasLog(idUsuario2)
    REFERENCES Perfil(idUsuario));

CREATE INDEX fk_curtidasLog_perfil1_idx ON curtidasLog(idUsuario1);

CREATE INDEX fk_curtidasLog_Midia1_idx ON curtidasLog(idMidia);

CREATE INDEX fk_curtidasLog_perfil2_idx ON curtidasLog(idUsuario2);


CREATE TABLE IF NOT EXISTS Console (
  idConsole INT primary key auto_increment,
  nome VARCHAR(60) ,
  idImagem INT ,
  idTipoMidia INT ,
  CONSTRAINT fk_Console_Midia1
    FOREIGN KEY Console(idImagem)
    REFERENCES Midia(idMidia),
    CONSTRAINT fk_Console_Midia2
    FOREIGN KEY Console(idTipoMidia)
    REFERENCES Midia(idTipo));

CREATE INDEX fk_Console_Midia1_idx ON Console(idImagem);

CREATE INDEX fk_Console_Midia2_idx ON Console(idTipoMidia);


CREATE TABLE IF NOT EXISTS ConsolePerfil (
  idConsole INT auto_increment,
  idUsuario INT ,
  isVisivel TINYINT ,
  primary key(idConsole, idUsuario),
  CONSTRAINT fk_ConsolePerfil_Console
    FOREIGN KEY ConsolePerfil(idConsole)
    REFERENCES Console(idConsole),
  CONSTRAINT fk_ConsolePerfil_perfil
    FOREIGN KEY ConsolePerfil(idUsuario)
    REFERENCES Perfil(idUsuario));

CREATE INDEX fk_ConsolePerfil_perfil_idx ON ConsolePerfil(idConsole);

CREATE INDEX fk_ConsolePerfil_Console_idx ON ConsolePerfil(idUsuario);


CREATE TABLE IF NOT EXISTS Interesse (
  idInteresses INT primary key auto_increment,
  nome VARCHAR(45) NULL,
  descricao VARCHAR(100) NULL);


CREATE TABLE IF NOT EXISTS GeneroJogos (
  idGeneroJogos INT primary key auto_increment,
  nome VARCHAR(45),
  descricao VARCHAR(100));


CREATE TABLE IF NOT EXISTS InteressePerfil (
  idUsuario INT ,
  idInteresses INT,
  isVisivel TINYINT default true,
  primary key(idUsuario, idInteresses),
  CONSTRAINT fk_InteressesPerfil_perfil
    FOREIGN KEY InteressePerfil(idUsuario)
    REFERENCES Perfil(idUsuario),
  CONSTRAINT fk_InteressesPerfil_Interesses
    FOREIGN KEY InteressePerfil(idInteresses)
    REFERENCES Interesse(idInteresses));

CREATE INDEX fk_InteressePerfil_Interesse_idx ON InteressePerfil(idInteresses);

CREATE INDEX fk_InteressePerfil_perfil_idx ON InteressePerfil(idUsuario);


CREATE TABLE IF NOT EXISTS GeneroJogosPerfil (
  idUsuario int,
  idGeneroJogos INT,
  isVisivel TINYINT default true,
  primary key(idUsuario, idGeneroJogos),
  CONSTRAINT fk_GeneroJogosPerfil_perfil
    FOREIGN KEY GeneroJogosPerfil(idUsuario)
    REFERENCES Perfil(idUsuario),
  CONSTRAINT fk_GeneroJogosPerfil_GeneroJogos
    FOREIGN KEY GeneroJogosPerfil(idGeneroJogos)
    REFERENCES GeneroJogos(idGeneroJogos));

CREATE INDEX fk_GeneroJogosPerfil_GeneroJogos_idx ON GeneroJogosPerfil(idGeneroJogos);

CREATE INDEX fk_GeneroJogosPerfil_perfil1_idx ON GeneroJogosPerfil(idUsuario);

CREATE TABLE IF NOT EXISTS Conversa (
  idConversa INT auto_increment,
  idUsuario1 INT ,
  idUsuario2 INT ,
  notificacoes INT ,
  alertaNotificacao TINYINT default true,
  primary key(idConversa, idUsuario1, idUsuario2),
  CONSTRAINT fk_Conversa_perfil1
    FOREIGN KEY Conversa(idUsuario1)
    REFERENCES Perfil(idUsuario),
  CONSTRAINT fk_Conversa_perfil2
    FOREIGN KEY Conversa(idUsuario2)
    REFERENCES Perfil(idUsuario));

CREATE INDEX fk_Conversa_perfil1_idx ON Conversa(idUsuario1);

CREATE INDEX fk_Conversa_perfil2_idx ON Conversa(idUsuario2);

CREATE TABLE IF NOT EXISTS Mensagem (
  idMensagem BIGINT auto_increment,
  idConversa INT ,
  horario DATETIME ,
  relator INT ,
  corpoMensagem VARCHAR(255),
  isVisualizada TINYINT default false,
  primary key(idMensagem, idconversa, horario, relator),
  CONSTRAINT fk_Mensagem_Conversa
    FOREIGN KEY Mensagem(idConversa)
    REFERENCES Conversa(idConversa),
  CONSTRAINT fk_Mensagem_perfil
    FOREIGN KEY Mensagem(relator)
    REFERENCES Perfil(idUsuario));

CREATE INDEX fk_Mensagem_Conversa_idx ON Mensagem(idConversa);

CREATE INDEX fk_Mensagem_perfil_idx ON Mensagem(Relator);
