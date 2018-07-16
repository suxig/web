package wechat;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class geto {
	public static void main(String[] args){
	//ReGetOpenId();
	}
	public class WxCodeServlet extends HttpServlet {
		 
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
	 
			doPost(request, response);
		}
	 
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
	 
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
	                //这里要将你的授权回调地址处理一下，否则微信识别不了
	 String redirect_uri=URLEncoder.encode("/*你的授权回调地址*/", "UTF-8");
	                //简单获取openid的话参数response_type与scope与state参数固定写死即可
			StringBuffer url=new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?redirect_uri="+redirect_uri+
					"&appid="+"wx13af3458dc23a31d"+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
			response.sendRedirect(url.toString());//这里请不要使用get请求单纯的将页面跳转到该url即可
		
			
	}
	}

	public class WxOpenIdServlet extends HttpServlet {
	 
		private static final long serialVersionUID = 1L;
	 
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
	                
			response.setContentType("text/html");
	 
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String code = request.getParameter("code");//获取code
			Map params = new HashMap();
			params.put("secret", "/*你的secret*/");
			params.put("appid", "/*你的appid*/");
			params.put("grant_type", "authorization_code");
			params.put("code", code);
			String result = HttpGetUtil.httpRequestToString(
					"https://api.weixin.qq.com/sns/oauth2/access_token", params);
			JSONObject jsonObject = JSONObject.fromObject(result);
	 
			String openid = jsonObject.get("openid").toString();
			System.out.println("得到的openid为:"+openid);
		}
	 
	 
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}
	}
	
	

}     
