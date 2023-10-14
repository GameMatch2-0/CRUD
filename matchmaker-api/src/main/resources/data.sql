-- Inserindo valores de exemplo na tabela Usuario
INSERT INTO Usuario (id, nome, apelido, orientacao_sexual, dt_nascimento, email, contato, senha, dt_cadastro, deleted)
VALUES
    ('93d22433-6cb2-453e-b8af-7ddaf302f6e2', 'João Silva', 'João', 'Heterossexual', '1990-05-15', 'joao@email.com', '+555123456789', 'senha123', '2023-08-01 10:15:00', false),
    ('79fe701a-727d-4130-9245-765ccc8f677d', 'Maria Souza', 'Maria', 'LGBTQ+', '1985-03-22', 'maria@email.com', '+555987654321', 'senha456', '2023-08-02 09:30:00', false),
    ('52bd147e-dfad-4c0d-bee4-bb1e49664d27', 'Carlos Santos', 'Carlos', 'Heterossexual', '1998-11-08', 'carlos@email.com', '+555777888999', 'senha789', '2023-08-03 14:20:00',false);
