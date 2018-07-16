package hehe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import hehe.DBConnection;

public class getq extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	/** 
	 * 随机指定范围内N个不重复的数 
	 * 最简单最基本的方法 
	 * @param min 指定范围最小值 
	 * @param max 指定范围最大值 
	 * @param n 随机数个数 
	 */  
	public static int[] randomCommon(int min, int max, int n){  
	    if (n > (max - min + 1) || max < min) {  
	           return null;  
	       }  
	    int[] result = new int[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num = (int) (Math.random() * (max - min)) + min;  
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }  
	    return result;  
	}  

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=utf-8");
		try {
			////////////
			// do something
			////////////
			DBConnection db = new DBConnection();
			ResultSet rs = db.executeQuery("select * from question");
			System.out.println("///////////////");
			int id = 0;
			String qus = "";
			String a = "";
			String b = "";
			String c = "";
			String d = "";
			String ans = "";
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			int i = 1;
			ArrayList<Question> list = new ArrayList();
			while(rs.next()){
				id=rs.getInt(1);
				qus=rs.getString(2);
				a=rs.getString(3);
				b=rs.getString(4);
				c=rs.getString(5);
				d=rs.getString(6);
				ans = rs.getString(7);
				System.out.println(ans);
				Question q = new Question(id,qus,a,b,c,d,ans);
				list.add(q);
				JSONObject obj = new JSONObject();
				obj.put("id", id);
				obj.put("qus", qus);
				obj.put("a", a);
				obj.put("b", b);
				obj.put("c", c);
				obj.put("d", d);
				obj.put("ans", ans);
				System.out.println(obj.toString());
				i++;
			}
			db.close();
			int x[] =randomCommon(1,10,4);
			JSONObject objjj = new JSONObject();
			for(i=0;i<4;i++)
			{
				Question s = list.get(x[i]);
				JSONObject objj = new JSONObject();
				objj.put("id", s.id);
				objj.put("qus", s.qus);
				objj.put("a", s.a);
				objj.put("b", s.b);
				objj.put("c", s.c);
				objj.put("d", s.d);
				objj.put("ans", s.ans);
				objjj.put(String.valueOf(i+1),objj);
			}
			out.print(objjj.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void doSearch(String student_id) {
		try {
			
			DBConnection db = new DBConnection();
			ResultSet rs = db.executeQuery("select * from student where id = '" + student_id +"'");
			
			String id = "";
			String name = "";
			String age = "";
			String dept = "";
			String address = "";
			
			while(rs.next()){
				name=rs.getString(1);
				age=rs.getString(2);
				dept=rs.getString(3);
				address=rs.getString(4);
			}
			db.close();
			
			JSONObject obj = new JSONObject();
			
			obj.put("result", "ok");
			obj.put("id", student_id);
			obj.put("name", name);
			obj.put("age", age);
			obj.put("dept", dept);
			obj.put("address", address);
			
			System.out.println(obj.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		doSearch("1");
	}

}