package ada.tech.alunos.controller;

import ada.tech.alunos.dto.DisciplinaRequestDto;
import ada.tech.alunos.dto.ErrorResponseDto;
import ada.tech.alunos.exception.PerseveranceException;
import ada.tech.alunos.service.DisciplinaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/disciplinas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DisciplinaController {

    @Inject
    protected DisciplinaService disciplinaService;

    @GET
    public Response buscarDiscplinas() {
        return Response
                .ok(this.disciplinaService.listarTodasDisciplinas())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response buscarDisciplina(@PathParam("id") int id) {
        try {
            return Response
                    .ok(disciplinaService.buscarDisciplina(id))
                    .build();
        } catch (PerseveranceException perseveranceException){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    public Response criarDisciplina(final DisciplinaRequestDto disciplina) {
        return Response
                .status(Response.Status.CREATED)
                .entity(this.disciplinaService.gravarDisciplina(disciplina))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response apagarDisciplina(@PathParam("id") int id) {
        this.disciplinaService.apagarDisciplina(id);
        return Response
                .noContent()
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarDisciplina(@PathParam("id") int id, final DisciplinaRequestDto disciplina) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(this.disciplinaService.atualizarDisciplina(id, disciplina))
                    .build();
        } catch (PerseveranceException perseveranceException) {
            return Response
                    .status(perseveranceException.getHttpStatus())
                    .entity(new ErrorResponseDto(perseveranceException.getMessage()))
                    .build();
        }
    }
}
