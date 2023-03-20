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
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nome;
    private String matricula;
    private String sexo;

    @ManyToOne
    @JoinColumn(name ="curso_id", nullable = false)
    private Curso curso;

    @ManyToMany(mappedBy = "alunos")
    private List<Disciplina> disciplinas;

}
