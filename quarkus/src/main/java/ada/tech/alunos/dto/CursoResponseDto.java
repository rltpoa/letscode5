package ada.tech.alunos.dto;

import ada.tech.alunos.model.Curso;
import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponseDto {
    private int id;
    private String nome;
    private String descricao;
    private Double duracao;

    public static CursoResponseDto from (Curso curso) {
        final CursoResponseDtoBuilder response = new CursoResponseDtoBuilder();
        response.id = curso.getId();
        response.nome = curso.getNome();
        response.descricao = curso.getDescricao();
        response.duracao = curso.getDuracao();
        return response.build();
    }
}
