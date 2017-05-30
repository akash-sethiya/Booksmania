package com.impetus.commons.tld;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchItemInList.
 */
public final class SearchItemInList {

    /**
     * Contains.
     * 
     * @param list
     *            the list
     * @param object
     *            the object
     * @return true, if successful
     */
    private SearchItemInList() {
        // TODO Auto-generated constructor stub
    }

    public static boolean contains(List<Integer> list, Integer object) {
        return list.contains(object);
    }
}
