package spring.model.board;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public class BreplyDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	@RequestMapping("/board/delete")
	public boolean delete(int rnum) {
		boolean flag = false;
		int cnt = mybatis.delete("breply.delete", rnum);
		if(cnt>0) flag = true;
		return flag;
	}
	
}
