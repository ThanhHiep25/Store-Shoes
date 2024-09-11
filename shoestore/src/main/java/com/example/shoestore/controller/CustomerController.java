package com.example.shoestore.controller;

import com.example.shoestore.model.Order;
import com.example.shoestore.service.OrderService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@Path("/customer")
public class CustomerController {

    @Inject
    private OrderService orderService;

    @POST
    @Path("/checkout")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response checkout(@FormParam("shippingAddress") String shippingAddress, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        
        if (cart == null || cart.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Cart is empty").build();
        }

        // Create an order, calculate total amount and save to the database
        Order order = new Order();
        BigDecimal totalAmount = orderService.calculateTotalAmount(cart);
        order.setTotalAmount(totalAmount);
        order.setShippingAddress(shippingAddress);
        orderService.saveOrder(order);

        // Clear the session after successful checkout
        session.removeAttribute("cart");

        // Send confirmation email and return response
        return Response.ok("Order placed successfully").build();
    }
}
