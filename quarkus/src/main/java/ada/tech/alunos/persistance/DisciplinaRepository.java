package ada.tech.alunos.persistance;

import ada.tech.alunos.model.Disciplina;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DisciplinaRepository implements PanacheRepositoryBase<Disciplina, Integer> {
    public List<Disciplina> findByNomeStartsWith(String prefixo){
        return find("SELECT a FROM Disciplina AS a WHERE a.nome LIKE ':prefixo%'", Parameters.with("prefixo", prefixo))
                .list();
    }
}
