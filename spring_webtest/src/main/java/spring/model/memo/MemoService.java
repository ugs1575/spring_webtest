package spring.model.memo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import spring.db.webtest.DBOpen;

@Repository
public class MemoService {
	private static MemoDAO dao;
		static {
			dao = new MemoDAO();
		}
		
		public boolean reply(MemoDTO dto) {
			boolean flag = false;
			try {
				
				Map map = new HashMap();
				map.put("ansnum", dto.getAnsnum());
				map.put("grpno", dto.getGrpno());
				
				flag = dao.replyCreate(dto);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			}
			
			return flag;
		}
	}

