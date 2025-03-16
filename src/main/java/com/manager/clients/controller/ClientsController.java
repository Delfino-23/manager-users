package com.manager.clients.controller;

import com.manager.clients.models.Clients;
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

    // Busca geral
    @GetMapping(path = "/")
    public List<Clients> findAll(){
        return repository.findAll();
    }

    // Busca especifica
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Clients clients) {
        System.out.println("Recebido: " + clients);
        Clients savedClient = repository.save(clients);
        System.out.println("Salvo no banco: " + savedClient);
        return ResponseEntity.ok(savedClient);
    }

    // Atualizar
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Clients clients){
        return repository.findById(id)
                .map(record -> {
                    record.setName(clients.getName());
                    record.setEmail(clients.getEmail());
                    record.setPassword(clients.getPassword());
                    Clients updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Atualizar
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
