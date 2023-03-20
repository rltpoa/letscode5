package ada.tech.alunos.service;

import ada.tech.alunos.dto.ProfessorRequestDto;
import ada.tech.alunos.dto.ProfessorResponseDto;
import ada.tech.alunos.exception.PerseveranceException;
import ada.tech.alunos.model.Professor;
import ada.tech.alunos.persistance.ProfessorRepository;
import org.apache.http.HttpStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ApplicationScoped
public class ProfessorServiceImplemetacao implements ProfessorService{
    @Inject
    protected ProfessorRepository repositorio;

    @Transactional
    @Override
    public ProfessorResponseDto gravarProfessor(ProfessorRequestDto request) {
        final Professor professor = new Professor();
        professor.setNome(request.getNome());
        professor.setTitulo(request.getTitulo());
        professor.setSexo(request.getSexo());
        repositorio.persist(professor);
        return ProfessorResponseDto.from(professor);
    }

    @Transactional
    @Override
    public List<ProfessorResponseDto> listarProfessores(Optional<String> prefixo) {
        List<Professor> professoresEncontrados =  prefixo.isPresent() ? repositorio
                .findByNomeStartsWith(prefixo.get())
                : repositorio.listAll();

        return professoresEncontrados
                .stream()
                .map(professor -> ProfessorResponseDto.from(professor))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<ProfessorResponseDto> listarTodosProfessores() {
        return this.listarProfessores(Optional.empty());
    }

    @Transactional
    @Override
    public ProfessorResponseDto buscarProfessor(int id) throws PerseveranceException {
        Professor professorEncontrado = Optional
                .ofNullable(repositorio.findById(id))
                .orElseThrow(() -> new PerseveranceException(HttpStatus.SC_NOT_FOUND, "Aluno não encontrado"));

        return ProfessorResponseDto
                .from(professorEncontrado);
    }

    @Transactional
    @Override
    public void apagarProfessor(int id) {
        repositorio.deleteById(id);
    }

    @Transactional
    @Override
    public ProfessorResponseDto atualizarProfessor(int id, ProfessorRequestDto request) throws PerseveranceException{
        Professor professorEncontrado = Optional
                .ofNullable(repositorio.findById(id))
                .orElseThrow(() -> new PerseveranceException(HttpStatus.SC_NOT_FOUND, "Professor não encontrado"));
        professorEncontrado.setNome(request.getNome());
        professorEncontrado.setTitulo(request.getTitulo());
        professorEncontrado.setSexo(request.getSexo());
        repositorio.persist(professorEncontrado);
        return ProfessorResponseDto.from(professorEncontrado);
    }
}
