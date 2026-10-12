package br.com.rm568156.cp.sistema_cursos.service;

import br.com.rm568156.cp.sistema_cursos.exceptions.ResourceNotFoundException;
import br.com.rm568156.cp.sistema_cursos.model.Curso;
import br.com.rm568156.cp.sistema_cursos.repository.CursoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Page<Curso> findAll(Integer page, Integer size) {
        return cursoRepository.findAll(
                Pageable.ofSize(size).withPage(page)
        );
    }

    @Override
    public Curso findById(Long id) {
        Optional<Curso> byId = this.cursoRepository.findById(id);
        if (!byId.isPresent()){
            throw new ResourceNotFoundException("Curso",id);
        }

        return byId.get();
    }

    @Override
    public Curso create(Curso curso) {
        curso.setId(null);
        return cursoRepository.save(curso);
    }

    @Override
    public Curso update(Long id, Curso updated) {
        Curso existing = findById(id);
        updated.setId(id);
        return cursoRepository.save(updated);
    }

    @Override
    public void delete(Long id) {
        Curso curso = findById(id);
        cursoRepository.delete(curso);
    }
}
