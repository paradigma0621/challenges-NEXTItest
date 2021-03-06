package com.paradigma0621.NEXTItest.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity // Indica que essa classe será uma entidade do JPA
		// Criará no banco de dados uma tabela Pedido com todos os campos
		// dessa classe de forma automática
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Cliente cliente;
		
	
	private double totalDaCompra;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Date dataCompra;
	
	  // Caso deseje-se deletar um 'pedido' sem deletar conjuntamente os produtos do mesmo da lista 
	  // de produtos, deve-se alterar a linha abaixo para:
	  // @OneToMany(mappedBy = "pedido") // nesse caso é preciso remover todos os produtos da
	  // de dentro do pedido antes de se conseguir excluir o pedido
	  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // note que nesta anotação 
	  //definimos a propriedade mappedBy como “pedido” que é para informar que o atributo com
	  //o nome pedido na entity Produto que é dona do relacionamento.
	  private List<Produto> produtos = new ArrayList<>();
  
	  
	public Pedido() {
	}

	public Pedido(Integer id, Cliente cliente, Date dataCompra) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.totalDaCompra = 0; // Começa sem nenhum produto adicionado, portanto preço 
								// total inicial é = R$ 0.00 
		this.dataCompra = dataCompra;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getTotalDaCompra() {
		totalDaCompra = 0.0;
		for (Produto p : produtos) {
			totalDaCompra = totalDaCompra + p.getPreco() * p.getQuantidade();
		}
			
		return Math.round(totalDaCompra * 100.0)/100.0; // Arredonda para 2 casas decimais
									// de modo que para a exibição do atributo 'totalDaCompra' com
									// GET 'pedidos' venha formatado em valor monetário
	}

	public void setTotalDaCompra(double totalDaCompra) {
		this.totalDaCompra = totalDaCompra;
	}
	
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	
	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}