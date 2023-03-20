package ada.tech.alunos.service;

import ada.tech.alunos.dto.DisciplinaRequestDto;
import ada.tech.alunos.dto.DisciplinaResponseDto;
import ada.tech.alunos.exception.PerseveranceException;

import java.util.List;
import java.util.Optional;

public interface DisciplinaService {
    DisciplinaResponseDto gravarDisciplina(DisciplinaRequestDto disciplina);
    List<DisciplinaResponseDto> listarDisciplinas(Optional<String> prefixo);
    List<DisciplinaResponseDto> listarTodasDisciplinas();
    DisciplinaResponseDto buscarDisciplina(int id) throws PerseveranceException;
    void apagarDisciplina(int id);
    DisciplinaResponseDto atualizarDisciplina(int id, DisciplinaRequestDto disciplina) throws PerseveranceException;
}
