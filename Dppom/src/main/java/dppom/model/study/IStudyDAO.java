package dppom.model.study;

import java.util.List;
import java.util.Map;

public interface IStudyDAO 
{
	public int getTotalCount(Map<String, Object> map);
	
	public List<StudyDTO> selectAll(Map<String, Object> map);
}
