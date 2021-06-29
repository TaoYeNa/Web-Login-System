<%@ page import="java.util.*" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSetMetaData" %>
<%@ page import="jakarta.servlet.jsp.JspWriter" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: fengqiyin
  Date: 2021/6/8
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <% String name = request.getParameter("Username");
  String  pass = request.getParameter("Password");
  /*倒叙打印字符串与密码
char[] nameC = name.toCharArray();
int start =0;
int end = nameC.length-1;
while(start<end){
char temp = nameC[start];
nameC[start] = nameC[end];
nameC[end] = temp;
start++;
end --;
}
out.println("The reverse of Username is:    "+ String.valueOf(nameC));
*/
    LinkedHashMap<String,ArrayList<String>> map = (LinkedHashMap) request.getAttribute("user_map");
    HashMap<String,String> keys = (HashMap)request.getAttribute("key_map");
    if(map.isEmpty() || keys.isEmpty()){
      out.println("cannot read map");
    }
    if(keys.get(name)==null){
      out.println("Username does not exist");
    }
    else{
      if(keys.get(name).equals(pass)){
        out.println("-----------------Login success----------------------------<br>");
        out.println("--------------user_Database table----------------------"+"<br>");
        for(String i : map.keySet()) {
          out.println(i+":   ");
          Iterator<String> iterator = map.get(i).iterator();
          while (iterator.hasNext()) {
            out.println(iterator.next());
          }
          out.print("<br>");
        }
        out.println("----------------------------------------------------------");
      }
      else{
        out.println("Incorrect password");
      }
    }





  %>
  </body>
</html>

