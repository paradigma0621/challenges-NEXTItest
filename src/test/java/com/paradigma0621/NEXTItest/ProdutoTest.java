package com.paradigma0621.NEXTItest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;

import org.junit.jupiter.api.Test;
//import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.paradigma0621.NEXTItest.domain.Produto;
import com.paradigma0621.NEXTItest.repositories.ProdutoRepository;
import com.paradigma0621.NEXTItest.services.ProdutoService;

@SpringBootTest
@Sql("/cleanAndCreateTestDb.sql") // Deleta e cria o banco de dados 'testNEXTIdb' para garantir que
									// não tenha nenhum item ao iniciar.
									// Esse banco de dados será usado para apenas Tests
@ActiveProfiles("test") // usando arquivo de configuração src/test/resources/application-test.properties
						// para definir banco de dados separado de nome 'testNEXTIdb'
class ProdutoTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired // Vai ser instanciado automaticamente
	private ProdutoRepository produtoRepository;

	@Autowired // Para instanciar automaticamente o objeto
	private ProdutoService prodService;
	
	@Test
	void dadosProduto() {
		Produto p1 = new Produto(null, "ComputadorTeste", "Notebook Samsung Teste", 6479.10d, 3);
		prodService.insert(p1);            // Testará a implementação do insert de ProdutoService
		Produto obj = prodService.find(1); // Testará a implementação do find de ProdutoService
		
		assertTrue(obj.getId().equals(1));
		assertTrue(obj.getNome().equals(p1.getNome()));
		assertTrue(obj.getDescricao().equals(p1.getDescricao()));
		assertEquals(obj.getQuantidade(), p1.getQuantidade());
		assertEquals(obj.getPreco(), p1.getPreco());
	}

	@Test
	void adicionarProdutos() {
		Produto p1 = new Produto(null, "Prod.1", "Descr.1", 449.11d, 1);
		Produto p2 = new Produto(null, "Prod.2", "Descr.2", 449.11d, 2);
		Produto p3 = new Produto(null, "Prod.3", "Descr.3", 449.11d, 3);
		Produto p4 = new Produto(null, "Prod.4", "Descr.4", 449.11d, 4);
		Produto p5 = new Produto(null, "Prod.5", "Descr.5", 449.11d, 5);
		Produto p6 = new Produto(null, "Prod.6", "Descr.6", 449.11d, 6);
		Produto p7 = new Produto(null, "Prod.7", "Descr.7", 449.11d, 7);
		Produto p8 = new Produto(null, "Prod.8", "Descr.8", 449.11d, 8);
		Produto p9 = new Produto(null, "Prod.9", "Descr.9", 449.11d, 9);
		Produto p10 = new Produto(null, "Prod.10", "Descr.10", 449.11d, 10);
		Produto p11 = new Produto(null, "Prod.11", "Descr.11", 449.11d, 11);
		
		prodService.insert(p1);
		prodService.insert(p2);
		prodService.insert(p3);
		prodService.insert(p4);
		prodService.insert(p5);
		prodService.insert(p6);
		prodService.insert(p7);
		prodService.insert(p8);
		prodService.insert(p9);
		prodService.insert(p10);
		prodService.insert(p11);
		
		assertEquals(produtoRepository.count(), 11); // Testa o número de linhas na tabela 'produto' 
													// do banco de dados - se é igual ao número 
													// adicionado (=11)
	
		prodService.delete(5);	// Testará a implementação do delete de ProdutoService
		prodService.delete(4);
		assertEquals(produtoRepository.count(), 9); // Removeu 2 elementos da base de dados; ficaram 9
		 											// Confere com o limero de registros da tabela?
		
	}
	
}

