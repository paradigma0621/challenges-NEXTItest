package com.paradigma0621.NEXTItest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paradigma0621.NEXTItest.domain.Cliente;
import com.paradigma0621.NEXTItest.domain.Pedido;
import com.paradigma0621.NEXTItest.domain.Produto;
import com.paradigma0621.NEXTItest.repositories.ClienteRepository;
import com.paradigma0621.NEXTItest.repositories.PedidoRepository;
import com.paradigma0621.NEXTItest.repositories.ProdutoRepository;

@SpringBootApplication
public class NextItestApplication implements CommandLineRunner {

	@Autowired //Vai ser instanciado automaticamente
	private ProdutoRepository produtoRepository;


	@Autowired //Vai ser instanciado automaticamente
	private ClienteRepository clienteRepository;
	
	@Autowired //Vai ser instanciado automaticamente
	private PedidoRepository pedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(NextItestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//---------------- Base de dados amostral gera na inicialização ------------------------
		
		//Produtos
		
		Produto p1 = new Produto(null, "Computador", "Notebook Samsung Galaxy Book S - Intel®Core™ i5 -8GB - 256GB SSD -Tela 13.3\" Full HD -Touch",	6479.10d, 3);
		Produto p2 = new Produto(null, "Impressora", "Impressora multifuncional HP DeskJet Ink Advantage 2774", 449.11d, 2);
		Produto p3 = new Produto(null, "Mouse", "Mouse com fio USB Logitech M90 - Cinza", 34.90d, 4);
		Produto p4 = new Produto(null, "Teclado", "Teclado com fio USB Logitech K120 Resistente à Respingos e Layout ABNT2", 59.99d, 1);
		Produto p5 = new Produto(null, "Luminária", "Luminária Articulada De Mesa Abajur Tipo Pixar", 121.00d, 1);

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		//-----------------------------------------------------------------------
		//Clientes
		
	    Date dataFormatada1 = new Date();
	    //Date dataFormatada2 = formato.parse("08/02/1960");
	    //Date dataFormatada3 = formato.parse("12/03/1974");
		
		Cliente c1 = new Cliente(null, "Fábio Santos", "040.630.489-20", dataFormatada1);
		Cliente c3 = new Cliente(null, "Rose Soares Silveira", "277.529.179-15", dataFormatada1);
		Cliente c2 = new Cliente(null, "João Pedro de Souza", "010.238.187-10", dataFormatada1);
		clienteRepository.saveAll(Arrays.asList(c1, c2,c3));
		
		

		//-----------------------------------------------------------------------
		//Pedidos
		//SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
	    Date dataCompra1 = formato.parse("23/01/2001");
	    Date dataCompra2 = formato.parse("13/04/2007");
	    Date dataCompra3 = formato.parse("28/09/2019");
	    Date dataCompra4 = formato.parse("30/11/2020");
	    
	    Pedido pedido1 = new Pedido(null, c1, 31.23, dataCompra3);
	    Pedido pedido2 = new Pedido(null, c2, 129.16, dataCompra2);

	    c2.getPedidos().addAll(Arrays.asList(pedido2));
	    p3.setPedido(pedido2);
	    p4.setPedido(pedido2);
	    pedido2.getProdutos().addAll(Arrays.asList(p3, p4));

	    c1.getPedidos().addAll(Arrays.asList(pedido1));
	    p1.setPedido(pedido1);
	    p5.setPedido(pedido1);
	    pedido1.getProdutos().addAll(Arrays.asList(p1, p5));
	    
	    pedidoRepository.saveAll(Arrays.asList(pedido2, pedido1));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		}
		
}
