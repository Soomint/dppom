package dppom.command.board;

import org.springframework.ui.Model;

public interface BoardCommand {

	//추상메소드 - 오버라이딩의 목적으로 생성된다.
	void execute(Model model);
}
