package br.com.lucasdev.contacts.dto;

import java.time.LocalDate;

public record ContactUpdateDTO(
        Long id,
        String name,
        String phone,
        String email,
        AddressContactDTO address,
        LocalDate birth,
        String notes
) {
}
