package br.com.rm568156.cp.sistema_cursos.service;

import br.com.rm568156.cp.sistema_cursos.exceptions.ResourceNotFoundException;
import br.com.rm568156.cp.sistema_cursos.model.Modulo;
import br.com.rm568156.cp.sistema_cursos.repository.ModuloRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModuloServiceImpl implements ModuloService{

    private final ModuloRepository moduloRepository;

    public ModuloServiceImpl(ModuloRepository moduloRepository) {
        this.moduloRepository = moduloRepository;
    }


    @Override
    public Page<Modulo> findAll(Integer page, Integer size) {
        return moduloRepository.findAll(
                Pageable.ofSize(size).withPage(page)
        );
    }

    @Override
    public Modulo findById(Long id) {
        Optional<Modulo> byId = this.moduloRepository.findById(id);
        if (!byId.isPresent()){
            throw new ResourceNotFoundException("Modulo", id);
        }

        return byId.get();
    }

    @Override
    public Modulo create(Modulo modulo) {
        modulo.setId(null);
        return moduloRepository.save(modulo);
    }

    @Override
    public Modulo update(Long id, Modulo updated) {
        Modulo existing = findById(id);
        updated.setId(id);
        return moduloRepository.save(updated);
    }

    @Override
    public void delete(Long id) {
        Modulo modulo = findById(id);
        moduloRepository.delete(modulo);
    }
}
