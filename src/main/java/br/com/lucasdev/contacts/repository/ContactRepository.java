package br.com.lucasdev.contacts.repository;

import br.com.lucasdev.contacts.domain.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
