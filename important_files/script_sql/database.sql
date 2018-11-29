--criar o banco de dados backend_test
CREATE DATABASE backend_test;

--criar as sequencias e tabelas
CREATE SEQUENCE sq_company_address;
CREATE TABLE tb_company_address (
    
    id INT DEFAULT nextval('sq_company_address') NOT NULL,
    tx_street VARCHAR(100) NOT NULL,
    it_number INT NOT NULL,
    tx_neighborhood VARCHAR(100),
    tx_complement VARCHAR(100),
    tx_cep VARCHAR(15) NOT NULL,    
    tx_city VARCHAR(100) NOT NULL,
    CONSTRAINT pk_company_address PRIMARY KEY (id)
    
);

CREATE SEQUENCE sq_coin;
CREATE TABLE tb_coin (
    
    id INT DEFAULT nextval('sq_coin') NOT NULL,
    tx_code VARCHAR(5) NOT NULL,
    tx_name VARCHAR(50) NOT NULL,
    rl_bid FLOAT NOT NULL,
    rl_ask FLOAT NOT NULL,
    dt_last_quote TIMESTAMP NOT NULL,
    CONSTRAINT pk_coin PRIMARY KEY (id),
    CONSTRAINT rk_coin UNIQUE (tx_code)
    
);

CREATE SEQUENCE sq_company;
CREATE TABLE tb_company (
    
    id INT DEFAULT nextval('sq_company') NOT NULL,
    tx_cnpj VARCHAR(50) NOT NULL,
    tx_name VARCHAR(100) NOT NULL,
    tx_email VARCHAR(200),
    id_company_address INT NOT NULL,
    id_coin INT NOT NULL,
    CONSTRAINT pk_company PRIMARY KEY (id),
    CONSTRAINT rk_company UNIQUE (tx_cnpj),
    CONSTRAINT fk_company_1 FOREIGN KEY (id_company_address) 
		REFERENCES tb_company_address (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_company_2 FOREIGN KEY (id_coin) 
		REFERENCES tb_coin (id) ON UPDATE RESTRICT ON DELETE RESTRICT              
    
);

--insere os tipo de moedas de trabalho
INSERT INTO tb_coin(tx_code, tx_name, rl_bid, rl_ask, dt_last_quote)
VALUES ('USD', 'Dólar', 3.8747, 3.8767, now());

INSERT INTO tb_coin(tx_code, tx_name, rl_bid, rl_ask, dt_last_quote)
VALUES ('EUR', 'Euro', 4.3614, 4.364, now());

INSERT INTO tb_coin(tx_code, tx_name, rl_bid, rl_ask, dt_last_quote)
VALUES ('ARS', 'Peso Argentino', 0.1001, 0.1003, now());

INSERT INTO tb_coin(tx_code, tx_name, rl_bid, rl_ask, dt_last_quote)
VALUES ('GBP', 'Libra Esterlina', 4.9396, 4.9437, now());

INSERT INTO tb_coin(tx_code, tx_name, rl_bid, rl_ask, dt_last_quote)
VALUES ('BTC', 'Bitcoin', 15740, 15788.99999, now());