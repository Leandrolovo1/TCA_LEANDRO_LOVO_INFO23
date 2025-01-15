-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Tempo de geração: 13-Jan-2025 às 20:10
-- Versão do servidor: 8.0.39
-- versão do PHP: 7.4.3-4ubuntu2.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `info23_LEANDRO`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `Administrador`
--

CREATE TABLE `Administrador` (
  `id_adm` int NOT NULL,
  `nome` varchar(30) NOT NULL,
  `senha` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `Administrador`
--

INSERT INTO `Administrador` (`id_adm`, `nome`, `senha`) VALUES
(1, 'leandro', '1234');

-- --------------------------------------------------------

--
-- Estrutura da tabela `Estoque`
--

CREATE TABLE `Estoque` (
  `id_estoque` int NOT NULL,
  `fk_id_produto` int NOT NULL,
  `quantidade` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Funcionario`
--

CREATE TABLE `Funcionario` (
  `id_funcionario` int NOT NULL,
  `fk_id_adm` int NOT NULL DEFAULT '1',
  `nome` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `senha` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(30) NOT NULL,
  `telefone` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `Funcionario`
--

INSERT INTO `Funcionario` (`id_funcionario`, `fk_id_adm`, `nome`, `senha`, `email`, `telefone`) VALUES
(1, 1, 'leandro', '1234', 'leandro', '123123123');

-- --------------------------------------------------------

--
-- Estrutura da tabela `itens_venda`
--

CREATE TABLE `itens_venda` (
  `id_item` int NOT NULL,
  `fk_id_produto` int NOT NULL,
  `fk_id_venda` int NOT NULL,
  `quantidade` int NOT NULL,
  `preco_unit` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Produto`
--

CREATE TABLE `Produto` (
  `id_produto` int NOT NULL,
  `nome_produto` varchar(30) NOT NULL,
  `Categoria` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Marca` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `preco_produto` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Venda`
--

CREATE TABLE `Venda` (
  `id_venda` int NOT NULL,
  `fk_id_funcionario` int NOT NULL,
  `data_venda` datetime NOT NULL,
  `valor_pago` decimal(10,2) NOT NULL,
  `total_venda` decimal(10,2) NOT NULL,
  `troco` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `Administrador`
--
ALTER TABLE `Administrador`
  ADD PRIMARY KEY (`id_adm`);

--
-- Índices para tabela `Estoque`
--
ALTER TABLE `Estoque`
  ADD PRIMARY KEY (`id_estoque`),
  ADD KEY `id_produto` (`fk_id_produto`);

--
-- Índices para tabela `Funcionario`
--
ALTER TABLE `Funcionario`
  ADD PRIMARY KEY (`id_funcionario`),
  ADD KEY `fk_id_adm` (`fk_id_adm`);

--
-- Índices para tabela `itens_venda`
--
ALTER TABLE `itens_venda`
  ADD PRIMARY KEY (`id_item`),
  ADD KEY `fk_itens_venda_produto` (`fk_id_produto`),
  ADD KEY `fk_itens_venda_venda` (`fk_id_venda`);

--
-- Índices para tabela `Produto`
--
ALTER TABLE `Produto`
  ADD PRIMARY KEY (`id_produto`);

--
-- Índices para tabela `Venda`
--
ALTER TABLE `Venda`
  ADD PRIMARY KEY (`id_venda`),
  ADD KEY `fk_id_funcionario` (`fk_id_funcionario`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `Administrador`
--
ALTER TABLE `Administrador`
  MODIFY `id_adm` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `Estoque`
--
ALTER TABLE `Estoque`
  MODIFY `id_estoque` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `Funcionario`
--
ALTER TABLE `Funcionario`
  MODIFY `id_funcionario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `itens_venda`
--
ALTER TABLE `itens_venda`
  MODIFY `id_item` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `Produto`
--
ALTER TABLE `Produto`
  MODIFY `id_produto` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `Venda`
--
ALTER TABLE `Venda`
  MODIFY `id_venda` int NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `Estoque`
--
ALTER TABLE `Estoque`
  ADD CONSTRAINT `Estoque_ibfk_1` FOREIGN KEY (`fk_id_produto`) REFERENCES `Produto` (`id_produto`);

--
-- Limitadores para a tabela `Funcionario`
--
ALTER TABLE `Funcionario`
  ADD CONSTRAINT `fk_id_adm` FOREIGN KEY (`fk_id_adm`) REFERENCES `Administrador` (`id_adm`);

--
-- Limitadores para a tabela `itens_venda`
--
ALTER TABLE `itens_venda`
  ADD CONSTRAINT `fk_itens_venda_produto` FOREIGN KEY (`fk_id_produto`) REFERENCES `Produto` (`id_produto`),
  ADD CONSTRAINT `fk_itens_venda_venda` FOREIGN KEY (`fk_id_venda`) REFERENCES `Venda` (`id_venda`);

--
-- Limitadores para a tabela `Venda`
--
ALTER TABLE `Venda`
  ADD CONSTRAINT `fk_id_funcionario` FOREIGN KEY (`fk_id_funcionario`) REFERENCES `Funcionario` (`id_funcionario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
