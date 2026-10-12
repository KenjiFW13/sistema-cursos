package br.com.rm568156.cp.sistema_cursos.dto;

import br.com.rm568156.cp.sistema_cursos.validation.MultipleOf;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ModuloRequest(

        @NotBlank(message = "Título do módulo não pode ser vazio!")
        @Size(max = 150, message = "Título não pode ter mais de 150 caracteres")
        String titulo,

        @NotNull(message = "Ordem não pode ser vazio!")
        @Positive
        Integer ordem,

        @NotNull(message = "Carga horária não pode ser nula")
        @Positive(message = "A carga horária deve ser positiva")
        @MultipleOf(5)
        Integer cargaHoraria
) {
}
