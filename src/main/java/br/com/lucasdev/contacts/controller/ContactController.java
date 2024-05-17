package br.com.lucasdev.contacts.controller;

import br.com.lucasdev.contacts.dto.ContactRequestDTO;
import br.com.lucasdev.contacts.domain.contact.Contact;
import br.com.lucasdev.contacts.dto.ContactResponseDTO;
import br.com.lucasdev.contacts.dto.ContactUpdateDTO;
import br.com.lucasdev.contacts.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ContactResponseDTO> register(@RequestBody @Valid ContactRequestDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var contact = new Contact(dados);
        repository.save(contact);
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(contact.getId()).toUri();

        return ResponseEntity.created(uri).body(new ContactResponseDTO(contact));
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> findAll() {
        var contactsList = repository.findAll().stream().map(ContactResponseDTO::new).toList();
        return ResponseEntity.ok(contactsList);
    }


    @PutMapping()
    @Transactional
    public ResponseEntity<ContactResponseDTO> update(@RequestBody ContactUpdateDTO data) {
        var contact = repository.getReferenceById(data.id());
        contact.updateInformations(data);
        return ResponseEntity.ok(new ContactResponseDTO(contact));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Contact> contact = repository.findById(id);
        if (contact.isPresent()) repository.deleteById(contact.get().getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> findById(@PathVariable Long id) {
        var contact = repository.findById(id);
        if (contact.isPresent()) return ResponseEntity.ok(new ContactResponseDTO(contact.get()));
        return ResponseEntity.notFound().build();
    }

}
