package com.kosmo.Dppom;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dppom.command.board.BestMainViewCommand;
import dppom.command.board.BoardCommand;
import dppom.command.board.NewMainViewCommand;
import dppom.command.study.StudyMainViewCommand;

/*
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/*
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		// 게시글 best
		BoardCommand bCommand = new BestMainViewCommand();
		bCommand.execute(model);

		// 최신 사람찾기
		BoardCommand pCommand = new NewMainViewCommand();
		pCommand.execute(model);

		// 동영상 최다 조회수
		StudyMainViewCommand smvCommand = new StudyMainViewCommand();
		smvCommand.execute(model);

		return "MainView";
	}

	@RequestMapping("Main")
	public String mainview(Model model) {

		// 게시글 best
		BoardCommand bCommand = new BestMainViewCommand();
		bCommand.execute(model);

		// 최신 사람찾기
		BoardCommand pCommand = new NewMainViewCommand();
		pCommand.execute(model);

		// 동영상 최다 조회수
		StudyMainViewCommand smvCommand = new StudyMainViewCommand();
		smvCommand.execute(model);

		return "MainView";
	}

	@RequestMapping("API")
	public String API(Model model) {
		return "jsontest/jsonUniv";
	}

	// @RequestMapping("API")
	public String API2(Model model) {

		return "jsontest/jobdicDetail_dom";
	}

}