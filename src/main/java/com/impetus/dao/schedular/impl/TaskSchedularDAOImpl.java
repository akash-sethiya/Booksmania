package com.impetus.dao.schedular.impl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.impetus.dao.schedular.api.TaskSchedularDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskSchedularDAOImpl.
 */
public class TaskSchedularDAOImpl implements TaskSchedularDAO {

    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.schedular.api.TaskSchedularDAO#addAndUpdateBook(java.
     * lang.String)
     */
    public void addAndUpdateBook(String csvFilePath) {
        Session hibernateSession = sessionFactory.openSession();
        hibernateSession.beginTransaction();
        String createSQL = "create table raw_table(ISBN varchar(255),author varchar(255),availability INTEGER(11),description varchar(255),imageName varchar(255),publisher varchar(255),quantity INT,title varchar(255),bookLanguage INTEGER(11),category INTEGER(11))";
        SQLQuery query = hibernateSession.createSQLQuery(createSQL);
        query.executeUpdate();
        hibernateSession.getTransaction().commit();

        hibernateSession.beginTransaction();
        String loadCSVFileSQL = " LOAD DATA  INFILE '" + csvFilePath
                + "' INTO TABLE raw_table "
                + " FIELDS TERMINATED BY \',\' ENCLOSED BY \'\"'"
                + " LINES TERMINATED BY \'\\r\\n\' IGNORE 1 LINES";

        query = hibernateSession.createSQLQuery(loadCSVFileSQL);
        query.executeUpdate();
        hibernateSession.getTransaction().commit();

        hibernateSession.beginTransaction();
        query = hibernateSession.createSQLQuery("call addAndUpdateBooks()");
        query.executeUpdate();
        hibernateSession.getTransaction().commit();

        String deleteSQL = "drop table raw_table";
        hibernateSession.beginTransaction();
        query = hibernateSession.createSQLQuery(deleteSQL);
        query.executeUpdate();
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
    }
}
