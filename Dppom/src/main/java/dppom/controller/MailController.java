package dppom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dppom.mail.MailSender;

@Controller
public class MailController {
	@RequestMapping("/member/MailAgree")
	public String mailAgree(Model model, HttpServletRequest req) {
		model.addAttribute("email", req.getParameter("email"));
		String authKey = MailSender.MailMain(req.getParameter("email"));
		model.addAttribute("key", authKey);
		return "member/MailAgree";
	}
}
