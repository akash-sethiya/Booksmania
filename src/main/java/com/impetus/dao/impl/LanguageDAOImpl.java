package com.impetus.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.api.LanguageDAO;
import com.impetus.domain.Language;

// TODO: Auto-generated Javadoc
/**
 * The Class LanguageDAOImpl.
 */
@Repository("languageDAO")
public class LanguageDAOImpl implements LanguageDAO {

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate = null;
    
    private Logger logger = LoggerFactory.getLogger(LanguageDAOImpl.class);
    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.LanguageDAO#getLanguages()
     */
    @SuppressWarnings("unchecked")
    public List<Language> getLanguages() throws DAOException {
        try{
            logger.info("in getLanguages()method");
            return (List<Language>) hibernateTemplate.find("from Language");
        }
        catch(DataAccessException e)
        {
            logger.error("DATA-ACCESS-EXCEPTION occured in getLanguages() method");
            throw new DAOException("DATA-ACCESS-EXCEPTION occured in getLangauges",e);
        }
        catch(Exception e){
            logger.error("error occured in getLanguages() method");
            throw new DAOException("error occured in getLangauges",e);
        }
        
    }
}
