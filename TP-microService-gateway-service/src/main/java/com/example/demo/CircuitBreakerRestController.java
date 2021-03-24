package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@EnableHystrix
//public class CircuitBreakerRestController {
//	@GetMapping("/defaultCountries")
//	public Map<String,String> countries() {
//		Map<String,String> data = new HashMap<>();
//		data.put("message",  "default Countries");
//		data.put("countries", "Maroc, Algérie, Tunisie, .........");
//		return data;
//	}
//	@GetMapping("/defaultSalat")
//	public Map<String,String> salat() {
//		Map<String,String> data = new HashMap<>();
//		data.put("message", "Horaire Salawat En Nwakchout ");
//		data.put("Majr",  "7:00");
//		data.put("Addohr", "14:00");
//		return data;
//	}
//}
