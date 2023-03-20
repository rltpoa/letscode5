package ada.tech.alunos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    @ManyToOne
    @JoinColumn(name ="professor_id", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name ="curso_id", nullable = false)
    private Curso curso;

    @ManyToMany
    @JoinTable(name = "disciplinas_alunos",
                joinColumns = @JoinColumn(name ="disciplina_fk"),
                inverseJoinColumns = @JoinColumn(name = "aluno_fk"))
    private List<Aluno> alunos;

}
