CREATE TABLE IF NOT EXISTS TDS_TB_FERRAMENTAS (
    id_produto VARCHAR(36) PRIMARY KEY,
    nome_produto VARCHAR(100) NOT NULL,
    tipo_produto VARCHAR(20) NOT NULL,
    classificacao_produto VARCHAR(20) NOT NULL,
    tamanho_produto DOUBLE PRECISION NOT NULL,
    preco_produto DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS TDS_TB_USUARIOS (
    id_usuario VARCHAR(36) PRIMARY KEY,
    email_usuario VARCHAR(255) NOT NULL UNIQUE,
    nome_usuario VARCHAR(100) NOT NULL,
    senha_usuario VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);