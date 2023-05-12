package net.javaguides.sms.repository;

import net.javaguides.sms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {


    Book findAllById(Long id);

    Book findAllByTitle(String title);
    @Query(value = "select * from springweb.book s where s.title ilike %:keyword% or s.author ilike %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);


}
