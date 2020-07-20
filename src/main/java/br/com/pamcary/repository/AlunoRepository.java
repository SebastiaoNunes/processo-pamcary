package br.com.pamcary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pamcary.domain.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
