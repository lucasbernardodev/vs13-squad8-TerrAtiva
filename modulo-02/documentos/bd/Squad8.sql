CREATE TABLE "ALUGUEL_PAGAMENTOS" (
  "PAGAMENTO_ID" INT NOT NULL,
  "MENSALIDADE_ID" INT NOT NULL,
  "MES_REFERENCIA" NUMBER(2) NOT NULL,
  "EMISSAO" DATE NOT NULL,
  "VENCIMENTO" DATE NOT NULL,
  "TAXAS" NUMBER(12,2),
  "CODIGO_BARRAS_BOLETO" NVARCHAR2(50) NOT NULL,
  "DATA_PAGAMENTO" DATE,
  "PAGO" CHAR(1) NOT NULL,
  "CRIADO" VARCHAR2(60),
  "EDITADO" VARCHAR2(60),
  CONSTRAINT "_COPY_3" PRIMARY KEY ("PAGAMENTO_ID"),
  CONSTRAINT "COD_BARRAS_UK" UNIQUE ("CODIGO_BARRAS_BOLETO"),
  CONSTRAINT "ALPA_MERE_CK" CHECK (MES_REFERENCIA BETWEEN 1 AND 12),
  CONSTRAINT "ALPA_PAGO_CK" CHECK (UPPER(PAGO) = 'S' OR UPPER(PAGO) = 'N')
);

CREATE TABLE "CONTRATOS" (
  "CONTRATO_ID" INT NOT NULL,
  "LOCATARIO_ID" INT NOT NULL,
  "TERRENO_ID" INT NOT NULL,
  "ATIVO" CHAR(1) NOT NULL,
  "DATA_ASSINATURA" DATE NOT NULL,
  "DATA_INICIO" DATE NOT NULL,
  "DATA_FINAL" DATE,
  "DIA_VENCIMENTO_ALUGUEL" NUMBER(2) NOT NULL,
  "CRIADO" VARCHAR2(60),
  "EDITADO" VARCHAR2(60),
  CONSTRAINT "_COPY_5" PRIMARY KEY ("CONTRATO_ID"),
  CONSTRAINT "CONTRATOS_ATIVO_CK" CHECK (UPPER(ATIVO) = 'S' OR UPPER(ATIVO) = 'N'),
  CONSTRAINT "CONTRATOS_DIVEAL_CK" CHECK (DIA_VENCIMENTO_ALUGUEL BETWEEN 1 AND 31)
);

CREATE TABLE "ENDERECO_TERRENOS" (
  "ENDERECO_TERRENO_ID" INTEGER NOT NULL,
  "LOGRADOURO" VARCHAR2(60),
  "NUMERO" NUMBER(9),
  "COMPLEMENTO" VARCHAR2(60),
  "BAIRRO" VARCHAR2(60) NOT NULL,
  "MUNICIPIO_COD_IBGE" NUMBER(7) NOT NULL,
  "CEP" NUMBER(9) NOT NULL,
  "LOCALIZACAO" VARCHAR2(30) NOT NULL,
  "CRIADO" VARCHAR2(60),
  "EDITADO" VARCHAR2(60),
  CONSTRAINT "_COPY_1" PRIMARY KEY ("ENDERECO_TERRENO_ID")
);

CREATE TABLE "ENDERECOS" (
  "ENDERECO_ID" INTEGER NOT NULL,
  "USUARIO_ID" INT NOT NULL,
  "LOGRADOURO" VARCHAR2(60),
  "NUMERO" NUMBER(9),
  "COMPLEMENTO" VARCHAR2(60),
  "BAIRRO" VARCHAR2(60) NOT NULL,
  "MUNICIPIO_COD_IBGE" NUMBER(7) NOT NULL,
  "CEP" NUMBER(9) NOT NULL,
  "CRIADO" VARCHAR2(60),
  "EDITADO" VARCHAR2(60),
  CONSTRAINT "_COPY_7" PRIMARY KEY ("ENDERECO_ID")
);

CREATE TABLE "ESTADO_MUNICIPIOS" (
  "NOME_ESTADO" VARCHAR2(35) NOT NULL,
  "ESTADO_COD" NUMBER(2) NOT NULL,
  "NOME_MUNICIPIO" VARCHAR2(60) NOT NULL,
  "MUNICIPIO_COD_IBGE" NUMBER(7) NOT NULL,
  PRIMARY KEY ("MUNICIPIO_COD_IBGE")
);

CREATE TABLE "MENSALIDADES" (
  "MENSALIDADE_ID" INT NOT NULL,
  "CONTRATO_ID" INT NOT NULL,
  "VALOR_MENSAL" NUMBER(12,2) NOT NULL,
  "ANO_EXERCICIO" NUMBER(4) NOT NULL,
  "DATA_REAJUSTE" DATE,
  "CRIADO" VARCHAR2(60),
  "EDITADO" VARCHAR2(60),
  CONSTRAINT "_COPY_4" PRIMARY KEY ("MENSALIDADE_ID"),
  CONSTRAINT "MENSALIDADES_ANEX_CK" CHECK (ANO_EXERCICIO BETWEEN 2010 AND 2200)
);

CREATE TABLE "TERRENOS" (
  "TERRENO_ID" INT NOT NULL,
  "TITULO" VARCHAR2(100) NOT NULL,
  "DESCRICAO" VARCHAR2(1000) NOT NULL,
  "DONO_ID" INT NOT NULL,
  "ENDERECO_TERRENO_ID" INT NOT NULL,
  "PRECO" NUMBER(12,2) NOT NULL,
  "TAMANHO" VARCHAR2(30) NOT NULL,
  "DISPONIVEL" CHAR(1) NOT NULL,
  "CRIADO" VARCHAR2(60),
  "EDITADO" VARCHAR2(60),
  CONSTRAINT "_COPY_6" PRIMARY KEY ("TERRENO_ID"),
  CONSTRAINT "TERRENOS_DISPONIVEL_CK" CHECK (UPPER(DISPONIVEL) = 'S' OR UPPER(DISPONIVEL) = 'N')
);

CREATE TABLE "USUARIOS" (
  "USUARIO_ID" INT NOT NULL,
  "NOME" VARCHAR2(50) NOT NULL,
  "SOBRENOME" VARCHAR2(80) NOT NULL,
  "EMAIL" VARCHAR2(100) NOT NULL,
  "SENHA" VARCHAR2(255) NOT NULL,
  "CPF" VARCHAR2(11) NOT NULL,
  "DATA_NASCIMENTO" DATE NOT NULL,
  "SEXO" CHAR(1) NOT NULL,
  "ATIVO" CHAR(1) NOT NULL,
  "CELULAR" VARCHAR2(20) NOT NULL,
  "TELEFONE_FIXO" VARCHAR2(20),
  "CRIADO" VARCHAR2(60),
  "EDITADO" VARCHAR2(60),
  CONSTRAINT "_COPY_2" PRIMARY KEY ("USUARIO_ID"),
  CONSTRAINT "CPF_UK" UNIQUE ("CPF"),
  CONSTRAINT "EMAIL_UK" UNIQUE ("EMAIL"),
  CONSTRAINT "USUARIOS_ATIVO_CK" CHECK (UPPER(ATIVO) = 'S' OR UPPER(ATIVO) = 'N'),
  CONSTRAINT "USUARIOS_SEXO_CK" CHECK (UPPER(SEXO) = 'F' OR UPPER(SEXO) = 'M')
);

ALTER TABLE "ALUGUEL_PAGAMENTOS" ADD CONSTRAINT "FK_ALPA_MENSALIDADES_1" FOREIGN KEY ("MENSALIDADE_ID") REFERENCES "MENSALIDADES" ("MENSALIDADE_ID");
ALTER TABLE "CONTRATOS" ADD CONSTRAINT "FK_CONTRATOS_USUARIOS_1" FOREIGN KEY ("LOCATARIO_ID") REFERENCES "USUARIOS" ("USUARIO_ID");
ALTER TABLE "CONTRATOS" ADD CONSTRAINT "FK_CONTRATOS_TERRENOS_1" FOREIGN KEY ("TERRENO_ID") REFERENCES "TERRENOS" ("TERRENO_ID");
ALTER TABLE "ENDERECO_TERRENOS" ADD CONSTRAINT "FK_ENTE_ESMU_1" FOREIGN KEY ("MUNICIPIO_COD_IBGE") REFERENCES "ESTADO_MUNICIPIOS" ("MUNICIPIO_COD_IBGE");
ALTER TABLE "ENDERECOS" ADD CONSTRAINT "FK_ENDERECOS_USUARIOS_1" FOREIGN KEY ("USUARIO_ID") REFERENCES "USUARIOS" ("USUARIO_ID");
ALTER TABLE "ENDERECOS" ADD CONSTRAINT "FK_ENDERECOS_ESMU_1" FOREIGN KEY ("MUNICIPIO_COD_IBGE") REFERENCES "ESTADO_MUNICIPIOS" ("MUNICIPIO_COD_IBGE");
ALTER TABLE "MENSALIDADES" ADD CONSTRAINT "FK_MENSALIDADES_CONTRATOS_1" FOREIGN KEY ("CONTRATO_ID") REFERENCES "CONTRATOS" ("CONTRATO_ID");
ALTER TABLE "TERRENOS" ADD CONSTRAINT "FK_TERRENOS_USUARIOS_1" FOREIGN KEY ("DONO_ID") REFERENCES "USUARIOS" ("USUARIO_ID");
ALTER TABLE "TERRENOS" ADD CONSTRAINT "FK_TEEN_TERRENOS_1" FOREIGN KEY ("ENDERECO_TERRENO_ID") REFERENCES "ENDERECO_TERRENOS" ("ENDERECO_TERRENO_ID");

-- Sequences Usuario
CREATE SEQUENCE seq_usuario
 START WITH     1001
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

-- Sequences Endereços
CREATE SEQUENCE seq_endereco
 START WITH     1001
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

-- Sequences Terreno
CREATE SEQUENCE seq_terreno
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

-- Sequences Contratos
CREATE SEQUENCE seq_contrato
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

-- Sequences Mensalidades
CREATE SEQUENCE seq_mensalidade
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

-- Sequences Aluguel_Pagamento
CREATE SEQUENCE seq_aluguel_pagamento
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;