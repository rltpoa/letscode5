package ada.tech.alunos.dto;

import ada.tech.alunos.model.Aluno;
import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoResponseDto {
    private int id;
    private String nome;
    private String matricula;
    private String sexo;

    public static AlunoResponseDto from (Aluno aluno) {
        final AlunoResponseDtoBuilder response = new AlunoResponseDtoBuilder();
        response.id = aluno.getId();
        response.nome = aluno.getNome();
        response.matricula = aluno.getMatricula();
        response.sexo =  aluno.getSexo();
        return response.build();
    }

    @Override
    public String toString() {
        return "O meu nome é: " + nome;
    }
}
