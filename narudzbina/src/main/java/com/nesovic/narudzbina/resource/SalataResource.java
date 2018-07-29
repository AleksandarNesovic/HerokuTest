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

import com.nesovic.narudzbina.model.Salata;
import com.nesovic.narudzbina.service.ServiceSalata;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Path("/salata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api("/Salata")
@SwaggerDefinition(tags= {@Tag(name="/Salata",description="REST Endpoints for Salate")})
public class SalataResource {
	
	ServiceSalata salata=new ServiceSalata();
	
	@GET
	public ArrayList<Salata> getSalata(){
		return salata.sveSalate();
	}
	@GET
	@Path("/{id}")
	public Salata getSalataById(@PathParam("id") int id) {
		return salata.selsectSalataById(id);
	}
	@POST
	public Response addSalata(Salata sal,@Context UriInfo uriInfo) {
		Salata novaSalata=salata.insertSalata(sal);
		String idSal=String.valueOf(novaSalata.getId_sal());
		URI uri=uriInfo.getAbsolutePathBuilder().path(idSal).build();
		return Response.created(uri).entity(novaSalata).build();
	}
	@DELETE
	@Path("/{id}")
	public Response deleteSalata(@PathParam("id") int id) {
		salata.deleteSalata(id);
		return Response.noContent().build();
		
	}
	@PUT
	@Path("/{id}")
	public Response updateSalata(@PathParam("id") int id,Salata sal,@Context UriInfo uriInfo) {
		sal.setId_sal(id);
		salata.updateSalata(sal);
		String idSal=String.valueOf(sal.getId_sal());
		URI uri=uriInfo.getAbsolutePathBuilder().path(idSal).build();
		return Response.created(uri).entity(sal).build();
	}


}
