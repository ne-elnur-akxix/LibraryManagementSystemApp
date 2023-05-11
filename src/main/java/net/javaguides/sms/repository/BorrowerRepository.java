package net.javaguides.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.sms.entity.Borrower;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BorrowerRepository extends JpaRepository<Borrower, Long>{

    @Query("SELECT b FROM Borrower b WHERE b.id = :id")
    public Optional<Borrower> findById(@Param("id") Long id);

    // Search borrower by first name and/or last name
    @Query("SELECT b FROM Borrower b WHERE (:firstName IS NULL OR b.firstName LIKE %:firstName%) AND (:lastName IS NULL OR b.lastName LIKE %:lastName%)")
    public List<Borrower> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}