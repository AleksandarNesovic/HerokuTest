package com.nesovic.narudzbina.resource;

import java.net.URI;
import java.util.ArrayList;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.nesovic.narudzbina.model.GlavnoJelo;
import com.nesovic.narudzbina.service.ServiceGlavnojelo;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Path("/glavnojelo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api("/Glavno jelo")
@SwaggerDefinition(tags= {@Tag(name="/Glavnojelo",description="REST Endpoints for Glavno jelo")})
public class GlavnoJeloResource {
	
	ServiceGlavnojelo glavno=new ServiceGlavnojelo();
	
	@GET
	public ArrayList<GlavnoJelo> getJela(){
		return glavno.svaJela();
	}
	@GET
	@Path("/{id}")
	public GlavnoJelo getJeloById(@PathParam("id") int id) {
		return glavno.selectJelaById(id);
	}
	@GET
	@Path("/sort")
	public Response sortJelabyCena() {
		ArrayList<GlavnoJelo> sortLista=glavno.sortJelaByCena();
		return Response.ok().entity(sortLista).build();
	}
	@POST
	public Response addJelo(GlavnoJelo g,@Context UriInfo uriInfo) {
		GlavnoJelo glavnoJelo=glavno.insertGlavnoJelo(g);
		String idJela=String.valueOf(glavnoJelo.getId_glj());
		URI uri=uriInfo.getAbsolutePathBuilder().path(idJela).build();
		return Response.created(uri).entity(glavnoJelo).build();
	}
	@DELETE
	@Path("/{id}")
	public Response deleteJelo(@PathParam("id") int id) {
		glavno.deleteGlavnoJelo(id);
		return Response.noContent().build();
		
	}
	@PUT
	@Path("/{id}")
	public Response updateJelo(@PathParam("id") int id,GlavnoJelo g,@Context UriInfo uriInfo) {
		g.setId_glj(id);
		glavno.updateGlavnoJelo(g);
		String idJela=String.valueOf(g.getId_glj());
		URI uri=uriInfo.getAbsolutePathBuilder().path(idJela).build();
		return Response.created(uri).entity(g).build();
	}

}
