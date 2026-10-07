package br.com.rm568156.cp.sistema_cursos.repository;

import br.com.rm568156.cp.sistema_cursos.model.Modulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {
    Page<Modulo> findByCursoId(Long cursoId, Pageable pageable);
}
