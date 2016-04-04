package MiniTwitter.mvc;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import MiniTwitter.bean.UserBean;
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
		model.addAttribute("message", userName);
		return "userInfoPage";
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
		model.addAttribute("userBean", new UserBean());
		return "signupPage";
	}

	@RequestMapping("doSignup")
	public ModelAndView doSignup(@ModelAttribute @Valid UserBean userBean,BindingResult result) {
		ModelAndView view = new ModelAndView("signupPage");
		if(!result.hasFieldErrors()) {
			int res = miniTwitterService.signupUser(userBean);
			if (res == -1) {
				result.addError(new ObjectError("userId", "Username already used"));
			} else if (res == -2) {
				result.addError(new ObjectError("err", "Falied to register user"));
			} else {
				view.setViewName("redirect:/login?signup");
			}
		}
		return view;
	}
}
