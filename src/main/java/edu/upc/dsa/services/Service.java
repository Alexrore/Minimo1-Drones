package edu.upc.dsa.services;

import edu.upc.dsa.util.Manager;
import edu.upc.dsa.util.ManagerImpl;
import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/", description = "Endpoints del servicio DSA Minimo1")
@Path("/")
public class Service {
    private final Manager manager;

    public Service() {
        this.manager = ManagerImpl.getInstance();
        if (manager.size()== 0){
            Dron d1 = this.manager.añadirDron("1", "Dron 1", "Fabrica 1", "Modelo 1");
            d1.setHorasVuelo(20);
            Dron d2 = this.manager.añadirDron("2", "Dron 2", "Fabrica 2", "Modelo 2");
            d2.setHorasVuelo(40);
            Dron d3 = this.manager.añadirDron("3", "Dron 3", "Fabrica 3", "Modelo 3");
            d3.setHorasVuelo(60);
            Dron d4 = this.manager.añadirDron("4", "Dron 4", "Fabrica 4", "Modelo 4");
            d4.setHorasVuelo(80);

            Piloto p1 = this.manager.añadirPiloto("1", "Nombre 1", "Apellidos 1");
            p1.setHorasVuelo(80);
            Piloto p2 = this.manager.añadirPiloto("2", "Nombre 2", "Apellidos 2");
            p2.setHorasVuelo(60);
            Piloto p3 = this.manager.añadirPiloto("3", "Nombre 3", "Apellidos 3");
            p3.setHorasVuelo(40);
            Piloto p4 = this.manager.añadirPiloto("4", "Nombre 4", "Apellidos 4");
            p4.setHorasVuelo(20);
        }
    }

    // ---------- DRONES ----------

    @GET
    @Path("/drones")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Lista de drones", notes = "Ordenados descendentemente por horas de vuelo")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK") })
    public Response getDrones() {
        List<Dron> drones = this.manager.dornesPorHorasDeVueloDesc();
        GenericEntity<List<Dron>> entity = new GenericEntity<List<Dron>>(drones) {};
        return Response.ok(entity).build(); // 200
    }

    @POST
    @Path("/drones")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Añadir dron")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Creado"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public Response createDron(Dron body) {
        if (body == null || body.getId() == null || body.getNombre() == null
                || body.getFabricante() == null || body.getModelo() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Dron created = this.manager.añadirDron(body.getId(), body.getNombre(),
                body.getFabricante(), body.getModelo());
        return Response.status(Response.Status.CREATED).entity(created).build(); // 201
    }

    @POST
    @Path("/drones/almacen/{id}")
    @ApiOperation(value = "Poner dron en mantenimiento")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No existe"),
            @ApiResponse(code = 409, message = "Ya estaba en mantenimiento")
    })
    public Response putInMaintenance(@PathParam("id") String id) {
        Dron d = this.manager.getDron(id);
        if (d == null) return Response.status(Response.Status.NOT_FOUND).build();
        if (d.isMantenimiento()) return Response.status(Response.Status.CONFLICT).build();
        this.manager.guardarDronAlmacen(id);
        return Response.ok().build();
    }

    @POST
    @Path("/drones/reparar")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Reparar el primer dron en cola")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Cola vacía")
    })
    public Response reparar() {
        Dron d = this.manager.repararDron();
        if (d == null) return Response.noContent().build(); // 204
        return Response.ok(d).build(); // 200
    }

    // ---------- PILOTOS ----------

    @GET
    @Path("/pilotos")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Lista de pilotos", notes = "Ordenados descendentemente por horas de vuelo")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK") })
    public Response getPilotos() {
        List<Piloto> pilotos = this.manager.pilotosPorHorasDeVueloDesc();
        GenericEntity<List<Piloto>> entity = new GenericEntity<List<Piloto>>(pilotos) {};
        return Response.ok(entity).build(); // 200
    }

    @POST
    @Path("/pilotos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Añadir piloto")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Creado"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public Response createPiloto(Piloto body) {
        if (body == null || body.getId() == null || body.getNombre() == null || body.getApellidos() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Piloto created = this.manager.añadirPiloto(body.getId(), body.getNombre(), body.getApellidos());
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

}
