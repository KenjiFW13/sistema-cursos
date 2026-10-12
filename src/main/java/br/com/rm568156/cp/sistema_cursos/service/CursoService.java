package br.com.rm568156.cp.sistema_cursos.service;

import br.com.rm568156.cp.sistema_cursos.model.Curso;
import org.springframework.data.domain.Page;

public interface CursoService {

    Page<Curso> findAll(Integer page, Integer size);

    Curso findById(Long id);

    Curso create(Curso curso);

    Curso update(Long id, Curso updated);

    void delete(Long id);
}
