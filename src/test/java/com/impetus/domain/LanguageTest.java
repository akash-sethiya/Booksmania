package com.impetus.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class LanguageTest {

    @InjectMocks
    private Language lang;
    
    private Integer languageId;
    private String languageName;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSetLanguageId() {
        lang.setLanguage(languageName);
        lang.setLanguageId(languageId);
        
        assertEquals(languageId,lang.getLanguageId());
        assertEquals(languageName,lang.getLanguage());
        
    }

}
