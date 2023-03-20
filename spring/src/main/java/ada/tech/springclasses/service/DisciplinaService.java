package ada.tech.alunos.service;

import ada.tech.alunos.dto.DisciplinaRequestDto;
import ada.tech.alunos.dto.DisciplinaResponseDto;

import java.util.List;
import java.util.Optional;

public interface DisciplinaService {
    DisciplinaResponseDto gravarDisciplina(DisciplinaRequestDto disciplina);
    List<DisciplinaResponseDto> listarDisciplinas(Optional<String> prefixo);
    DisciplinaResponseDto buscarDisciplina(int id);
    void apagarDisciplina(int id);
    DisciplinaResponseDto atualizarDisciplina(int id, DisciplinaRequestDto disciplina);
}
