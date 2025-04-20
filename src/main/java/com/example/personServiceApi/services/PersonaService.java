package com.example.personServiceApi.services;

import com.example.personServiceApi.models.Persona;
import com.example.personServiceApi.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonaService {
    private PersonaRepository personaRepository;

    public PersonaService(@Autowired PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

    public List<Persona> getAll(){
        return personaRepository.findAll();
    }

    public Persona getById(Long id){
        return personaRepository.findBy(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public Persona save(Persona persona){
        return personaRepository.save(persona);
    }

    public void delete(Long id){
        personaRepository.delete(id);
    }
}
