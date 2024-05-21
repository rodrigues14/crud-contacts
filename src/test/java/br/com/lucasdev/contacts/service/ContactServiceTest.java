package br.com.lucasdev.contacts.service;

import br.com.lucasdev.contacts.domain.address.AddressContact;
import br.com.lucasdev.contacts.domain.contact.Contact;
import br.com.lucasdev.contacts.dto.AddressContactDTO;
import br.com.lucasdev.contacts.dto.ContactRequestDTO;
import br.com.lucasdev.contacts.dto.ContactResponseDTO;
import br.com.lucasdev.contacts.dto.ContactUpdateDTO;
import br.com.lucasdev.contacts.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

     @InjectMocks
     private ContactService service;

     @Mock
     private ContactRepository repository;

     @Mock
     private ContactRequestDTO contactRequestDTO;

    @Mock
    private Contact contact;

    @Mock
    private AddressContactDTO addressContactDTO;

    @Captor
    private ArgumentCaptor<Contact> argumentCaptor;

    @Mock
    private ContactUpdateDTO contactUpdateDTO;

    @Mock
    private AddressContact addressContact;

    @Test
    void shouldRegisterContact() {
        BDDMockito.given(contactRequestDTO.address()).willReturn(addressContactDTO);

        service.register(contactRequestDTO);

        BDDMockito.then(repository).should().save(argumentCaptor.capture());
        Contact expectedContact = new Contact(contactRequestDTO);
        assertEquals(expectedContact, argumentCaptor.getValue());
    }

    @Test
    void shouldListAllContacts() {
        AddressContact address = new AddressContact("Street 1", 101, "Neighborhood 1", "City 1", "State 1", "12456");
        Contact contact1 = new Contact(1l, "John Doe", "123-456-7890", "john.doe@example.com", address, LocalDate.of(1990, 1, 1), "Notes 1");
        Contact contact2 = new Contact(2l, "Jane Smith", "098-765-4321", "jane.smith@example.com", address, LocalDate.of(1995, 2, 2), "Notes 2");
        List<Contact> contacts = List.of(contact1, contact2);
        BDDMockito.given(repository.findAll()).willReturn(contacts);

        List<ContactResponseDTO> list = service.findAll();

        BDDMockito.then(repository).should().findAll();
        List<ContactResponseDTO> expectedList = contacts.stream().map(ContactResponseDTO::new).toList();
        assertEquals(expectedList, list);
    }

    @Test
    void shouldUpdateContact() {
        Long contactId = 1l;

        BDDMockito.given(contactUpdateDTO.id()).willReturn(contactId);
        BDDMockito.given(repository.getReferenceById(contactId)).willReturn(contact);
        BDDMockito.given(contact.getAddress()).willReturn(addressContact);

        service.update(contactUpdateDTO);

        BDDMockito.then(contact).should().updateInformations(contactUpdateDTO);
    }

    @Test
    void shouldDeleteContact() {
        Long id = 1l;
        BDDMockito.given(repository.findById(id)).willReturn(Optional.of(contact));
        BDDMockito.given(contact.getId()).willReturn(id);

        service.delete(id);

        BDDMockito.then(repository).should().deleteById(id);
    }

    @Test
    void shouldFindContactById() {
        Long id = 1l;
        BDDMockito.given(repository.findById(id)).willReturn(Optional.of(contact));
        BDDMockito.given(contact.getId()).willReturn(id);
        BDDMockito.given(contact.getAddress()).willReturn(addressContact);

        service.findById(id);

        BDDMockito.then(repository).should().findById(id);
    }

    @Test
    void shouldNotFindContactThatDoesNotExist() {
        Long id = 1l;
        BDDMockito.given(repository.findById(id)).willReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> service.findById(id));
    }

}