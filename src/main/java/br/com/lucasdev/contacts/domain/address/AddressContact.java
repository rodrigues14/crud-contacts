package br.com.lucasdev.contacts.domain.address;

import br.com.lucasdev.contacts.dto.AddressContactDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AddressContact {
    private String street;
    private int number;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;

    public AddressContact(AddressContactDTO data) {
        this.street = data.street();
        this.number = data.number();
        this.neighborhood = data.neighborhood();
        this.city = data.city();
        this.state = data.state();
        this.zipCode = data.zipCode();
    }

    public void updateInformations(AddressContactDTO data) {
        if (data.street() != null) this.street = data.street();
        if (data.number() != 0) this.number = data.number();
        if (data.neighborhood() != null) this.neighborhood = data.neighborhood();
        if (data.city() != null) this.city = data.city();
        if (data.state() != null) this.state = data.state();
        if (data.zipCode() != null) this.zipCode = data.zipCode();
    }
}
