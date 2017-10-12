package fr.elearning.kaf.home;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(method=RequestMethod.GET,produces="text/html")
	public String homeAction(Map<String, Object> model){
		model.put("message", "Jus de bagarre pour garder la pêche");
		return "welcome";
	}
}
