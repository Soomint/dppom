package dppom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosmo.Dppom.Constant;

import dppom.command.study.StudyCommand;
import dppom.command.study.StudySearchCommand;
import dppom.command.study.StudySelectCommand;

// 어학/자격증 컨트롤러 
@Controller
public class StudyController {

	// Spring JDBC를 사용하기 위한 설정
	// JDBC템플릿 설정
	private JdbcTemplate template;
	StudyCommand command = null;
	StudySelectCommand sSelCommand = null;
	StudySearchCommand sSeaCommand = null;

	// setter설정
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	// 어학/자격증
	@RequestMapping("/Study")
	public String study(Model model, HttpServletRequest req) {
		System.out.println("study()호출");
		model.addAttribute("req", req);
		command = new StudyCommand();
		command.execute(model);

		return "Study/Study";
	}

	@RequestMapping("/StudySelect")
	public String studySelect(Model model, HttpServletRequest req) {
		System.out.println("studySelect()호출");
		model.addAttribute("req", req);
		sSelCommand = new StudySelectCommand();
		sSelCommand.execute(model);

		return "Study/Study";
	}

	@RequestMapping(value = "/StudySearch", method = RequestMethod.GET)
	public String QnASearch(Model model, HttpServletRequest req) {
		System.out.println("StudySearch()호출");
		model.addAttribute("req", req);
		sSeaCommand = new StudySearchCommand();
		sSeaCommand.execute(model);

		return "Study/Study";
	}
}