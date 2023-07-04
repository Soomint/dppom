package dppom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosmo.Dppom.Constant;

import dppom.command.qna.QnAAdminViewCommand;
import dppom.command.qna.QnAAdminWriteCommand;
import dppom.command.qna.QnACommand;
import dppom.command.qna.QnADeleteCommand;
import dppom.command.qna.QnAEditActionCommand;
import dppom.command.qna.QnAEditCommand;
import dppom.command.qna.QnAPassCommand;
import dppom.command.qna.QnAViewCommand;
import dppom.command.qna.QnAWriteCommand;

//건의사항 컨트롤러 

@Controller
public class QnAController {
	private JdbcTemplate template;
	QnACommand command = null;
	QnAViewCommand vCommand = null;
	QnAPassCommand pCommand = null;
	QnAWriteCommand wCommand = null;
	QnAAdminViewCommand avCommand = null;
	QnAAdminWriteCommand awCommand = null;
	QnADeleteCommand dCommand = null;
	QnAEditCommand eCommand = null;
	QnAEditActionCommand eaCommand = null;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	@RequestMapping("/QnALogin")
	public String QnALogin() {
		return "member/Login";
	}

	// 건의사항
	@RequestMapping("/QnA")
	public String QnA(Model model, HttpServletRequest req, HttpSession session) {
		System.out.println("QnA()호출");
		model.addAttribute("req", req);
		model.addAttribute("session", session);
		model.addAttribute("searchColumn", req.getParameter("searchColumn"));
		model.addAttribute("searchWord", req.getParameter("searchWord"));
		command = new QnACommand();
		command.execute(model);

		return "QnA/QnA";
	}

	@RequestMapping(value = "/QnASearch", method = RequestMethod.GET)
	public String QnASearch(Model model, HttpServletRequest req, HttpSession session) {
		System.out.println("QnASearch()호출");
		model.addAttribute("req", req);
		model.addAttribute("searchColumn", req.getParameter("searchColumn"));
		model.addAttribute("searchWord", req.getParameter("searchWord"));
		command = new QnACommand();
		command.execute(model);

		return "QnA/QnA";
	}

	@RequestMapping("/QnAPass")
	public String QnAPass(HttpServletRequest req, Model model, HttpSession session) {
		System.out.println("QnAPass()호출");
		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("reply", req.getParameter("reply"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "QnA/QnAPass";
	}

	@RequestMapping("/QnAPassAction")
	public String QnAPassAction(HttpServletRequest req, Model model) {
		System.out.println("QnAPassAction()호출");

		String resultPage = null;

		pCommand = new QnAPassCommand();

		int password = pCommand.execute(req.getParameter("idx"), req.getParameter("pass"));

		if (password <= 0) {
			System.out.println("패스워드가 일치하지 않아요ㅠ");

			// 뷰로 전송할 데이터 저장
			model.addAttribute("nope", "패스워드가 일치하지 않습니다!");
			model.addAttribute("idx", req.getParameter("idx"));
			model.addAttribute("reply", req.getParameter("reply"));
			model.addAttribute("nowPage", req.getParameter("nowPage"));
			resultPage = "QnA/QnAPass";
		} else {
			model.addAttribute("req", req);
			vCommand = new QnAViewCommand();
			vCommand.execute(model);
			model.addAttribute("idx", req.getParameter("idx"));
			model.addAttribute("reply", req.getParameter("reply"));
			model.addAttribute("nowPage", req.getParameter("nowPage"));
			resultPage = "redirect:QnAView";
		}
		return resultPage;
	}

	@RequestMapping("/QnAView")
	public String QnAView(HttpServletRequest req, Model model) {
		model.addAttribute("req", req);
		vCommand = new QnAViewCommand();
		vCommand.execute(model);
		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("reply", req.getParameter("reply"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "QnA/QnAView";
	}

	@RequestMapping("/QnAWrite")
	public String QnAWrite(HttpServletRequest req, Model model) {
		System.out.println("QnAWrite()호출");

		return "QnA/QnAWrite";
	}

	@RequestMapping("/QnAWriteAction")
	public String QnAWriteAction(HttpServletRequest req, Model model) {
		System.out.println("QnAWriteAction()호출");

		model.addAttribute("req", req);
		System.out.println(req.getParameter("id"));
		wCommand = new QnAWriteCommand();
		wCommand.execute(model);

		return "redirect:QnA";
	}

	@RequestMapping(value = "/QnAAdminWrite", method = RequestMethod.POST)
	public String QnAAdminWrite(HttpServletRequest req, Model model) {
		System.out.println("QnAAdminWrite()호출");
		model.addAttribute("req", req);

		avCommand = new QnAAdminViewCommand();
		avCommand.execute(model);
		return "QnA/QnAAdminWrite";
	}

	@RequestMapping(value = "/QnAAdminWriteAction", method = RequestMethod.POST)
	public String QnAAdminWriteAction(HttpServletRequest req, Model model) {
		System.out.println("QnAAdminWriteAction()호출");

		model.addAttribute("req", req);

		awCommand = new QnAAdminWriteCommand();
		awCommand.execute(model);
		model.addAttribute("reply", "O");

		return "redirect:QnAView";
	}

	@RequestMapping(value = "/QnADelete", method = RequestMethod.POST)
	public String QnADelete(HttpServletRequest req, Model model) {
		System.out.println("QnADelete()호출");
		model.addAttribute("req", req);

		dCommand = new QnADeleteCommand();
		dCommand.execute(model);

		return "redirect:QnA";
	}

	@RequestMapping("/QnAEdit")
	public String QnAEdit(HttpServletRequest req, Model model) {
		System.out.println("QnAEdit()호출");
		model.addAttribute("req", req);

		eCommand = new QnAEditCommand();
		eCommand.execute(model);

		return "QnA/QnAEdit";
	}

	@RequestMapping("/QnAEditAction")
	public String QnAEditAction(HttpServletRequest req, Model model) {
		System.out.println("QnAEditAction()호출");
		model.addAttribute("req", req);

		eaCommand = new QnAEditActionCommand();
		eaCommand.execute(model);

		return "redirect:QnAView";
	}

	@RequestMapping("/QnAAdminEdit")
	public String QnAAdminEditAction(HttpServletRequest req, Model model) {
		System.out.println("QnAAdminEdit()호출");
		model.addAttribute("req", req);

		avCommand = new QnAAdminViewCommand();
		avCommand.execute(model);
		return "QnA/QnAAdminEdit";
	}
}