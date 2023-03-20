package ada.tech.alunos.service;

import ada.tech.alunos.dto.AlunoRequestDto;
import ada.tech.alunos.dto.AlunoResponseDto;
import ada.tech.alunos.exception.PerseveranceException;
import ada.tech.alunos.model.Aluno;
import ada.tech.alunos.persistance.AlunoRepository;
import org.apache.http.HttpStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class AlunoServiceImplementacao  implements AlunoService{
    @Inject
    protected AlunoRepository repositorio;

    @Transactional
    @Override
    public AlunoResponseDto gravarAluno(AlunoRequestDto request) {
        final Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setMatricula(request.getMatricula());
        aluno.setSexo(request.getSexo());

        repositorio.persist(aluno);

        return AlunoResponseDto.from(aluno);
    }

    @Transactional
    @Override
    public List<AlunoResponseDto> listarAlunos(Optional<String> prefixo) {
        List<Aluno> alunosEncontrados =  prefixo.isPresent() ? repositorio
                .findByNomeStartsWith(prefixo.get())
                : repositorio.listAll();

        return alunosEncontrados
                .stream()
                .map(aluno -> AlunoResponseDto.from(aluno))
                .collect(Collectors.toList());
    }
    @Transactional
    @Override
    public List<AlunoResponseDto> listarTodosAlunos() {
        return this.listarAlunos(Optional.empty());
    }

    @Transactional
    @Override
    public AlunoResponseDto buscarAluno(int id) throws PerseveranceException{
        Aluno alunoEncontrado = Optional
                .ofNullable(repositorio.findById(id))
                .orElseThrow(() -> new PerseveranceException(HttpStatus.SC_NOT_FOUND, "Aluno não encontrado"));

        return AlunoResponseDto
                .from(alunoEncontrado);
    }

    @Transactional
    @Override
    public void apagarAluno(int id) {
        repositorio.deleteById(id);
    }

    @Transactional
    @Override
    public AlunoResponseDto atualizarAluno(int id, AlunoRequestDto request) throws PerseveranceException  {
        Aluno alunoEncontrado = Optional
                .ofNullable(repositorio.findById(id))
                .orElseThrow(() -> new PerseveranceException(HttpStatus.SC_NOT_FOUND, "Aluno não encontrado"));
        alunoEncontrado.setNome(request.getNome());
        alunoEncontrado.setMatricula(request.getMatricula());
        alunoEncontrado.setSexo(request.getSexo());
        repositorio.persist(alunoEncontrado);
        return AlunoResponseDto.from(alunoEncontrado);
    }
}
