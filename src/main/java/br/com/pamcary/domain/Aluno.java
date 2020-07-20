package br.com.pamcary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

@Entity
@Table(name = "aluno")
public class Aluno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "idade")
	private Integer idade;
}
