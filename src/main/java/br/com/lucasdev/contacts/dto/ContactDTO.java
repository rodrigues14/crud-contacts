package br.com.lucasdev.contacts.dto;

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
}
