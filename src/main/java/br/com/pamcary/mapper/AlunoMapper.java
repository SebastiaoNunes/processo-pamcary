package br.com.pamcary.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.pamcary.domain.Aluno;
import br.com.pamcary.dto.DadosAluno;

@Component
public class AlunoMapper {
	
	public Aluno toEntity(DadosAluno dadosAluno) {
		return Aluno.builder()
				.id(dadosAluno.getId())
				.nome(dadosAluno.getNome())
				.idade(dadosAluno.getIdade())
				.build();
	}
	
	public DadosAluno toDto(Aluno aluno) {
		return DadosAluno.builder()
				.id(aluno.getId())
				.nome(aluno.getNome())
				.idade(aluno.getIdade())
				.build();
	}
	
	public List<DadosAluno> toDtoList(List<Aluno> alunos) {
		return alunos.stream().map(aluno -> {
			return DadosAluno.builder()
			.id(aluno.getId())
			.nome(aluno.getNome())
			.idade(aluno.getIdade())
			.build();
		}).collect(Collectors.toList());
	}
}
