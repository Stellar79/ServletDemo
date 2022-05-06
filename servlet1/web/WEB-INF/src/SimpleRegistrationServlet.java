import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleRegistrationServlet extends HttpServlet {

    private PreparedStatement pstmt;
    /**
     * Initialize variables
     */
    public void init() throws ServletException{
        initializeJDBC();
    }

    /**
     * Process the HTTP Post request
     */

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String mi = req.getParameter("MI");
        String email = req.getParameter("email");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        String telephone = req.getParameter("telephone");
        String street = req.getParameter("street");

        try {
            if(firstName.length() == 0 || lastName.length()== 0){
                printWriter.println("Last name and first name are required! ");
            }else {
                storeStudent(lastName,firstName,mi,email,city,state,zip,telephone,street);
                printWriter.println(firstName+ " " + lastName +"is now registered in database");
            }
        } catch (Exception e) {
            printWriter.println("Error: " + e.getMessage());
        } finally {
            printWriter.close();
        }


    }

    private void initializeJDBC(){
        try {
            // load driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            //Establish a connection
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/servletdemodb","root","2565703q");
            System.out.println("Connected to database");
            pstmt = conn.prepareStatement("insert into Address(lastName, firstName,mi, eamil,city, state, zip, telephone, street) values  (?, ?, ?, ? ,? , ?, ?, ?, ?)");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    private void storeStudent(String lastName, String firstName, String mi, String email,
                              String city, String state, String zip, String telephone,
                              String street) throws SQLException {
                pstmt.setString(1, lastName);
                pstmt.setString(2, firstName);
                pstmt.setString(3, mi);
                pstmt.setString(4, email);
                pstmt.setString(5, city);
                pstmt.setString(6, state);
                pstmt.setString(7, zip);
                pstmt.setString(8, telephone);
                pstmt.setString(9, street);
                pstmt.executeUpdate();

    }

}
