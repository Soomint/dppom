package dppom.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dppom.command.board.BestMainViewCommand;
import dppom.command.board.BoardCommand;
import dppom.command.board.NewMainViewCommand;
import dppom.command.board.plusCommand;
import dppom.command.member.CheckMemberLoginCommand;
import dppom.command.study.StudyMainViewCommand;
import dppom.model.session.ISession;
import dppom.model.session.SessionDTO;

@Controller
public class SessionController {

	// 마이바티스 사용함 (sqlSession은 servlet-contex.xml에서 생성된 빈을 자동으로 주입받음)
	@Autowired
	private SqlSession sqlSession;

	// 로그인 처리
	@RequestMapping(value = "LoginProcess", method = RequestMethod.POST)
	public ModelAndView LoginProcess(HttpSession session, HttpServletRequest req, HttpServletResponse response,
			Model model) throws IOException {
		// ModelAndView 는 뷰로 전송할 데이터의 저장과 뷰를 호출하는 2가지를 동시에 처리할 수 있다.

		ModelAndView mav = new ModelAndView();
		// 메인화면
		// mav.setViewName("/MainView"); // 뷰 호출 (실제 경로 조립)= return
		// "login/login" (포워드방식)

		String PageURL = req.getParameter("PageURL"); // 뷰에서 hidden으로 입력 받음

		System.out.println("세션컨트롤러 LoginProcess()진입 " + PageURL);

		String PageURLreq = req.getParameter("PageURLreq"); // hidden
		System.out.println("세션컨트롤러 LoginProcess() 재진입(로그인 실패) " + req.getParameter("PageURLreq"));

		String go = "redirect:" + PageURL;

		if (PageURLreq == "") { // 실패 안했을경우
			if (PageURL == "" || PageURL == null) { // 갑자기 로그인 풀린경우도 포함(null)
				mav.setViewName("/MainView"); // URL 받아온거 없으면 메인으로
			} else {
				mav.setViewName(go); // 요청명 으로 리다이렉트
			}
		} else {
			go = "redirect:" + PageURLreq;
			mav.setViewName(go);
		}

		// DB처리
		ISession ui = sqlSession.getMapper(ISession.class);
		SessionDTO dto = ui.LoginProcess(req.getParameter("id"), req.getParameter("pass"));

		System.out.println(req.getParameter("id") + " " + req.getParameter("pass"));

		// 세션으로 로그인처리
		if (dto != null) {

			// 오늘 첫 로그인인지 아닌자 판단
			model.addAttribute("req", req);
			CheckMemberLoginCommand cmlCommand = new CheckMemberLoginCommand();
			boolean flag = cmlCommand.execute(model);
			if (flag && !(req.getParameter("id").equals("wm") || req.getParameter("id").equals("ms")
					|| req.getParameter("id").equals("jg"))) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('오늘 첫 로그인으로 1포인트가 지급되었습니다!');</script>");

				BoardCommand bcommand = new plusCommand();
				bcommand.execute(model);

				out.flush();
			}

			// 회원 인증에 성공한 경우 세션영역에 dto객체를 저장
			session.setAttribute("login_id", dto);
		} else {

			// 회원 인증에 실패한 경우
			// 로그인 실패 alert창 띄우기 -대신 redirect 호출 할수없음
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디 또는 패스워드를 다시한번 확인해주세요');</script>");
			out.flush();

			req.setAttribute("PageURLreq", PageURL); // 실패시에도 현재페이지 유지 (req영역에
														// 공유)
			// 로그인 화면
			mav.setViewName("/member/Login"); // 포워드 페이지 변환 ( 멤버 컨트롤러 다시 호출 X)
			// mav.setViewName("redirect:Login"); //// 리다이렉트시 Login 컨트롤 다시 호출

		}

		// 게시글 best
		BoardCommand bCommand = new BestMainViewCommand();
		bCommand.execute(model);

		// 최신 사람찾기
		BoardCommand pCommand = new NewMainViewCommand();
		pCommand.execute(model);

		// 동영상 최다 조회수
		StudyMainViewCommand smvCommand = new StudyMainViewCommand();
		smvCommand.execute(model);
		return mav;
	}

	// 로그아웃 화면 - 사용자단, 관리자단 모두
	@RequestMapping("Logout")
	public String Logout(HttpSession session) {

		// 세션영역을 비워준다.
		session.setAttribute("login_id", null);
		return "redirect:Main";
	}

}