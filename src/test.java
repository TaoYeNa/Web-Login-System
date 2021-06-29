import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "test", value = "/test")
public class test extends HttpServlet {
    Connection conn = getConnentionOfDatabase();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Statement st = conn.createStatement();
            String sql_command = "SELECT id,username,password\r\n" + "From login";
            ResultSet res = st.executeQuery(sql_command);
            LinkedHashMap<String, ArrayList<String>> user_map = new LinkedHashMap<>();
            HashMap<String, String> key_map = new HashMap<>();
            user_map.put("id", new ArrayList<String>());
            user_map.put("username", new ArrayList<String>());
            user_map.put("password", new ArrayList<String>());
            while(res.next()){
                int id = res.getInt("id");
                String name = res.getString("username");
                String pass = res.getString("password");
                key_map.put(name,pass);
                user_map.get("id").add(String.valueOf(id));
                user_map.get("username").add(name);
                user_map.get("password").add(pass);
            }

            request.setAttribute("user_map", user_map);
            request.setAttribute("res", res);
            request.setAttribute("key_map", key_map);
            //res.close();
            //conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public static Connection getConnentionOfDatabase() {
        Connection connection = null;
        Statement st;
        String ip = "jdbc:mysql://localhost:3306/test1?useSSL=false&serverTimezone=GMT%2B8";// 数据库的地址"架包://主机地址：数据库端口/数据库名?用户是否加密"
        String username = "root";// 数据的用户名
        String password = "n31316347";// 数据库的登录密码

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(ip, username, password);



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
