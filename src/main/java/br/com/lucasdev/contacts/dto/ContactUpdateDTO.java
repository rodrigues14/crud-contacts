package br.com.lucasdev.contacts.dto;

import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record ContactUpdateDTO(
        Long id,
        String name,
        String phone,
        @Email
        String email,
        AddressContactDTO address,
        LocalDate birth,
        String notes
) {
}
