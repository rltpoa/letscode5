package ada.tech.alunos.controller;

import ada.tech.alunos.dto.AlunoRequestDto;
import ada.tech.alunos.dto.ErrorResponseDto;
import ada.tech.alunos.exception.PerseveranceException;
import ada.tech.alunos.service.AlunoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunosController {
    @Inject
    protected AlunoService alunoService;

    @GET
    public Response buscarAlunos() {
        return Response
                .ok(this.alunoService.listarTodosAlunos())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response buscarAluno(@PathParam("id") int id) {
        try {
            return Response
                    .ok(alunoService.buscarAluno(id))
                    .build();
        } catch (PerseveranceException perseveranceException){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    public Response criarAluno(final AlunoRequestDto aluno) {
        return Response
                .status(Response.Status.CREATED)
                .entity(this.alunoService.gravarAluno(aluno))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response apagarAluno(@PathParam("id") int id) {
        this.alunoService.apagarAluno(id);
        return Response
                .noContent()
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarAluno(@PathParam("id") int id, final AlunoRequestDto aluno) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(this.alunoService.atualizarAluno(id, aluno))
                    .build();
        } catch (PerseveranceException perseveranceException) {
            return Response
                    .status(perseveranceException.getHttpStatus())
                    .entity(new ErrorResponseDto(perseveranceException.getMessage()))
                    .build();
        }
    }
}


