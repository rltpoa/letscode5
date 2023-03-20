package ada.tech.alunos.service;

import ada.tech.alunos.dto.DisciplinaRequestDto;
import ada.tech.alunos.dto.DisciplinaResponseDto;
import ada.tech.alunos.exception.PerseveranceException;
import ada.tech.alunos.model.Disciplina;
import ada.tech.alunos.persistance.DisciplinaRepository;
import org.apache.http.HttpStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class DisciplinaServiceImplementacao implements DisciplinaService {
    @Inject
    protected DisciplinaRepository repositorio;

    @Transactional
    @Override
    public DisciplinaResponseDto gravarDisciplina(DisciplinaRequestDto request) {
        final Disciplina disciplina = new Disciplina();
        disciplina.setNome(request.getNome());
        repositorio.persist(disciplina);

        return DisciplinaResponseDto.from(disciplina);
    }

    @Transactional
    @Override
    public List<DisciplinaResponseDto> listarDisciplinas(Optional<String> prefixo) {
        List<Disciplina> discipliansEncontradas =  prefixo.isPresent() ? repositorio
                .findByNomeStartsWith(prefixo.get())
                : repositorio.listAll();

        return discipliansEncontradas
                .stream()
                .map(disciplinas -> DisciplinaResponseDto.from(disciplinas))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public List<DisciplinaResponseDto> listarTodasDisciplinas() {
        return this.listarDisciplinas(Optional.empty());
    }

    @Transactional
    @Override
    public DisciplinaResponseDto buscarDisciplina(int id) throws PerseveranceException {
      Disciplina disciplinaEncontrada = Optional
                .ofNullable(repositorio.findById(id))
                .orElseThrow(() -> new PerseveranceException(HttpStatus.SC_NOT_FOUND, "Aluno não encontrado"));

        return DisciplinaResponseDto
                .from(disciplinaEncontrada);
    }

    @Transactional
    @Override
    public void apagarDisciplina(int id) {
        repositorio.deleteById(id);
    }

    @Transactional
    @Override
    public DisciplinaResponseDto atualizarDisciplina(int id, DisciplinaRequestDto request) throws PerseveranceException{
        Disciplina disciplinaEncontrada = Optional
                .ofNullable(repositorio.findById(id))
                .orElseThrow(() -> new PerseveranceException(HttpStatus.SC_NOT_FOUND, "Disciplina não encontrada"));
        disciplinaEncontrada.setNome(request.getNome());
        repositorio.persist(disciplinaEncontrada);
        return DisciplinaResponseDto.from(disciplinaEncontrada);
    }
}
