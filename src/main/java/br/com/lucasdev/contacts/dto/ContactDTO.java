package br.com.lucasdev.contacts.dto;

import br.com.lucasdev.contacts.domain.contact.Contact;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ContactDTO(
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String phone,
        String email,
        AddressContactDTO address,
        LocalDate birth,
        String notes
) {
        public ContactDTO(Contact contact) {
                this(contact.getId(), contact.getName(), contact.getPhone(),
                        contact.getEmail(), new AddressContactDTO(contact.getAddress()),
                        contact.getBirth(), contact.getNotes());
        }
}
