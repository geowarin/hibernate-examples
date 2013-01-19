package com.geowarin.hibernate.jpa.standalone;

import java.io.InputStream;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.HibernateException;
import org.hibernate.internal.SessionImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Abstract unit test case class.
 * This will load the test-data.xml dataset before each test case and will clean the database before each test
 * 
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
public abstract class AbstractDbUnitJpaTest {

	private static EntityManagerFactory entityManagerFactory;
	private static IDatabaseConnection connection;
	private static IDataSet dataset;
	protected static EntityManager entityManager;

	/**
	 * Will load test-dataset.xml before each test case
	 * @throws DatabaseUnitException 
	 * @throws HibernateException 
	 */
	@BeforeClass
	public static void initEntityManager() throws HibernateException, DatabaseUnitException {
		entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");
		entityManager = entityManagerFactory.createEntityManager();
		connection = new DatabaseConnection(((SessionImpl) (entityManager.getDelegate())).connection());
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());

		FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
		flatXmlDataSetBuilder.setColumnSensing(true);
		InputStream dataSet = Thread.currentThread().getContextClassLoader().getResourceAsStream("test-data.xml");
		dataset = flatXmlDataSetBuilder.build(dataSet);
	}

	@AfterClass
	public static void closeEntityManager() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	/**
	 * Will clean the dataBase before each test
	 * 
	 * @throws SQLException 
	 * @throws DatabaseUnitException 
	 */
	@Before
	public void cleanDB() throws DatabaseUnitException, SQLException {
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
	}
}
