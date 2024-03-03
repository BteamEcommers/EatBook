package eBook.EatBook.domain.category.repository;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameIn(List<String> names);
    List<Book> findByCategoryId(Integer categoryId);

    // 'id'를 이용하여 카테고리 조회
    Category findById(int id);
}
