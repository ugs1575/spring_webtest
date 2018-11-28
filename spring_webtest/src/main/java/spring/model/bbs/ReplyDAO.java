package spring.model.bbs;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	public void setMybatis(SqlSessionTemplate mybatis) {
	this.mybatis = mybatis;
	}
	
	public int rcount(int bbsno){
		  return mybatis.selectOne("reply.rcount", bbsno);
	}

	public boolean create(ReplyDTO dto) {
		boolean flag = false;
		
		int cnt = (Integer) mybatis.insert("reply.create", dto);
		if(cnt>0) flag = true;
		
		return flag;
	}
	public boolean update(ReplyDTO dto) {
		boolean flag = false;
		
		int cnt = (Integer) mybatis.insert("reply.update", dto);
		if(cnt>0) flag = true;
		
		return flag;
	}
	
	public List<ReplyDTO> list(Map map){
		
		return mybatis.selectList("reply.list", map);
	}
	public ReplyDTO read(int rnum){
		
		return mybatis.selectOne("reply.read", rnum);
	}
	
	public int total(int bbsno) {
		return mybatis.selectOne("reply.total", bbsno);
	}
	
	public boolean delete(int rnum) {
		boolean flag = false;
		int cnt = mybatis.delete("reply.delete", rnum);
		if(cnt>0) flag = true;
		
		return flag;
	}
//	하나의 글의 여러댓글들 삭제
	public int bdelete(int bbsno) {
		return mybatis.delete("reply.bdelete", bbsno);
	}
	


}
