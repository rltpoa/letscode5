package ada.tech.alunos.persistance;

import ada.tech.alunos.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    List<Disciplina> findByNomeStartsWith(String prefixo);
}
