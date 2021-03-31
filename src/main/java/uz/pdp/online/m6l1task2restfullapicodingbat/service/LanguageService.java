package uz.pdp.online.m6l1task2restfullapicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Language;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.LanguageRepository;

import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public Result addLanguage(Language language){

        boolean existsByName = languageRepository.existsByName(language.getName());
        if (existsByName)
            return new Result( "Language already exists",false);

        languageRepository.save(language);
        return new Result("Language saved",true);
    }


    public Page<Language> getLanguagePage(Integer page){
        Pageable pageable= PageRequest.of(page,3);
        Page<Language> languagePage = languageRepository.findAll(pageable);
        return languagePage;
    }

    public Language getOneLanguage(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }

    public Result editLanguage(Integer id,Language language){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new Result(" not found ",false);

        boolean b = languageRepository.existsByNameAndIdNot(language.getName(), id);
        if (b)
            return new Result("This language already exists",false);

        Language editedLanguage = optionalLanguage.get();
        editedLanguage.setName(language.getName());
        languageRepository.save(editedLanguage);
        return new Result("language edited",true);

    }

    public Result deleteLanguage(Integer id){
        try {
            languageRepository.deleteById(id);
            return new Result("deleted",true);
        }catch (Exception e ){
            return  new Result("error in delete",false);
        }

    }








}
