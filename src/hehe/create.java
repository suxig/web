package hehe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class create extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String dept = request.getParameter("dept");
		String address = request.getParameter("address");
		System.out.println("id:" + id + "  name:" + name + " age:" + age +" dept:" + dept + " address:" + address );
		try {
			DBConnection b =new DBConnection();
			String sql = "insert into student(id,name,age,dept,address) " +"VALUES ('" + id+ "','" + name+ "','" + age+ "','" + dept+ "','" + address+ "')";
			
			b.execute(sql);
			b.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JSONObject object;
		try {
			////////////
			// do something
			////////////
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			
			obj.put("code", "success");
			obj.put("user", name);
			
			System.out.println(obj.toString());
			out.print(obj.toString());
			out.flush();
			out.close();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	 
	public static void main(String[] args) {

	}

}
