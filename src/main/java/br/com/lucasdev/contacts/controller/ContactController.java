package br.com.lucasdev.contacts.controller;

import br.com.lucasdev.contacts.dto.ContactDTO;
import br.com.lucasdev.contacts.domain.contact.Contact;
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
    public ResponseEntity register(@RequestBody @Valid ContactDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var contact = new Contact(dados);
        repository.save(contact);
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(contact.getId()).toUri();

        return ResponseEntity.created(uri).body(new ContactDTO(contact));
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> findAll() {
        var contactsList = repository.findAll().stream().map(c -> new ContactDTO(c)).toList();
        return ResponseEntity.ok(contactsList);
    }


    @PutMapping()
    @Transactional
    public ResponseEntity update(@RequestBody ContactDTO data) {
        var contact = repository.getReferenceById(data.id());
        contact.updateInformations(data);
        return ResponseEntity.ok(new ContactDTO(contact));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Contact> contact = repository.findById(id);
        if (contact.isPresent()) repository.deleteById(contact.get().getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> findById(@PathVariable Long id) {
        var contact = repository.findById(id);
        if (contact.isPresent()) return ResponseEntity.ok(new ContactDTO(contact.get()));
        return ResponseEntity.notFound().build();
    }

}
