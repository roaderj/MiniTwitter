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

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "This is welcome page!");
		return "welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute("title", "Login");
		model.addAttribute("message", "Enter your username/password:");
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

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String loginPage(Model model, Principal principal) {
		model.addAttribute("title", "User Info");

		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();

		model.addAttribute("message",
				"User Info - This is protected page!. Hello " + userName);

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
/*
	@RequestMapping("toLogin")
	public String toLogin(Model model) { 
		model.addAttribute("userBean", new UserBean());
		return "login";
	}

	@RequestMapping("doLogin")
	public ModelAndView doLogin(@ModelAttribute @Valid UserBean userBean,BindingResult result) {
		ModelAndView view = new ModelAndView("login");
		if(!result.hasFieldErrors()) {
			if (!miniTwitterService.authenicateUser(userBean)) {
				result.addError(new ObjectError("err", "Username or password incorrect"));
			} else {
				view.setViewName("welcome");
			}
		}
		return view;
	}*/
}
