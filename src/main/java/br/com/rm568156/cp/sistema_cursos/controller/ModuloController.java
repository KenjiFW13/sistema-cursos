package br.com.rm568156.cp.sistema_cursos.controller;

import br.com.rm568156.cp.sistema_cursos.dto.ModuloRequest;
import br.com.rm568156.cp.sistema_cursos.dto.ModuloResponse;
import br.com.rm568156.cp.sistema_cursos.mapper.ModuloMapper;
import br.com.rm568156.cp.sistema_cursos.model.Curso;
import br.com.rm568156.cp.sistema_cursos.model.Modulo;
import br.com.rm568156.cp.sistema_cursos.service.CursoService;
import br.com.rm568156.cp.sistema_cursos.service.ModuloService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/cursos/{cursoId}/modulos")
@Validated
public class ModuloController {

    private final ModuloService moduloService;
    private final CursoService cursoService;

    public ModuloController(ModuloService moduloService, CursoService cursoService) {
        this.moduloService = moduloService;
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<Page<ModuloResponse>> findAll(
            @PathVariable Long cursoId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        cursoService.findById(cursoId);

        Page<Modulo> modulos = moduloService.findAll(page, size);
        Page<ModuloResponse> response = modulos.map(ModuloMapper::toResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuloResponse> findById(@PathVariable Long cursoId, @PathVariable Long id) {
        cursoService.findById(cursoId);
        Modulo modulo = moduloService.findById(id);
        return ResponseEntity.ok(ModuloMapper.toResponse(modulo));
    }

    @PostMapping
    public ResponseEntity<ModuloResponse> create(
            @PathVariable Long cursoId,
            @RequestBody @Valid ModuloRequest request) {

        Curso curso = cursoService.findById(cursoId);

        Modulo modulo = new Modulo();
        modulo.setTitulo(request.titulo());
        modulo.setOrdem(request.ordem());
        modulo.setCargaHoraria(request.cargaHoraria());
        modulo.setCurso(curso);

        Modulo salvo = moduloService.create(modulo);

        return ResponseEntity.created(
                URI.create("/api/cursos/" + cursoId + "/modulos/" + salvo.getId())
        ).body(ModuloMapper.toResponse(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuloResponse> update(
            @PathVariable Long cursoId,
            @PathVariable Long id,
            @RequestBody @Valid ModuloRequest request) {

        cursoService.findById(cursoId);

        Modulo modulo = moduloService.findById(id);
        modulo.setTitulo(request.titulo());
        modulo.setOrdem(request.ordem());
        modulo.setCargaHoraria(request.cargaHoraria());

        Modulo atualizado = moduloService.update(id, modulo);
        return ResponseEntity.ok(ModuloMapper.toResponse(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long cursoId, @PathVariable Long id) {
        cursoService.findById(cursoId);
        moduloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}