package com.example.personServiceApi.controller;

import com.example.personServiceApi.models.Persona;
import com.example.personServiceApi.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping ("/personas")
public class PersonaController {
    private PersonaService personaService;

    public PersonaController(@Autowired PersonaService personaService){
        this.personaService = personaService;
    }

    @GetMapping
    public List<Persona> getAllPersonas(){
        return personaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersona(@PathVariable Long id) {
        try {
            Persona persona = personaService.getById(id); // Llama al service
            return ResponseEntity.ok(persona);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public  ResponseEntity<Persona> createPersona(@RequestBody Persona persona){
        return  ResponseEntity.status(HttpStatus.CREATED).body(personaService.save(persona));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id){
        personaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

