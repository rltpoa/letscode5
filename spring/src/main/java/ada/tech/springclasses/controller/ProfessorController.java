package ada.tech.alunos.controller;

import ada.tech.alunos.dto.ProfessorRequestDto;
import ada.tech.alunos.dto.ProfessorResponseDto;
import ada.tech.alunos.model.Professor;

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

    final private List<Professor> professores = new ArrayList();

    @GET
    public Response buscarProfessores() {
        return Response.ok(professores.stream().map(ProfessorResponseDto::from).collect(Collectors.toList())).build();
    }
    @GET
    @Path("/{id}")
    public Response buscarProfessor(@PathParam("id") int id) {
        final Optional<Professor> professor = professores
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst();
        if (professor.isPresent()) {
            return Response
                    .ok(professor.get())
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
    @POST
    public Response criarProfessor (final ProfessorRequestDto professor) {
        professores.add(new Professor(professores.size(), professor.getNome(), professor.getTitulo(), professor.getSexo()));
        return Response
                .status(Response.Status.CREATED)
                .entity(professores.get(professores.size()-1))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response apagarProfessor(@PathParam("id") int id) {
        final Optional<Professor> professor = professores
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst();
        if (professor.isPresent()) {
            professores.remove(professor.get());
            return Response
                    .ok(professor.get())
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
}
