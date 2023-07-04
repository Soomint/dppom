package dppom.command.qna;

import dppom.model.qna.QnADAO;

public class QnAPassCommand {
	public int execute(String idx, String pass) {
		QnADAO dao = new QnADAO();

		int password = dao.password(idx, pass);

		return password;
	}
}
