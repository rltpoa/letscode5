package ada.tech.alunos.persistance;


import ada.tech.alunos.model.Disciplina;
import ada.tech.alunos.model.Professor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;


import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProfessorRepository implements PanacheRepositoryBase<Professor, Integer> {
    public List<Professor> findByNomeStartsWith(String prefixo){
        return find("SELECT a FROM Professor AS a WHERE a.nome LIKE ':prefixo%'", Parameters.with("prefixo", prefixo))
                .list();
    }
}
