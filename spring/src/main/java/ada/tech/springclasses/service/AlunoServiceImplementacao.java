package ada.tech.alunos.service;

import ada.tech.alunos.dto.AlunoRequestDto;
import ada.tech.alunos.dto.AlunoResponseDto;
import ada.tech.alunos.model.Aluno;
import ada.tech.alunos.persistance.AlunoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AlunoServiceImplementacao  implements AlunoService{
    final private AlunoRepository repositorio;

    public AlunoServiceImplementacao(AlunoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public AlunoResponseDto gravarAluno(AlunoRequestDto request) {
        final Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        return AlunoResponseDto.from(repositorio.save(aluno));
    }

    @Override
    public List<AlunoResponseDto> listarAlunos(Optional<String> prefixo) {
        final List<Aluno> listaAlunos = prefixo.isPresent() ? repositorio.findByNomeStartsWith(prefixo.get()) : repositorio.findAll();
        return listaAlunos.stream().map(AlunoResponseDto::from).collect(Collectors.toList());
    }

    @Override
    public AlunoResponseDto buscarAluno(int id) {
        return AlunoResponseDto.from(repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado")));
    }

    @Override
    public void apagarAluno(int id) {
        repositorio.deleteById(id);
    }

    @Override
    public AlunoResponseDto atualizarAluno(int id, AlunoRequestDto request) {
        final Aluno aluno = repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        aluno.setNome(request.getNome());
        return AlunoResponseDto.from(repositorio.save(aluno));
    }
}
