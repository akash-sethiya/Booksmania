package com.impetus.services.api;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.impetus.domain.Language;

// TODO: Auto-generated Javadoc
/**
 * The Interface LanguageService.
 */
@Transactional(readOnly = false)
public interface LanguageService {

    /**
     * Gets the languages.
     * 
     * @return the languages
     * @throws ServiceException 
     * @throws DAOException 
     */
    List<Language> getLanguages();
}
