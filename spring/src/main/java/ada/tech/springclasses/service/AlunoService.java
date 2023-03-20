package ada.tech.alunos.service;

import ada.tech.alunos.dto.AlunoRequestDto;
import ada.tech.alunos.dto.AlunoResponseDto;

import java.util.List;
import java.util.Optional;

public interface AlunoService {
    AlunoResponseDto gravarAluno(AlunoRequestDto aluno);
    List<AlunoResponseDto> listarAlunos(Optional<String> prefixo);
    AlunoResponseDto buscarAluno(int id);
    void apagarAluno(int id);
    AlunoResponseDto atualizarAluno(int id, AlunoRequestDto aluno);
}
