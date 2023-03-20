package ada.tech.alunos.service;

import ada.tech.alunos.dto.CursoRequestDto;
import ada.tech.alunos.dto.CursoResponseDto;
import ada.tech.alunos.exception.PerseveranceException;
import ada.tech.alunos.model.Curso;
import ada.tech.alunos.persistance.CursoRepository;
import org.apache.http.HttpStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CursoServiceImplementacao implements CursoService{
    @Inject
    protected  CursoRepository repositorio;

    @Transactional
    @Override
    public CursoResponseDto gravarCurso(CursoRequestDto request) {
        final Curso curso = new Curso();
        curso.setNome(request.getNome());
        repositorio.persist(curso);
        return CursoResponseDto.from(curso);
    }

    @Transactional
    @Override
    public List<CursoResponseDto> listarCursos(Optional<String> prefixo) {
        List<Curso> cursosEncontrados =  prefixo.isPresent() ? repositorio
                .findByNomeStartsWith(prefixo.get())
                : repositorio.listAll();

        return cursosEncontrados
                .stream()
                .map(curso -> CursoResponseDto.from(curso))
                .collect(Collectors.toList());
    }
    @Transactional
    @Override
    public List<CursoResponseDto> listarTodosCursos() {
        return this.listarCursos(Optional.empty());
    }

    @Override
    public CursoResponseDto buscarCurso(int id) throws PerseveranceException {
        Curso cursoEncontrado = Optional
                .ofNullable(repositorio.findById(id))
                .orElseThrow(() -> new PerseveranceException(HttpStatus.SC_NOT_FOUND, "Curso não encontrado"));

        return CursoResponseDto
                .from(cursoEncontrado);

    }

    @Transactional
    @Override
    public void apagarCurso(int id) {
        repositorio.deleteById(id);
    }

    @Transactional
    @Override
    public CursoResponseDto atualizarCurso(int id, CursoRequestDto request) throws PerseveranceException {
        Curso cursoEncontrado = Optional
                .ofNullable(repositorio.findById(id))
                .orElseThrow(() -> new PerseveranceException(HttpStatus.SC_NOT_FOUND, "Aluno não encontrado"));
        cursoEncontrado.setNome(request.getNome());
        cursoEncontrado.setDescricao(request.getDescricao());
        cursoEncontrado.setDuracao(request.getDuracao());
        repositorio.persist(cursoEncontrado);
        return CursoResponseDto.from(cursoEncontrado);
    }
}
