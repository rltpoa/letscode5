package ada.tech.alunos.service;

import ada.tech.alunos.dto.AlunoRequestDto;
import ada.tech.alunos.dto.AlunoResponseDto;
import ada.tech.alunos.exception.PerseveranceException;

import java.util.List;
import java.util.Optional;

public interface AlunoService {
    AlunoResponseDto gravarAluno(AlunoRequestDto aluno);
    List<AlunoResponseDto> listarAlunos(Optional<String> prefixo);
    List<AlunoResponseDto> listarTodosAlunos();
    AlunoResponseDto buscarAluno(int id) throws PerseveranceException;
    void apagarAluno(int id);
    AlunoResponseDto atualizarAluno(int id, AlunoRequestDto aluno) throws PerseveranceException;
}
