package br.com.rm568156.cp.sistema_cursos.service;

import br.com.rm568156.cp.sistema_cursos.model.Modulo;
import org.springframework.data.domain.Page;

public interface ModuloService {

    Page<Modulo> findAll(Integer page, Integer size);

    Modulo findById(Long id);

    Modulo create(Modulo modulo);

    Modulo update(Long id, Modulo updated);

    void delete(Long id);
}
