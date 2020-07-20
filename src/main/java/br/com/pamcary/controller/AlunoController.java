package br.com.pamcary.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.pamcary.domain.Aluno;
import br.com.pamcary.dto.DadosAluno;
import br.com.pamcary.mapper.AlunoMapper;
import br.com.pamcary.service.AlunoService;
import br.com.pamcary.dto.Response;

@Controller
@RequestMapping("/api/v1/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AlunoMapper alunoMapper;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<?>> save(@Valid @RequestBody DadosAluno alunoDTO) {
		Optional<Aluno> aluno = alunoService.salvar(alunoMapper.toEntity(alunoDTO));
		
		if (!aluno.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(Response.create("Erro ao salvar aluno"));
		}
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(Response.create(alunoMapper.toDto(aluno.get()), "Aluno salvo com sucesso."));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<?>> getOne(@PathVariable("id") String id) {
		Optional<Aluno> aluno = alunoService.buscarAlunoById(id);
		
		if (!aluno.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(Response.create("Aluno nao encontrado"));
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Response.create(alunoMapper.toDto(aluno.get()), "Aluno recuperado com sucesso."));
	}
	
	@GetMapping
	public ResponseEntity<Response<?>> getAll() {
		Optional<List<Aluno>> alunos = alunoService.buscarAlunos();
		
		if (!alunos.isPresent() || alunos.get().isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(Response.create("Nao existem alunos cadastrados"));
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Response.create(alunoMapper.toDtoList(alunos.get()), "Alunos recuperados com sucesso."));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<?>> delete(@PathVariable("id") String id) {
		Optional<Aluno> aluno = alunoService.buscarAlunoById(id);
		
		if (!aluno.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(Response.create("Aluno nao encontrado"));
		}
		
		alunoService.excluirAlunoById(id);
		
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(Response.create("Aluno excluido com sucesso."));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<?>> update(@RequestBody DadosAluno alunoDTO, @PathVariable("id") String id) {
		Optional<Aluno> aluno = alunoService.atualizar(alunoMapper.toEntity(alunoDTO), id);
		
		if (!aluno.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(Response.create("Erro ao atualizar aluno"));
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Response.create(alunoMapper.toDto(aluno.get()), "Aluno atualizado com sucesso."));
	}
}
