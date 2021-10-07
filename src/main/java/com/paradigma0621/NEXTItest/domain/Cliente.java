package com.paradigma0621.NEXTItest.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity // Indica que essa classe será uma entidade do JPA
		// Criará no banco de dados uma tabela Cliente com todos os campos
		// dessa classe de forma automática
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dataNascimento;

	  @JsonIgnore //Para evitar referência cíclica. Esse lado da associação não será serializada
	  @OneToMany(mappedBy = "cliente") // note que nesta anotação 
	  // definimos a propriedade mappedBy como “cliente” que é para informar que o atributo com
	  // o nome cliente na entity Pedido que é dona do relacionamento.
	  // NOTA: Não é possível a exclusão de um cliente que seja dono de algum pedido. Poderíamos
	  // permitir tal funcionalidade alterando a linha acima para:
	  // @OneToMany(mappedBy = "cliente", cascade=CascadeType.ALL)
	  // o que acarretaria que apagando um cliente seriam apagados todos os seus pedidos 
	  // conjuntamente.

	private List<Pedido> pedidos = new ArrayList<>();
    
	public Cliente() {
	}

	public Cliente(Integer id, String nome, String cpf, Date dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


    public List<Pedido> getPedidos() {
		return pedidos;
	}
	
  
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}