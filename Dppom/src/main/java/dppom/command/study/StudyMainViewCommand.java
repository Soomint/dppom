package dppom.command.study;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import dppom.model.study.StudyDAO;
import dppom.model.study.StudyDTO;

public class StudyMainViewCommand {
	public void execute(Model model) {
		StudyDAO dao = new StudyDAO();
		// 파라미터 전체를 받아오기
		Map<String, Object> map = model.asMap();

		List<StudyDTO> studyData = dao.selectAll(map);

		model.addAttribute("studyData", studyData);

	}
}