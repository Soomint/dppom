package dppom.command.feedback;

import org.springframework.ui.Model;

public interface FeedbackCommand {

	//추상메소드
	void execute(Model model);
	
}
