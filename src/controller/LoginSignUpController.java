package controller;


import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import entity.Company;
import entity.FilterSP;
import entity.Job;
import entity.User;
import utils.ErrorHandler;
import utils.SQLHandler;


import java.security.SecureRandom;
import java.util.Base64;

@Controller
public class LoginSignUpController {

	
	  private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	  private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

	    public static String generateNewToken() {
	        byte[] randomBytes = new byte[24];
	        secureRandom.nextBytes(randomBytes);
	        return base64Encoder.encodeToString(randomBytes);
	    }
	
	@RequestMapping("login")
	public String login() {
		System.out.print("vao login");
		return "login/login";
	}
	
	@RequestMapping("signout")
	public String signout(HttpServletRequest request,HttpServletResponse response) {
		System.out.print("vao signout");
		Cookie cookie = WebUtils.getCookie(request, "token");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "login/login";
	}
	
	//  isSuccess = true;
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPost(@RequestParam("pass") String pass, @RequestParam("email") String email, ModelMap model, HttpServletResponse respone) {
		System.out.println("postlogin");
		System.out.println(pass);
		System.out.println(email);
		
		boolean[] isSuccess = {true};
		String[] token = {""};
		getSession(email, pass, (Session session)-> {
			StoredProcedureQuery query = session.createStoredProcedureQuery( "SP_DANGNHAP");
			query.registerStoredProcedureParameter( 1, String.class, ParameterMode.IN);
			query.setParameter(1, email);
			List<Object[]> resultLists = query.getResultList();
			for (Object[] item : resultLists) {
//				username = item[0].toString();
//				role = item[1].toString();
			}
			
		}, () -> {
			isSuccess[0] = false;
		});
		if (isSuccess[0]) {
			
			// luu vao cookie token
			getSession("sa", "1234", (Session sess)-> {
				System.out.println("vao day");
				Query query = sess.createSQLQuery("SELECT Token FROM UserLogin WHERE Email = '" + email + "'");
				List<Object[]> list =  query.list();
				token[0] = String.valueOf(list.get(0));
				Cookie cookie = new Cookie("token", token[0]);	
				respone.addCookie(cookie);
				System.out.println(token[0]);
			}, null);
			
//			System.out.println(username);
//			System.out.println(role);
		
			return "redirect:/index.html";
			
		} else {
			// gui message model attribute la fail
			model.addAttribute("errorMess", "Loi Dang Nhap");
			return "login/login";
		}
		
	}

	@RequestMapping("signup")
	public String signup() {
		System.out.println("sign up get");
		return "signup/signup";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signupPost(HttpServletRequest request, ModelMap model,@RequestParam("pass") String pass, @RequestParam("email") String email, @RequestParam("username") String username,@RequestParam(value = "isComp", defaultValue = "false") boolean isComp) {
		System.out.println("sign up post");
		System.out.println(pass);
		System.out.println(email);
		System.out.println(username);
		System.out.println(isComp);
		getSession("sa", "1234", (Session session) -> {
			StoredProcedureQuery query = session.createStoredProcedureQuery( "SP_TAOLOGIN");
			query.registerStoredProcedureParameter( 1, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter( 2, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter( 3, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter( 4, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter( 5, String.class, ParameterMode.IN);
			query.setParameter(1, email);
			query.setParameter(2, pass);
			query.setParameter(3, username);
			
			if (isComp) {
				query.setParameter(4, "Company");

			} else {
				query.setParameter(4, "User");
			}
			query.setParameter(5, generateNewToken());
			query.execute();
			
			// ket noi compid
			if (isComp) {
				String location = request.getParameter("location");
				String description = request.getParameter("description");
				String phone = request.getParameter("phone");
				String name = request.getParameter("name");
				Company company = new Company(location, description,name,phone);
				session.save(company);
				Transaction transaction = session.beginTransaction();
				session.createSQLQuery("UPDATE UserLogin SET CompId = "+ company.getCompId()+" WHERE Email = '"+email+"'").executeUpdate();
				transaction.commit();
				
			}
		},null);
		return "login/login";
	}
	
	public void test() {
		getSession("sa", "1234", (Session sess) -> {	
			StoredProcedureQuery query = sess.createStoredProcedureQuery( "SP_DANGNHAP");
			query.registerStoredProcedureParameter( 1, String.class, ParameterMode.IN);
			query.setParameter(1, "socbaycff@gmail.com");
			List<Object[]> resultLists = query.getResultList();
			for (Object[] item : resultLists) {
				System.out.print(item[0]);
			}
			
		},() -> {
			
			
		});
	}


	public static void getSession(String login, String pass, SQLHandler handler, ErrorHandler onError) {
		Configuration config = new Configuration();
		config.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		config.setProperty("hibernate.connection.url", "jdbc:sqlserver://localhost:51254;Database=Recruitment;characterEncoding=UTF-8");
		config.setProperty("hibernate.connection.username", login);
		config.setProperty("hibernate.connection.password", pass);
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");

		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Job.class);
		config.addAnnotatedClass(FilterSP.class);
		config.addAnnotatedClass(Company.class);

		try {
			
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			// work with the session
			handler.invoke(session);

			session.close();
			sessionFactory.close();

		} catch (Exception ex) {
			if (onError != null) {
				onError.onError();
			}
			ex.printStackTrace();
		}
	}

}
