package ada.tech.alunos.controller;

import ada.tech.alunos.dto.CursoRequestDto;
import ada.tech.alunos.dto.ErrorResponseDto;
import ada.tech.alunos.exception.PerseveranceException;
import ada.tech.alunos.service.CursoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoController {
    @Inject
    protected CursoService cursoService;

    @GET
    public Response buscarCursos() {
        return Response
                .ok(this.cursoService.listarTodosCursos())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response buscarCurso(@PathParam("id") int id) {
        try {
            return Response
                    .ok(cursoService.buscarCurso(id))
                    .build();
        } catch (PerseveranceException perseveranceException){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    public Response criarCursos (final CursoRequestDto curso) {
        return Response
                .status(Response.Status.CREATED)
                .entity(this.cursoService.gravarCurso(curso))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response apagarCurso(@PathParam("id") int id) {
        this.cursoService.apagarCurso(id);
        return Response
                .noContent()
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCurso(@PathParam("id") int id, final CursoRequestDto curso) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(this.cursoService.atualizarCurso(id, curso))
                    .build();
        } catch (PerseveranceException perseveranceException) {
            return Response
                    .status(perseveranceException.getHttpStatus())
                    .entity(new ErrorResponseDto(perseveranceException.getMessage()))
                    .build();
        }
    }
}
