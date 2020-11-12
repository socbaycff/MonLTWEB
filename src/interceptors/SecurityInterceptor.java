package interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
	
		  Cookie token = WebUtils.getCookie(request, "token");
		    if (token != null) {
		    	System.out.println("token khac null");
		       // request.getSession()
		    } else {
		       response.sendRedirect(request.getContextPath()+ "/login.html");
		       return false;
		    }
		return true;
	}

	
}
