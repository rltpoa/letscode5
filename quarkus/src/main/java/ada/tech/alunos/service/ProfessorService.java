package ada.tech.alunos.service;

import ada.tech.alunos.dto.ProfessorRequestDto;
import ada.tech.alunos.dto.ProfessorResponseDto;
import ada.tech.alunos.exception.PerseveranceException;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    ProfessorResponseDto gravarProfessor(ProfessorRequestDto professor);
    List<ProfessorResponseDto> listarProfessores(Optional<String> prefixo);
    List<ProfessorResponseDto> listarTodosProfessores();
    ProfessorResponseDto buscarProfessor(int id) throws PerseveranceException;
    void apagarProfessor(int id);
    ProfessorResponseDto atualizarProfessor(int id, ProfessorRequestDto professor) throws PerseveranceException;
}
