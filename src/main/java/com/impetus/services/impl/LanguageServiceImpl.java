package com.impetus.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.commons.exception.ServiceException;
import com.impetus.dao.api.LanguageDAO;
import com.impetus.domain.Language;
import com.impetus.services.api.LanguageService;

// TODO: Auto-generated Javadoc
/**
 * The Class LanguageServiceImpl.
 */
@Service("languageService")
public class LanguageServiceImpl implements LanguageService {

    /** The language dao. */
    @Autowired
    private LanguageDAO languageDAO;

    public void setLanguageDAO(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.LanguageService#getLanguages()
     */
    public List<Language> getLanguages(){
        try{
            return languageDAO.getLanguages();
        }
        catch(Exception e){
            throw new ServiceException("error occured in getLangauges method()",e);
        }
    }

}
