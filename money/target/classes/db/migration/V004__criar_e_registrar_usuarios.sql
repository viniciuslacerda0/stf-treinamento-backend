CREATE TABLE usuario(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (nome, email, senha) values ("admin fakezao", "adminfake@adminfake.com", "$2a$10$oWlseSHzyM7bkpzmwlYV4.Bw5S4S9B/aQRUDd2iMHOo5U9HlooVb2
");
