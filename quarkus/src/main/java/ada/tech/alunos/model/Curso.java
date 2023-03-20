package ada.tech.alunos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descricao;
    private Double duracao;

    @OneToMany(mappedBy = "curso")
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "curso")
    private List<Aluno> alunos;

}
