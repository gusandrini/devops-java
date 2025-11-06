-- ==========================================
-- SCRIPT DE LIMPEZA DO BANCO (DROP TABLES)
-- Projeto Mottu - SQL Server
-- ==========================================

-- Tabelas dependentes (nível mais baixo)
IF OBJECT_ID('t_cm_manutencao', 'U') IS NOT NULL DROP TABLE t_cm_manutencao;
IF OBJECT_ID('t_cm_moto', 'U') IS NOT NULL DROP TABLE t_cm_moto;
IF OBJECT_ID('t_cm_modelo', 'U') IS NOT NULL DROP TABLE t_cm_modelo;
IF OBJECT_ID('t_cm_funcionario_funcao', 'U') IS NOT NULL DROP TABLE t_cm_funcionario_funcao;
IF OBJECT_ID('t_cm_funcionario', 'U') IS NOT NULL DROP TABLE t_cm_funcionario;
IF OBJECT_ID('t_cm_funcao', 'U') IS NOT NULL DROP TABLE t_cm_funcao;
IF OBJECT_ID('t_cm_telefone', 'U') IS NOT NULL DROP TABLE t_cm_telefone;
IF OBJECT_ID('t_cm_cliente', 'U') IS NOT NULL DROP TABLE t_cm_cliente;
IF OBJECT_ID('t_cm_filial_departamento', 'U') IS NOT NULL DROP TABLE t_cm_filial_departamento;
IF OBJECT_ID('t_cm_departamento', 'U') IS NOT NULL DROP TABLE t_cm_departamento;
IF OBJECT_ID('t_cm_filial', 'U') IS NOT NULL DROP TABLE t_cm_filial;
IF OBJECT_ID('t_cm_logradouro', 'U') IS NOT NULL DROP TABLE t_cm_logradouro;
IF OBJECT_ID('t_cm_bairro', 'U') IS NOT NULL DROP TABLE t_cm_bairro;
IF OBJECT_ID('t_cm_cidade', 'U') IS NOT NULL DROP TABLE t_cm_cidade;
IF OBJECT_ID('t_cm_estado', 'U') IS NOT NULL DROP TABLE t_cm_estado;
IF OBJECT_ID('t_cm_pais', 'U') IS NOT NULL DROP TABLE t_cm_pais;
