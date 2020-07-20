package br.com.pamcary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pamcary.domain.Aluno;
import br.com.pamcary.exception.AppMessage;
import br.com.pamcary.exception.PamcaryException;
import br.com.pamcary.repository.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Override
	public Optional<Aluno> salvar(Aluno aluno) {
		return Optional.ofNullable(alunoRepository.save(aluno));
	}
	
	@Override
	public Optional<Aluno> atualizar(Aluno aluno, String id) {
		if (!aluno.getId().equals(Long.valueOf(id))) {
			throw new PamcaryException(AppMessage.builder()
					.message("Aluno com id divergentes.")
					.build());
		}
		
		return Optional.ofNullable(alunoRepository.save(aluno));
	}

	@Override
	public Optional<Aluno> buscarAlunoById(String id) {
		return alunoRepository.findById(Long.valueOf(id));
	}

	@Override
	public Optional<List<Aluno>> buscarAlunos() {
		return Optional.ofNullable(alunoRepository.findAll());
	}

	@Override
	public void excluirAlunoById(String id) {
		alunoRepository.deleteById(Long.valueOf(id));
	}
}
