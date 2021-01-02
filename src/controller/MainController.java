package controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import entity.FilterSP;
import entity.Job;

@Controller
public class MainController {

	@Autowired
	ServletContext context;

	@RequestMapping("index")
	public String index(ModelMap model, @CookieValue(value = "token") String token) {
		// lay username role tu db bang token
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			// truy van username role
			Query query = sess.createSQLQuery("SELECT Username, Role FROM UserLogin WHERE Token = '" + token + "'");
			List<Object[]> list = query.list();
			model.addAttribute("username", String.valueOf(list.get(0)[0]));
			model.addAttribute("role", String.valueOf(list.get(0)[1]));
			// truy van job theo thu tu add sau cung
			Query newJobQuery = sess.createQuery("FROM Job J ORDER BY J.JobId DESC");
			List<Job> newJobsList = newJobQuery.setMaxResults(5).list();
			model.addAttribute("newJobs", newJobsList);
			// truy van random job
			Query randomJobQuery = sess.createQuery("FROM Job ORDER BY NEWID()");
			List<Job> randomJobsList = randomJobQuery.setMaxResults(3).list();
			model.addAttribute("randomJobs", randomJobsList);

		}, null);
		return "home/index";
	}

	@RequestMapping("jobs")
	public String jobs(ModelMap model, HttpServletResponse response, @CookieValue(value = "token") String token) {

		System.out.println("job list");
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			Query query = sess.createQuery("FROM Job");
			List<Job> list = query.list();

			getFilter(sess, model);
			model.addAttribute("jobs", list);

			Query query1 = sess.createSQLQuery("SELECT Username, Role FROM UserLogin WHERE Token = '" + token + "'");
			List<Object[]> list1 = query1.list();
			model.addAttribute("username", String.valueOf(list1.get(0)[0]));
			model.addAttribute("role", String.valueOf(list1.get(0)[1]));

		}, null);

		return "jobs/jobs";
	}

	@RequestMapping(value = "jobs", method = RequestMethod.POST) // filter mapping
	public String jobsPost(ModelMap model, HttpServletRequest request) {
		System.out.println("job list post");
		ArrayList<String> selectedCat = new ArrayList<String>();
		ArrayList<Integer> selectedEdu = new ArrayList<>();
		ArrayList<Integer> selectedExp = new ArrayList<>();

		String[] ls = request.getParameterValues("cats");
		if (ls != null) {

			for (String str : ls) {
				selectedCat.add(str);
			}
		}

		String[] ls2 = request.getParameterValues("edulvs");
		if (ls2 != null) {

			for (String str : ls2) {
				selectedEdu.add(Integer.parseInt(str));
			}
		}
		String[] ls3 = request.getParameterValues("expYear");
		if (ls3 != null) {

			for (String str : ls3) {
				selectedExp.add(Integer.parseInt(str));
			}
		}
		//
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			// filter use 'IN' querry

			String whereStr = "";

			if (selectedCat.size() != 0) {
				whereStr += "WHERE  job.Category IN :cats";
			}
			if (selectedEdu.size() != 0) {
				if (whereStr != "") {
					whereStr += " AND job.EducationLV IN :edulvs";
				} else {
					whereStr += " WHERE job.EducationLV IN :edulvs";
				}

			}
			if (selectedExp.size() != 0) {
				if (whereStr != "") {
					whereStr += " AND job.ExpYear IN :exps";
				} else {
					whereStr += " WHERE job.ExpYear IN :exps";
				}

			}
			String hql = "FROM Job job ";
			hql += whereStr;
			System.out.println(hql);
			Query query = sess.createQuery(hql);
			if (selectedCat.size() != 0) {

				query.setParameterList("cats", selectedCat);
			}
			if (selectedEdu.size() != 0) {
				query.setParameterList("edulvs", selectedEdu);
			}
			if (selectedExp.size() != 0) {
				query.setParameterList("exps", selectedExp);
			}

			List<Job> filtedJobList = query.getResultList();

//					filtedJobList = jobList.stream().filter(eduFilter).filter(expFilter).filter(catFilter).collect(Collectors.toList());
//					System.out.println("in debug danh sach filter");
			System.out.println(filtedJobList.size());

			model.addAttribute("jobs", filtedJobList);

		}, null);

		return "jobs/jobs";
	}
	
	@RequestMapping("cv-viewer/{jid}/{uid}")
	public String detail(@PathVariable("jid") String jobId,@PathVariable("uid") String userId, ModelMap model, @CookieValue("token") String token) {
		System.out.println("vao cv viewer");
		// truy van voi id de nap vao detail
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> { 
			Query query = sess.createSQLQuery("SELECT Email FROM UserLogin WHERE Token = '" + token + "'");
			List<String> list = query.list();
			model.addAttribute("email", list.get(0));
		}, null);
		model.addAttribute("jobid", jobId);
		model.addAttribute("uid", userId);
		return "cv-viewer/cv-viewer";
	}

	@RequestMapping("job-details/{id}")
	public String detail(@PathVariable("id") String id, ModelMap model, @CookieValue("token") String token) {
		System.out.println("detail with path variable");
		// truy van voi id de nap vao detail
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			// get thong tin job
			Job job = sess.get(Job.class, Integer.parseInt(id));
			model.addAttribute("job", job);
			
			// truy van userid role
			Query query = sess.createSQLQuery("SELECT UserId, Role FROM UserLogin WHERE Token = '" + token + "'");
			List<Object[]> list1 = query.list();
			
			int userId = (int) list1.get(0)[0];
			boolean isUser = String.valueOf(list1.get(0)[1]).equals("User");
			if (isUser == true) {
				System.out.println("Day la user");

			} else {
				System.out.println("khong la user");

			}
			model.addAttribute("isUser", isUser); // neu quyen User thi dc add cv + danh favourite
			// check da dang cv chua
			Query checkExist = sess.createSQLQuery("SELECT UserId FROM ApplyJob WHERE JobId = '" + id + "'");
			List<Integer> list2 = checkExist.list();
			if (list2.size() == 0 ? false : true) {
				System.out.println("da dang tuyen");
			}
			model.addAttribute("uploaded", list2.size() == 0 ? false : true);
			
			// check da add fav chua
			Query checkFav = sess.createSQLQuery("SELECT UserId FROM FavouriteJob WHERE JobId = '" + id + "'");
			List<Integer> list3 = checkFav.list();
			if (list3.size() != 0 ? true : false) {
				System.out.println("da add fav");
				
			} else {
				
				System.out.println("chua add");
			}
			model.addAttribute("favourite", list3.size() != 0 ? true : false);
			
			
			
			// gui thong tin company
			Query compQuery = sess.createSQLQuery(
					"SELECT UserId, Email, Location, C.Description,Name,Phone FROM Job J, Company C, UserLogin U WHERE JobId = "+ id +"  AND J.OwnerId = U.UserId AND U.CompId = C.CompId");
			List<Object[]> list = compQuery.list();
			model.addAttribute("email", list.get(0)[1]);
			model.addAttribute("location", list.get(0)[2]);
			model.addAttribute("description", list.get(0)[3]);
			model.addAttribute("comp_name", list.get(0)[4]);
			model.addAttribute("phone", list.get(0)[5]);

		}, null);

		return "job-details/job-details";
	}
	
	@RequestMapping(value = "postCV/{id}", method = RequestMethod.POST)
	public String postCV(@PathVariable("id") String id, ModelMap model, @CookieValue("token") String token,@RequestParam("cvtuyendung") MultipartFile cvpdf) throws IllegalStateException, IOException {
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> { 
			Query query = sess.createSQLQuery("SELECT UserId FROM UserLogin WHERE Token = '" + token + "'");
			List<Integer> list = query.list();
			System.out.println("id user la " + list.get(0));
			System.out.println("id job la " + id);
			Transaction transaction = sess.beginTransaction();
			Query insert = sess.createSQLQuery("INSERT INTO ApplyJob VALUES("+id +","+list.get(0)+",GETDATE())");
			int executeUpdate = insert.executeUpdate();
			transaction.commit();
			if (!cvpdf.isEmpty() && executeUpdate != 0) {
				String uploadsDir = "/cvs/" + id;
				String realPathtoUploads = context.getRealPath(uploadsDir);
				if (!new File(realPathtoUploads).exists()) {
					new File(realPathtoUploads).mkdir();
				}

				System.out.println("realPathtoUploads = {}" + realPathtoUploads);

				String orgName = String.valueOf(list.get(0)); // luu anh co ten la id cua job va id cua nguoi tuyen dung
				String filePath = realPathtoUploads + "/"+ orgName;
				System.out.println(filePath);
				File dest = new File(filePath);
				try {
					cvpdf.transferTo(dest);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, null);
		
		
		return "redirect:/job-details/"+ id +".html";
	}
	
	@RequestMapping(value = "removeCV/{id}", method = RequestMethod.POST)
	public String detailRemoveCV(@PathVariable("id") String id, @CookieValue("token") String token) throws IllegalStateException, IOException {
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> { 
			Query query = sess.createSQLQuery("SELECT UserId FROM UserLogin WHERE Token = '" + token + "'");
			List<Integer> list = query.list();
			System.out.println("id user la " + list.get(0));
			System.out.println("id job la " + id);
			Transaction transaction = sess.beginTransaction();
			Query insert = sess.createSQLQuery("DELETE FROM ApplyJob WHERE JobId = " + id + " AND UserId = " + list.get(0));
			int executeUpdate = insert.executeUpdate();
			transaction.commit();
			if (executeUpdate != 0) {
				String uploadsDir = "/cvs/" + id;
				String realPathtoUploads = context.getRealPath(uploadsDir);
				if (!new File(realPathtoUploads).exists()) {
					new File(realPathtoUploads).mkdir();
				}

				System.out.println("realPathtoUploads = {}" + realPathtoUploads);

				String orgName = String.valueOf(list.get(0)); // luu anh co ten la id cua job va id cua nguoi tuyen dung
				String filePath = realPathtoUploads + "/"+ orgName;
				System.out.println(filePath);
				File dest = new File(filePath);
				try {
					dest.delete();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, null);
		
		
		return "redirect:/job-details/"+ id +".html";
	}

	@RequestMapping("job-add")
	public String addJob() {
		return "add-job/addjob";
	}

	@RequestMapping(value = "job-add", method = RequestMethod.POST)
	public String addJobPost(Job job, ModelMap model, @RequestParam("Luong") String luongStr,
			@RequestParam("minhhoa") MultipartFile minhHoa, @CookieValue("token") String token)
			throws IllegalStateException, IOException {
		// System.out.println(luongStr);
		System.out.println(minhHoa.getOriginalFilename());
		System.out.println("job add post");
		int jobid = 0;
		int lastId[] = { 0 };
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			// get user id
			int userId = (int) sess.createSQLQuery("SELECT UserId FROM UserLogin WHERE Token = '" + token + "'").list()
					.get(0);
			Transaction transaction = sess.beginTransaction();
			try {
				Job job1 = new Job(job.getTitle(), job.getDescription(), job.getCategory(), job.getEducationLV(),
						job.getExpYear(), new BigInteger(luongStr), userId);
				sess.save(job1);// luu db
				lastId[0] = ((BigDecimal) sess.createSQLQuery("SELECT IDENT_CURRENT('Job')").list().get(0)).intValue();

				transaction.commit();

			} catch (Exception ex) {
				System.out.println("catch----------");
				transaction.rollback();
				System.out.println(ex.getMessage());

				ex.printStackTrace();
			}

		}, null);
		// save file vao thu muc resource
		if (!minhHoa.isEmpty()) {
			String uploadsDir = "/uploads/";
			String realPathtoUploads = context.getRealPath(uploadsDir);
			if (!new File(realPathtoUploads).exists()) {
				new File(realPathtoUploads).mkdir();
			}

			System.out.println("realPathtoUploads = {}" + realPathtoUploads);

			String orgName = String.valueOf(lastId[0]); // luu anh co ten la id cua job
			String filePath = realPathtoUploads + orgName;
			File dest = new File(filePath);
			minhHoa.transferTo(dest);

		}

		return "redirect:/jobs.html";
	}

	@RequestMapping("edit")
	public String editProfile(@CookieValue("token") String token, ModelMap model) {
		System.out.println("edit profile da vao");
		// truy van
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			// truy van 2 lan
			// lan dau truy van co ban
			// lan 2 if la company thi truy van them company
			Query query = sess
					.createSQLQuery("SELECT Username, Email, CompId FROM UserLogin  WHERE Token = '" + token + "' ");
			List<Object[]> list = query.list();
			// truyen data voi
			model.addAttribute("username", list.get(0)[0]);
			model.addAttribute("email", list.get(0)[1]);

			try {
				int compId = (int) list.get(0)[2];
				model.addAttribute("compId", compId); // add xuong de truyen lai cho post
				Query query1 = sess
						.createSQLQuery("SELECT Location, Description,Name,Phone FROM Company WHERE CompId =" + compId);
				List<Object[]> list1 = query1.list();
				model.addAttribute("location", list1.get(0)[0]);
				model.addAttribute("description", list1.get(0)[1]);
				model.addAttribute("comp_name", list1.get(0)[2]);
				model.addAttribute("phone", list1.get(0)[3]);
			} catch (Exception e) {
				model.addAttribute("compId", 0); // khong phai acc company
			}

		}, null);

		;

		return "edit-profile/edit-profile";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String editProfilePost(@CookieValue("token") String token, ModelMap model, HttpServletRequest request) {
		System.out.println("edit profile da vao");
		// nhan dc data moi va save
		// 2 flow cho 2 loai acc
		System.out.println("test post");

		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			Transaction transaction = sess.beginTransaction();
			try {
				String username = request.getParameter("username");
				String email = request.getParameter("email");
				String companyName = request.getParameter("comp_name");
				Query query = sess.createSQLQuery("UPDATE UserLogin SET Username = '" + username + "',Email = '" + email
						+ "' WHERE Token = '" + token + "'");
				query.executeUpdate();
				if (companyName != null) {
					String location = request.getParameter("location");
					String phone = request.getParameter("phone");
					String description = request.getParameter("description");
					String compId = request.getParameter("compId");
					Query query1 = sess.createSQLQuery(
							"UPDATE Company SET Location = '" + location + "', Phone = '" + phone + "', Description = '"
									+ description + "',Name = '" + companyName + "' WHERE CompId =" + compId);
					query1.executeUpdate();

				}
				transaction.commit();

			} catch (Exception ex) {
				System.out.println("catch----------");
				transaction.rollback();
				System.err.println(ex.getMessage());
				// ex.printStackTrace();
			}

		}, null);

		return "redirect:/edit.html"; // van o trang nay k di dau
	}
	
	@RequestMapping("cvlist/{id}")
	public String cvlist(@PathVariable("id") String id, ModelMap model) {
		System.out.println("cvlist da vao");
		System.out.println(id);

		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			// lay danh sach email kem userid
			Query query = sess
					.createSQLQuery("select U.UserId, Email, ApplyTime, JobId from ApplyJob A, UserLogin U where JobId = "+id +" and U.UserId = A.UserId");
			List<Object[]> list = query.list();
			model.addAttribute("cvs", list);
		}, null);

		return "cvlist/cvlist";
	}

	@RequestMapping("update/{id}")
	public String updateJob(@PathVariable("id") String id, ModelMap model) {
		System.out.println("update da vao");
		System.out.println(id);
		// doi lai truy van chu k dc dung cache
		// chua truyen data xuong view
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			Job job = sess.get(Job.class, Integer.parseInt(id));
			model.addAttribute("job", job);
		}, null);

		return "update-job/update-job";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public String updateSave(@PathVariable("id") String id, Job job, @RequestParam("Luong") String luongStr,
			@CookieValue("token") String token) {
		System.out.println("vao update save");
		// update bang hibernate
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			int userId = (int) sess.createSQLQuery("SELECT UserId FROM UserLogin WHERE Token = '" + token + "'").list()
					.get(0);
			Transaction transaction = sess.beginTransaction();
			try {
				Job job1 = new Job(job.getTitle(), job.getDescription(), job.getCategory(), job.getEducationLV(),
						job.getExpYear(), new BigInteger(luongStr), userId);
				job1.setJobId(Integer.parseInt(id));
				sess.update(job1);
				transaction.commit();

			} catch (Exception ex) {
				System.out.println("catch----------");
				transaction.rollback();
				System.err.println(ex.getMessage());
				// ex.printStackTrace();
			}

		}, null);

		return "redirect:/job-details/" + id + ".html"; // quay ve detail
	}

	@RequestMapping("delete/{id}")
	public String deleteJob(@PathVariable("id") String id) {
		System.out.println("vao delete");
		// delete voi id
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			Transaction transaction = sess.beginTransaction();
			try {
				Job job1 = new Job();
				job1.setJobId(Integer.parseInt(id));

				String uploadsDir = "/uploads/";
				String realPathtoUploads = context.getRealPath(uploadsDir);
				if (!new File(realPathtoUploads).exists()) {
					new File(realPathtoUploads).mkdir();
				}

				String filePath = realPathtoUploads + String.valueOf(job1.getJobId());
				File dest = new File(filePath);

				sess.delete(job1);
				dest.delete();
				transaction.commit();

			} catch (Exception ex) {
				System.out.println("catch----------");
				transaction.rollback();
				System.err.println(ex.getMessage());
				// ex.printStackTrace();
			}

		}, null);

		return "redirect:/jobs.html";
	}
	
	
	@RequestMapping("addFavourite/{id}")
	public String addFavourite(@PathVariable("id") String id, ModelMap model,@CookieValue("token") String token) {
		System.out.println("add favourite da vao");
	
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			// lay id user
			Query query = sess.createSQLQuery("SELECT UserId FROM UserLogin WHERE Token = '" + token + "'");
			List<Integer> list = query.list();
			System.out.println("id user la " + list.get(0));
			System.out.println("id job la " + id);
			
			// add favourite 
			Transaction transaction = sess.beginTransaction();
			Query insert = sess.createSQLQuery("INSERT INTO FavouriteJob VALUES("+id +","+list.get(0)+")");
			int executeUpdate = insert.executeUpdate();
			transaction.commit();
		}, null);

		return "redirect:/job-details/"+id+".html";
	}
	
	@RequestMapping("removeFavourite/{id}")
	public String removeFavourite(@PathVariable("id") String id, ModelMap model,@CookieValue("token") String token) {
		System.out.println("remove favourite da vao");
	
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			// lay id user
			Query query = sess.createSQLQuery("SELECT UserId FROM UserLogin WHERE Token = '" + token + "'");
			List<Integer> list = query.list();
			System.out.println("id user la " + list.get(0));
			System.out.println("id job la " + id);
			
			// add favourite 
			Transaction transaction = sess.beginTransaction();
			Query insert = sess.createSQLQuery("DELETE FROM FavouriteJob WHERE UserId = " + list.get(0) +" AND JobId = " + id);
			int executeUpdate = insert.executeUpdate();
			transaction.commit();
		}, null);

		return "redirect:/job-details/"+id+".html";
	}
	
	@RequestMapping("fav-jobs")
	public String favouriteJobs(ModelMap model, HttpServletResponse response, @CookieValue(value = "token") String token) {

		System.out.println("job list");
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			
		
			Query userQuery = sess.createSQLQuery("SELECT Username, Role, UserId FROM UserLogin WHERE Token = '" + token + "'");
			List<Object[]> userInfo = userQuery.list();
			model.addAttribute("username", String.valueOf(userInfo.get(0)[0]));
			model.addAttribute("role", String.valueOf(userInfo.get(0)[1]));
			
			Query favJobQuery = sess.createQuery("FROM Job J, FavouriteJob F WHERE J.JobId = F.JobId AND UserId = " + String.valueOf(userInfo.get(0)[2]));
			List<Object[]> jobList = favJobQuery.list();
			
			List<Job> favJobs = new ArrayList<Job>();
			for (Object[] j : jobList) {
				favJobs.add(((Job) j[0]));		 // lay job only
			}
			//getFilter(sess, model); // chua lam filter job
			model.addAttribute("jobs", favJobs);
		}, null);

		return "jobs/jobs";
	}

	private void getFilter(Session session, ModelMap model) {
		Query query = session.createSQLQuery("EXEC SP_CATFILLTER").addEntity(FilterSP.class);

		List<FilterSP> cats = query.list();
		model.addAttribute("cats", cats);
		Query query1 = session.createSQLQuery("EXEC SP_EDUFILLTER").addEntity(FilterSP.class);

		List<FilterSP> edulvs = query1.list();
		model.addAttribute("edulvs", edulvs);
		Query query2 = session.createSQLQuery("EXEC SP_EXPFILLTER").addEntity(FilterSP.class);

		List<FilterSP> expyears = query2.list();
		model.addAttribute("expYear", expyears);
	}
	
	

}
