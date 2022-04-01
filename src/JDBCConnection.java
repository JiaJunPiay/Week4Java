import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import oracle.jdbc.driver.OracleDriver;

class DatabaseConnection{
	void create(Connection con, Statement statement, ResultSet res){
		//createStatement
		//Write query to tell database
		//As a string, but treat it as a database when writing the query
		//Return ResultSet which is a object that has the result from the query;
		try {
			statement = con.createStatement();
			res = statement.executeQuery("SELECT * FROM STUDENT WHERE id = 1");
			
			//Get the metadata from the result such as the column names and the column type in the database
			ResultSetMetaData rsmd = res.getMetaData();
			System.out.println("Column Name	Column Type");
			for(int i = 1; i<= rsmd.getColumnCount(); i++) {
				System.out.println(rsmd.getColumnName(i) + "		" + rsmd.getColumnTypeName(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void prepare(Connection con, PreparedStatement pStat, ResultSet res) {
		//prepareStatement
		//When you don't have a placeholder, you can use the createStatement. If you have a placeholder (eg specific query that requires users input), use prepareStatement 
		//? is a placeholder, so it's a incomplete query and awaiting the program to complete it (Eg waiting for a user input to allow the user to search for specific student)
		//If a specific id is used for rather than a placeholder, a error will appear "ORA-01006: bind variable does not exist"
		try {
			pStat = con.prepareStatement("SELECT * FROM STUDENT WHERE id = ?");
			Scanner scan = new Scanner(System.in);
			System.out.println("Please enter the ID of the Student you want to search for : ");
			int sID = scan.nextInt();
			//Depending on what type the data is, eg setInt() setString() etc
			//The 1 infront represent which placeholder to replace, eg. WHERE id = ?,?,?,?,?" pStat.setInt(5, id) will replace the fifth ?
			pStat.setInt(1, sID);
			res = pStat.executeQuery();
			
			
			
			System.out.println();
			System.out.println("ID	Name	EngMark	MathMark SciMark");
			while(res.next()) {
				int id = res.getInt(1);
				String name = res.getString(2);
				int engMark = res.getInt(3);
				int mathMark = res.getInt(4);
				int sciMark = res.getInt(5);
				//Rows are starting from 1
				System.out.println(id + "	" + name + "	" + engMark + "	" + mathMark + "	" + sciMark);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	void update(Connection con, PreparedStatement pStat) {
		//Update Data
		//Using prepareStatement as it requires inputs
		Scanner scan = new Scanner(System.in);
		
		try {
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			System.out.print("Please enter the ID of the Student you want to update : ");
			int tempID = scan.nextInt();
			System.out.print("Please update "+ tempID + " new English Mark : ");
			int tempMark = scan.nextInt();
			
			pStat.setInt(1, tempMark);
			pStat.setInt(2, tempID);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int rowsUpdated = pStat.executeUpdate();
			System.out.println(rowsUpdated + " rows has been updated!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void delete(Connection con, PreparedStatement pStat) {
		//Delete Data
		//Using prepareStatement as it requires inputs
		try {
			Scanner scan = new Scanner(System.in);
			pStat = con.prepareStatement("DELETE FROM STUDENT WHERE id = ?");
			
			System.out.print("Please enter the ID of the Student you want to delete : ");
			int tempID = scan.nextInt();
			
			pStat.setInt(1, tempID);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int rowsUpdated = pStat.executeUpdate();
			System.out.println(rowsUpdated + " rows has been updated!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void insert(Connection con, PreparedStatement pStat) {
		//Insert Data
		//Using prepareStatement as it requires inputs
		try {
			Scanner scan = new Scanner(System.in);
			
			pStat = con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?,?)");
			
			System.out.print("Please enter the ID of the Student : ");
			int tempID = scan.nextInt();
			pStat.setInt(1, tempID);
			
			System.out.print("Please enter the name of the "+ tempID +" : ");
			String tempName = scan.next();
			pStat.setString(2, tempName);
			
			System.out.print("Please enter the English mark of the "+ tempName +" : ");
			int tempEng = scan.nextInt();
			pStat.setInt(3, tempEng);
			
			System.out.print("Please enter the Maths mark of "+ tempName +" : ");
			int tempMath = scan.nextInt();
			pStat.setInt(4, tempMath);
			
			System.out.print("Please enter the Science mark of "+ tempName +" : ");
			int tempSci = scan.nextInt();
			pStat.setInt(5, tempSci);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int rowsUpdated = pStat.executeUpdate();
			System.out.println(rowsUpdated + " rows has been updated!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void transaction(Connection con, PreparedStatement pStat) {
		//Transaction -> Either all execute or nothing gets committed using setAutoCommit()
		
		//setAutoCommit() -> User will commit the data themselves rather than letting the program commit after every execution
		//true -> System will auto commit for you, false -> User will commit themselves
		//Partially execution will not be reflected in the database.
		try {
			con.setAutoCommit(false);
			
			//Using prepareStatement as it requires inputs
			Scanner scan = new Scanner(System.in);
			
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			System.out.print("Please enter the ID of the Student you want to update : ");
			int tempID = scan.nextInt();
			System.out.print("Please update "+ tempID + " new English Mark : ");
			int tempMark = scan.nextInt();
			
			pStat.setInt(1, tempMark);
			pStat.setInt(2, tempID);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int rowsUpdated = pStat.executeUpdate();
			System.out.println(rowsUpdated + " rows has been updated!");
			
			
			System.out.print("Please enter the ID of the 2nd Student you want to update : ");
			int tempID2 = scan.nextInt();
			System.out.print("Please update "+ tempID2 + " new English Mark : ");
			int tempMark2 = scan.nextInt();
			
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			pStat.setInt(1, tempMark2);
			pStat.setInt(2, tempID2);
			
			int rowsUpdated2 = pStat.executeUpdate();
			System.out.println(rowsUpdated2 + " rows has been updated!");
			
			//Insert Data
			//Using prepareStatement as it requires inputs
			
			pStat = con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?,?)");
			
			System.out.print("Please enter the ID of the Student : ");
			int tempIDInsert = scan.nextInt();
			pStat.setInt(1, tempID);
			
			System.out.print("Please enter the name of the "+ tempIDInsert +" : ");
			String tempName = scan.next();
			pStat.setString(2, tempName);
			
			System.out.print("Please enter the English mark of the "+ tempName +" : ");
			int tempEng = scan.nextInt();
			pStat.setInt(3, tempEng);
			
			System.out.print("Please enter the Maths mark of "+ tempName +" : ");
			int tempMath = scan.nextInt();
			pStat.setInt(4, tempMath);
			
			System.out.print("Please enter the Science mark of "+ tempName +" : ");
			int tempSci = scan.nextInt();
			pStat.setInt(5, tempSci);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int rowsUpdatedInsert = pStat.executeUpdate();
			System.out.println(rowsUpdatedInsert + " rows has been updated!");
			
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void batch(Connection con, PreparedStatement pStat) throws SQLException {
		
		//addBatch -> using for a bulk of data
		//Using prepareStatement as it requires inputs
		try {
			con.setAutoCommit(false);
			
			Scanner scan = new Scanner(System.in);
			
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			System.out.print("Please enter the ID of the Student you want to update : ");
			int tempID = scan.nextInt();
			System.out.print("Please update "+ tempID + " new English Mark : ");
			int tempMark = scan.nextInt();
			
			pStat.setInt(1, tempMark);
			pStat.setInt(2, tempID);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int updateRow = pStat.executeUpdate();
			
			
			System.out.print("Please enter the ID of the 2nd Student you want to update : ");
			int tempID2 = scan.nextInt();
			System.out.print("Please update "+ tempID2 + " new English Mark : ");
			int tempMark2 = scan.nextInt();
			
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			pStat.setInt(1, tempMark2);
			pStat.setInt(2, tempID2);
			
			int updateRow2 = pStat.executeUpdate();
			System.out.println(updateRow2 + " rows has been updated!");
			
			//if there are data that has been updated
			if(updateRow > 0 && updateRow2 > 0) {
				con.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//close connection
			//good practice to close it after use
			con.close();
			pStat.close();
		}
	}
}

public class JDBCConnection {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement statement = null;
		ResultSet res = null;
		PreparedStatement pStat = null;
		
		//Registering oracle drive
		DriverManager.registerDriver(new OracleDriver());
		System.out.println("Oracle Driver registered!");
		
		//Establishing the connection with the database, returns a Connection
		//"api(jdbc):database(oracle/mysql/etc):driver type:@//ip of the database server:port number/db edition)", username, password
		con = DriverManager.getConnection("jdbc:oracle:thin:@//127.0.0.1:1521/XE", "SYSTEM", "Car.fix-99");
		System.out.println("Connection has been esablished!");
		
		DatabaseConnection dbc = new DatabaseConnection();
		
		//dbc.create(con, statement, res);
		//dbc.prepare(con, pStat, res);
		//dbc.update(con, pStat);
		//dbc.delete(con, pStat);
		//dbc.transaction(con, pStat);
		dbc.batch(con, pStat);
		

	}

}


/*
 
 		//Properties -> Java Built Path -> Libraries -> Class Path -> Add External Jar to import jar file external jar file 
		try {
			/*
			
			//createStatement
			//Write query to tell database
			//As a string, but treat it as a database when writing the query
			//Return ResultSet which is a object that has the result from the query;
			statement = con.createStatement();
			res = statement.executeQuery("SELECT * FROM STUDENT WHERE id = 1");
			
			//Get the metadata from the result such as the column names and the column type in the database
			ResultSetMetaData rsmd = res.getMetaData();
			System.out.println("Column Name	Column Type");
			for(int i = 1; i<= rsmd.getColumnCount(); i++) {
				System.out.println(rsmd.getColumnName(i) + "		" + rsmd.getColumnTypeName(i));
			}
			
			
			//prepareStatement
			//When you don't have a placeholder, you can use the createStatement. If you have a placeholder (eg specific query that requires users input), use prepareStatement 
			//? is a placeholder, so it's a incomplete query and awaiting the program to complete it (Eg waiting for a user input to allow the user to search for specific student)
			//If a specific id is used for rather than a placeholder, a error will appear "ORA-01006: bind variable does not exist"
			pStat = con.prepareStatement("SELECT * FROM STUDENT WHERE id = ?");
			Scanner scan = new Scanner(System.in);
			System.out.println("Please enter the ID of the Student you want to search for : ");
			int sID = scan.nextInt();
			//Depending on what type the data is, eg setInt() setString() etc
			//The 1 infront represent which placeholder to replace, eg. WHERE id = ?,?,?,?,?" pStat.setInt(5, id) will replace the fifth ?
			pStat.setInt(1, sID);
			res = pStat.executeQuery();
			
			
			
			System.out.println();
			System.out.println("ID	Name	EngMark	MathMark SciMark");
			while(res.next()) {
				int id = res.getInt(1);
				String name = res.getString(2);
				int engMark = res.getInt(3);
				int mathMark = res.getInt(4);
				int sciMark = res.getInt(5);
				//Rows are starting from 1
				System.out.println(id + "	" + name + "	" + engMark + "	" + mathMark + "	" + sciMark);
			}
			*/
			
			/*
			//Update Data
			//Using prepareStatement as it requires inputs
			Scanner scan = new Scanner(System.in);
			
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			System.out.print("Please enter the ID of the Student you want to update : ");
			int tempID = scan.nextInt();
			System.out.print("Please update "+ tempID + " new English Mark : ");
			int tempMark = scan.nextInt();
			
			pStat.setInt(1, tempMark);
			pStat.setInt(2, tempID);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int rowsUpdated = pStat.executeUpdate();
			System.out.println(rowsUpdated + " rows has been updated!");
			*/
			
			/*
			//Delete Data
			//Using prepareStatement as it requires inputs
			Scanner scan = new Scanner(System.in);
			
			pStat = con.prepareStatement("DELETE FROM STUDENT WHERE id = ?");
			System.out.print("Please enter the ID of the Student you want to delete : ");
			int tempID = scan.nextInt();
			
			pStat.setInt(1, tempID);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int rowsUpdated = pStat.executeUpdate();
			System.out.println(rowsUpdated + " rows has been updated!");
			*/
			
			/*
			//Insert Data
			//Using prepareStatement as it requires inputs
			Scanner scan = new Scanner(System.in);
			
			pStat = con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?,?)");
			
			System.out.print("Please enter the ID of the Student : ");
			int tempID = scan.nextInt();
			pStat.setInt(1, tempID);
			
			System.out.print("Please enter the name of the "+ tempID +" : ");
			String tempName = scan.next();
			pStat.setString(2, tempName);
			
			System.out.print("Please enter the English mark of the "+ tempName +" : ");
			int tempEng = scan.nextInt();
			pStat.setInt(3, tempEng);
			
			System.out.print("Please enter the Maths mark of "+ tempName +" : ");
			int tempMath = scan.nextInt();
			pStat.setInt(4, tempMath);
			
			System.out.print("Please enter the Science mark of "+ tempName +" : ");
			int tempSci = scan.nextInt();
			pStat.setInt(5, tempSci);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int rowsUpdated = pStat.executeUpdate();
			System.out.println(rowsUpdated + " rows has been updated!");
			*/
			
			/*
			//Transaction -> Either all execute or nothing gets committed using setAutoCommit()
			
			//setAutoCommit() -> User will commit the data themselves rather than letting the program commit after every execution
			//true -> System will auto commit for you, false -> User will commit themselves
			//Partially execution will not be reflected in the database.
			con.setAutoCommit(false);
			
			//Using prepareStatement as it requires inputs
			Scanner scan = new Scanner(System.in);
			
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			System.out.print("Please enter the ID of the Student you want to update : ");
			int tempID = scan.nextInt();
			System.out.print("Please update "+ tempID + " new English Mark : ");
			int tempMark = scan.nextInt();
			
			pStat.setInt(1, tempMark);
			pStat.setInt(2, tempID);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int rowsUpdated = pStat.executeUpdate();
			System.out.println(rowsUpdated + " rows has been updated!");
			
			
			System.out.print("Please enter the ID of the 2nd Student you want to update : ");
			int tempID2 = scan.nextInt();
			System.out.print("Please update "+ tempID2 + " new English Mark : ");
			int tempMark2 = scan.nextInt();
			
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			pStat.setInt(1, tempMark2);
			pStat.setInt(2, tempID2);
			
			int rowsUpdated2 = pStat.executeUpdate();
			System.out.println(rowsUpdated2 + " rows has been updated!");
			
			//Insert Data
			//Using prepareStatement as it requires inputs
			
			pStat = con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?,?)");
			
			System.out.print("Please enter the ID of the Student : ");
			int tempIDInsert = scan.nextInt();
			pStat.setInt(1, tempID);
			
			System.out.print("Please enter the name of the "+ tempIDInsert +" : ");
			String tempName = scan.next();
			pStat.setString(2, tempName);
			
			System.out.print("Please enter the English mark of the "+ tempName +" : ");
			int tempEng = scan.nextInt();
			pStat.setInt(3, tempEng);
			
			System.out.print("Please enter the Maths mark of "+ tempName +" : ");
			int tempMath = scan.nextInt();
			pStat.setInt(4, tempMath);
			
			System.out.print("Please enter the Science mark of "+ tempName +" : ");
			int tempSci = scan.nextInt();
			pStat.setInt(5, tempSci);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int rowsUpdatedInsert = pStat.executeUpdate();
			System.out.println(rowsUpdatedInsert + " rows has been updated!");
			
			con.commit();
			*/
			
			/*
			//addBatch -> using for a bulk of data
			//Using prepareStatement as it requires inputs
			Scanner scan = new Scanner(System.in);
			
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			System.out.print("Please enter the ID of the Student you want to update : ");
			int tempID = scan.nextInt();
			System.out.print("Please update "+ tempID + " new English Mark : ");
			int tempMark = scan.nextInt();
			
			pStat.setInt(1, tempMark);
			pStat.setInt(2, tempID);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			pStat.addBatch();
			
			
			System.out.print("Please enter the ID of the 2nd Student you want to update : ");
			int tempID2 = scan.nextInt();
			System.out.print("Please update "+ tempID2 + " new English Mark : ");
			int tempMark2 = scan.nextInt();
			
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			pStat.setInt(1, tempMark2);
			pStat.setInt(2, tempID2);
			
			pStat.addBatch();
			int[] rowsUpdated = pStat.executeBatch();
			System.out.println(rowsUpdated.length + " rows has been updated!");
			
			con.commit();
			
			
			//addBatch -> using for a bulk of data
			//Using prepareStatement as it requires inputs
			con.setAutoCommit(false);
			
			Scanner scan = new Scanner(System.in);
			
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			System.out.print("Please enter the ID of the Student you want to update : ");
			int tempID = scan.nextInt();
			System.out.print("Please update "+ tempID + " new English Mark : ");
			int tempMark = scan.nextInt();
			
			pStat.setInt(1, tempMark);
			pStat.setInt(2, tempID);
			
			//When modifying data on the table, use executeUpdate(), it'll return how many rows has been updated
			int updateRow = pStat.executeUpdate();
			
			
			System.out.print("Please enter the ID of the 2nd Student you want to update : ");
			int tempID2 = scan.nextInt();
			System.out.print("Please update "+ tempID2 + " new English Mark : ");
			int tempMark2 = scan.nextInt();
			
			pStat = con.prepareStatement("UPDATE STUDENT SET ENGLISHMARK = ? WHERE id = ?");
			pStat.setInt(1, tempMark2);
			pStat.setInt(2, tempID2);
			
			int updateRow2 = pStat.executeUpdate();
			System.out.println(updateRow2 + " rows has been updated!");
			
			//if there are data that has been updated
			if(updateRow > 0 && updateRow2 > 0) {
				con.commit();
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.rollback();
			System.out.println("Rollback!");
			//e.printStackTrace();
			
		}
 
 */
