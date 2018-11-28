package spring.model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.db.board.DBClose;
import spring.db.board.DBOpen;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}

	// CREATE
	public boolean create(BoardDTO dto) {
		boolean flag = false;
		int cnt = mybatis.insert("board.create", dto);
		if (cnt > 0) {
			flag = true;
		}
		return flag;
	}

	// READ

	public BoardDTO read(int num) {

		return mybatis.selectOne("board.read", num);

	}

	// UPDATE

	public boolean update(BoardDTO dto) {
		boolean flag = false;
		int cnt = mybatis.update("board.update", dto);
		if(cnt>0) {
			flag = true;
		}
		return flag;
	}

	// DELETE

	public boolean delete(int num) {
		boolean flag = false;
		int cnt = mybatis.delete("board.delete", num);
		if(cnt>0) {
			flag = true;
		}
		return flag;
	}

	// LIST

	public List<BoardDTO> getList(Map map) {

		return mybatis.selectList("board.list", map);

	}

	// count
	public int getTotal(Map map) {
		return mybatis.selectOne("board.total", map);
	}

	// 조회수
	public void viewCount(int num) {
		mybatis.update("board.viewCount", num);
	}

	// 답변 수
	public boolean checkRefnum(int num) {
		boolean flag = false;
		int cnt = mybatis.selectOne("board.checkRefnum", num);
		if(cnt>0) {
			flag = true;
		}
		return flag;
	}

	// ansnum 업데이트
	public void updateAnsnum(Map map){
		mybatis.update("board.updateAnsnum", map);
	}

	// 답변 생성
	public boolean insertReply(BoardDTO dto){
		boolean flag = false;
		int cnt = mybatis.insert("board.insertReply", dto);
		if(cnt>0) {
			flag = true;
		}
		return flag;
	}

	public boolean passwdCheck(Map map) {
		boolean flag = false;
		int cnt = mybatis.selectOne("board.passwdCheck", map);
		if (cnt > 0) {
			flag = true;
		}
		return flag;
	}

	public BoardDTO readReply(int num) {
		return mybatis.selectOne("board.readReply", num);
	}
}
