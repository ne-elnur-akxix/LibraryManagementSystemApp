package net.javaguides.sms.repository;

import net.javaguides.sms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select * from book s where s.name like %:keyword% or s.author like %:keyword% or s.publisher like %:keyword% or s.id like %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);

    List<Book> findByOrderByIdAsc();
    List<Book> findByOrderByAuthorAsc();
    List<Book> findByOrderByNameAsc();
    List<Book> findByOrderByPriceAsc();
//    List<Book> findByOrderByISBNAsc();
    List<Book> findByOrderByPublisherAsc();
    Optional<Book> findById(int id);
}
