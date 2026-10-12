package br.com.rm568156.cp.sistema_cursos.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " com id "+id+" não encontrado");
    }
}
