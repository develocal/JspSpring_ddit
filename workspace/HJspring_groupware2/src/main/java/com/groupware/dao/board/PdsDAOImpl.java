package com.groupware.dao.board;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.groupware.dto.PdsVO;
import com.groupware.request.SearchCriteria;

public class PdsDAOImpl implements PdsDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session=session;
	}
	
	@Override
	public List<PdsVO> selectPdsCriteria(SearchCriteria cri) throws SQLException {
		
		int offset=cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
				
		List<PdsVO> pdsList=
		   session.selectList("Pds-Mapper.selectSearchPdsList",cri,rowBounds);	
			
		return pdsList;
	}

	@Override
	public int selectPdsCriteriaTotalCount(SearchCriteria cri) throws SQLException {
		
		List<PdsVO> pdsList= 
				session.selectList("Pds-Mapper.selectSearchPdsList",cri);
		int count=pdsList.size();
		
		return count;
	}

	@Override
	public PdsVO selectPdsByPno(int pno) throws SQLException {
		
		PdsVO pds=session.selectOne("Pds-Mapper.selectPdsByPno",pno);
		
		return pds;
	}

	@Override
	public void insertPds(PdsVO pds) throws SQLException {
		
		session.update("Pds-Mapper.insertPds",pds);
		

	}

	@Override
	public void updatePds(PdsVO pds) throws SQLException {
		
		session.update("Pds-Mapper.updatePds",pds);
		

	}

	@Override
	public void deletePds(int pno) throws SQLException {
		
		session.update("Pds-Mapper.deletePds",pno);
		

	}

	@Override
	public void increaseViewCnt(int pno) throws SQLException {
		
		session.update("Pds-Mapper.increaseViewCnt",pno);
		
		

	}

	@Override
	public int getSeqNextValue() throws SQLException {
		
		int pno=session.selectOne("Pds-Mapper.selectPdsSeqNext");
		
		return pno;
	}

}
