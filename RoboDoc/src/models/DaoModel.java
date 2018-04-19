package models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * 
 * @author Pooja Gupta Date:04/03/2018 Lab: #4 File: DaoModel.java
 *
 */
public class DaoModel {

	// variables
	DBConnect connection = null;
	Statement statement = null;
	String tableName = "p_gupt_doc";

	// constructor
	public DaoModel() {
		System.out.println("Connecting to the database to create or access the table...");
		connection = new DBConnect();
		System.out.println("Connected to the database successfully...");

	}

	/**
	 * method to create table
	 */
	public void createTable() {
		try {
			System.out.println("Creating table in the database");

			statement = connection.connect().createStatement();
			// create table query statement
			String createQuery = "CREATE TABLE " + tableName + " (id INTEGER not NULL, " + "name VARCHAR(10), "
					+ "category VARCHAR(20), " + "PRIMARY KEY(id))";

			statement.executeUpdate(createQuery);

			System.out.println("Table successfully created..");
			// close the connection
			connection.connect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to insert Bank records into table
	 * 
	 * @param records
	 *            Bank records list to be inserted
	 */
	/*
	 * public void insertRecords(BankRecords[] records) { try {
	 * System.out.println("Inserting records into table..");
	 * 
	 * int pid = 1; statement = connection.connect().createStatement();
	 * 
	 * // prepared statement for insert PreparedStatement preparedInsertStatement =
	 * connection.connect() .prepareStatement("INSERT INTO " + tableName +
	 * " (pid,id,income,pep) VALUES(?,?,?,?)"); // iterate and insert records into
	 * table for (BankRecords record : records) { preparedInsertStatement.setInt(1,
	 * pid++); preparedInsertStatement.setString(2, record.getId());
	 * preparedInsertStatement.setFloat(3, (float) record.getIncome());
	 * preparedInsertStatement.setString(4, record.getPep());
	 * preparedInsertStatement.executeUpdate(); }
	 * 
	 * System.out.println("Records successfully inserted.."); // close the
	 * connection connection.connect().close(); } catch (SQLException e) {
	 * e.printStackTrace(); } }
	 */

	public void insertRecords() {
		try {
			System.out.println("Inserting records into table..");
			statement = connection.connect().createStatement();
			String getQuery = "SELECT  id, name, category FROM " + tableName;
			ResultSet resultSet = statement.executeQuery(getQuery);
			
		
			
			while (resultSet.next()) {
				System.out.printf("%10d %10S %10S \n", resultSet.getObject(1), resultSet.getObject(2),
						resultSet.getObject(3));
			}
			
			connection.connect().close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * method to get all the records in the table
	 * 
	 * @return records in the table
	 */
	public ResultSet retrieveRecods() {
		ResultSet results = null;
		System.out.println("Retrieving bank records from the database..");
		try {
			statement = connection.connect().createStatement();
			String getQuery = "SELECT  id, name, category FROM " + tableName;
			results = statement.executeQuery(getQuery);
			connection.connect().close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Method to display report on console
	 * 
	 * @param resultSet
	 *            data in the form of resultset which needs to be displayed on
	 *            console
	 * @throws SQLException
	 */
	public void displayReportOnConsole(ResultSet resultSet) throws SQLException {
		System.out.printf("-----------------%-20s----------------\n", "Loan Analysis Report");
		System.out.printf("%10S %10S %10S %10S \n", "PID", "ID", "Income", "PEP");
		while (resultSet.next()) {
			System.out.printf("%10d %10S %10.2f %10S \n", resultSet.getObject(1), resultSet.getObject(2),
					resultSet.getObject(3), resultSet.getObject(4));
		}
	}

	/**
	 * method which serialize and deserialize Bank records using HashMap
	 * 
	 * @param resultSet
	 *            data which will be serialised and deserialized
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public void serialiseAndDeserialize(ResultSet resultSet)
			throws SQLException, IOException, ClassNotFoundException, InterruptedException {

		System.out.println("---------------Serializing and Deserializing---------------------------");
		List<HashMap<String, Object>> recordsList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row;
		// get metadata of the resultset
		java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
		int columns = metaData.getColumnCount();
		// prepare a list of HashMap from the resultset
		while (resultSet.next()) {
			row = new HashMap<String, Object>(columns);
			for (int i = 1; i <= columns; i++) {
				row.put(metaData.getColumnName(i), resultSet.getObject(i));
			}
			recordsList.add(row);
		}

		// serialize list and calculate time taken to serialize
		System.out.println("Serialising data...");
		long startTime = System.nanoTime();
		FileOutputStream fos = new FileOutputStream("bankrecords.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(recordsList);
		oos.close();
		fos.close();
		long endTime = System.nanoTime();

		long timeTakenForSerialization = endTime - startTime;
		// Deserialize data and calculate the time taken to deserialize
		System.out.println("Data serialised...");
		System.out.println("Time taken in milliseconds for serialization is: " + timeTakenForSerialization / 1000000.0);

		// process sleep for 5 seconds
		Thread.sleep(5000);

		System.out.println("Deserializing data...");
		startTime = System.nanoTime();
		FileInputStream fis = new FileInputStream("BankRecords.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		@SuppressWarnings({ "unused", "unchecked" })
		List<HashMap<String, Object>> deserialisedList = (List<HashMap<String, Object>>) ois.readObject();
		ois.close();
		fis.close();
		endTime = System.nanoTime();
		long timeTakenForDeserialization = endTime - startTime;
		System.out.println("Data deserialised...");
		System.out.println(
				"Time taken in milliseconds for deserialization is: " + timeTakenForDeserialization / 1000000.0);
		System.out.println("Time difference in milliseconds between serialization and deserialization is: "
				+ (timeTakenForSerialization - timeTakenForDeserialization) / 1000000.0);

	}

	/**
	 * method to delete all the records within table
	 */
	public void deleteRecords() {
		System.out.println("Deleting records present in the table...");
		try {
			statement = connection.connect().createStatement();
			String query = "DELETE FROM " + tableName;
			statement.execute(query);
			connection.connect().close();
			System.out.println("Records deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * method to delete table
	 */
	public void dropTable() {
		System.out.println("Dropping table...");
		try {
			statement = connection.connect().createStatement();
			String query = "DROP TABLE " + tableName;
			statement.execute(query);
			System.out.println("Table dropped...");
			connection.connect().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * check if table already exists
	 * 
	 * @return return true if table exists
	 */
	public Boolean tableExists() {
		System.out.println("Checking if table exists..");
		try {
			statement = connection.connect().createStatement();
			String query = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + tableName + "'";
			if (statement.executeQuery(query) != null) {
				System.out.println("Table exists...");
				return true;
			}
			connection.connect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Table doesn't exist..");
		return false;
	}

}
