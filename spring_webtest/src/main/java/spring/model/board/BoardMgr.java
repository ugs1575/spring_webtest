package spring.model.board;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.db.board.DBClose;
import spring.db.board.DBOpen;

@Service
public class BoardMgr {
	@Autowired
	private BoardDAO dao;
	//autowire를 통해 만든 dao는 single ton 형식이기 때문에 static이 필요 없다구! dao 안에는 해쉬코드 옵니다용
	
	//트랜잭션 처리
	//접속자가 많아 DAO 생성이 증가되여 성능이 저하될 수 있음.
	//아래의 과정을 통해 DAO생성을 제한하면서도 필요 기능을 제공함으로써 과부화 방지
	public boolean reply(BoardDTO dto) {
		boolean flag = false;
		Map map = new HashMap();
		map.put("ref", dto.getRef());
		map.put("ansnum", dto.getAnsnum());
		try {
			dao.updateAnsnum(map);
			flag = dao.insertReply(dto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {

		}
		return flag;
	}
}
