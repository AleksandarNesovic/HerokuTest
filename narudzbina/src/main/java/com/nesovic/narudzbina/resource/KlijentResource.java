package com.nesovic.narudzbina.resource;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.json.Json;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.nesovic.narudzbina.exceptions.AuthFaildException;
import com.nesovic.narudzbina.jwt.LoginService;
import com.nesovic.narudzbina.model.Credentials;
import com.nesovic.narudzbina.model.Klijent;
import com.nesovic.narudzbina.service.ServiceKlijent;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Path("/klijenti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api("/Klijent")
@SwaggerDefinition(tags= {@Tag(name="/Klijent",description="REST Endpoints for Klijenti")})
public class KlijentResource {

	ServiceKlijent klijent=new ServiceKlijent();
	
	@GET
	public ArrayList<Klijent> getKlijent(){
		return klijent.sviKlijenti();
	}
	@GET
	@Path("/{id}")
	public Klijent getKlijentById(@PathParam("id") int id) {
		return klijent.selectKlijentiById(id);
	}
	@POST
	public Response addKlijent(Klijent k,@Context UriInfo uriInfo) {
		Klijent noviKlijent=klijent.insertKlijent(k);
		String idKlijenta=String.valueOf(noviKlijent.getId_klijenta());
		URI uri=uriInfo.getAbsolutePathBuilder().path(idKlijenta).build();
		return Response.created(uri).entity(noviKlijent).build();
	}
	@DELETE
	@Path("/{id}")
	public Response deleteKlijent(@PathParam("id") int id) {
		klijent.deleteKlijent(id);;
		return Response.noContent().build();
		
	}
	@PUT
	@Path("/{id}")
	public Response updateKlijent(@PathParam("id") int id,Klijent k,@Context UriInfo uriInfo) {
		k.setId_klijenta(id);;
		klijent.updateKlijent(k);;
		String idKlijent=String.valueOf(k.getId_klijenta());
		URI uri=uriInfo.getAbsolutePathBuilder().path(idKlijent).build();
		return Response.created(uri).entity(k).build();
	}
	@POST
	@Path("/login")
	public Response Login(Credentials crd) {
		LoginService log=new LoginService();
		try {
			if(log.Login(crd)!="") {
				return Response.ok(log.Login(crd)).entity(Json.createValue(log.Login(crd))).build();
			}
		}catch (ClassNotFoundException e) {
			System.out.println("Resource not found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("eror");
			e.printStackTrace();
		}catch (java.lang.NullPointerException e) {
			System.out.println("erorrrr");
			return Response.status(Status.CONFLICT).entity(Json.createValue("Pogresan username or password")).build();
		}catch(AuthFaildException e) {
			return Response.status(Status.UNAUTHORIZED).entity(Json.createValue("Pogresna sifra")).build();
		}
		return Response.status(Status.CONFLICT).header("Access-Control-Allow-Origin", "*").entity("Pogresna sifra!!!")
				.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
	}

}
