package ada.tech.alunos.persistance;

import ada.tech.alunos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    List<Curso> findByNomeStartsWith(String prefixo);
}
