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

import com.nesovic.narudzbina.model.Slatkis;
import com.nesovic.narudzbina.service.ServiceSlatkis;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Path("/slatkis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api("/Slatkis")
@SwaggerDefinition(tags= {@Tag(name="/Slatkis",description="REST Endpoints for Slatkisi")})
public class SlatkisResource {
	
	ServiceSlatkis slatkis=new ServiceSlatkis();
	
	@GET
	public ArrayList<Slatkis> getSlatkis(){
		return slatkis.sviSlatkisi();
	}
	@GET
	@Path("/{id}")
	public Slatkis getSlatkisById(@PathParam("id") int id) {
		return slatkis.selectSlatkisById(id);
	}
	@POST
	public Response addSlatkis(Slatkis slat,@Context UriInfo uriInfo) {
		Slatkis noviSlatkis=slatkis.insertSlatkis(slat);
		String idSlat=String.valueOf(noviSlatkis.getId_slat());
		URI uri=uriInfo.getAbsolutePathBuilder().path(idSlat).build();
		return Response.created(uri).entity(noviSlatkis).build();
	}
	@DELETE
	@Path("/{id}")
	public Response deleteSlatkis(@PathParam("id") int id) {
		slatkis.deleteSlatkis(id);
		return Response.noContent().build();
		
	}
	@PUT
	@Path("/{id}")
	public Response updateSlatkis(@PathParam("id") int id,Slatkis slat,@Context UriInfo uriInfo) {
		slat.setId_slat(id);;
		slatkis.updateSlatkis(slat);;
		String idSlat=String.valueOf(slat.getId_slat());
		URI uri=uriInfo.getAbsolutePathBuilder().path(idSlat).build();
		return Response.created(uri).entity(slat).build();
	}
}
