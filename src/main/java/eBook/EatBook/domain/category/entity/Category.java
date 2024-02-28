package eBook.EatBook.domain.category.entity;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Category extends BaseEntity {

    @OneToMany(mappedBy = "category")
    private List<Book> books;

    @Column
    private String categoryName;

    @Column
    private String content;
}
