package dppom.model.qna;

import java.util.List;
import java.util.Map;

public interface IQnADAO 
{
	public List<QnADTO> selectAll(Map<String, Object> map);
	
	public QnADTO selectOne(String idx);
	
	public int password(String idx, String pass);
	
	public void write(String id, String pass, String title, String content);
	
	public void adminWrite(String idx, String reply);
	
	public void delete(String idx);
	
	public void edit(String idx, String pass, String title, String content);
	
	public int getTotalCount(Map<String, Object> map);
}
