package uz.pdp.online.m6l1task2restfullapicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Category;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Language;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.CategoryDto;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.CategoryRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.LanguageRepository;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LanguageRepository languageRepository;

    public Result addCat(CategoryDto categoryDto ){

        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new Result("Language not found",false);

        Language language = optionalLanguage.get();
        boolean b = categoryRepository.existsByNameAndLanguage(categoryDto.getName(), language);
        if (b)
            return new Result("This category already exists in this language",false);

        Category category=new Category();
        category.setDescription(categoryDto.getDescription());
        category.setLanguage(language);
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new Result("category saved",true);

    }
    public Page<Category> getCategoryPage(Integer page){
        Pageable pageable= PageRequest.of(page,3);

        return categoryRepository.findAll(pageable);
    }

    public Category getOneCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

   public Result editCategory(Integer id,CategoryDto categoryDto){
       Optional<Category> optionalCategory = categoryRepository.findById(id);
       if (!optionalCategory.isPresent())
           return new Result("Category not found by id",false);

       Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
       if (!optionalLanguage.isPresent())
           return new Result("Language not found",false);

       boolean andIdNot = categoryRepository.existsByNameAndLanguageAndIdNot(categoryDto.getName(), optionalLanguage.get(), id);
       if (andIdNot)
           return new Result("This category already exists in this language",false);


       Category category = optionalCategory.get();
       category.setName(categoryDto.getName());
       category.setDescription(categoryDto.getDescription());
       category.setLanguage(optionalLanguage.get());
       categoryRepository.save(category);
       return new Result("Category edited",true);

    }

    public Result deleteCate(Integer id){
        try {
            categoryRepository.deleteById(id);
            return new Result("deleted",true);
        }catch (Exception e){
            return new Result("Error",false);
        }



    }





}