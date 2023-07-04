package dppom.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import dppom.command.board.MyWritngCommand;
import dppom.command.board.RViewCommand;
import dppom.command.member.InfoConfirmCommand;
import dppom.command.member.InfoConfirmDeleteCommand;
import dppom.command.member.MemberCommand;
import dppom.command.member.OutActionCommand;
import dppom.command.member.TotalpointCommand;
import dppom.command.member.memberInfoCommand;
import dppom.command.member.memberModifyActionCommand;
import dppom.command.member.myPageCommand;
import dppom.model.member.MemberDAO;

// myPoint , myModify , myOut 관련 ( 마이페이지) 

@Controller
public class MypageController {
	MemberCommand command = null;

	/******* 포인트 ********/
	// 포인트 조회 (마이페이지 디폴트)
	@RequestMapping("myHome")
	public String myHome(Model model, HttpServletRequest req) // * Model : 뷰로
																// 데이터를 전송할때 사용
																// *
																// HttpServletRequest
																// : 폼 데이터를
																// 전송받을때 사용
	{
		System.out.println("마이 페이지 접속(내 포인트조회 디폴트)");

		model.addAttribute("req", req); // 컨트롤러가 받은 파라미터 전체를 ListCommand로 넘긴다.

		// 포인트 총합 커맨드
		command = new TotalpointCommand(); // 모델에 쿼리스트링 있는거 다 넣어서 보냄 id,
											// nowPage,
		command.execute(model);

		return "mypage/myHome";
	}

	// 적립 및 차감 포인트 조회
	@RequestMapping("mypointPlus")
	public String mypointPlus(Model model, HttpServletRequest req) {
		System.out.println("mypointPlus 적립");

		model.addAttribute("req", req); // post받은거 + 쿼리

		String id = req.getParameter("id"); // 쿼리스트링으로 받은것
		System.out.println("세션 아이디: " + id);

		// 포인트 총합 커맨드
		command = new TotalpointCommand();
		command.execute(model);

		return "mypage/mypoint/mypointPlus";
	}

	/***** 회원 정보 수정 및 탈퇴 ******/
	// 회원 정보 수정
	@RequestMapping("myModify")
	public String myModify(HttpServletRequest req, Model model) {
		System.out.println("회원수정");

		String id = req.getParameter("id"); // 쿼리스트링으로 받은것
		System.out.println("세션 아이디:" + id);

		model.addAttribute("req", req); // 다 담아랏 -

		command = new memberInfoCommand();
		command.execute(model);

		return "mypage/mymodify/myModify";
	}

	// 수정 처리
	@RequestMapping("myModifyAction")
	public String myModifyAction(HttpServletRequest req, Model model) {
		System.out.println("myModifyAction()메소드 호출");

		model.addAttribute("req", req);
		command = new memberModifyActionCommand();
		command.execute(model);

		// 수정처리후 상세보기 페이지로 이동
		model.addAttribute("idx", req.getParameter("idx")); // hidden으로 받아온거

		return "mypage/mymodify/myModifyAction";
	}

	// 회원 탈퇴
	@RequestMapping("myOut")
	public String memberAdmin(HttpServletRequest req, Model model) {
		System.out.println("회원탈퇴1");

		return "mypage/mymodify/myOut";
	}

	@RequestMapping("myOut2")
	public String memberAdmin2(HttpServletRequest req, Model model) {
		model.addAttribute("req", req);
		model.addAttribute("id", req.getParameter("id"));
		System.out.println("회원탈퇴 비밀번호 입력");

		return "mypage/mymodify/myOut2";
	}

	@RequestMapping("outPassAction")
	public String QnAPassAction(HttpServletRequest req, HttpServletResponse response, Model model) throws IOException {
		System.out.println("outPassAction()호출");

		String resultPage = null;

		MemberDAO dao = new MemberDAO();
		String isUser = dao.password(req.getParameter("id"), req.getParameter("pass")); // -
																						// 면
																						// 실패니까
																						// alert뜨고
																						// 그화면
																						// 그대로
																						// 성공이면
																						// out3으로

		System.out.println("isUser" + isUser);

		if (isUser == "") {
			// 패스워드 불일치
			System.out.println("비밀번호 불일치");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디 또는 패스워드를 다시한번 확인해주세요');</script>");
			out.flush();

			return "/mypage/mymodify/myOut2";
		} else {
			System.out.println("비밀번호 일치");

			// 탈퇴 처리
			MemberDAO memdao = new MemberDAO();
			System.out.println(req.getParameter("id"));
			memdao.myOut(req.getParameter("id"));
			memdao.close();
			return "redirect:myOut3";
		}

	}

	@RequestMapping("myOut3")
	public String memberAdmin3(Model model) {
		System.out.println("회원탈퇴3 완료");

		return "mypage/mymodify/myOut3";
	}

	// 탈퇴 피드백 처리 OutAction
	@RequestMapping("OutAction")
	public String OutAction(HttpServletRequest req, Model model, HttpSession session) {
		System.out.println("OutAction");
		model.addAttribute("req", req);
		command = new OutActionCommand();
		command.execute(model);

		System.out.println("메인 이동");
		// 세션영역을 비워준다.
		session.setAttribute("login_id", null);
		return "redirect:Main";
	}

	// 마이페이지 내글보기
	@RequestMapping("myWritingList")
	public String reportAdmin(Model model, HttpServletRequest req) {
		System.out.println("myWritingList()호출");

		// 컨트롤러가 받은 파라미터 전체를 MajCommand로 넘긴다.
		model.addAttribute("req", req);

		command = new MyWritngCommand();
		command.execute(model);

		return "mypage/mywritinglist/myWritingList";
	}

	@RequestMapping("myWritingView")
	public String myWritingView(HttpServletRequest req, Model model) {
		System.out.println("myWritingView()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("checking", req.getParameter("checking"));

		RViewCommand viewcommand = new RViewCommand();
		viewcommand.execute(model);

		if (req.getParameter("category").equals("전공")) {
			return "Board/Review/ReviewView";
		} else if (req.getParameter("category").equals("교양")) {
			return "Board/Review/ReviewView";
		} else if (req.getParameter("category").equals("솔루션")) {
			return "Board/Dataroom/DataView";
		} else if (req.getParameter("category").equals("레포트")) {
			return "Board/Dataroom/DataView";
		} else if (req.getParameter("category").equals("기타")) {
			return "Board/Dataroom/DataView";
		} else if (req.getParameter("category").equals("레포트")) {
			return "Board/Dataroom/DataView";
		} else {
			return "Board/Party/PartyView";
		}
	}

	@RequestMapping("InfoConfirm")
	public String InfoConfirm(HttpServletRequest req, Model model) {
		System.out.println("InfoConfirm 접속");

		String id = req.getParameter("id");
		model.addAttribute("req", req);
		model.addAttribute("id", id);

		MemberCommand mcommand = new myPageCommand();

		mcommand.execute(model);

		return "mypage/mymodify/InfoConfirm";
	}

	@RequestMapping("InfoConfirmAction")
	public String InfoConfirmAction(Model model, HttpServletRequest req) {
		System.out.println("InfoConfirmAction 접속");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		String realpath = "";
		String saveFileName = "";

		//////////////////////////// 파일업로드 코드////////////////////////
		// 파일이 저장될 path 설정
		String path = "C:\\02WorkSpace\\Dppom\\src\\main\\webapp\\resources\\certi";

		try {

			// MultipartHttpServletRequest 생성
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
			Iterator iter = mhsr.getFileNames();
			MultipartFile mfile = null;
			String fieldName = "";

			// 디렉토리가 없다면 생성
			File dir = new File(path);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}

			// 값이 나올때까지
			while (iter.hasNext()) {

				fieldName = (String) iter.next();

				// 내용을 가져와서
				mfile = mhsr.getFile(fieldName);
				String origName;
				origName = new String(mfile.getOriginalFilename().getBytes(), "UTF-8");// 한글깨짐
				// 방지
				System.out.println("업로드 요청 파일 이름 : " + origName);

				String finalFileName = req.getParameter("id");

				// 확장자
				String ext = origName.substring(origName.lastIndexOf('.'));

				// 기존파일 불러오기 및 삭제
				InfoConfirmDeleteCommand idcommand = new InfoConfirmDeleteCommand();
				System.out.println(idcommand.execute(model));
				File prevfile = new File(
						"c:/02WorkSpace/Dppom/src/main/webapp/resources/certi/" + idcommand.execute(model));
				if (prevfile.exists())
					prevfile.delete();

				// 파일명이 없다면
				int num = 0;
				if ("".equals(finalFileName + ext)) {
					continue;
				}

				finalFileName = new String(finalFileName.getBytes(), "UTF-8");

				// 파일명 변경
				saveFileName = finalFileName + ext;

				// 설정한 path에 파일저장
				File serverFile = new File(path + File.separator + saveFileName);
				mfile.transferTo(serverFile);
				realpath = serverFile.getAbsolutePath();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		///////////////////////////// 파일업로드 끝/////////////////////////

		InfoConfirmCommand command = new InfoConfirmCommand();
		command.execute(model, saveFileName);
		model.addAttribute("id", req.getParameter("id"));
		return "redirect:InfoConfirm";
	}
}