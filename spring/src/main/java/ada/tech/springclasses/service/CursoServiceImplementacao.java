package ada.tech.alunos.service;

import ada.tech.alunos.dto.CursoRequestDto;
import ada.tech.alunos.dto.CursoResponseDto;
import ada.tech.alunos.model.Curso;
import ada.tech.alunos.persistance.CursoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CursoServiceImplementacao implements CursoService{
    final private CursoRepository repositorio;

    public CursoServiceImplementacao(CursoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public CursoResponseDto gravarCurso(CursoRequestDto request) {
        final Curso curso = new Curso();
        curso.setNome(request.getNome());
        return CursoResponseDto.from(repositorio.save(curso));
    }

    @Override
    public List<CursoResponseDto> listarCursos(Optional<String> prefixo) {
        final List<Curso> listaCursos = prefixo.isPresent() ? repositorio.findByNomeStartsWith(prefixo.get()) : repositorio.findAll();
        return listaCursos.stream().map(CursoResponseDto::from).collect(Collectors.toList());
    }

    @Override
    public CursoResponseDto buscarCurso(int id) {
        return CursoResponseDto.from(repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado")));
    }

    @Override
    public void apagarCurso(int id) {
        repositorio.deleteById(id);
    }

    @Override
    public CursoResponseDto atualizarCurso(int id, CursoRequestDto request) {
        final Curso curso = repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
        curso.setNome(request.getNome());
        return CursoResponseDto.from(repositorio.save(curso));
    }
}
