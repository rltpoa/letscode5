package ada.tech.alunos.service;

import ada.tech.alunos.dto.CursoRequestDto;
import ada.tech.alunos.dto.CursoResponseDto;
import ada.tech.alunos.exception.PerseveranceException;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    CursoResponseDto gravarCurso(CursoRequestDto curso);
    List<CursoResponseDto> listarCursos(Optional<String> prefixo);
    List<CursoResponseDto> listarTodosCursos();
    CursoResponseDto buscarCurso(int id) throws PerseveranceException;
    void apagarCurso(int id);
    CursoResponseDto atualizarCurso(int id, CursoRequestDto curso) throws PerseveranceException;
}
