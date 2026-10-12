package br.com.rm568156.cp.sistema_cursos.controller;

import br.com.rm568156.cp.sistema_cursos.dto.CursoRequest;
import br.com.rm568156.cp.sistema_cursos.dto.CursoResponse;
import br.com.rm568156.cp.sistema_cursos.mapper.CursoMapper;
import br.com.rm568156.cp.sistema_cursos.model.Curso;
import br.com.rm568156.cp.sistema_cursos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/cursos")
@Validated
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<Page<CursoResponse>> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<Curso> cursos = cursoService.findAll(page, size);
        Page<CursoResponse> response = cursos.map(CursoMapper::toResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> findById(@PathVariable Long id) {
        Curso curso = cursoService.findById(id);
        return ResponseEntity.ok(CursoMapper.toResponse(curso));
    }

    @PostMapping
    public ResponseEntity<CursoResponse> create(@RequestBody @Valid CursoRequest request) {
        Curso curso = new Curso();
        curso.setTitulo(request.titulo());
        curso.setDescricao(request.descricao());

        Curso salvo = cursoService.create(curso);
        return ResponseEntity.created(
                URI.create("/api/cursos/" + salvo.getId())
        ).body(CursoMapper.toResponse(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> update(@PathVariable Long id, @RequestBody @Valid CursoRequest request) {
        Curso curso = cursoService.findById(id);
        curso.setTitulo(request.titulo());
        curso.setDescricao(request.descricao());

        Curso atualizado = cursoService.update(id, curso);
        return ResponseEntity.ok(CursoMapper.toResponse(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}