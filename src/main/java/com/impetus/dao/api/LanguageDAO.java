package com.impetus.dao.api;

import java.util.List;

import com.impetus.commons.exception.DAOException;
import com.impetus.domain.Language;

// TODO: Auto-generated Javadoc
/**
 * The Interface LanguageDAO.
 */
public interface LanguageDAO {

    /**
     * Gets the languages.
     * 
     * @return the languages
     * @throws DAOException 
     */
    List<Language> getLanguages()throws DAOException;

}
