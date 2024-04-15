package br.com.lucasdev.contacts.dto;

import br.com.lucasdev.contacts.model.AddressContact;

public record AddressContactDTO(
        String street,
        int number,
        String neighborhood,
        String city,
        String state,
        String zipCode
) {
    public AddressContactDTO(AddressContact data) {
        this(data.getStreet(), data.getNumber(), data.getNeighborhood(),
                data.getCity(), data.getState(), data.getZipCode());
    }
}
