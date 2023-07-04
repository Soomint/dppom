package dppom.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kosmo.Dppom.Constant;

import dppom.command.DataRoom.DataDeleteCommand;
import dppom.command.DataRoom.DataListCommand;
import dppom.command.DataRoom.ModifyDataActionCommand;
import dppom.command.DataRoom.ModifyDataCommand;
import dppom.command.DataRoom.SearchDPActionCommand;
import dppom.command.party.ModifyPartyActionCommand;
import dppom.command.party.ModifyPartyCommand;
import dppom.command.party.PartyListCommand;
import dppom.command.party.SearchPTActionCommand;
import dppom.command.party.WritingPartyActionCommand;
import dppom.command.board.BoardCommand;
import dppom.command.board.DeleteActionCommand;
import dppom.command.board.LibCommand;
import dppom.command.board.MajCommand;
import dppom.command.board.ModifyActionCommand;
import dppom.command.board.ModifyCommand;
import dppom.command.board.RViewCommand;
import dppom.command.board.WriteActionCommand;
import dppom.command.board.likeupCommand;
import dppom.command.board.minusCommand;
import dppom.command.board.searchActionCommand;
import dppom.command.member.CheckMemberPointCommand;
import dppom.command.reply.ReplyCommand;
import dppom.model.session.SessionDTO;

@Controller
public class BoardController {

	private JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	BoardCommand command = null;

	// =======Review 컨트롤러
	// 전공후기 리스트
	@RequestMapping("Review")
	public String Review(HttpServletRequest req, Model model) {
		System.out.println("MajReview()호출");
		String category = "전공";
		String board_type = "review";
		String filename = "Review";

		// 컨트롤러가 받은 파라미터 전체를 MajCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("category", category);
		model.addAttribute("board_type", board_type);
		model.addAttribute("filename", filename);

		command = new MajCommand();
		command.execute(model);

		return "Board/Review/MajReview";

	}

	// 교양후기 리스트
	@RequestMapping("LibReview")
	public String LibReview(HttpServletRequest req, Model model) {
		System.out.println("LibReview()호출");
		String category = "교양";
		String board_type = "review";
		String filename = "LibReview";

		// 컨트롤러가 받은 파라미터 전체를 LibCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("category", category);
		model.addAttribute("board_type", board_type);
		model.addAttribute("filename", filename);

		command = new LibCommand();
		command.execute(model);

		return "Board/Review/LibReview";

	}

	// 글 상세보기
	@RequestMapping("ReviewView")
	public String ReviewView(HttpServletRequest req, Model model) {
		System.out.println("ReviewView()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		command = new RViewCommand();
		command.execute(model);

		model.addAttribute("board_type", req.getParameter("filename").toLowerCase());
		model.addAttribute("board_idx", req.getParameter("idx"));
		ReplyCommand rCommand = new ReplyCommand();
		rCommand.execute(model);

		return "Board/Review/ReviewView";
	}

	// 후기 글쓰기
	@RequestMapping("ReviewWrite")
	public String ReviewWrite(HttpServletRequest req, Model model) {
		System.out.println("ReviewWrite()호출");

		return "Board/Review/ReviewWrite";

	}

	// 후기글 쓰기 액션
	@RequestMapping("writeAction")
	public String writeAction(HttpServletRequest req, Model model) {
		System.out.println("writeAction()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		WriteActionCommand command = new WriteActionCommand();
		command.execute(model);

		// 뷰 연결이 아닌 페이지이동을 할때 redirect를 사용한다.
		System.out.println(req.getParameter("category"));
		if (req.getParameter("category").equals("교양")) {
			return "redirect:LibReview";
		} else {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "redirect:Review";
		}
	}

	// 후기글 수정
	@RequestMapping("ModifyReview")
	public String ModifyReview(HttpServletRequest req, Model model) {
		System.out.println("ModifyReview()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("idx", Integer.parseInt(req.getParameter("idx")));
		System.out.println(req.getParameter("idx"));
		command = new ModifyCommand();
		command.execute(model);

		return "Board/Review/ModifyReview";

	}

	// 후기수정 액션
	@RequestMapping("modifyAction")
	public String modifyAction(HttpServletRequest req, Model model) {
		System.out.println("modifyAction()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("univ", req.getParameter("univ"));
		String filename = req.getParameter("filename");
		System.out.println(req.getParameter("univ"));
		command = new ModifyActionCommand();
		command.execute(model);

		// 뷰 연결이 아닌 페이지이동을 할때 redirect를 사용한다.
		System.out.println(req.getParameter("category"));
		int idx = Integer.parseInt(req.getParameter("idx"));

		if (req.getParameter("category").equals("교양")) {
			return "redirect:LibReview?idx=" + idx;
		} else {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "redirect:ReviewView?idx=" + idx + "&filename=" + filename;
		}
	}

	// 후기글 삭제
	@RequestMapping(value = "deleteView")
	public String deleteView(HttpServletRequest req, Model model) {
		System.out.println("deleteView()호출");

		String filename = req.getParameter("filename"); // 돌아갈 리스트 파일명
		String idx = req.getParameter("idx"); // 게시글 번호

		// 패스워드 검증후 이동할 페이지
		String modePage = null;

		System.out.println("삭제하였습니다.");

		// 게시물 즉시 삭제후 리스트 페이지로 이동
		model.addAttribute("req", req);
		command = new DeleteActionCommand();
		command.execute(model);

		modePage = "redirect:" + filename;

		return modePage;
	}

	// 후기 검색
	@RequestMapping("searchAction")
	public String searchAction(HttpServletRequest req, Model model) {
		System.out.println("searchAction()호출");
		System.out.println(req.getParameter("category"));

		System.out.println("매핑에서 컬럼" + req.getParameter("sel1"));
		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("sel1", req.getParameter("sel1"));
		model.addAttribute("search", req.getParameter("search"));
		model.addAttribute("category", req.getParameter("category"));
		model.addAttribute("filename", req.getParameter("filename"));
		model.addAttribute("board_type", req.getParameter("board_type"));
		command = new searchActionCommand();
		command.execute(model);

		// 뷰 연결이 아닌 페이지이동을 할때 redirect를 사용한다.
		System.out.println(req.getParameter("category"));
		if (req.getParameter("category").equals("교양")) {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "Board/Review/LibReview";
		} else {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "Board/Review/MajReview";
		}

	}

	// ========DataRoom 컨트롤러
	// 솔루션
	@RequestMapping("DataList")
	public String DataList(HttpServletRequest req, Model model) {
		System.out.println("DataList()호출");
		String filename = "DataList";
		String board_type = "dataroom";
		String category = "솔루션";

		// 컨트롤러가 받은 파라미터 전체를 MajCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("category", category);
		model.addAttribute("filename", filename);
		model.addAttribute("board_type", board_type);
		command = new DataListCommand();
		command.execute(model);

		return "Board/Dataroom/DataList";

	}

	// 레포트
	@RequestMapping("report")
	public String report(HttpServletRequest req, Model model) {
		System.out.println("report()호출");
		String filename = "report";
		String board_type = "dataroom";
		String category = "레포트";

		// 컨트롤러가 받은 파라미터 전체를 MajCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("category", category);
		model.addAttribute("filename", filename);
		model.addAttribute("board_type", board_type);
		command = new DataListCommand();
		command.execute(model);

		return "Board/Dataroom/ReportList";

	}

	// 기타
	@RequestMapping("Detc")
	public String Detc(HttpServletRequest req, Model model) {
		System.out.println("Detc()호출");
		String filename = "Detc";
		String board_type = "dataroom";
		String category = "기타";

		// 컨트롤러가 받은 파라미터 전체를 MajCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("category", category);
		model.addAttribute("filename", filename);
		model.addAttribute("board_type", board_type);
		command = new DataListCommand();
		command.execute(model);

		return "Board/Dataroom/DetcList";

	}

	// 데이터 상세보기
	@RequestMapping("DataView")
	public String DataView(HttpServletRequest req, Model model) {
		System.out.println("DataView()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		command = new RViewCommand();
		command.execute(model);

		HttpSession session = req.getSession();
		SessionDTO dto = (SessionDTO) session.getAttribute("login_id");
		if (dto != null) {
			CheckMemberPointCommand cCommand = new CheckMemberPointCommand();
			model.addAttribute("point", cCommand.point(dto.getId()));
			System.out.println(cCommand.point(dto.getId()));
		}

		model.addAttribute("board_type", req.getParameter("filename").toLowerCase());
		model.addAttribute("board_idx", req.getParameter("idx"));
		ReplyCommand rCommand = new ReplyCommand();
		rCommand.execute(model);

		return "Board/Dataroom/DataView";
	}

	// 데이터 검색
	@RequestMapping("searchDataAction")
	public String searchDataAction(HttpServletRequest req, Model model) {
		System.out.println("searchDataAction()호출");
		System.out.println(req.getParameter("board_type"));

		System.out.println("매핑에서 컬럼" + req.getParameter("sel1"));
		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("sel1", req.getParameter("sel1"));
		model.addAttribute("search", req.getParameter("search"));
		model.addAttribute("filename", req.getParameter("filename"));
		model.addAttribute("board_type", req.getParameter("board_type"));
		model.addAttribute("category", req.getParameter("category"));
		command = new SearchDPActionCommand();
		command.execute(model);

		// 뷰 연결이 아닌 페이지이동을 할때 redirect를 사용한다.
		System.out.println("나가기전: " + req.getParameter("board_type"));

		System.out.println(req.getParameter("category"));
		if (req.getParameter("category").equals("솔루션")) {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "Board/Dataroom/DataList";
		} else if (req.getParameter("category").equals("레포트")) {
			return "Board/Dataroom/ReportList";
		} else {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "Board/Dataroom/DetcList";
		}
	}

	// 데이터 쓰기
	@RequestMapping("DataWrite")
	public String DataWrite(HttpServletRequest req, Model model) {
		System.out.println("DataWrite()호출");

		return "Board/Dataroom/DataWrite";
	}

	// 데이터글 쓰기 액션
	@RequestMapping("writeDataAction")
	public String writeDataAction(HttpServletRequest req, Model model) {
		System.out.println("writeDataAction()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		String realpath = "";
		String saveFileName = "";

		//////////////////////////// 파일업로드 코드////////////////////////
		// 파일이 저장될 path 설정
		String path = "C:\\02WorkSpace\\Dppom\\src\\main\\webapp\\resources\\upload";

		try {
			File file = new File("c:/02WorkSpace/Dppom/src/main/webapp/resources/upload");

			// 위의 경로안에 있는 모든 파일리스트들을 배열로 받아온다.
			File[] Files = file.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					return true;
				}
			});

			// 똑같은 이름의 파일이 저장되는 것(덮어쓰기)를 방지하기 위해 파일명이 중복될 경우 (숫자)처리하기위한 과정
			// fileName에는 파일이름의 길이가 짧은 순으로 정렬된다.
			String[] fileName = new String[Files.length];
			int a = 0;
			for (File f : Files) {
				fileName[a] = f.getName().substring(0, f.getName().lastIndexOf('.'));
				a++;
			}
			for (int x = 0; x < fileName.length; x++) {
				int xLength = fileName[x].length();
				for (int y = 0; y < x; y++) {
					int yLength = fileName[y].length();
					if (xLength < yLength) {
						String temp = fileName[x];
						fileName[x] = fileName[y];
						fileName[y] = temp;
					}
				}
			}

			// 파일업로드를 위한 MultipartHttpServletRequest 생성
			// multipart/form-data는 서버에서 MultipartHttpServletRequest로 받아야함
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
			Iterator iter = mhsr.getFileNames();
			MultipartFile mfile = null;
			String fieldName = "";

			// 파일을 저장할 디렉토리가 없다면 생성
			File dir = new File(path);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}

			// 저장할 파일의 값이 나올때까지 반복문 돌린다.
			while (iter.hasNext()) {

				fieldName = (String) iter.next();

				// 저장할 파일의 내용을 가져온다.
				mfile = mhsr.getFile(fieldName);
				String origName;
				origName = new String(mfile.getOriginalFilename().getBytes(), "UTF-8"); // 한글깨짐
																						// 방지

				System.out.println("파일 이름 : " + origName);
				// 받아온 파일명(확장자 제외)
				String origfileName = origName.substring(0, origName.lastIndexOf('.'));
				String finalFileName = origfileName;

				// 파일명이 없다면 ok
				int num = 0;
				if ("".equals(origName)) {
					continue;
					// 파일명이 있다면 덮어쓰기를 방지하기 위해 파일명 뒤에 (숫자)처리
				} else {
					for (int j = 0; j < fileName.length; j++) {
						if (fileName[j].equals(finalFileName)) {
							num++;
							String plusname = "(" + num + ")";
							finalFileName = origfileName + plusname;
						}
					}
				}
				finalFileName = new String(finalFileName.getBytes(), "UTF-8");

				// 파일의 확장자
				String ext = origName.substring(origName.lastIndexOf('.'));

				// 최종 파일명 설정
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

		// 글쓰기 커맨드
		WriteActionCommand command = new WriteActionCommand();
		command.execute(model, saveFileName);

		// 뷰 연결이 아닌 페이지이동을 할때 redirect를 사용한다.
		System.out.println(req.getParameter("board_type"));

		if (req.getParameter("category").equals("솔루션")) {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "redirect:DataList";
		} else if (req.getParameter("category").equals("레포트")) {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "redirect:report";
		} else {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "redirect:Detc";
		}
	}

	// 데이터글 수정
	@RequestMapping("ModifyData")
	public String ModifyData(HttpServletRequest req, Model model) {
		System.out.println("ModifyData()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("idx", Integer.parseInt(req.getParameter("idx")));
		System.out.println(req.getParameter("idx"));
		command = new ModifyDataCommand();
		command.execute(model);

		return "Board/Dataroom/ModifyData";

	}

	// 데이터수정 액션
	@RequestMapping("modifyDataAction")
	public String modifyDataAction(HttpServletRequest req, Model model) {
		System.out.println("modifyDataAction()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);

		String realpath = "";
		String saveFileName = "";

		//////////////////////////// 파일업로드 코드////////////////////////
		// 파일이 저장될 path 설정
		String path = "C:\\02WorkSpace\\Dppom\\src\\main\\webapp\\resources\\upload";

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
				origName = new String(mfile.getOriginalFilename().getBytes(), "UTF-8");// 한글깨짐방지

				System.out.println("파일 이름 : " + origName);
				// 받아온 파일명
				String origfileName = origName.substring(0, origName.lastIndexOf('.'));
				String finalFileName = origfileName;

				// 확장자
				String ext = origName.substring(origName.lastIndexOf('.'));

				// 기존파일 불러오기 및 삭제
				DataDeleteCommand ddcommand = new DataDeleteCommand();
				System.out.println(ddcommand.execute(model));
				File prevfile = new File(
						"c:/02WorkSpace/Dppom/src/main/webapp/resources/upload/" + ddcommand.execute(model));
				if (prevfile.exists())
					prevfile.delete();

				File file = new File("c:/02WorkSpace/Dppom/src/main/webapp/resources/upload");

				File[] Files = file.listFiles(new FilenameFilter() {

					@Override
					public boolean accept(File dir, String name) {
						return true;
					}
				});

				String[] fileName = new String[Files.length];
				int a = 0;
				for (File f : Files) {
					fileName[a] = f.getName().substring(0, f.getName().lastIndexOf('.'));
					a++;
				}
				for (int x = 0; x < fileName.length; x++) {
					int xLength = fileName[x].length();
					for (int y = 0; y < x; y++) {
						int yLength = fileName[y].length();
						if (xLength < yLength) {
							String temp = fileName[x];
							fileName[x] = fileName[y];
							fileName[y] = temp;
						}
					}
				}

				// 파일명이 없다면
				int num = 0;
				if ("".equals(origName)) {
					continue;
				} else {
					for (int j = 0; j < fileName.length; j++) {
						if (fileName[j].equals(finalFileName)) {
							num++;
							String plusname = "(" + num + ")";
							finalFileName = origfileName + plusname;
						}
					}
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

		model.addAttribute("univ", req.getParameter("univ"));
		System.out.println(req.getParameter("univ"));
		ModifyDataActionCommand command = new ModifyDataActionCommand();
		command.execute(model, saveFileName);

		int nowPage = Integer.parseInt(req.getParameter("nowPage"));
		String filename = req.getParameter("filename");
		String category = req.getParameter("category");

		// 뷰 연결이 아닌 페이지이동을 할때 redirect를 사용한다.
		System.out.println(req.getParameter("category"));
		int idx = Integer.parseInt(req.getParameter("idx"));

		return "redirect:DataView?idx=" + idx + "&nowPage=" + nowPage + "&filename=" + filename;

	}

	// 데이터글 삭제
	@RequestMapping(value = "deleteData")
	public String deleteData(HttpServletRequest req, Model model) {
		System.out.println("deleteData()호출");

		model.addAttribute("req", req);
		String idx = req.getParameter("idx"); // 게시글 번호
		int nowPage = Integer.parseInt(req.getParameter("nowPage"));

		// 패스워드 검증후 이동할 페이지
		String modePage = null;

		// 기존파일 불러오기 및 삭제
		DataDeleteCommand ddcommand = new DataDeleteCommand();
		File prevfile = new File("c:/02WorkSpace/Dppom/src/main/webapp/resources/upload/" + ddcommand.execute(model));
		if (prevfile.exists())
			prevfile.delete();

		System.out.println("삭제하였습니다.");

		// 게시물 즉시 삭제후 리스트 페이지로 이동
		command = new DeleteActionCommand();
		command.execute(model);

		modePage = "redirect:DataList?nowPage=" + nowPage;

		return modePage;
	}

	// ========Party 컨트롤러
	@RequestMapping("Party")
	public String PartyList(HttpServletRequest req, Model model) {
		System.out.println("PartyList()호출");

		String filename = "Party";
		String board_type = "party";
		String category = req.getParameter("category");

		System.out.println("컨트롤러 카테고리 " + category);

		// 컨트롤러가 받은 파라미터 전체를 MajCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("category", category);
		model.addAttribute("filename", filename);
		model.addAttribute("board_type", board_type);
		command = new PartyListCommand();
		command.execute(model);

		return "Board/Party/PartyList";
	}

	// 파티 상세보기
	@RequestMapping("PartyView")
	public String PartyView(HttpServletRequest req, Model model) {
		System.out.println("PartyView()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		command = new RViewCommand();
		command.execute(model);

		model.addAttribute("board_type", req.getParameter("filename").toLowerCase());
		model.addAttribute("board_idx", req.getParameter("idx"));
		ReplyCommand rCommand = new ReplyCommand();
		rCommand.execute(model);

		return "Board/Party/PartyView";
	}

	// 파티글쓰기
	@RequestMapping("PartyWrite")
	public String PartyWrite(HttpServletRequest req, Model model) {
		System.out.println("PartyWrite()호출");

		return "Board/Party/PartyWrite";
	}

	// 데이터글 쓰기 액션
	@RequestMapping("writePartyAction")
	public String writePartyAction(HttpServletRequest req, Model model) {
		System.out.println("writePartyAction()호출");

		String category = req.getParameter("category");
		System.out.println("컨트롤러 카테고리 " + category);

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("category", category);
		command = new WritingPartyActionCommand();
		command.execute(model);

		// 뷰 연결이 아닌 페이지이동을 할때 redirect를 사용한다.
		System.out.println(req.getParameter("board_type"));

		if (req.getParameter("category").equals("팀프로젝트")) {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "redirect:Party";
		} else if (req.getParameter("category").equals("스터디")) {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "redirect:Party";
		} else {
			System.out.println("나가기전: " + req.getParameter("category"));
			return "redirect:Party";
		}
	}

	// 파티글 수정
	@RequestMapping("ModifyParty")
	public String ModifyParty(HttpServletRequest req, Model model) {
		System.out.println("ModifyParty()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("idx", Integer.parseInt(req.getParameter("idx")));
		System.out.println(req.getParameter("idx"));
		command = new ModifyPartyCommand();
		command.execute(model);

		return "Board/Party/ModifyParty";

	}

	// 파티글수정 액션
	@RequestMapping("modifyPartyAction")
	public String modifyPartyAction(HttpServletRequest req, Model model) {
		System.out.println("modifyPartyAction()호출");

		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("univ", req.getParameter("univ"));
		System.out.println(req.getParameter("univ"));
		model.addAttribute("category", req.getParameter("category"));
		command = new ModifyPartyActionCommand();
		command.execute(model);

		int nowPage = Integer.parseInt(req.getParameter("nowPage"));
		String category = req.getParameter("category");

		// 뷰 연결이 아닌 페이지이동을 할때 redirect를 사용한다.
		System.out.println("수정액션 카테고리: " + req.getParameter("category"));
		model.addAttribute(category);
		int idx = Integer.parseInt(req.getParameter("idx"));

		return "redirect:Party?idx=" + idx + "&nowPage=" + nowPage;

	}

	// 파티글 삭제
	@RequestMapping(value = "deleteParty")
	public String deleteParty(HttpServletRequest req, Model model) {
		System.out.println("deleteParty()호출");

		String idx = req.getParameter("idx"); // 게시글 번호
		int nowPage = Integer.parseInt(req.getParameter("nowPage"));
		String category = req.getParameter("category");

		// 패스워드 검증후 이동할 페이지
		String modePage = null;

		System.out.println("삭제하였습니다.");

		// 게시물 즉시 삭제후 리스트 페이지로 이동
		model.addAttribute("req", req);
		model.addAttribute("category", category);
		command = new DeleteActionCommand();
		command.execute(model);

		modePage = "redirect:Party?nowPage=" + nowPage;

		return modePage;
	}

	// 파티 검색
	@RequestMapping("searchPartyAction")
	public String searchPartyAction(HttpServletRequest req, Model model) {
		System.out.println("searchPartyAction()호출");
		System.out.println(req.getParameter("board_type"));

		System.out.println("매핑에서 컬럼" + req.getParameter("sel1"));
		// 컨트롤러가 받은 파라미터 전체를 RViewCommand로 넘긴다.
		model.addAttribute("req", req);
		model.addAttribute("sel1", req.getParameter("sel1"));
		model.addAttribute("search", req.getParameter("search"));
		model.addAttribute("filename", req.getParameter("filename"));
		model.addAttribute("board_type", req.getParameter("board_type"));
		model.addAttribute("category", req.getParameter("category"));
		command = new SearchPTActionCommand();
		command.execute(model);

		// 뷰 연결이 아닌 페이지이동을 할때 redirect를 사용한다.
		System.out.println("나가기전: " + req.getParameter("board_type"));

		return "Board/Party/PartyList";
	}

	@RequestMapping("/DownloadAction")
	@ResponseBody
	public byte[] fileDownload(HttpServletResponse resp, HttpServletRequest req, Model model) {

		// 다운로드할 파일의 이름과 경로를 가져온다.
		String fileName = req.getParameter("filename");
		String filePath = "C:\\02WorkSpace\\Dppom\\src\\main\\webapp\\resources\\upload\\" + fileName;

		byte[] fileByte = null;
		try {
			System.out.println("파일다운로드 실행");
			// 해당 경로에 있는 파일을 다운로드하는 과정
			fileByte = FileUtils.readFileToByteArray(new File(filePath));
			resp.setContentType("application/octet-stream");
			resp.setContentLength(fileByte.length);
			// 다운로드할 파일명 설정
			resp.setHeader("Content-Disposition",
					"attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			resp.setHeader("Content-Transfer-Encoding", "binary");

			// session영역에 저장된 회원의 아이디를 가져온다.
			model.addAttribute("req", req);
			HttpSession session = req.getSession();
			SessionDTO dto = (SessionDTO) session.getAttribute("login_id");
			String id = dto.getId();
			model.addAttribute("id", id);

			// 다운로드 받은 회원의 포인트 차감
			command = new minusCommand();
			command.execute(model);

			System.out.println("파일다운로드 종료");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileByte;
	}

	// 좋아요 처리 컨트롤
	@RequestMapping("likeup")
	public String likeup(HttpServletRequest req, Model model) {
		System.out.println("likeup()호출");

		String id = req.getParameter("id");
		String idx = req.getParameter("idx");
		String filename = req.getParameter("filename");
		String nowPage = req.getParameter("nowPage");

		model.addAttribute("req", req);
		model.addAttribute("id", id);
		model.addAttribute("idx", idx);
		model.addAttribute("filename", filename);
		model.addAttribute("nowPage", nowPage);

		System.out.println("컨트롤러에서 파일네임 : " + filename);

		command = new likeupCommand();
		command.execute(model);

		if (filename.equals("Review")) {
			return "redirect:ReviewView";
		} else if (filename.equals("LibReview")) {
			return "redirect:ReviewView";
		} else if (filename.equals("DataList")) {
			return "redirect:DataView";
		} else if (filename.equals("report")) {
			return "redirect:DataView";
		} else if (filename.equals("Detc")) {
			return "redirect:DataView";
		} else {
			return "redirect:PartyView";
		}
	}
}