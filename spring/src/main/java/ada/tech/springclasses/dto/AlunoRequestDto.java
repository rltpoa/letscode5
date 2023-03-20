package ada.tech.alunos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoRequestDto {
    private String nome;
    private String matricula;
    private String sexo;
}
