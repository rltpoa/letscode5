package ada.tech.alunos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoRequestDto {
    @NotNull
    private String nome;
    private String matricula;
    private String sexo;
}
