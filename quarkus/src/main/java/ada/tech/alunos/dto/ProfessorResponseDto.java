package ada.tech.alunos.dto;

import ada.tech.alunos.model.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
@AllArgsConstructor
public class ProfessorResponseDto {
    private int id;
    private String nome;
    private String titulo;
    private String sexo;

    public static ProfessorResponseDto from (Professor professor) {
        return new ProfessorResponseDto(
                professor.getId(),
                professor.getNome(),
                professor.getTitulo(),
                professor.getSexo()
        );
    }
}
