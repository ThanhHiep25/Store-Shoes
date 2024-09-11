package com.example.shoestore.controller;

import com.example.shoestore.model.Product;
import com.example.shoestore.service.ProductService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Path("/guest")
public class GuestController {

    @Inject
    private ProductService productService;

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewProducts() {
        List<Product> products = productService.getAllProducts();
        return Response.ok(products).build();
    }

    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewProductDetails(@PathParam("id") Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return Response.ok(product).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/cart/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addToCart(@FormParam("productId") Long productId, @FormParam("quantity") int quantity, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);
        session.setAttribute("cart", cart);
        return Response.ok("Added to cart").build();
    }

    @GET
    @Path("/cart")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewCart(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        return Response.ok(cart).build();
    }

    @POST
    @Path("/cart/update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateCart(@FormParam("productId") Long productId, @FormParam("quantity") int quantity, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart != null && cart.containsKey(productId)) {
            if (quantity == 0) {
                cart.remove(productId);
            } else {
                cart.put(productId, quantity);
            }
            session.setAttribute("cart", cart);
            return Response.ok("Cart updated").build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response registerUser(@FormParam("username") String username, @FormParam("password") String password, @FormParam("email") String email, @FormParam("phone") String phone, @FormParam("address") String address) {
        // Logic to register a new user, save to database and send email
        return Response.ok("Registration successful").build();
    }
}
