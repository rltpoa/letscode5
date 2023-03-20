package ada.tech.alunos.service;

import ada.tech.alunos.dto.ProfessorRequestDto;
import ada.tech.alunos.dto.ProfessorResponseDto;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    ProfessorResponseDto gravarProfessor(ProfessorRequestDto professor);
    List<ProfessorResponseDto> listarProfessores(Optional<String> prefixo);
    ProfessorResponseDto buscarProfessor(int id);
    void apagarProfessor(int id);
    ProfessorResponseDto atualizarProfessor(int id, ProfessorRequestDto professor);
}
