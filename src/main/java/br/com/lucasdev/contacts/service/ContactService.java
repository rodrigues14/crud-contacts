package br.com.lucasdev.contacts.service;

import br.com.lucasdev.contacts.domain.contact.Contact;
import br.com.lucasdev.contacts.dto.ContactRequestDTO;
import br.com.lucasdev.contacts.dto.ContactResponseDTO;
import br.com.lucasdev.contacts.dto.ContactUpdateDTO;
import br.com.lucasdev.contacts.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public ContactResponseDTO register(ContactRequestDTO data) {
        Contact contact = new Contact(data);
        repository.save(contact);
        return new ContactResponseDTO(contact);
    }

    public List<ContactResponseDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(ContactResponseDTO::new)
                .toList();
    }

    public ContactResponseDTO update(ContactUpdateDTO data) {
        Contact contact = repository.getReferenceById(data.id());
        contact.updateInformations(data);
        return new ContactResponseDTO(contact);
    }

    public void delete(Long id) {
        Optional<Contact> contact = repository.findById(id);
        contact.ifPresent(c -> repository.deleteById(c.getId()));
    }

    public ContactResponseDTO findById(Long id) {
        var contact = repository.findById(id);
        if (contact.isPresent()) return new ContactResponseDTO(contact.get());
        throw new EntityNotFoundException();
    }

}
