package MiniTwitter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class MainController {
	
	@RequestMapping("/home")
	public String welcome(Model model) { 
		model.addAttribute("welcome", "Welcome to MiniTwitter");
		return "helloworld";
	}
}
