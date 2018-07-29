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
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import com.nesovic.narudzbina.annotations.Secured;
import com.nesovic.narudzbina.model.Narudzbina;
import com.nesovic.narudzbina.service.ServiceKlijent;
import com.nesovic.narudzbina.service.ServiceNarudzbina;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Path("/narudzbine")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api("/Narudzbina")
@SwaggerDefinition(tags= {@Tag(name="/Narudzbina",description="REST Endpoints for Narudzbine")})
public class NarudzbinaResource {
	
	ServiceNarudzbina narudzbina=new ServiceNarudzbina();
	ServiceKlijent daoKlijent=new ServiceKlijent();
	
	@GET
	public ArrayList<String> getNarudzbina(){
		return narudzbina.sveNarudzbine();
	}
	@GET
	@Path("/{id}")
	public Narudzbina selectNarudzbinaById(@PathParam("id") int id) {
		return narudzbina.selectNarudzbineById(id);
	}
	@POST
	@Secured
	public Response addNarudzbina(Narudzbina n,@Context UriInfo uriInfo,@Context SecurityContext securityContext) {
		n.setKlijent(daoKlijent.selectKlijentiById(Integer.parseInt(securityContext.getUserPrincipal().getName())));
		Narudzbina novaNarudzbina=narudzbina.insertNarudzbina(n);
		URI uri=uriInfo.getAbsolutePathBuilder().path(Integer.toString(novaNarudzbina.getId_narudzbine())).build();
		return Response.created(uri).entity(novaNarudzbina).build();
	}
	@DELETE
	@Path("/{id}")
	public Response deleteNarudzbina(@PathParam("id") int id) {
		narudzbina.deleteNarudzbina(id);
		return Response.noContent().build();
	}
	@PUT
	@Path("/{id}")
	public Response updateNarudzbina(@PathParam("id") int id,Narudzbina n,@Context UriInfo uriInfo) {
		n.setId_narudzbine(id);
		narudzbina.updateNarudzbina(n);
		URI uri=uriInfo.getAbsolutePathBuilder().path(Integer.toString(n.getId_narudzbine())).build();
		return Response.created(uri).entity(n).build();
	}
	@GET
	@Path("/datum/{datum}")
	public ArrayList<String> selectNrudbineByDatum(@PathParam("datum") String datum){
		return narudzbina.selectNarudzbineByDatum(datum);
	}
}
