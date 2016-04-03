package MiniTwitter.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import MiniTwitter.bean.UserBean;
import MiniTwitter.service.IMiniTwitterService;
 
@Controller
public class MiniTwitterController {
	
	@Autowired
	private IMiniTwitterService miniTwitterService;
	
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
	}
}
