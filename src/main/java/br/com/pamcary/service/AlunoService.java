package br.com.pamcary.service;

import java.util.List;
import java.util.Optional;

import br.com.pamcary.domain.Aluno;

public interface AlunoService {
	
	public Optional<Aluno> salvar(Aluno aluno);
	public Optional<Aluno> atualizar(Aluno aluno, String id);
	public Optional<Aluno> buscarAlunoById(String id);
	public Optional<List<Aluno>> buscarAlunos();
	public void excluirAlunoById(String id);
}
