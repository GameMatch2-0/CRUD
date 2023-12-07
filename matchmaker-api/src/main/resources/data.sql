INSERT INTO usuario (id_usuario, nome, sobrenome, email, celular, senha, dt_nascimento, identidade_genero, dt_cadastro)
VALUES
    ('1', 'João', 'Silva', 'joao@example.com', '987654321', 'senha123', '1990-01-01', 'Masculino', NOW()),
    ('2', 'Maria', 'Santos', 'maria@example.com', '987654322', 'senha456', '1985-05-15', 'Feminino', NOW()),
    ('3', 'Carlos', 'Oliveira', 'carlos@example.com', '987654323', 'senha789', '1995-08-20', 'Masculino', NOW()),
    ('4', 'Ana', 'Costa', 'ana@example.com', '987654324', 'senhaabc', '1988-11-10', 'Feminino', NOW()),
    ('5', 'Pedro', 'Ribeiro', 'pedro@example.com', '987654325', 'senhaxyz', '1992-03-25', 'Masculino', NOW());

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