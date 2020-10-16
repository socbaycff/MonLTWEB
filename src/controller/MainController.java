package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import entity.FilterSP;
import entity.Job;

@Controller
public class MainController {
	private List<Job> jobList;
	private List<Job> filtedJobList;
	public Job selectedJob;
	private ArrayList<String> selectedCat = new ArrayList<String>();
	private ArrayList<String> selectedEdu = new ArrayList<String>();;
	private ArrayList<String> selectedExp = new ArrayList<String>();;

	@RequestMapping("index")
	public String index(ModelMap model) {
		model.addAttribute("username", LoginSignUpController.username);
		model.addAttribute("role", LoginSignUpController.role);
		return "home/index";
	}

	@RequestMapping("jobs")
	public String jobs(ModelMap model) {
		System.out.println("job list");
		LoginSignUpController.getSession(LoginSignUpController.loginMail, LoginSignUpController.loginPass,
				(Session sess) -> {
					Query query = sess.createQuery("FROM Job");
					jobList = query.list();

					getFilter(sess, model);
					model.addAttribute("jobs", jobList);
				}, null);
		model.addAttribute("role", LoginSignUpController.role);
		model.addAttribute("username", LoginSignUpController.username);
		return "jobs/jobs";
	}

	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String jobsPost(ModelMap model, HttpServletRequest request) {
		System.out.println("job list post");
		selectedCat.clear();
		selectedEdu.clear();
		selectedExp.clear();
		String[] ls = request.getParameterValues("cats");
		if (ls != null) {
			
			for (String str : ls) {
				selectedCat.add(str);
			}
		}

		String[] ls2 = request.getParameterValues("edulvs");
		if (ls2 != null) {
			
			for (String str : ls2) {
				selectedEdu.add(str);
			}
		}
		String[] ls3 = request.getParameterValues("expYear");
		if (ls3 != null) {
			
			for (String str : ls3) {
				selectedExp.add(str);
			}
		}
		System.out.println("size cua edu");
		System.out.println(selectedEdu.size());
		System.out.println(jobList.get(0).getEducationLV());
		System.out.println(selectedEdu);
		Predicate<Job> catFilter = job -> {
			if (selectedCat.size() == 0) {
				return true;
			} 
			return selectedCat.contains(job.getCategory());
		};
		Predicate<Job> eduFilter = job -> {
			if (selectedEdu.size() == 0) {
				return true;
			}
			return selectedEdu.contains(job.getEducationLV().toString());
		};
		Predicate<Job> expFilter = job -> {
			if (selectedExp.size() == 0) {
				return true;
			}
			return selectedExp.contains(job.getExpYear().toString());
		};
		
		filtedJobList = jobList.stream().filter(eduFilter).filter(expFilter).filter(catFilter).collect(Collectors.toList());
		System.out.println("in debug danh sach filter");
		System.out.println(filtedJobList.size());

		model.addAttribute("jobs", filtedJobList);
		return "jobs/jobs";
	}

	@RequestMapping("job-details/{id}")
	public String detail(@PathVariable("id") String id, ModelMap model) {
		System.out.println("detail with path variable");
		// truy van voi id de nap vao detail
		for (Job job : jobList) {

			if (job.getJobId() == Integer.parseInt(id)) {
				selectedJob = job;
				model.addAttribute("job", job);
				break;
			}
		}
		return "job-details/job-details";
	}

	@RequestMapping("job-add")
	public String addJob() {
		return "add-job/addjob";
	}

	@RequestMapping(value = "job-add", method = RequestMethod.POST)
	public String addJobPost(Job job, ModelMap model, @RequestParam("Luong") String luongStr) {
		// System.out.println(luongStr);
		System.out.println("job add post");
		LoginSignUpController.getSession(LoginSignUpController.loginMail, LoginSignUpController.loginPass,
				(Session sess) -> {
					Transaction transaction = sess.beginTransaction();
					try {
						Job job1 = new Job(job.getTitle(), job.getDescription(), job.getCategory(),
								job.getEducationLV(), job.getExpYear(), new BigInteger(luongStr));
						sess.save(job1);
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

	/*
	 * @ModelAttribute(value = "cats") public List<FilterSP> getCats() { return
	 * cats; }
	 * 
	 * @ModelAttribute(value = "edulvs") public List<FilterSP> getEdulv() { return
	 * edulvs; }
	 * 
	 * @ModelAttribute(value = "expYear") public List<FilterSP> getExp() { return
	 * expyears; }
	 */

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
