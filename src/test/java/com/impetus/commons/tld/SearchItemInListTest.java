package com.impetus.commons.tld;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SearchItemInListTest {

    @InjectMocks
    private SearchItemInList searchItemList;
    
    @Mock
    private List<Integer> list;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @SuppressWarnings("static-access")
    @Test
    public void testContains() {
        Integer object=1;
        boolean contains=true;
        when(list.contains(object)).thenReturn(contains);
        assertEquals(contains,searchItemList.contains(list, object));
        
        
    }

}
