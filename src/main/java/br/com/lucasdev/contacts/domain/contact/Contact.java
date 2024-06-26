package br.com.lucasdev.contacts.domain.contact;

import br.com.lucasdev.contacts.domain.address.AddressContact;
import br.com.lucasdev.contacts.dto.ContactRequestDTO;
import br.com.lucasdev.contacts.dto.ContactUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "contacts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;
    @Embedded
    private AddressContact address;
    private LocalDate birth;
    private String notes;

    public Contact(ContactRequestDTO data) {
        this.name = data.name();
        this.phone = data.phone();
        this.email = data.email();
        this.address = new AddressContact(data.address());
        this.birth = data.birth();
        this.notes = data.notes();
    }

    public void updateInformations(ContactUpdateDTO data) {
        if (data.name() != null) this.name = data.name();
        if (data.phone() != null) this.phone = data.phone();
        if (data.email() != null) this.email = data.email();
        if (data.address() != null) this.address.updateInformations(data.address());
        if (data.birth() != null) this.birth = data.birth();
        if (data.address() != null) this.notes = data.notes();
    }
}
