CREATE TABLE pessoa(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(30),
    bairro VARCHAR(30),
    cep VARCHAR(30),
    cidade VARCHAR(30),
    numero VARCHAR(10),
    complemento VARCHAR(30),
    ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome,ativo) values("João", 1);
INSERT INTO pessoa (nome,ativo) values("Maria", 0);
INSERT INTO pessoa (nome,ativo) values("Pablo do Arrocha", 0);
INSERT INTO pessoa (nome,ativo) values("Zé da paçoca",1);
INSERT INTO pessoa (nome,logradouro, bairro, cep, cidade, numero, complemento,ativo) values("Wesley Safadão","Ali na esquina", "Centro", "58400000", "Campina Grande","na rua", 12, 0);
INSERT INTO pessoa (nome,logradouro, bairro, cep, cidade, numero,ativo) values("Rihanna","barzin", "Cruzeiro", "58415500", "Campina Grande", 219, 1);
INSERT INTO pessoa (nome,logradouro, bairro, cep, cidade, numero,ativo) values("Messi","estacionamento", "Catolé", "5840030", "Campina Grande", 1231, 0);