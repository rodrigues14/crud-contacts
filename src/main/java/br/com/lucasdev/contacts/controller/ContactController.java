package br.com.lucasdev.contacts.controller;

import br.com.lucasdev.contacts.dto.AddressContactDTO;
import br.com.lucasdev.contacts.dto.ContactDTO;
import br.com.lucasdev.contacts.domain.contact.Contact;
import br.com.lucasdev.contacts.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid ContactDTO dados) {
        repository.save(new Contact(dados));
    }

    @GetMapping
    public List<ContactDTO> findAll() {
        return repository.findAll().stream().map(c -> new ContactDTO(c.getId(), c.getName(), c.getPhone(),
                c.getEmail(), new AddressContactDTO(c.getAddress()), c.getBirth(), c.getNotes()))
                .toList();
    }


    @PutMapping()
    @Transactional
    public void update(@RequestBody ContactDTO data) {
        var contact = repository.getReferenceById(data.id());
        contact.updateInformations(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Optional<Contact> contact = repository.findById(id);
        if (contact.isPresent()) repository.deleteById(contact.get().getId());
    }

}
