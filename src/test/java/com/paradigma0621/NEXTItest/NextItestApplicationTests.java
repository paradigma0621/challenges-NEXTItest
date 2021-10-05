package com.paradigma0621.NEXTItest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.paradigma0621.NEXTItest.domain.Produto;
import com.paradigma0621.NEXTItest.repositories.ProdutoRepository;

@SpringBootTest
@ActiveProfiles("test") //usando arquivo de configuração src/test/resources/application-test.properties para definir banco de dados separado de nome 'testNEXTIdb'
@Sql("/createTestDb.sql") //Cria o banco de dados 'testNEXTIdb', usado para apenas Tests
class NextItestApplicationTests {

	@Autowired //Vai ser instanciado automaticamente
	private ProdutoRepository produtoRepository;

	@Test
		void incluirProduto() {
		Produto p1 = new Produto(null, "ComputadorTeste", "Notebook Samsung Teste",	6479.10d, 3);
		Produto p2 = new Produto(null, "ImpressoraTeste", "Impressora multifuncional HP Teste", 449.11d, 2);
		Produto p3 = new Produto(null, "MouseTeste", "Mouse com fio USB Logitech Teste", 34.90d, 20);
		Produto p4 = new Produto(null, "TecladoTeste", "Teclado com fio USB Logitech K120 Teste", 59.99d, 7);
		Produto p5 = new Produto(null, "LumináriaTeste", "Luminária Articulada Teste", 121.00d, 4);

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
			 
		//fazer um get list size de produtos que subiram, pra ver se bate com o número incluído
		assertTrue(true);
	}
/*
	@Test
	void excluirProduto() {
		assertTrue(true);
	}
    
	@Test
	void atualizarProduto() {
		assertTrue(true);
	}
	
	@Test
	void listarProdutos() {
		assertTrue(true);
	}
	*/
	
	//testar se adicionar produto: se ele adiciona certo
	//testar se adicionar lista de produtos: se ele adiciona certo
	//testar se adicionar cliente: se ele adiciona certo
	//testar se adicionar lista de clientes: se ele adiciona certo
	//testar se adicionar pedido: se ele adiciona certo
	//testar se adicionar lista de pedidos: se ele adiciona certo
	
	
	//testar um pedido de 3x produtoX, se calcula preço total certo
	//testar um pedido de 3x produtoX + 4x ProdutoY, se calcula preço total certo
	//testar um pedido de 3x produtoX + 4x ProdutoY + 11x ProdutoZ, se calcula preço total certo
	
	//testar se eu vincular o clienteX com os produtosX e produtoY, relacionando com pedidoK

	 /*Eu como cliente quero cadastrar clientes e produtos e após isso criar pedidos para os clientes
	 contendo todas as informações relevantes ao pedido.
	 
	 
    Cliente
        Incluir, excluir, atualizar e listar.
    Produto
        Incluir, excluir, atualizar e listar.
    Pedido
        Incluir, excluir, atualizar e listar.
        Vincular cliente a um pedido.
        Remover cliente de um pedido.
        Vincular produto(s) a um pedido.
        Remover produto(s) de um pedido.
	  */
}

