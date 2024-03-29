package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.BoardVO;
import com.jsp.request.SearchCriteria;

public interface BoardDAO {

	List<BoardVO> selectBoardCriteria(SearchCriteria cri) throws SQLException;

	int selectBoardCriteriaTotalCount(SearchCriteria cri) throws SQLException;

	BoardVO selectBoardByBno(int bno) throws SQLException;

	void insertBoard(BoardVO board) throws SQLException;

	void updateBoard(BoardVO board) throws SQLException;

	void deleteBoard(int bno) throws SQLException;

	// viewcnt(조회수) 증가
	void increaseViewCnt(int bno) throws SQLException;

	// board_seq.nextval 가져오기 -----> 매퍼에 추가안함
	//int selectBoardSeqNext() throws SQLException;

}
