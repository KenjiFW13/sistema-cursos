package br.com.rm568156.cp.sistema_cursos.repository;

import br.com.rm568156.cp.sistema_cursos.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Page<Curso> findById(Long cursoId, Pageable pageable);
}
