package com.impetus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class BookCategory.
 */
@Entity
public class BookCategory implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The category id. */
    @Id
    @GeneratedValue
    private int categoryId;

    /** The category. */
    private String category;

    /**
     * Gets the category id.
     * 
     * @return the category id
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category id.
     * 
     * @param categoryId
     *            the new category id
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the category.
     * 
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category.
     * 
     * @param category
     *            the new category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
