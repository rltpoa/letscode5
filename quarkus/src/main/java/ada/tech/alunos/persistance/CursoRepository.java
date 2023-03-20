package ada.tech.alunos.persistance;

import ada.tech.alunos.model.Aluno;
import ada.tech.alunos.model.Curso;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CursoRepository implements PanacheRepositoryBase<Curso, Integer> {
    public List<Curso> findByNomeStartsWith(String prefixo){
        return find("SELECT a FROM Curso AS a WHERE a.nome LIKE ':prefixo%'", Parameters.with("prefixo", prefixo))
                .list();
    }
}
