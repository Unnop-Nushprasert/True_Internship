package org.acme;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.vertx.core.json.JsonArray;


@Path("/customer")
public class CustomerController {

    @Inject
    CustomerService service;

    
    
  
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/order/add")
    public JsonArray addOrder(JsonArray json) {
        return service.addOrder(json);
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public JsonArray getCustomerList() {
		return service.getCustomerList();
    }
}

