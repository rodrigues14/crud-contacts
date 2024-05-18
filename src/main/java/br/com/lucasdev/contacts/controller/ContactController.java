package br.com.lucasdev.contacts.controller;

import br.com.lucasdev.contacts.dto.ContactRequestDTO;
import br.com.lucasdev.contacts.dto.ContactResponseDTO;
import br.com.lucasdev.contacts.dto.ContactUpdateDTO;
import br.com.lucasdev.contacts.service.ContactService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ContactResponseDTO> register(@RequestBody @Valid ContactRequestDTO data, UriComponentsBuilder uriComponentsBuilder) {
        ContactResponseDTO dto = service.register(data);
        URI uri = uriComponentsBuilder.path("/contact/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> findAll() {
        List<ContactResponseDTO> contactsList = service.findAll();
        return ResponseEntity.ok(contactsList);
    }


    @PutMapping()
    @Transactional
    public ResponseEntity<ContactResponseDTO> update(@RequestBody @Valid ContactUpdateDTO data) {
        ContactResponseDTO contact = service.update(data);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> findById(@PathVariable Long id) {
        try {
            ContactResponseDTO contact = service.findById(id);
            return ResponseEntity.ok(contact);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
