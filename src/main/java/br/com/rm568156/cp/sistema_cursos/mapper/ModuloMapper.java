package br.com.rm568156.cp.sistema_cursos.mapper;

import br.com.rm568156.cp.sistema_cursos.dto.ModuloResponse;
import br.com.rm568156.cp.sistema_cursos.model.Modulo;

public class ModuloMapper {

    private ModuloMapper() {
    }

    public static ModuloResponse toResponse(Modulo modulo) {
        return new ModuloResponse(
                modulo.getId(),
                modulo.getCurso().getId(),
                modulo.getTitulo(),
                modulo.getOrdem(),
                modulo.getCargaHoraria()
        );

    }
}
