package com.example.MicroServicesExample.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.MicroServicesExample.EmployeeConstants.EmployeeConstants;
import com.example.MicroServicesExample.Entity.ProjectEntity;
import com.example.MicroServicesExample.Model.Employee;
import com.example.MicroServicesExample.Repository.ProjectRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;


@Service
@Slf4j
public class ProjectService {
	
	private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

	private WebClient webClient;

	@Autowired
	public ProjectService(WebClient webClient) {
		this.webClient = webClient;
	}

	@Autowired
	private ProjectRepo projectRepo;

	public String saveData(ProjectEntity entity) {
		projectRepo.save(entity);
		return "Data saved!";
	}

	public String saveAll(List<ProjectEntity> entityList) {
		projectRepo.saveAll(entityList);
		return "Data saved successfully";
	}

	public ProjectEntity getEntity(int id) {
		return projectRepo.findById(id).orElse(null);
	}

	public List<ProjectEntity> getAllEntities() {
		return projectRepo.findAll();
	}

	// Consuming Employee Rest API using web-client
	 public Flux<Employee> retrieveAllEmployees() {
	        log.info("Retrieving all employees...");
	        return webClient
	        		.get()
	        		.uri(EmployeeConstants.GET_ALL_EMPLOYEES)
	                .retrieve()
	                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
	                        Mono.error(new RuntimeException("Client error: " + clientResponse.statusCode())))
	                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
	                        Mono.error(new RuntimeException("Server error: " + clientResponse.statusCode())))
	                .bodyToFlux(Employee.class)
	                .doOnError(error -> log.error("Error retrieving employees: {}", error.getMessage()));
	    }

	    public Mono<Employee> getEmployeeById(int id) {
	        log.info("Retrieving employee with ID: {}", id);
	        return webClient
	        		.get()
	        		.uri(EmployeeConstants.GET_EMPLOYEE_BY_ID, id)
	                .retrieve()
	                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
	                        Mono.error(new RuntimeException("Client error: " + clientResponse.statusCode())))
	                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
	                        Mono.error(new RuntimeException("Server error: " + clientResponse.statusCode())))
	                .bodyToMono(Employee.class)
	                .doOnError(error -> log.error("Error retrieving employee with ID {}: {}", id, error.getMessage()));
	    }
	    
	    public String addEmployee(Employee employee) {
	    	log.info("employee saved to db");
		    	return webClient
		    			.post()
		    			.uri(EmployeeConstants.SAVE_EMPLOYEE)
		    			.bodyValue(employee)
		    			.retrieve()
		    			.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> 
		    					Mono.error(new RuntimeException("Client error: "+clientResponse.statusCode())) )
		    			.onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
		    					Mono.error(new RuntimeException("Server error: "+clientResponse.statusCode())))
		    			.bodyToMono(String.class)
		    			.block();
		    		
	    }
	    
	    public  String addAllEmp(List<Employee> employee){
	    	log.info("All employees saved to db");
	    	return webClient
	    			.post()
	    			.uri(EmployeeConstants.SAVE_ALL_EMPLOYEES)
	    			.bodyValue(employee)
	    			.retrieve()
	    			.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> 
							Mono.error(new RuntimeException("Client error: "+clientResponse.statusCode())) )
	    			.onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
							Mono.error(new RuntimeException("Server error: "+clientResponse.statusCode())))
	    			.bodyToMono(String.class)
	    			.block();
	    }
	    
	    public String updateEmployee(int id,Employee employee) {
	    	log.info("employee update in db");
	    	return webClient
	    			.put()
	    			.uri(EmployeeConstants.UPDATE_EMPLOYEE, id)
	    			.bodyValue(employee)
	    			.retrieve()
	    			.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> 
							Mono.error(new RuntimeException("Client error: "+clientResponse.statusCode())) )
	    			.onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
							Mono.error(new RuntimeException("Server error: "+clientResponse.statusCode())))
	    			.bodyToMono(String.class)
	    			.block();
	    }
	    
	    public String deleteEmployee(int id) {
	    	log.info("employee deleted in db");
	    	return webClient
	    			.delete()
	    			.uri(EmployeeConstants.DELETE_EMPLOYEE, id)
	    			.retrieve()
	    			.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> 
							Mono.error(new RuntimeException("Client error: "+clientResponse.statusCode())) )
	    			.onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
							Mono.error(new RuntimeException("Server error: "+clientResponse.statusCode())))
	    			.bodyToMono(String.class)
	    			.block();
	    }
	    
	}
