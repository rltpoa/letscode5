package ada.tech.alunos.controller;

import ada.tech.alunos.dto.DisciplinaRequestDto;
import ada.tech.alunos.dto.ErrorResponseDto;
import ada.tech.alunos.dto.ProfessorRequestDto;
import ada.tech.alunos.dto.ProfessorResponseDto;
import ada.tech.alunos.exception.PerseveranceException;
import ada.tech.alunos.model.Professor;
import ada.tech.alunos.service.DisciplinaService;
import ada.tech.alunos.service.ProfessorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/professores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfessorController {

    @Inject
    protected ProfessorService professorService;

    @GET
    public Response buscarDiscplinas() {
        return Response
                .ok(this.professorService.listarTodosProfessores())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response buscarProfessor(@PathParam("id") int id) {
        try {
            return Response
                    .ok(professorService.buscarProfessor(id))
                    .build();
        } catch (PerseveranceException perseveranceException){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    public Response criarDisciplina(final ProfessorRequestDto professor) {
        return Response
                .status(Response.Status.CREATED)
                .entity(this.professorService.gravarProfessor(professor))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response apagarProfessor(@PathParam("id") int id) {
        this.professorService.apagarProfessor(id);
        return Response
                .noContent()
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarProfessor(@PathParam("id") int id, final ProfessorRequestDto professor) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(this.professorService.atualizarProfessor(id, professor))
                    .build();
        } catch (PerseveranceException perseveranceException) {
            return Response
                    .status(perseveranceException.getHttpStatus())
                    .entity(new ErrorResponseDto(perseveranceException.getMessage()))
                    .build();
        }
    }
}
