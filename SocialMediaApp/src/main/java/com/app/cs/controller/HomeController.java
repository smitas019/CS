/**
 * 
 */
package com.app.cs.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.cs.entity.User;
import com.app.cs.service.SocialMediaService;

/**
 * @author suhas
 *
 */
@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	private SocialMediaService socialMediaService;
	
	Random rand = new Random();
	
	/**
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	/**
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/verifyUser", method = RequestMethod.POST)
	public ModelAndView verifyUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userName= req.getParameter("userName");
		String password= req.getParameter("password");
		ModelAndView model = null;
		req.getSession().setAttribute("userName",userName);
		if((userName==null || password==null) || userName=="" || password=="") {
			model=new ModelAndView("home");
			model.addObject("errorMsg", "Please enter username and password!!");
		}else {
			User resp=socialMediaService.validateUser(userName,password);
			if(resp!=null) {
				req.getRequestDispatcher("dashboard").forward(req, res);
			}else {
				model=new ModelAndView("home");
				model.addObject("errorMsg", "User name or password is incorrect !!");
			}
		}
		
		return model;
	}
	
	/**
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/dashboard", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView dashboard(HttpServletRequest req, HttpServletResponse res) {
		String userName= req.getParameter("userName");
		if(userName==null) {
			userName = (String) req.getSession().getAttribute("userName");
		}
		ModelAndView model = null;
		req.getSession().setAttribute("userName",userName);
		User resp=socialMediaService.getUser(userName);
		model = getModelObject(req, userName, resp);
		model.addObject("successMsg",req.getAttribute("successMsg"));
		model.addObject("errorMsg",req.getAttribute("errorMsg"));
		return model;
	}

	/**
	 * @param req
	 * @param userName
	 * @param resp
	 * @return
	 */
	private ModelAndView getModelObject(HttpServletRequest req, String userName, User resp) {
		ModelAndView model;
		if(resp!=null) {
			req.setAttribute("userName", userName);
			model=new ModelAndView("dashboard");
			model.addObject("userName", userName);
			model.addObject("posts", resp.getUsersPost());
		}else {
			model=new ModelAndView("home");
			model.addObject("errorMsg", "Something went wrong !!");
		}
		return model;
	}
	
	/**
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/createPost", method = RequestMethod.POST)
	public void createPost(HttpServletRequest req, HttpServletResponse res,
			Model model) throws ServletException, IOException {
		String userName= (String) req.getSession().getAttribute("userName");
		String content=req.getParameter("content");
		if(socialMediaService.createPost(userName,rand.nextInt(),content)) {
			req.setAttribute("successMsg", "Post has been successfully created!");
			/*
			 * model.addAttribute("posts",socialMediaService.getUser(userName).getUsersPost(
			 * )); return "dashboard";
			 */
			req.getRequestDispatcher("dashboard").forward(req, res);
		}
		model.addAttribute("errorMsg", "Something went wrong !!");
		req.getRequestDispatcher("home").include(req, res);
	}

	/**
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest req, HttpServletResponse res,
			Model model) throws ServletException, IOException {
		String userName= (String) req.getSession().getAttribute("userName");
		String name=req.getParameter("name");
		List<User> userList=socialMediaService.search(name,userName);
		if(userList!=null && userList.size()>0) {
			model.addAttribute("user", userList);
			return "searchResult";
		}
		req.setAttribute("errorMsg", "No Search found !!");
		req.getRequestDispatcher("dashboard").forward(req, res);
		return "dashboard";
	}
	
	/**
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/follow", method = {RequestMethod.GET, RequestMethod.POST})
	public void follow(HttpServletRequest req, HttpServletResponse res,
			Model model) throws ServletException, IOException {
		String userName= (String) req.getSession().getAttribute("userName");
		String folowee=req.getParameter("folowee");
		if(socialMediaService.follow(userName, folowee)) {
			req.setAttribute("successMsg", "Followed!");
			/*
			 * model.addAttribute("posts",
			 * socialMediaService.getUser(userName).getUsersPost()); return "dashboard";
			 */
			
			req.getRequestDispatcher("dashboard").forward(req, res);
		}
		model.addAttribute("errorMsg", "Something went wrong !!");
		req.getRequestDispatcher("home").include(req, res);
		}
	
	/**
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/unfollow", method = {RequestMethod.GET, RequestMethod.POST})
	public void unfollow(HttpServletRequest req, HttpServletResponse res,
			Model model) throws ServletException, IOException {
		String userName= (String) req.getSession().getAttribute("userName");
		String unfolowee=req.getParameter("folowee");
		if(socialMediaService.unfollow(userName, unfolowee)) {
			req.setAttribute("successMsg", "Unfollowed!");
			/*
			 * model.addAttribute("posts",
			 * socialMediaService.getUser(userName).getUsersPost()); return "dashboard";
			 */
			req.getRequestDispatcher("dashboard").forward(req, res);
		}
		model.addAttribute("errorMsg", "Something went wrong !!");
		req.getRequestDispatcher("home").include(req, res);
	}
	
	/**
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req,HttpServletResponse res,Model model) {
		req.getSession().invalidate();
		return "home";
	}
}
