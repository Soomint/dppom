package dppom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dppom.command.member.FindPassActionCommand;
import dppom.command.member.JoinSuccessCommand;
import dppom.command.member.MemberCommand;
import dppom.command.member.findIdCommand;
import dppom.command.member.modifyPassCommand;

// 로그인 , ID , 패스워드 찾기 , 회원가입 

@Controller
public class MembrController {

	MemberCommand command = null;

	// 로그인 페이지
	@RequestMapping("Login")
	public String login(Model model, HttpServletRequest req) {
		System.out.println("login()");

		String PageURL = req.getParameter("PageURL"); // 쿼리스트링으로 전달받음
		System.out.println("멤버컨트롤러 - 로그인" + PageURL);
		model.addAttribute("PageURL", PageURL); // 로그인 뷰로 전달

		String PageURLreq = (String) req.getAttribute("PageURLreq"); // 로그인 실패시
																		// 공유하는
																		// req영역에서
																		// get
																		// 해옴
		System.out.println("멤버컨트롤러 - 재로그인" + PageURLreq);
		model.addAttribute("PageURLreq", PageURLreq); // 로그인 뷰로 전달

		return "/member/Login";

	}

	// 아이디 찾기
	@RequestMapping("FindId")
	public String FindId(Model model, HttpServletRequest req) {
		System.out.println("FindId 접속");

		return "member/FindId";

	}

	// 아이디 찾기 처리
	@RequestMapping("FindIdAction")
	public String FindIdAction(Model model, HttpServletRequest req) {
		System.out.println("FindIdAction매핑 컨트롤러");

		model.addAttribute("req", req);// id , email
		command = new findIdCommand();
		command.execute(model);

		// 아이디 찾기 결과 화면
		return "member/FindIdAction";
	}

	// 패스워드 찾기
	@RequestMapping("FindPass")
	public String FindPass(Model model, HttpServletRequest req) {
		System.out.println("FindPass 컨트롤러");

		return "member/FindPass";

	}

	// 패스워드 찾기 처리
	@RequestMapping("FindPassAction")
	public String FindPassAction(Model model, HttpServletRequest req) {
		System.out.println("FindPassAction 컨트롤러");

		FindPassActionCommand command1 = new FindPassActionCommand(); // implements
																		// X ver

		model.addAttribute("req", req);
		command1.execute(model);

		return "member/FindPassAction";

	}

	// 패스워드 수정 modifyPass
	@RequestMapping("modifyPass")
	public String modifyPass(Model model, HttpServletRequest req) {
		System.out.println("modifyPass 컨트롤러");

		command = new modifyPassCommand();

		model.addAttribute("req", req); // id(hidden) 포함

		command.execute(model);

		return "member/modifyPass";

	}

	/* 회원가입 */
	// 회원가입페이지
	@RequestMapping("Join")
	public String join(Model model, HttpServletRequest req) {
		System.out.println("회원가입");

		return "member/Join";

	}

	// ID 중복 검사
	@RequestMapping("isIdchk")
	public String isIdchk(Model model, HttpServletRequest req) {
		System.out.println("isIdchk아이디 중복 검사");

		return "member/IdChk";

	}

	// 회원가입 처리
	@RequestMapping("JoinSuccess")
	public String JoinSuccess(Model model, HttpServletRequest req) {
		System.out.println("JoinSuccess 결과 ");

		command = new JoinSuccessCommand();

		model.addAttribute("req", req);
		command.execute(model);

		// return "redirect:Login"; // 커맨드 execute하고 돌아와서 Login 컨트롤러 호출 -
		// Login에서 모델에 add한 것만 뷰에 뜰수있음
		return "member/Login"; // 커맨드에서 모델에 add한 JoingMsg 뷰에 뜸

	}

}
