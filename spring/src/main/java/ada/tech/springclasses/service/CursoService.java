package ada.tech.alunos.service;

import ada.tech.alunos.dto.CursoRequestDto;
import ada.tech.alunos.dto.CursoResponseDto;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    CursoResponseDto gravarCurso(CursoRequestDto curso);
    List<CursoResponseDto> listarCursos(Optional<String> prefixo);
    CursoResponseDto buscarCurso(int id);
    void apagarCurso(int id);
    CursoResponseDto atualizarCurso(int id, CursoRequestDto curso);
}
