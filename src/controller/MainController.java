package controller;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import entity.Job;

@Controller
public class MainController {
	private List<Job> jobList;
	
	@RequestMapping("index")
	public String index( ModelMap model) {
		model.addAttribute("username", LoginSignUpController.username);
		model.addAttribute("role", LoginSignUpController.role);
		return "home/index";
	}
	
	@RequestMapping("jobs")
	public String jobs(ModelMap model) {
		System.out.println("job list");
		LoginSignUpController.getSession(LoginSignUpController.loginMail, LoginSignUpController.loginPass, (Session sess)-> {
				Query query = sess.createQuery("FROM Job");
				jobList = query.list();
				model.addAttribute("jobs", jobList);
		}, null);
		return "jobs/jobs";
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String jobsPost(ModelMap model) {
		System.out.println("job list post");
		
		model.addAttribute("jobs", jobList);
		return "jobs/jobs";
	}
	
	@RequestMapping("job-details/{id}")
	public String detail(@PathVariable("id") String id) {
		System.out.println("detail with path variable");
		System.out.println(id);
		return "job-details/job-details";
	}
	
	@RequestMapping("job-add")
	public String addJob() {
		return "add-job/addjob";
	}
	
	@RequestMapping(value = "job-add", method = RequestMethod.POST)
	public String addJobPost(Job job, ModelMap model, @RequestParam("Luong") String luongStr) {
	//	System.out.println(luongStr);
		System.out.println("job add post");
		LoginSignUpController.getSession(LoginSignUpController.loginMail, LoginSignUpController.loginPass, (Session sess)-> {
			Transaction transaction = sess.beginTransaction();
			try {
				Job job1 = new Job(job.getTitle(),job.getDescription(),job.getCategory(),job.getEducationLV(),job.getExpYear(),new BigInteger(luongStr));
				sess.save(job1);
				transaction.commit();
				
			} catch (Exception ex) {
				System.out.println("catch----------");
				transaction.rollback();
				System.err.println(ex.getMessage());
			//	ex.printStackTrace();
			}
			
	}, null);

		return "redirect:/jobs.html";
	}
}
