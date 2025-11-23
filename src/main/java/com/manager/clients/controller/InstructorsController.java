package com.manager.clients.controller;

import com.manager.clients.models.Instructors;
import com.manager.clients.repository.InstructorsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/instructors"})
public class InstructorsController {

    // REPOSITORIO
    private final InstructorsRepository repository;

    InstructorsController(InstructorsRepository instructorsRepository) {
        this.repository = instructorsRepository;
    }

    // MÉTODOS

    // Busca geral
    @GetMapping(path = "/")
    public List<Instructors> findAll(){
        return repository.findAll();
    }

    // Busca especifica
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Instructors> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Instructors instructors) {
        System.out.println("Recebido: " + instructors);
        Instructors savedInstructor = repository.save(instructors);
        System.out.println("Salvo no banco: " + savedInstructor);
        return ResponseEntity.ok(savedInstructor);
    }

    // Atualizar
    @PutMapping(path = "/{id}")
    public ResponseEntity<Instructors> update(@PathVariable("id") long id, @RequestBody Instructors instructors){
        return repository.findById(id)
                .map(record -> {
                    record.setName(instructors.getName());
                    record.setEmail(instructors.getEmail());
                    record.setPassword(instructors.getPassword());
                    record.setPhone(instructors.getPhone());
                    record.setCpf(instructors.getCpf());
                    record.setCourse(instructors.getCourse());
                    Instructors updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Deletar
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
