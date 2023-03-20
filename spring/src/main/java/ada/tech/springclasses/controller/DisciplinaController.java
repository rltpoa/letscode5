package ada.tech.alunos.controller;

import ada.tech.alunos.dto.DisciplinaRequestDto;
import ada.tech.alunos.dto.DisciplinaResponseDto;
import ada.tech.alunos.model.Disciplina;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/disciplinas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DisciplinaController {

    final private List<Disciplina> disciplinas = new ArrayList();

    @GET
    public Response buscarDiscplinas() {
        return Response
                .ok(disciplinas.stream()
                        .map(DisciplinaResponseDto::from)
                        .collect(Collectors.toList()))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response buscarDisciplina(@PathParam("id") int id) {
        final Optional<Disciplina> disciplina = disciplinas
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst();
        if (disciplina.isPresent()) {
            return Response
                    .ok(disciplina.get())
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
    @POST
    public Response criarDiscplinas (final DisciplinaRequestDto disciplina) {
        disciplinas.add(new Disciplina(disciplinas.size(), disciplina.getNome()));
        return Response
                .status(Response.Status.CREATED)
                .entity(disciplinas.get(disciplinas.size()-1))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response apagarDiscplina(@PathParam("id") int id) {
        final Optional<Disciplina> disciplina = disciplinas.stream().filter(it -> it.getId() == id).findFirst();
        if (disciplina.isPresent()) {
            disciplinas.remove(disciplina.get());
            return Response.ok(disciplina.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
