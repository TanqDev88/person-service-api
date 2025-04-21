package com.example.personServiceApi.repositories;

import com.example.personServiceApi.models.Persona;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonaRepository {
    private List<Persona> personas = new ArrayList<>();
    private long idCounter = 1; // 👈 Contador de ID autoincremental

    public List<Persona> findAll() {
        return personas;
    }

    public Optional<Persona> findBy(Long id) {
        return personas.stream().filter(p -> p.getId() == (id)).findFirst();
    }

    public Persona save(Persona persona) {
        // Solo asigna un nuevo ID si el ID actual es 0
        if (persona.getId() == 0) {
            persona.setId(idCounter++);
        }
        personas.add(persona);
        return persona;
    }

    public void delete(Long id) {
        personas.removeIf(p -> p.getId() == (id));
    }
}
