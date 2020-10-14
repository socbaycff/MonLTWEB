package controller;


import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import entity.Job;
import entity.User;
import utils.ErrorHandler;
import utils.SQLHandler;

@Controller
public class LoginSignUpController {
	public static String loginMail;
	public static String loginPass;
	public static String username;
	public static String role;
	@RequestMapping("login")
	public String login() {

		System.out.print("vao login");
		return "login/login";
	}
	static boolean isSuccess = true;
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPost(@RequestParam("pass") String pass, @RequestParam("email") String email, ModelMap model) {
		System.out.println("postlogin");
		System.out.println(pass);
		System.out.println(email);
		loginMail = email;
		loginPass = pass;
		isSuccess = true;
		getSession(loginMail, loginPass, (Session session)-> {
			StoredProcedureQuery query = session.createStoredProcedureQuery( "SP_DANGNHAP");
			query.registerStoredProcedureParameter( 1, String.class, ParameterMode.IN);
			query.setParameter(1, email);
			List<Object[]> resultLists = query.getResultList();
			for (Object[] item : resultLists) {
				username = item[0].toString();
				role = item[1].toString();
			}
			
		}, () -> {
			isSuccess = false;
		});
		if (isSuccess) {
			// gui thong tin user da login
			System.out.println(username);
			System.out.println(role);
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
	public String signupPost(ModelMap model,@RequestParam("pass") String pass, @RequestParam("email") String email, @RequestParam("username") String username,@RequestParam("isComp") boolean isComp) {
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
			query.setParameter(1, email);
			query.setParameter(2, pass);
			query.setParameter(3, username);
			if (isComp) {
				query.setParameter(4, "Company");
			} else {
				query.setParameter(4, "User");
			}
			
			boolean isSuccess = query.execute();
			
		},null);
		model.addAttribute("pass", pass);
		model.addAttribute("email", email);
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
			
		},null);
	}


	public static void getSession(String login, String pass, SQLHandler handler, ErrorHandler onError) {
		Configuration config = new Configuration();
		config.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		config.setProperty("hibernate.connection.url", "jdbc:sqlserver://localhost:51254;Database=Recruitment");
		config.setProperty("hibernate.connection.username", login);
		config.setProperty("hibernate.connection.password", pass);
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Job.class);

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
