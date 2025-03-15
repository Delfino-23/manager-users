package com.manager.clients.controller;

import com.manager.clients.models.ClientsModel;
import com.manager.clients.repository.ClientsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/clients"})
public class ClientsController {

    // REPOSITORIO
    private final ClientsRepository repository;

    ClientsController(ClientsRepository clientsRepository) {
        this.repository = clientsRepository;
    }

    // MÉTODOS
    @GetMapping(path = "/")
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClientsModel create(@RequestBody ClientsModel clientsModel) {
        return repository.save(clientsModel);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ClientsModel clientsModel){
        return repository.findById(id)
                .map(record -> {
                    record.setName(clientsModel.getName());
                    record.setEmail(clientsModel.getEmail());
                    record.setPassword(clientsModel.getPassword());
                    ClientsModel updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
