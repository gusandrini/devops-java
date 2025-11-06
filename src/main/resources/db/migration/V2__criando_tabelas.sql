-- ================================
-- SCRIPT DE CRIAÇÃO DAS TABELAS
-- Projeto Mottu - SQL Server
-- ================================

CREATE TABLE t_cm_pais (
    id_pais BIGINT IDENTITY(1,1) PRIMARY KEY,
    nm_pais VARCHAR(50) NOT NULL
);

CREATE TABLE t_cm_estado (
    id_estado BIGINT IDENTITY(1,1) PRIMARY KEY,
    nm_estado VARCHAR(2) NOT NULL,
    id_pais BIGINT NOT NULL,
    CONSTRAINT fk_estado_pais FOREIGN KEY (id_pais)
        REFERENCES t_cm_pais(id_pais)
);

CREATE TABLE t_cm_cidade (
    id_cidade BIGINT IDENTITY(1,1) PRIMARY KEY,
    nm_cidade VARCHAR(50) NOT NULL,
    id_estado BIGINT NOT NULL,
    CONSTRAINT fk_cidade_estado FOREIGN KEY (id_estado)
        REFERENCES t_cm_estado(id_estado)
);

CREATE TABLE t_cm_bairro (
    id_bairro BIGINT IDENTITY(1,1) PRIMARY KEY,
    nm_bairro VARCHAR(100) NOT NULL,
    id_cidade BIGINT NOT NULL,
    CONSTRAINT fk_bairro_cidade FOREIGN KEY (id_cidade)
        REFERENCES t_cm_cidade(id_cidade)
);

CREATE TABLE t_cm_logradouro (
    id_logradouro BIGINT IDENTITY(1,1) PRIMARY KEY,
    nm_logradouro VARCHAR(100) NOT NULL,
    nr_logradouro INT NOT NULL,
    nm_complemento VARCHAR(100),
    id_bairro BIGINT NOT NULL,
    CONSTRAINT fk_logradouro_bairro FOREIGN KEY (id_bairro)
        REFERENCES t_cm_bairro(id_bairro)
);

CREATE TABLE t_cm_filial (
    id_filial BIGINT IDENTITY(1,1) PRIMARY KEY,
    nm_filial VARCHAR(100) NOT NULL,
    id_logradouro BIGINT NOT NULL,
    CONSTRAINT fk_filial_logradouro FOREIGN KEY (id_logradouro)
        REFERENCES t_cm_logradouro(id_logradouro)
);

CREATE TABLE t_cm_departamento (
    id_departamento BIGINT IDENTITY(1,1) PRIMARY KEY,
    nm_departamento VARCHAR(50) NOT NULL,
    ds_departamento VARCHAR(250) NOT NULL
);

CREATE TABLE t_cm_filial_departamento (
    id_filial_departamento BIGINT IDENTITY(1,1) PRIMARY KEY,
    id_filial BIGINT NOT NULL,
    id_departamento BIGINT NOT NULL,
    dt_entrada DATE NOT NULL,
    dt_saida DATE,
    CONSTRAINT fk_filial_departamento_filial FOREIGN KEY (id_filial)
        REFERENCES t_cm_filial(id_filial),
    CONSTRAINT fk_filial_departamento_departamento FOREIGN KEY (id_departamento)
        REFERENCES t_cm_departamento(id_departamento)
);

CREATE TABLE t_cm_cliente (
    id_cliente BIGINT IDENTITY(1,1) PRIMARY KEY,
    nm_cliente VARCHAR(100) NOT NULL,
    nm_email VARCHAR(100) NOT NULL,
    nr_cpf VARCHAR(255) NOT NULL,
    id_logradouro BIGINT NOT NULL,
    CONSTRAINT fk_cliente_logradouro FOREIGN KEY (id_logradouro)
        REFERENCES t_cm_logradouro(id_logradouro)
);

CREATE TABLE t_cm_telefone (
    id_telefone BIGINT IDENTITY(1,1) PRIMARY KEY,
    nr_ddi VARCHAR(4) NOT NULL,
    nr_ddd VARCHAR(5) NOT NULL,
    nr_telefone VARCHAR(15) NOT NULL,
    id_cliente BIGINT NOT NULL,
    CONSTRAINT fk_telefone_cliente FOREIGN KEY (id_cliente)
        REFERENCES t_cm_cliente(id_cliente)
);

CREATE TABLE t_cm_funcao (
    id_funcao BIGINT IDENTITY(1,1) PRIMARY KEY,
    nm_funcao VARCHAR(255) NOT NULL CHECK (nm_funcao IN ('ADMIN','OPERACIONAL','ATENDIMENTO','ANALISTA'))
);

CREATE TABLE t_cm_funcionario (
    id_funcionario BIGINT IDENTITY(1,1) PRIMARY KEY,
    nm_funcionario VARCHAR(100) NOT NULL,
    nm_email_corporativo VARCHAR(100) NOT NULL,
    nm_senha VARCHAR(225) NOT NULL,
    nm_cargo VARCHAR(50) NOT NULL,
    id_filial BIGINT NOT NULL,
    CONSTRAINT fk_funcionario_filial FOREIGN KEY (id_filial)
        REFERENCES t_cm_filial(id_filial)
);

CREATE TABLE t_cm_funcionario_funcao (
    id_funcionario BIGINT NOT NULL,
    id_funcao BIGINT NOT NULL,
    PRIMARY KEY (id_funcao, id_funcionario),
    CONSTRAINT fk_funcionario_funcao_funcionario FOREIGN KEY (id_funcionario)
        REFERENCES t_cm_funcionario(id_funcionario),
    CONSTRAINT fk_funcionario_funcao_funcao FOREIGN KEY (id_funcao)
        REFERENCES t_cm_funcao(id_funcao)
);

CREATE TABLE t_cm_modelo (
    id_modelo BIGINT PRIMARY KEY,
    nm_modelo VARCHAR(255)
);

CREATE TABLE t_cm_moto (
    id_moto BIGINT IDENTITY(1,1) PRIMARY KEY,
    nm_placa VARCHAR(10),
    km_rodado FLOAT,
    st_moto VARCHAR(255) NOT NULL CHECK (st_moto IN ('DISPONIVEL','LOCADA','RESERVADA','INATIVA')),
    id_cliente BIGINT NOT NULL,
    id_filial_departamento BIGINT NOT NULL,
    id_modelo BIGINT NOT NULL,
    CONSTRAINT fk_moto_cliente FOREIGN KEY (id_cliente)
        REFERENCES t_cm_cliente(id_cliente),
    CONSTRAINT fk_moto_filial_departamento FOREIGN KEY (id_filial_departamento)
        REFERENCES t_cm_filial_departamento(id_filial_departamento),
    CONSTRAINT fk_moto_modelo FOREIGN KEY (id_modelo)
        REFERENCES t_cm_modelo(id_modelo)
);

CREATE TABLE t_cm_manutencao (
    id_manutencao BIGINT IDENTITY(1,1) PRIMARY KEY,
    id_moto BIGINT NOT NULL,
    dt_entrada DATE NOT NULL,
    dt_saida DATE,
    ds_manutencao VARCHAR(300) NOT NULL,
    CONSTRAINT fk_manutencao_moto FOREIGN KEY (id_moto)
        REFERENCES t_cm_moto(id_moto)
);
