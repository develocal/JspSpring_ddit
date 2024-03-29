package com.bms.dao.reply;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.bms.dto.reply.ReplyVO;
import com.bms.request.reply.CommunityReplyRequest;

public class ReplyDaoImpl implements ReplyDao{
	
	@Autowired
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public void mobileReplyWrite(ReplyVO reply) throws SQLException {
		sqlSession.update("Reply-Mapper.mobileReplyWrite",reply);
	}
	@Override
	public ReplyVO mobileReply(String board_code) throws SQLException {
		ReplyVO reply = sqlSession.selectOne("Reply-Mapper.mobileReply",board_code);
		return reply;
	}
	@Override
	public void mobileReplyDelete(String reply_code) throws SQLException {
		sqlSession.delete("Reply-Mapper.mobileReplyDelete", reply_code);
	}
	@Override
	public void mobileReplyModify(ReplyVO reply) throws SQLException {
		sqlSession.update("Reply-Mapper.mobileReplyModify",reply);
	}
	@Override
	public List<ReplyVO> mobileReplyList(String board_code) throws SQLException {
		List<ReplyVO> reply = sqlSession.selectList("Reply-Mapper.mobileReplyList", board_code);
		return reply;
	}
	@Override
	public int mobileReplyCnt(String board_code) throws SQLException {
		int cnt = sqlSession.selectOne("Reply-Mapper.mobileReplyCnt", board_code);
		return cnt;
	}
	@Override
	public List<CommunityReplyRequest> mobileReplyUpCode() throws SQLException {
		List<CommunityReplyRequest> replyUp = sqlSession.selectList("Reply-Mapper.mobileReplyUpCode");
		return replyUp;
	}
	@Override
	public void mobileReplyRegist(ReplyVO reply) throws SQLException {
		sqlSession.update("Reply-Mapper.mobileReplyWrite",reply);
	}
	@Override
	public void mobileReplyResRegist(ReplyVO reply) throws SQLException {
		sqlSession.update("Reply-Mapper.mobileReplyResRegist", reply);
	}
	

}
