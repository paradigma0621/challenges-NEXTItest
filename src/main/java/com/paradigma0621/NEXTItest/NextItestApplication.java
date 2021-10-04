package com.paradigma0621.NEXTItest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paradigma0621.NEXTItest.domain.Cliente;
import com.paradigma0621.NEXTItest.repositories.ClienteRepository;
import com.paradigma0621.NEXTItest.repositories.ProdutoRepository;

@SpringBootApplication
public class NextItestApplication implements CommandLineRunner {

	@Autowired //Vai ser instanciado automaticamente
	private ProdutoRepository produtoRepository;


	@Autowired //Vai ser instanciado automaticamente
	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(NextItestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Base de dados amostral
		
		//Produtos
		/*
		Produto p1 = new Produto(null, "Computador", "Notebook Samsung Galaxy Book S - Intel®Core™ i5 -8GB - 256GB SSD -Tela 13.3\" Full HD -Touch",	6479.10d, 3);
		Produto p2 = new Produto(null, "Impressora", "Impressora multifuncional HP DeskJet Ink Advantage 2774", 449.11d, 2);
		Produto p3 = new Produto(null, "Mouse", "Mouse com fio USB Logitech M90 - Cinza", 34.90d, 4);
		Produto p4 = new Produto(null, "Teclado", "Teclado com fio USB Logitech K120 Resistente à Respingos e Layout ABNT2", 59.99d, 1);
		Produto p5 = new Produto(null, "Luminária", "Luminária Articulada De Mesa Abajur Tipo Pixar", 121.00d, 1);
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		*/

		//-----------------------------------------------------------------------
		//Clientes
		/*SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
	    Date dataFormatada1 = formato.parse("23/01/1951");
	    Date dataFormatada2 = formato.parse("08/02/1960");
	    Date dataFormatada3 = formato.parse("12/03/1974");
		
		Cliente c1 = new Cliente(null, "Fábio Santos", "040.630.489-20", dataFormatada1);
		Cliente c3 = new Cliente(null, "Rose Soares Silveira", "277.529.179-15", dataFormatada2);
		Cliente c2 = new Cliente(null, "João Pedro de Souza", "010.238.187-10", dataFormatada3);
		clienteRepository.saveAll(Arrays.asList(c1, c2,c3));
		*/
	}

		
}
