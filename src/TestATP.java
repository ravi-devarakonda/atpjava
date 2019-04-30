import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;



public class TestATP {
	
	private static String INSTANCE = "";
	private static String CREDENTIALS = "";
	private static String USER = "";
	private static String PASSWORD ="";
	
	private static void getConnection() {
		
		
		
		try {
			OracleDataSource ODS = new OracleDataSource();
			
			ODS.setURL("jdbc:oracle:thin:@"+INSTANCE+"?TNS_ADMIN="+CREDENTIALS);
			ODS.setUser(USER);
			ODS.setPassword(PASSWORD);
			ODS.getConnection();
			
			System.out.println("Connection test Succeeded. You are connected to ATP as Admin!");
			
			
		} catch (SQLException e) {
			System.out.println("Connection Unsuccessful with errror "+ e.getMessage());
			e.printStackTrace();
		}
		
	}

	private static void readProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = TestATP.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			prop.load(input);
			INSTANCE = prop.getProperty("dbinstance");
			USER = prop.getProperty("dbuser");
			PASSWORD = prop.getProperty("dbpassword");
			CREDENTIALS = prop.getProperty("dbcredpath");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		readProperties();
		getConnection();
	}
	
}
