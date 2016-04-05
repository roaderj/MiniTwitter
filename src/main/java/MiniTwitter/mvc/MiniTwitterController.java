package MiniTwitter.mvc;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import MiniTwitter.object.UserRelation;
import MiniTwitter.service.IMiniTwitterService;

@Controller
public class MiniTwitterController {

	@Autowired
	private IMiniTwitterService miniTwitterService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "loginPage";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = {"/", "/userInfo"} , method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {
		String userName = principal.getName();
		model.addAttribute("user", userName);
		List<String> tweets = miniTwitterService.getTweet(userName, "");
		model.addAttribute("tweets", tweets);
		return "userInfoPage";
	}
	
	@RequestMapping(value = {"/tweetMessage"} , method = RequestMethod.POST)
	public String tweetMessage(Model model, Principal principal, @RequestParam("message") String message) {
		String userName = principal.getName();
		model.addAttribute("user", userName);
		miniTwitterService.tweetMessage(userName, message);
		List<String> tweets = miniTwitterService.getTweet(userName, "");
		model.addAttribute("tweets", tweets);
		return "userInfoPage";
	}
	
	@RequestMapping(value = {"/tweetsSearch"} , method = RequestMethod.POST)
	public String tweetsSearch(Model model, Principal principal, @RequestParam("search") String search) {
		String userName = principal.getName();
		model.addAttribute("user", userName);
		List<String> tweets = miniTwitterService.getTweet(userName, search);
		model.addAttribute("tweets", tweets);
		return "userInfoPage";
	}
	
	@RequestMapping(value = {"/followers"} , method = RequestMethod.GET)
	public String followerPage(Model model, Principal principal) {
		String userName = principal.getName();
		model.addAttribute("user", userName);
		List<String> followers = miniTwitterService.getFollowers(userName);
		model.addAttribute("followers", followers);
		return "followerPage";
	}
	
	@RequestMapping(value = {"/following"} , method = RequestMethod.GET)
	public String followingPage(Model model, Principal principal) {
		String userName = principal.getName();
		model.addAttribute("user", userName);
		List<String> followings = miniTwitterService.getFollowings(userName);
		model.addAttribute("followings", followings);
		return "followingPage";
	}
	
	@RequestMapping(value = {"/findUser"} , method = RequestMethod.GET)
	public String findPage(Model model, Principal principal, @RequestParam("findUser") String find) {
		String userName = principal.getName();
		List<UserRelation> users = miniTwitterService.findUser(userName,find);
		model.addAttribute("users", users);
		return "findPage";
	}
	
	@RequestMapping(value = {"/followToggle"} , method = RequestMethod.POST)
	public String handleFollow(Model model, Principal principal, 
			@RequestParam("followUser") String followUser, @RequestParam("relation") String relation) {
		String userName = principal.getName();
		if (relation.equals("0"))
			miniTwitterService.startFollow(userName,followUser);
		else if (relation.equals("1")) {
			System.out.println("reach");
			miniTwitterService.unFollow(userName,followUser);
		}
		return "redirect:/userInfo";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {
		model.addAttribute("title", "Access Denied!");

		if (principal != null) {
			model.addAttribute("message", "Hi " + principal.getName()
					+ "<br> You do not have permission to access this page!");
		} else {
			model.addAttribute("msg",
					"You do not have permission to access this page!");
		}
		return "403Page";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupPage(Model model) { 
		return "signupPage";
	}

	@RequestMapping(value = "/signupForm", method = RequestMethod.POST)
	public String doSignup(@RequestParam("username") String uname,
            @RequestParam("password") String pwd) {
		int res = miniTwitterService.signupUser(uname, pwd);
		if (res == -1) {
			return "redirect:/signup?userExist";
		} else if (res == -2) {
			return "redirect:/signup?error";
		} else {
			return "redirect:/login?signup";
		}
	}
}
