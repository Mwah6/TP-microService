package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableHystrix
public class MicroServiceGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceGatewayServiceApplication.class, args);
	}
	//  Configuration statique
	@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r
						.path("/publicCountries/**")
						.filters(f->f
								.addRequestHeader("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
								.addRequestHeader("x-rapidapi-key","4802530af0mshb39aa55e3b645f2p1dc98fjsn21622d84ded8")
								.rewritePath("/publicCountries/(?<segment>.*)", "/${segment}")
								.hystrix(h->h.setName("countries").setFallbackUri("forward:/defaultCountries"))
								)
						.uri("https://restcountries-v1.p.rapidapi.com")					
						.id("r1"))
				.route(r -> r
						.path("/muslim/**")
						.filters(f->f
								.addRequestHeader("x-rapidapi-host", "muslimsalat.p.rapidapi.com")
								.addRequestHeader("x-rapidapi-key","4802530af0mshb39aa55e3b645f2p1dc98fjsn21622d84ded8")
								.rewritePath("/muslim/(?<segment>.*)", "/${segment}")
								.hystrix(h->h.setName("muslimsalat").setFallbackUri("forward:/defaultSalat"))
								)
						.uri("https://muslimsalat.p.rapidapi.com")					
						.id("r2"))
				.build();
	}

	//  Configuration dynamique
	@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp) {
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}

	@RestController
	class CircuitBreakerRestController {
		@GetMapping("/defaultCountries")
		public Map<String,String> countries() {
			Map<String,String> data = new HashMap<>();
			data.put("message",  "default Countries");
			data.put("countries", "Maroc, Alg??rie, Tunisie, .........");
			return data;
		}
		@GetMapping("/defaultSalat")
		public Map<String,String> salat() {
			Map<String,String> data = new HashMap<>();
			data.put("message", "Horaire Salawat En Nwakchout ");
			data.put("Majr",  "7:00");
			data.put("Addohr", "14:00");
			return data;
		}
	}

}
