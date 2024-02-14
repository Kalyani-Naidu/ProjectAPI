package com.example.MicroServicesExample.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MicroServicesExample.Entity.ProjectEntity;
import com.example.MicroServicesExample.Model.Employee;
import com.example.MicroServicesExample.Service.ProjectService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/projectsApi")
public class ProjectController {
	
	@Autowired
	private ProjectService service;
	
	@PostMapping(path = "/save")
	public ResponseEntity<String> savePro(@RequestBody ProjectEntity entity){
		String status = service.saveData(entity); 
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	} 
	
	@PostMapping(path = "/saveAll")
	public ResponseEntity<String> saveAllPro(@RequestBody List<ProjectEntity> entities){
		String status = service.saveAll(entities);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/getData/{empId}")
	public ResponseEntity<ProjectEntity> getPro(@PathVariable int empId){
		ProjectEntity entity = service.getEntity(empId);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll")
	public ResponseEntity<List<ProjectEntity>> getAllPro(){
		List<ProjectEntity> status = service.getAllEntities();
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	//Consuming Employee Rest Service using web-client

	@GetMapping("/all")
	public ResponseEntity<Flux<Employee>> getAllEmp(){
	    Flux<Employee> employee = service.retrieveAllEmployees();
	    return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@GetMapping("/{empId}")
	public ResponseEntity<Mono<Employee>> getEmpByID(@PathVariable int empId){
	    Mono<Employee> employee = service.getEmployeeById(empId);
	    return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@PostMapping(path = "/saveEmp")
	public ResponseEntity<String> saveEmp(@RequestBody Employee entity){
		String status = service.addEmployee(entity);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/saveAllEmp")
	public ResponseEntity<String> saveAllEmp(@RequestBody List<Employee> entities){
		String status = service.addAllEmp(entities);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/updateEmp/{empId}")
	public ResponseEntity<String> updateEmp(@PathVariable("empId") int empId, @RequestBody Employee entity){
	    String status = service.updateEmployee(empId, entity);
	    return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteEmp/{empId}")
	public ResponseEntity<String> deleteEmp(@PathVariable("empId") int empId){
	    String status = service.deleteEmployee(empId);
	    return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
}
