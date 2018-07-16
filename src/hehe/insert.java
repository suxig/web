package hehe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import hehe.DBConnection;

public class insert extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=utf-8");
		String student_id = request.getParameter("id");
		String score = request.getParameter("score");
		System.out.println("id:" + student_id);
		System.out.println("score:" + score);
		try {
			////////////
			// do something
			////////////
			DBConnection db = new DBConnection();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
			db.execute("INSERT INTO score (id, score, time) VALUES ('"+student_id+"','"+score+"','"+date+"')");

			db.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}