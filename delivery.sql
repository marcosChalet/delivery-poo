CREATE DATABASE Restaurante;
USE Restaurante;

CREATE TABLE pessoa (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    rg VARCHAR(50) NOT NULL,
    cpf VARCHAR(50) NOT NULL,
    telefone VARCHAR(50) NOT NULL
);

CREATE TABLE endereco (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
	rua VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    cep VARCHAR(50) NOT NULL,
    fk_pessoa INT NOT NULL,
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(id) ON DELETE CASCADE
);

CREATE TABLE categorias (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome_categoria VARCHAR(30) NOT NULL
);

CREATE TABLE produtos (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	descricao VARCHAR(30) NOT NULL,
    preco DOUBLE NOT NULL,
    estoque INT NOT NULL,
    fk_categoria INT NOT NULL,
    FOREIGN KEY (fk_categoria) REFERENCES categorias(id)
);

CREATE TABLE administrador (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    reg_funcionario INT NOT NULL,
    funcao VARCHAR(30) NOT NULL,
    fk_pessoa INT NOT NULL,
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(id)
);

CREATE TABLE cliente (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fk_pessoa INT NOT NULL,
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(id)
);

CREATE TABLE compras (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    qtd_produtos INT NOT NULL,
    total DOUBLE NOT NULL,
    status_compra VARCHAR(50) NOT NULL,
    fk_cod_cliente INT NOT NULL,
    fk_endereco_entrega INT NOT NULL,
    FOREIGN KEY (fk_cod_cliente) REFERENCES pessoa (id),
    FOREIGN KEY (fk_endereco_entrega) REFERENCES endereco (id)
);

CREATE TABLE prod_comprados (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL,
    preco DOUBLE NOT NULL,
    qtd INT NOT NULL,
    fk_categoria INT NOT NULL,
    fk_compra INT NOT NULL,
    FOREIGN KEY (fk_categoria) REFERENCES categorias(id),
    FOREIGN KEY (fk_compra) REFERENCES compras(id) ON DELETE CASCADE
);

# Compra de teste
#INSERT INTO compras (id, qtd_produtos, total, status_compra, fk_cod_cliente, fk_endereco_entrega)
#VALUES (DEFAULT, 4, 160, "Finalizada", 1, 1);
#INSERT INTO prod_comprados (id, descricao, preco, qtd, fk_categoria, fk_compra)
#VALUES (DEFAULT, "Carne de sol", 50, 3, 3, 2);
#INSERT INTO prod_comprados (id, descricao, preco, qtd, fk_categoria, fk_compra)
#VALUES (DEFAULT, "Refri 2,5L", 10, 1, 5, 2);


# Usuários cliente para teste de funções
INSERT INTO pessoa (id, nome, email, senha, rg, cpf, telefone) 
VALUES (DEFAULT, "Marcos", "chaletmarcos@gmail.com", "abcdefghij", "123456789-1", "123.123.123-12", "(88) 98888-8888");
INSERT INTO endereco (id, numero, rua, cidade, cep, fk_pessoa) 
VALUES (DEFAULT, 985 ,"Rua Oscar Freire", "São Paulo", "01426-001", LAST_INSERT_ID());


# Usuário admin para teste de funções
INSERT INTO pessoa (id, nome, email, senha, rg, cpf, telefone) 
VALUES (DEFAULT, "Taldo", "taldo@gmail.com", "abcdefghij", "987654321-1", "321.321.321-32",  "(99) 89999-9999");
INSERT INTO endereco (id, numero, rua, cidade, cep, fk_pessoa) 
VALUES (DEFAULT, 985 ,"Rua ABCDE", "Rio de Janeiro", "98765-432", LAST_INSERT_ID());

# Usuário para teste de remoção
INSERT INTO pessoa (id, nome, email, senha, rg, cpf, telefone) 
VALUES (DEFAULT, "Tmp", "tmp@gmail.com", "abcdefghij", "123456789-1", "111.111.111-11", "(88) 91111-1111");
INSERT INTO endereco (id, numero, rua, cidade, cep, fk_pessoa) 
VALUES (DEFAULT, 999 ,"Rua Tmp Oscar Freire", "tmp São Paulo", "01426-001", LAST_INSERT_ID());

# categorias iniciais para teste
INSERT INTO categorias (nome_categoria) VALUES ('Massas');
INSERT INTO categorias (nome_categoria) VALUES ('Petiscos');
INSERT INTO categorias (nome_categoria) VALUES ('Pratos executivos');
INSERT INTO categorias (nome_categoria) VALUES ('Pratos à la carte');
INSERT INTO categorias (nome_categoria) VALUES ('Bebidas');

# produtos iniciais para teste
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (1, 'Pizza quatro queijos', 35.99, 50);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (1, 'Pizza portuguesa', 32.99, 20);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (1, 'Pizza de calabreza', 35.99, 60);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (1, 'Pizza de frango', 33.99, 30);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (2, 'Coxinha de frango', 11.99, 50);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (2, 'Calabresa acebolada', 11.99, 50);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (2, 'Escondidinho de carne de sol', 11.99, 50);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (3, 'Picanha grelhada', 27.99, 50);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (3, 'Carne de sol', 22.99, 50);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (4, 'Filé medalhão', 72.99, 30);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (4, 'Filé a moda', 64.99, 30);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (5, 'Fanta laranja 2Litros', 7.99, 100);
INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (5, 'Coca-Cola 2Litros', 7.99, 100);
