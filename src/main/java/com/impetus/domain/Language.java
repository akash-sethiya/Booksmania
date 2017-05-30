package com.impetus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Language.
 */
@Entity
public class Language implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The language id. */
    @Id
    @GeneratedValue
    private Integer languageId;

    /** The language. */
    private String language;

    /**
     * Gets the language id.
     * 
     * @return the language id
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * Sets the language id.
     * 
     * @param languageId
     *            the new language id
     */
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    /**
     * Gets the language.
     * 
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the language.
     * 
     * @param language
     *            the new language
     */
    public void setLanguage(String language) {
        this.language = language;
    }
}
