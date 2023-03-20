package ada.tech.alunos.persistance;

import ada.tech.alunos.model.Aluno;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AlunoRepository implements PanacheRepositoryBase<Aluno, Integer> {
    public List<Aluno> findByNomeStartsWith(String prefixo){
        return find("SELECT a FROM Aluno AS a WHERE a.nome LIKE ':prefixo%'", Parameters.with("prefixo", prefixo))
                .list();
    }

}
