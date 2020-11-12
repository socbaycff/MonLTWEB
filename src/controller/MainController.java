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
			Query query = sess.createSQLQuery("SELECT Username, Role FROM UserLogin WHERE Token = '" + token + "'");
			List<Object[]> list = query.list();
			model.addAttribute("username", String.valueOf(list.get(0)[0]));
			model.addAttribute("role", String.valueOf(list.get(0)[1]));
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

	@RequestMapping("job-details/{id}")
	public String detail(@PathVariable("id") String id, ModelMap model) {
		System.out.println("detail with path variable");
		// truy van voi id de nap vao detail
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			Job job = sess.get(Job.class, Integer.parseInt(id));
			model.addAttribute("job", job);
		}, null);

		return "job-details/job-details";
	}

	@RequestMapping("job-add")
	public String addJob() {
		return "add-job/addjob";
	}

	@RequestMapping(value = "job-add", method = RequestMethod.POST)
	public String addJobPost(Job job, ModelMap model, @RequestParam("Luong") String luongStr,
			@RequestParam("minhhoa") MultipartFile minhHoa) throws IllegalStateException, IOException {
		// System.out.println(luongStr);
		System.out.println(minhHoa.getOriginalFilename());
		System.out.println("job add post");
		int jobid = 0;
		int lastId[] = { 0 };
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			Transaction transaction = sess.beginTransaction();
			try {
				Job job1 = new Job(job.getTitle(), job.getDescription(), job.getCategory(), job.getEducationLV(),
						job.getExpYear(), new BigInteger(luongStr));
				sess.save(job1);// luu db
				lastId[0] = ((BigDecimal) sess.createSQLQuery("SELECT IDENT_CURRENT('Job')").list().get(0)).intValue();

				transaction.commit();

			} catch (Exception ex) {
				System.out.println("catch----------");
				transaction.rollback();
				System.err.println(ex.getMessage());
				// ex.printStackTrace();
			}

		}, null);
		// save file vao thu muc resource
		if (!minhHoa.isEmpty()) {
			String uploadsDir = "/uploads/";
			String realPathtoUploads = context.getRealPath(uploadsDir);
			if (!new File(realPathtoUploads).exists()) {
				new File(realPathtoUploads).mkdir();
			}

			// System.out.println("realPathtoUploads = {}"+ realPathtoUploads);

			String orgName = String.valueOf(lastId[0]); // luu anh co ten la id cua job
			String filePath = realPathtoUploads + orgName;
			File dest = new File(filePath);
			minhHoa.transferTo(dest);

		}

		return "redirect:/jobs.html";
	}

	@RequestMapping("update/{id}")
	public String updateJob(@PathVariable("id") String id, ModelMap model) {
		System.out.println("update da vao");
		System.out.println(id);
		// doi lai truy van chu k dc dung cache

		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			Job job = sess.get(Job.class, Integer.parseInt(id));
			model.addAttribute("job", job);
		}, null);

		return "update-job/update-job";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public String updateSave(@PathVariable("id") String id, Job job, @RequestParam("Luong") String luongStr) {
		System.out.println("vao update save");
		// update bang hibernate
		LoginSignUpController.getSession("sa", "1234", (Session sess) -> {
			Transaction transaction = sess.beginTransaction();
			try {
				Job job1 = new Job(job.getTitle(), job.getDescription(), job.getCategory(), job.getEducationLV(),
						job.getExpYear(), new BigInteger(luongStr));
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
