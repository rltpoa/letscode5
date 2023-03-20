package ada.tech.alunos.dto;

import ada.tech.alunos.model.Disciplina;
import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaResponseDto {
    private int id;
    private String nome;

    public static DisciplinaResponseDto from (Disciplina disciplina) {
        final DisciplinaResponseDtoBuilder response = new DisciplinaResponseDtoBuilder();
        response.id = disciplina.getId();
        response.nome = disciplina.getNome();
        return response.build();
    }
}