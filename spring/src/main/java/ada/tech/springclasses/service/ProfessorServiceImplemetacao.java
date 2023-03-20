package ada.tech.alunos.service;

import ada.tech.alunos.dto.ProfessorRequestDto;
import ada.tech.alunos.dto.ProfessorResponseDto;
import ada.tech.alunos.model.Professor;
import ada.tech.alunos.persistance.ProfessorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProfessorServiceImplemetacao implements ProfessorService{
    final private ProfessorRepository repositorio;

    public ProfessorServiceImplemetacao(ProfessorRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public ProfessorResponseDto gravarProfessor(ProfessorRequestDto request) {
        final Professor professor = new Professor();
        professor.setNome(request.getNome());
        return ProfessorResponseDto.from(repositorio.save(professor));
    }

    @Override
    public List<ProfessorResponseDto> listarProfessores(Optional<String> prefixo) {
        final List<Professor> listaProfessores = prefixo.isPresent() ? repositorio.findByNomeStartsWith(prefixo.get()) : repositorio.findAll();
        return listaProfessores.stream().map(ProfessorResponseDto::from).collect(Collectors.toList());
    }

    @Override
    public ProfessorResponseDto buscarProfessor(int id) {
        return ProfessorResponseDto.from(repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado")));
    }

    @Override
    public void apagarProfessor(int id) {
        repositorio.deleteById(id);
    }

    @Override
    public ProfessorResponseDto atualizarProfessor(int id, ProfessorRequestDto request) {
        final Professor professor = repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));
        professor.setNome(request.getNome());
        return ProfessorResponseDto.from(repositorio.save(professor));
    }
}
