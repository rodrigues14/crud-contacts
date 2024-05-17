package br.com.lucasdev.contacts.dto;

import br.com.lucasdev.contacts.domain.contact.Contact;

import java.time.LocalDate;

public record ContactResponseDTO(
        Long id,
        String name,
        String phone,
        String email,
        AddressContactDTO address,
        LocalDate birth,
        String notes
) {

    public ContactResponseDTO(Contact contact) {
        this(contact.getId(), contact.getName(), contact.getPhone(),
                contact.getEmail(), new AddressContactDTO(contact.getAddress()),
                contact.getBirth(), contact.getNotes());
    }

}
