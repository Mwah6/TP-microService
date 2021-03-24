package com.example.demo;


import org.springframework.data.rest.core.config.Projection;

@Projection(name="fullCustomer", types = Customer.class)
public interface CustomerProjection {

}
