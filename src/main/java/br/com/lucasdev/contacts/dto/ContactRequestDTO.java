package br.com.lucasdev.contacts.dto;

import br.com.lucasdev.contacts.domain.contact.Contact;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ContactRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String phone,
        @Email
        String email,
        AddressContactDTO address,
        LocalDate birth,
        String notes
) {
        public ContactRequestDTO(Contact contact) {
                this(contact.getName(), contact.getPhone(),
                        contact.getEmail(), new AddressContactDTO(contact.getAddress()),
                        contact.getBirth(), contact.getNotes());
        }
}
