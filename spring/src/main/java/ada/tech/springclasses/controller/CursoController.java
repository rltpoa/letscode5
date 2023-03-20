package ada.tech.alunos.controller;

import ada.tech.alunos.dto.CursoRequestDto;
import ada.tech.alunos.dto.CursoResponseDto;
import ada.tech.alunos.model.Curso;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoController {
    final private List<Curso> cursos = new ArrayList();

    @GET
    public Response buscarCursos() {
        return Response
                .ok(cursos.stream()
                        .map(CursoResponseDto::from)
                        .collect(Collectors.toList()))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response buscarCurso(@PathParam("id") int id) {
        final Optional<Curso> curso = cursos
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst();
        if (curso.isPresent()) {
            return Response
                    .ok(curso.get())
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    public Response criarCursos (final CursoRequestDto curso) {
        cursos.add(new Curso(cursos.size(), curso.getNome(),curso.getDescricao(),curso.getDuracao()));
        return Response
                .status(Response.Status.CREATED)
                .entity(cursos.get(cursos.size()-1))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response apagarCurso(@PathParam("id") int id) {
        final Optional<Curso> curso = cursos
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst();
        if (curso.isPresent()) {
            cursos.remove(curso.get());
            return Response
                    .ok(curso.get())
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
}
