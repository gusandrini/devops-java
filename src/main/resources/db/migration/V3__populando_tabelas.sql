-- V3__populando_tabelas.sql (SQL Server)
SET XACT_ABORT ON;
BEGIN TRAN;

-----------------------------------------
-- Países
-----------------------------------------
INSERT INTO t_cm_pais (nm_pais) SELECT 'Brasil'    WHERE NOT EXISTS (SELECT 1 FROM t_cm_pais WHERE nm_pais='Brasil');
INSERT INTO t_cm_pais (nm_pais) SELECT 'Argentina' WHERE NOT EXISTS (SELECT 1 FROM t_cm_pais WHERE nm_pais='Argentina');
INSERT INTO t_cm_pais (nm_pais) SELECT 'Chile'     WHERE NOT EXISTS (SELECT 1 FROM t_cm_pais WHERE nm_pais='Chile');
INSERT INTO t_cm_pais (nm_pais) SELECT 'Peru'      WHERE NOT EXISTS (SELECT 1 FROM t_cm_pais WHERE nm_pais='Peru');
INSERT INTO t_cm_pais (nm_pais) SELECT 'Uruguai'   WHERE NOT EXISTS (SELECT 1 FROM t_cm_pais WHERE nm_pais='Uruguai');

DECLARE @id_pais_brasil BIGINT = (SELECT id_pais FROM t_cm_pais WHERE nm_pais='Brasil');

-----------------------------------------
-- Estados (todos do Brasil)
-----------------------------------------
INSERT INTO t_cm_estado (nm_estado, id_pais) SELECT 'SP', @id_pais_brasil WHERE NOT EXISTS (SELECT 1 FROM t_cm_estado WHERE nm_estado='SP' AND id_pais=@id_pais_brasil);
INSERT INTO t_cm_estado (nm_estado, id_pais) SELECT 'RJ', @id_pais_brasil WHERE NOT EXISTS (SELECT 1 FROM t_cm_estado WHERE nm_estado='RJ' AND id_pais=@id_pais_brasil);
INSERT INTO t_cm_estado (nm_estado, id_pais) SELECT 'BA', @id_pais_brasil WHERE NOT EXISTS (SELECT 1 FROM t_cm_estado WHERE nm_estado='BA' AND id_pais=@id_pais_brasil);
INSERT INTO t_cm_estado (nm_estado, id_pais) SELECT 'MG', @id_pais_brasil WHERE NOT EXISTS (SELECT 1 FROM t_cm_estado WHERE nm_estado='MG' AND id_pais=@id_pais_brasil);
INSERT INTO t_cm_estado (nm_estado, id_pais) SELECT 'RS', @id_pais_brasil WHERE NOT EXISTS (SELECT 1 FROM t_cm_estado WHERE nm_estado='RS' AND id_pais=@id_pais_brasil);

DECLARE @id_sp BIGINT = (SELECT id_estado FROM t_cm_estado WHERE nm_estado='SP' AND id_pais=@id_pais_brasil);
DECLARE @id_rj BIGINT = (SELECT id_estado FROM t_cm_estado WHERE nm_estado='RJ' AND id_pais=@id_pais_brasil);
DECLARE @id_ba BIGINT = (SELECT id_estado FROM t_cm_estado WHERE nm_estado='BA' AND id_pais=@id_pais_brasil);
DECLARE @id_mg BIGINT = (SELECT id_estado FROM t_cm_estado WHERE nm_estado='MG' AND id_pais=@id_pais_brasil);
DECLARE @id_rs BIGINT = (SELECT id_estado FROM t_cm_estado WHERE nm_estado='RS' AND id_pais=@id_pais_brasil);

-----------------------------------------
-- Cidades
-----------------------------------------
INSERT INTO t_cm_cidade (nm_cidade, id_estado) SELECT N'São Paulo',     @id_sp WHERE NOT EXISTS (SELECT 1 FROM t_cm_cidade WHERE nm_cidade=N'São Paulo'     AND id_estado=@id_sp);
INSERT INTO t_cm_cidade (nm_cidade, id_estado) SELECT N'Rio de Janeiro', @id_rj WHERE NOT EXISTS (SELECT 1 FROM t_cm_cidade WHERE nm_cidade=N'Rio de Janeiro' AND id_estado=@id_rj);
INSERT INTO t_cm_cidade (nm_cidade, id_estado) SELECT N'Salvador',       @id_ba WHERE NOT EXISTS (SELECT 1 FROM t_cm_cidade WHERE nm_cidade=N'Salvador'       AND id_estado=@id_ba);
INSERT INTO t_cm_cidade (nm_cidade, id_estado) SELECT N'Belo Horizonte', @id_mg WHERE NOT EXISTS (SELECT 1 FROM t_cm_cidade WHERE nm_cidade=N'Belo Horizonte' AND id_estado=@id_mg);
INSERT INTO t_cm_cidade (nm_cidade, id_estado) SELECT N'Porto Alegre',   @id_rs WHERE NOT EXISTS (SELECT 1 FROM t_cm_cidade WHERE nm_cidade=N'Porto Alegre'   AND id_estado=@id_rs);

DECLARE @id_sp_cid BIGINT = (SELECT id_cidade FROM t_cm_cidade WHERE nm_cidade=N'São Paulo' AND id_estado=@id_sp);
DECLARE @id_rj_cid BIGINT = (SELECT id_cidade FROM t_cm_cidade WHERE nm_cidade=N'Rio de Janeiro' AND id_estado=@id_rj);
DECLARE @id_salvador BIGINT = (SELECT id_cidade FROM t_cm_cidade WHERE nm_cidade=N'Salvador' AND id_estado=@id_ba);
DECLARE @id_bh BIGINT       = (SELECT id_cidade FROM t_cm_cidade WHERE nm_cidade=N'Belo Horizonte' AND id_estado=@id_mg);
DECLARE @id_poa BIGINT      = (SELECT id_cidade FROM t_cm_cidade WHERE nm_cidade=N'Porto Alegre' AND id_estado=@id_rs);

-----------------------------------------
-- Bairros
-----------------------------------------
INSERT INTO t_cm_bairro (nm_bairro, id_cidade) SELECT N'Centro',    @id_sp_cid WHERE NOT EXISTS (SELECT 1 FROM t_cm_bairro WHERE nm_bairro=N'Centro'    AND id_cidade=@id_sp_cid);
INSERT INTO t_cm_bairro (nm_bairro, id_cidade) SELECT N'Jardins',   @id_sp_cid WHERE NOT EXISTS (SELECT 1 FROM t_cm_bairro WHERE nm_bairro=N'Jardins'   AND id_cidade=@id_sp_cid);
INSERT INTO t_cm_bairro (nm_bairro, id_cidade) SELECT N'Copacabana',@id_rj_cid WHERE NOT EXISTS (SELECT 1 FROM t_cm_bairro WHERE nm_bairro=N'Copacabana' AND id_cidade=@id_rj_cid);
INSERT INTO t_cm_bairro (nm_bairro, id_cidade) SELECT N'Pituba',    @id_salvador WHERE NOT EXISTS (SELECT 1 FROM t_cm_bairro WHERE nm_bairro=N'Pituba'    AND id_cidade=@id_salvador);
INSERT INTO t_cm_bairro (nm_bairro, id_cidade) SELECT N'Savassi',   @id_bh WHERE NOT EXISTS (SELECT 1 FROM t_cm_bairro WHERE nm_bairro=N'Savassi'   AND id_cidade=@id_bh);

DECLARE @id_bairro_centro  BIGINT = (SELECT id_bairro FROM t_cm_bairro WHERE nm_bairro=N'Centro'    AND id_cidade=@id_sp_cid);
DECLARE @id_bairro_jardins BIGINT = (SELECT id_bairro FROM t_cm_bairro WHERE nm_bairro=N'Jardins'   AND id_cidade=@id_sp_cid);
DECLARE @id_bairro_copa    BIGINT = (SELECT id_bairro FROM t_cm_bairro WHERE nm_bairro=N'Copacabana' AND id_cidade=@id_rj_cid);
DECLARE @id_bairro_pituba  BIGINT = (SELECT id_bairro FROM t_cm_bairro WHERE nm_bairro=N'Pituba'    AND id_cidade=@id_salvador);
DECLARE @id_bairro_savassi BIGINT = (SELECT id_bairro FROM t_cm_bairro WHERE nm_bairro=N'Savassi'   AND id_cidade=@id_bh);

-----------------------------------------
-- Logradouros
-----------------------------------------
INSERT INTO t_cm_logradouro (nm_logradouro, nr_logradouro, nm_complemento, id_bairro)
SELECT N'Rua A', 100, N'Apto 1', @id_bairro_centro
WHERE NOT EXISTS (SELECT 1 FROM t_cm_logradouro WHERE nm_logradouro=N'Rua A' AND nr_logradouro=100 AND ISNULL(nm_complemento,'')=N'Apto 1');

INSERT INTO t_cm_logradouro (nm_logradouro, nr_logradouro, nm_complemento, id_bairro)
SELECT N'Av. Paulista', 1500, N'Conj. 5', @id_bairro_jardins
WHERE NOT EXISTS (SELECT 1 FROM t_cm_logradouro WHERE nm_logradouro=N'Av. Paulista' AND nr_logradouro=1500 AND ISNULL(nm_complemento,'')=N'Conj. 5');

INSERT INTO t_cm_logradouro (nm_logradouro, nr_logradouro, nm_complemento, id_bairro)
SELECT N'Rua Atlântica', 200, NULL, @id_bairro_copa
WHERE NOT EXISTS (SELECT 1 FROM t_cm_logradouro WHERE nm_logradouro=N'Rua Atlântica' AND nr_logradouro=200 AND nm_complemento IS NULL);

INSERT INTO t_cm_logradouro (nm_logradouro, nr_logradouro, nm_complemento, id_bairro)
SELECT N'Rua Bahia', 50, N'Casa', @id_bairro_pituba
WHERE NOT EXISTS (SELECT 1 FROM t_cm_logradouro WHERE nm_logradouro=N'Rua Bahia' AND nr_logradouro=50 AND ISNULL(nm_complemento,'')=N'Casa');

INSERT INTO t_cm_logradouro (nm_logradouro, nr_logradouro, nm_complemento, id_bairro)
SELECT N'Av. Cristóvão Colombo', 3000, NULL, @id_bairro_savassi
WHERE NOT EXISTS (SELECT 1 FROM t_cm_logradouro WHERE nm_logradouro=N'Av. Cristóvão Colombo' AND nr_logradouro=3000 AND nm_complemento IS NULL);

DECLARE @id_log_centro   BIGINT = (SELECT id_logradouro FROM t_cm_logradouro WHERE nm_logradouro=N'Rua A' AND nr_logradouro=100 AND ISNULL(nm_complemento,'')=N'Apto 1');
DECLARE @id_log_jardins  BIGINT = (SELECT id_logradouro FROM t_cm_logradouro WHERE nm_logradouro=N'Av. Paulista' AND nr_logradouro=1500 AND ISNULL(nm_complemento,'')=N'Conj. 5');
DECLARE @id_log_copa     BIGINT = (SELECT id_logradouro FROM t_cm_logradouro WHERE nm_logradouro=N'Rua Atlântica' AND nr_logradouro=200 AND nm_complemento IS NULL);
DECLARE @id_log_pituba   BIGINT = (SELECT id_logradouro FROM t_cm_logradouro WHERE nm_logradouro=N'Rua Bahia' AND nr_logradouro=50 AND ISNULL(nm_complemento,'')=N'Casa');
DECLARE @id_log_savassi  BIGINT = (SELECT id_logradouro FROM t_cm_logradouro WHERE nm_logradouro=N'Av. Cristóvão Colombo' AND nr_logradouro=3000 AND nm_complemento IS NULL);

-----------------------------------------
-- Filiais (agora sem chutar ID de logradouro)
-----------------------------------------
INSERT INTO t_cm_filial (nm_filial, id_logradouro) SELECT N'Filial Centro',     @id_log_centro  WHERE NOT EXISTS (SELECT 1 FROM t_cm_filial WHERE nm_filial=N'Filial Centro');
INSERT INTO t_cm_filial (nm_filial, id_logradouro) SELECT N'Filial Jardins',    @id_log_jardins WHERE NOT EXISTS (SELECT 1 FROM t_cm_filial WHERE nm_filial=N'Filial Jardins');
INSERT INTO t_cm_filial (nm_filial, id_logradouro) SELECT N'Filial Copacabana', @id_log_copa    WHERE NOT EXISTS (SELECT 1 FROM t_cm_filial WHERE nm_filial=N'Filial Copacabana');
INSERT INTO t_cm_filial (nm_filial, id_logradouro) SELECT N'Filial Pituba',     @id_log_pituba  WHERE NOT EXISTS (SELECT 1 FROM t_cm_filial WHERE nm_filial=N'Filial Pituba');
INSERT INTO t_cm_filial (nm_filial, id_logradouro) SELECT N'Filial Savassi',    @id_log_savassi WHERE NOT EXISTS (SELECT 1 FROM t_cm_filial WHERE nm_filial=N'Filial Savassi');

DECLARE @id_filial_centro  BIGINT = (SELECT id_filial FROM t_cm_filial WHERE nm_filial=N'Filial Centro');
DECLARE @id_filial_jardins BIGINT = (SELECT id_filial FROM t_cm_filial WHERE nm_filial=N'Filial Jardins');
DECLARE @id_filial_copa    BIGINT = (SELECT id_filial FROM t_cm_filial WHERE nm_filial=N'Filial Copacabana');
DECLARE @id_filial_pituba  BIGINT = (SELECT id_filial FROM t_cm_filial WHERE nm_filial=N'Filial Pituba');
DECLARE @id_filial_savassi BIGINT = (SELECT id_filial FROM t_cm_filial WHERE nm_filial=N'Filial Savassi');

-----------------------------------------
-- Departamentos
-----------------------------------------
INSERT INTO t_cm_departamento (nm_departamento, ds_departamento)
SELECT 'Logística', 'Departamento responsável pela logística da empresa'
WHERE NOT EXISTS (SELECT 1 FROM t_cm_departamento WHERE nm_departamento='Logística');

INSERT INTO t_cm_departamento (nm_departamento, ds_departamento)
SELECT 'RH', 'Departamento de Recursos Humanos'
WHERE NOT EXISTS (SELECT 1 FROM t_cm_departamento WHERE nm_departamento='RH');

INSERT INTO t_cm_departamento (nm_departamento, ds_departamento)
SELECT 'Financeiro', 'Departamento de controle financeiro'
WHERE NOT EXISTS (SELECT 1 FROM t_cm_departamento WHERE nm_departamento='Financeiro');

INSERT INTO t_cm_departamento (nm_departamento, ds_departamento)
SELECT 'TI', 'Departamento de tecnologia da informação'
WHERE NOT EXISTS (SELECT 1 FROM t_cm_departamento WHERE nm_departamento='TI');

INSERT INTO t_cm_departamento (nm_departamento, ds_departamento)
SELECT 'Comercial', 'Departamento de vendas e atendimento'
WHERE NOT EXISTS (SELECT 1 FROM t_cm_departamento WHERE nm_departamento='Comercial');

DECLARE @dep_log BIGINT = (SELECT id_departamento FROM t_cm_departamento WHERE nm_departamento='Logística');
DECLARE @dep_rh  BIGINT = (SELECT id_departamento FROM t_cm_departamento WHERE nm_departamento='RH');
DECLARE @dep_fin BIGINT = (SELECT id_departamento FROM t_cm_departamento WHERE nm_departamento='Financeiro');
DECLARE @dep_ti  BIGINT = (SELECT id_departamento FROM t_cm_departamento WHERE nm_departamento='TI');
DECLARE @dep_com BIGINT = (SELECT id_departamento FROM t_cm_departamento WHERE nm_departamento='Comercial');

-----------------------------------------
-- FilialDepartamento (datas ISO)
-----------------------------------------
INSERT INTO t_cm_filial_departamento (id_filial, id_departamento, dt_entrada, dt_saida)
SELECT @id_filial_centro,  @dep_log, '2023-01-01', NULL
WHERE NOT EXISTS (SELECT 1 FROM t_cm_filial_departamento WHERE id_filial=@id_filial_centro AND id_departamento=@dep_log);

INSERT INTO t_cm_filial_departamento (id_filial, id_departamento, dt_entrada, dt_saida)
SELECT @id_filial_jardins, @dep_rh,  '2023-02-01', NULL
WHERE NOT EXISTS (SELECT 1 FROM t_cm_filial_departamento WHERE id_filial=@id_filial_jardins AND id_departamento=@dep_rh);

INSERT INTO t_cm_filial_departamento (id_filial, id_departamento, dt_entrada, dt_saida)
SELECT @id_filial_copa,    @dep_fin, '2023-03-01', NULL
WHERE NOT EXISTS (SELECT 1 FROM t_cm_filial_departamento WHERE id_filial=@id_filial_copa AND id_departamento=@dep_fin);

INSERT INTO t_cm_filial_departamento (id_filial, id_departamento, dt_entrada, dt_saida)
SELECT @id_filial_pituba,  @dep_ti,  '2023-04-01', NULL
WHERE NOT EXISTS (SELECT 1 FROM t_cm_filial_departamento WHERE id_filial=@id_filial_pituba AND id_departamento=@dep_ti);

INSERT INTO t_cm_filial_departamento (id_filial, id_departamento, dt_entrada, dt_saida)
SELECT @id_filial_savassi, @dep_com, '2023-05-01', NULL
WHERE NOT EXISTS (SELECT 1 FROM t_cm_filial_departamento WHERE id_filial=@id_filial_savassi AND id_departamento=@dep_com);

-----------------------------------------
-- Clientes
-----------------------------------------
INSERT INTO t_cm_cliente (nm_cliente, nr_cpf, nm_email, id_logradouro)
SELECT N'João Silva', '12345678901', 'joao@email.com', @id_log_centro
WHERE NOT EXISTS (SELECT 1 FROM t_cm_cliente WHERE nm_email='joao@email.com');

INSERT INTO t_cm_cliente (nm_cliente, nr_cpf, nm_email, id_logradouro)
SELECT N'Maria Souza', '23456789012', 'maria@email.com', @id_log_jardins
WHERE NOT EXISTS (SELECT 1 FROM t_cm_cliente WHERE nm_email='maria@email.com');

INSERT INTO t_cm_cliente (nm_cliente, nr_cpf, nm_email, id_logradouro)
SELECT N'Carlos Lima', '34567890123', 'carlos@email.com', @id_log_copa
WHERE NOT EXISTS (SELECT 1 FROM t_cm_cliente WHERE nm_email='carlos@email.com');

INSERT INTO t_cm_cliente (nm_cliente, nr_cpf, nm_email, id_logradouro)
SELECT N'Ana Costa', '45678901234', 'ana@email.com', @id_log_pituba
WHERE NOT EXISTS (SELECT 1 FROM t_cm_cliente WHERE nm_email='ana@email.com');

INSERT INTO t_cm_cliente (nm_cliente, nr_cpf, nm_email, id_logradouro)
SELECT N'Lucas Rocha', '56789012345', 'lucas@email.com', @id_log_savassi
WHERE NOT EXISTS (SELECT 1 FROM t_cm_cliente WHERE nm_email='lucas@email.com');

-----------------------------------------
-- Telefones
-----------------------------------------
INSERT INTO t_cm_telefone (nr_telefone, nr_ddi, nr_ddd, id_cliente)
SELECT '912345678', '+55', '11', (SELECT id_cliente FROM t_cm_cliente WHERE nm_email='joao@email.com')
WHERE NOT EXISTS (SELECT 1 FROM t_cm_telefone WHERE nr_telefone='912345678');

INSERT INTO t_cm_telefone (nr_telefone, nr_ddi, nr_ddd, id_cliente)
SELECT '998877665', '+55', '21', (SELECT id_cliente FROM t_cm_cliente WHERE nm_email='maria@email.com')
WHERE NOT EXISTS (SELECT 1 FROM t_cm_telefone WHERE nr_telefone='998877665');

INSERT INTO t_cm_telefone (nr_telefone, nr_ddi, nr_ddd, id_cliente)
SELECT '987654321', '+55', '31', (SELECT id_cliente FROM t_cm_cliente WHERE nm_email='carlos@email.com')
WHERE NOT EXISTS (SELECT 1 FROM t_cm_telefone WHERE nr_telefone='987654321');

INSERT INTO t_cm_telefone (nr_telefone, nr_ddi, nr_ddd, id_cliente)
SELECT '977112233', '+55', '71', (SELECT id_cliente FROM t_cm_cliente WHERE nm_email='ana@email.com')
WHERE NOT EXISTS (SELECT 1 FROM t_cm_telefone WHERE nr_telefone='977112233');

INSERT INTO t_cm_telefone (nr_telefone, nr_ddi, nr_ddd, id_cliente)
SELECT '966554433', '+55', '51', (SELECT id_cliente FROM t_cm_cliente WHERE nm_email='lucas@email.com')
WHERE NOT EXISTS (SELECT 1 FROM t_cm_telefone WHERE nr_telefone='966554433');

-----------------------------------------
-- Modelos (tabela exige id_modelo explícito - sem IDENTITY)
-----------------------------------------
INSERT INTO t_cm_modelo (id_modelo, nm_modelo) SELECT 1, 'SPORT' WHERE NOT EXISTS (SELECT 1 FROM t_cm_modelo WHERE id_modelo=1);
INSERT INTO t_cm_modelo (id_modelo, nm_modelo) SELECT 2, 'E'     WHERE NOT EXISTS (SELECT 1 FROM t_cm_modelo WHERE id_modelo=2);
INSERT INTO t_cm_modelo (id_modelo, nm_modelo) SELECT 3, 'POP'   WHERE NOT EXISTS (SELECT 1 FROM t_cm_modelo WHERE id_modelo=3);

-----------------------------------------
-- Motos (usar st_moto válidos: DISPONIVEL | LOCADA | RESERVADA | INATIVA)
-----------------------------------------
DECLARE @fd_centro BIGINT = (SELECT id_filial_departamento FROM t_cm_filial_departamento WHERE id_filial=@id_filial_centro  AND id_departamento=@dep_log);
DECLARE @fd_jardins BIGINT= (SELECT id_filial_departamento FROM t_cm_filial_departamento WHERE id_filial=@id_filial_jardins AND id_departamento=@dep_rh);
DECLARE @fd_copa BIGINT   = (SELECT id_filial_departamento FROM t_cm_filial_departamento WHERE id_filial=@id_filial_copa    AND id_departamento=@dep_fin);
DECLARE @fd_pituba BIGINT = (SELECT id_filial_departamento FROM t_cm_filial_departamento WHERE id_filial=@id_filial_pituba  AND id_departamento=@dep_ti);
DECLARE @fd_savassi BIGINT= (SELECT id_filial_departamento FROM t_cm_filial_departamento WHERE id_filial=@id_filial_savassi AND id_departamento=@dep_com);

INSERT INTO t_cm_moto (nm_placa, id_modelo, st_moto, km_rodado, id_cliente, id_filial_departamento)
SELECT 'ABC1234', 1, 'DISPONIVEL', 1200.5, (SELECT id_cliente FROM t_cm_cliente WHERE nm_email='joao@email.com'),  @fd_centro
WHERE NOT EXISTS (SELECT 1 FROM t_cm_moto WHERE nm_placa='ABC1234');

INSERT INTO t_cm_moto (nm_placa, id_modelo, st_moto, km_rodado, id_cliente, id_filial_departamento)
SELECT 'DEF5678',  2, 'INATIVA',    800.0,  (SELECT id_cliente FROM t_cm_cliente WHERE nm_email='maria@email.com'), @fd_jardins
WHERE NOT EXISTS (SELECT 1 FROM t_cm_moto WHERE nm_placa='DEF5678');

INSERT INTO t_cm_moto (nm_placa, id_modelo, st_moto, km_rodado, id_cliente, id_filial_departamento)
SELECT 'GHI9012',  3, 'RESERVADA', 3000.8, (SELECT id_cliente FROM t_cm_cliente WHERE nm_email='carlos@email.com'), @fd_copa
WHERE NOT EXISTS (SELECT 1 FROM t_cm_moto WHERE nm_placa='GHI9012');

INSERT INTO t_cm_moto (nm_placa, id_modelo, st_moto, km_rodado, id_cliente, id_filial_departamento)
SELECT 'JKL3456',  1, 'LOCADA',     150.3,  (SELECT id_cliente FROM t_cm_cliente WHERE nm_email='ana@email.com'),   @fd_pituba
WHERE NOT EXISTS (SELECT 1 FROM t_cm_moto WHERE nm_placa='JKL3456');

INSERT INTO t_cm_moto (nm_placa, id_modelo, st_moto, km_rodado, id_cliente, id_filial_departamento)
SELECT 'MNO7890',  2, 'INATIVA',   2200.0,  (SELECT id_cliente FROM t_cm_cliente WHERE nm_email='lucas@email.com'), @fd_savassi
WHERE NOT EXISTS (SELECT 1 FROM t_cm_moto WHERE nm_placa='MNO7890');

-----------------------------------------
-- Manutenções
-----------------------------------------
INSERT INTO t_cm_manutencao (id_moto, dt_entrada, dt_saida, ds_manutencao)
SELECT (SELECT id_moto FROM t_cm_moto WHERE nm_placa='ABC1234'), '2024-01-01', '2024-01-05', N'Troca de óleo e revisão geral'
WHERE NOT EXISTS (SELECT 1 FROM t_cm_manutencao WHERE id_moto=(SELECT id_moto FROM t_cm_moto WHERE nm_placa='ABC1234') AND dt_entrada='2024-01-01');

INSERT INTO t_cm_manutencao (id_moto, dt_entrada, dt_saida, ds_manutencao)
SELECT (SELECT id_moto FROM t_cm_moto WHERE nm_placa='DEF5678'), '2024-02-10', '2024-02-15', N'Troca de pneu traseiro'
WHERE NOT EXISTS (SELECT 1 FROM t_cm_manutencao WHERE id_moto=(SELECT id_moto FROM t_cm_moto WHERE nm_placa='DEF5678') AND dt_entrada='2024-02-10');

INSERT INTO t_cm_manutencao (id_moto, dt_entrada, dt_saida, ds_manutencao)
SELECT (SELECT id_moto FROM t_cm_moto WHERE nm_placa='GHI9012'), '2024-03-20', NULL, N'Correção elétrica'
WHERE NOT EXISTS (SELECT 1 FROM t_cm_manutencao WHERE id_moto=(SELECT id_moto FROM t_cm_moto WHERE nm_placa='GHI9012') AND dt_entrada='2024-03-20');

INSERT INTO t_cm_manutencao (id_moto, dt_entrada, dt_saida, ds_manutencao)
SELECT (SELECT id_moto FROM t_cm_moto WHERE nm_placa='JKL3456'), '2024-04-12', '2024-04-14', N'Alinhamento e balanceamento'
WHERE NOT EXISTS (SELECT 1 FROM t_cm_manutencao WHERE id_moto=(SELECT id_moto FROM t_cm_moto WHERE nm_placa='JKL3456') AND dt_entrada='2024-04-12');

INSERT INTO t_cm_manutencao (id_moto, dt_entrada, dt_saida, ds_manutencao)
SELECT (SELECT id_moto FROM t_cm_moto WHERE nm_placa='MNO7890'), '2024-05-01', NULL, N'Troca de pastilha de freio'
WHERE NOT EXISTS (SELECT 1 FROM t_cm_manutencao WHERE id_moto=(SELECT id_moto FROM t_cm_moto WHERE nm_placa='MNO7890') AND dt_entrada='2024-05-01');

-----------------------------------------
-- Funções e Funcionário Admin
-----------------------------------------
INSERT INTO t_cm_funcao (nm_funcao) SELECT 'ADMIN'        WHERE NOT EXISTS (SELECT 1 FROM t_cm_funcao WHERE nm_funcao='ADMIN');
INSERT INTO t_cm_funcao (nm_funcao) SELECT 'OPERACIONAL'  WHERE NOT EXISTS (SELECT 1 FROM t_cm_funcao WHERE nm_funcao='OPERACIONAL');
INSERT INTO t_cm_funcao (nm_funcao) SELECT 'ATENDIMENTO'  WHERE NOT EXISTS (SELECT 1 FROM t_cm_funcao WHERE nm_funcao='ATENDIMENTO');
INSERT INTO t_cm_funcao (nm_funcao) SELECT 'ANALISTA'     WHERE NOT EXISTS (SELECT 1 FROM t_cm_funcao WHERE nm_funcao='ANALISTA');

INSERT INTO t_cm_funcionario (id_filial, nm_funcionario, nm_email_corporativo, nm_senha, nm_cargo)
SELECT @id_filial_centro, N'Eduarda Tiemi', 'duda@empresa.com',
       '$2a$12$G0dtDbKrJ8SQEQR1T/tCaeq.Qr1iQLmXTiAms9C4LIb816l/PaeGK', N'Gerente de Filial'
WHERE NOT EXISTS (SELECT 1 FROM t_cm_funcionario WHERE nm_email_corporativo='duda@empresa.com');

INSERT INTO t_cm_funcionario_funcao (id_funcionario, id_funcao)
SELECT f.id_funcionario, fu.id_funcao
FROM t_cm_funcionario f
JOIN t_cm_funcao fu ON fu.nm_funcao='ADMIN'
WHERE f.nm_email_corporativo='duda@empresa.com'
  AND NOT EXISTS (
      SELECT 1 FROM t_cm_funcionario_funcao x
      WHERE x.id_funcionario=f.id_funcionario AND x.id_funcao=fu.id_funcao
  );


