package eBook.EatBook.domain.category.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return this.categoryRepository.findAll();
    }

    public void categoryCreate(String categoryName){
        Category category = Category.builder()
                .categoryName(categoryName)
                .build();
        categoryRepository.save(category);

    }

    public Category getCategoryByCategoryName(String categoryName){
        Optional<Category> optionalCategory = this.categoryRepository.findByCategoryName(categoryName);
        if(optionalCategory.isEmpty()){
            return null;
        }

        return optionalCategory.get();
    }


}
