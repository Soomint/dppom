package dppom.model.reply;

import java.util.List;
import java.util.Map;

public interface IReplyDAO {
	public ReplyDTO selectOne(String idx);

	public void replyWrite(final String id, final String content, final String board_type, final int board_idx,
			final int reply_idx, final int reply_step, final int reply_indent);

	public List<ReplyDTO> selectAll(Map<String, Object> map);

	public void write(String id, String content, String board_type, String board_idx, int reply_step, int reply_indent);

	public void delete(String idx);

	public void edit(String idx, String content);

	public int getTotalCount(Map<String, Object> map);

	public void replyPrevUpdate(String board_type, int board_idx, int reply_idx, int reply_step);
}
