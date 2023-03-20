package ada.tech.alunos.service;

import ada.tech.alunos.dto.DisciplinaRequestDto;
import ada.tech.alunos.dto.DisciplinaResponseDto;
import ada.tech.alunos.model.Disciplina;
import ada.tech.alunos.persistance.DisciplinaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DisciplinaServiceImplementacao implements DisciplinaService {
    final private DisciplinaRepository repositorio;

    public DisciplinaServiceImplementacao(DisciplinaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public DisciplinaResponseDto gravarDisciplina(DisciplinaRequestDto request) {
        final Disciplina disciplina = new Disciplina();
        disciplina.setNome(request.getNome());
        return DisciplinaResponseDto.from(repositorio.save(disciplina));
    }

    @Override
    public List<DisciplinaResponseDto> listarDisciplinas(Optional<String> prefixo) {
        final List<Disciplina> listaDisciplinas = prefixo.isPresent() ? repositorio.findByNomeStartsWith(prefixo.get()) : repositorio.findAll();
        return listaDisciplinas.stream().map(DisciplinaResponseDto::from).collect(Collectors.toList());
    }

    @Override
    public DisciplinaResponseDto buscarDisciplina(int id) {
        return DisciplinaResponseDto.from(repositorio
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrada")));
    }

    @Override
    public void apagarDisciplina(int id) {
        repositorio.deleteById(id);
    }

    @Override
    public DisciplinaResponseDto atualizarDisciplina(int id, DisciplinaRequestDto request) {
        final Disciplina disciplina = repositorio
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrado"));
        disciplina.setNome(request.getNome());
        return DisciplinaResponseDto.from(repositorio.save(disciplina));
    }
}
