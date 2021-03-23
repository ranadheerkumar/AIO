package essentials;

//Modify JDBC Program which will use POJO (Plain Old Java Object) Class to retrieve data from Java Object.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConvertDBResult_JavaObject_JsonFile_POJO {

	public static void main(String[] args) throws SQLException {
		// 1) Create a connection
		// Connection con =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels",
		// "root", "root");
		Connection con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		// 2) Create statement/Query
		Statement stmt = con.createStatement();

		String s = "select * from customerinfo  limit 1";

		// 3,4) Execute statement/Query & Store data in resultset
		ResultSet rs = stmt.executeQuery(s);

		CustomerDetails cd = new CustomerDetails();

		while (rs.next()) {
			String bookname = rs.getString("BookName");
			String purchasedate = rs.getString("PurchasedDate");
			int amount = rs.getInt("Amount");
			String location = rs.getString("Location");

			cd.setBookname(bookname);
			cd.setPurchasedate(purchasedate);
			cd.setAmount(amount);
			cd.setLocation(location);
		}

		// 5) close connection
		con.close();

		System.out.println(cd.getLocation());
	}
}