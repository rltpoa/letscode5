package ada.tech.alunos.persistance;

import ada.tech.alunos.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    List<Professor> findByNomeStartsWith(String prefixo);
}
