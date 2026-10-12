package br.com.rm568156.cp.sistema_cursos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CursoRequest(

        @NotBlank(message = "O nome do curso não pode ser vazio!")
        @Size(max = 150, message = "O nome do curso deve ter no máximo 150 caracteres")
        String titulo,

        String descricao
) {
}
