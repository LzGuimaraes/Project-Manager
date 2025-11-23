-- Tabela de usuário
CREATE TABLE tb_user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

-- Tabela de projeto
CREATE TABLE tb_project (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    status VARCHAR(255) NOT NULL,
    user_id BIGINT,
    CONSTRAINT fk_project_user
        FOREIGN KEY (user_id)
        REFERENCES tb_user(id)
        ON DELETE SET NULL
);
