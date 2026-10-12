package br.com.rm568156.cp.sistema_cursos.dto;

public record ModuloResponse(Long id, Long CursoId, String titulo, Integer ordem, Integer cargaHoraria) {
}
