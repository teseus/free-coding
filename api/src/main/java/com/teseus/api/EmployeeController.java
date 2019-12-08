package com.teseus.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @RequestMapping(value = { "/create", "/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void create(@RequestBody Employee e){
        employeeService.create(e);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id) {
        Mono<Employee> e = employeeService.findById(id);
        HttpStatus status = e != null?HttpStatus.OK:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(e, status);
    }

    @GetMapping(value = "/name/{name}")
    @ResponseBody
    public Flux<Employee> findByName(@PathVariable String name){
        return employeeService.findByName(name);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Employee> findAll(){
        return employeeService.findAll();
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee e){
        return employeeService.update(e);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(Integer id){
        employeeService.delete(id).subscribe();
    }

}
