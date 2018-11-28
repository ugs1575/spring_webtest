package spring.model.memo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MemoTest {

	public static void main(String[] args) {
		
		Resource resource = new ClassPathResource("daoTest.xml");
		
		BeanFactory factory = new XmlBeanFactory(resource);
		
		MemoDAO dao = (MemoDAO)factory.getBean("dao");
		
		//create(dao);
		//read(dao);
		//update(dao);
		//delete(dao);
		list(dao);
		//total(dao);
		//increaseCnt(dao);
		//checkPasswd(dao);
	//	readReply(dao);
	}

	/*private static void readReply(MemoDAO dao) {
		MemoDTO dto= dao.readReply(1);
		p("번호" + dto.getMemono());
		p("ref" + dto.getRef());
		p("indent" + dto.getIndent());
		p("ansnum" + dto.getAnsnum());
	}
	private static void checkPasswd(MemoDAO dao) {
		Map map = new HashMap();
		map.put("num", 1);
		map.put("passwd", "1234");
		
		if(dao.passwdCheck(map))
			p("비밀번호 일치");
		else
			p("비밀번호 불일치");
	}


	private static void increaseCnt(MemoDAO dao) {
		dao.viewCount(1);
		MemoDTO dto = dao.read(1);
		System.out.println("조회수 : "+dto.getCount());
	}

	private static void total(MemoDAO dao) {
		Map map = new HashMap();
		map.put("word", "");
		map.put("col", "name");
		System.out.println(dao.total(map));
		
		
	}
*/
	private static void list(MemoDAO dao) {
		Map map = new HashMap();
		map.put("word", "");
		map.put("col", "title");
		map.put("sno", 1);
		map.put("eno", 10);
		p(dao.list(map));
		
		List<MemoDTO> list = dao.list(map);
		
		Iterator<MemoDTO> iter = list.iterator();
		
		while(iter.hasNext()) {
			MemoDTO dto = iter.next();
			p(dto);
			p("==========================");
		}
		
	}

	private static void p(List<MemoDTO> list) {
		for(int i = 0; i<list.size(); i++) {
			MemoDTO dto = new MemoDTO();
			System.out.println("-----------------------");
			System.out.println(dto.getMemono());
			System.out.println(dto.getTitle());
			System.out.println(dto.getContent());
			System.out.println(dto.getWdate());
			System.out.println(dto.getViewcnt());
			System.out.println("-----------------------");
		}
		
	}
	
	
/*
	private static void delete(MemoDAO dao) {
		if(dao.delete(2))
			p("성공");
		else
			p("실패");
	}

	private static void update(MemoDAO dao) {
		MemoDTO dto = new MemoDTO();
		dto.setName("임사장");
		dto.setSubject("장미빛 인생");
		dto.setContent("인생역전");
		dto.setFilename("sdf.dc");
		dto.setFilesize(10);
		dto.setNum(1);
		
		if(dao.update(dto)) {
			p("썽공");
		} else {
			p("실패");
			
		}
	}	
	
	private static void read(MemoDAO dao) {
		p(dao.read(1));
	}
	
	
*/
	private static void p(MemoDTO dto) {
		System.out.println(dto.getMemono());
		System.out.println(dto.getTitle());
		System.out.println(dto.getContent());
		System.out.println(dto.getWdate());
		System.out.println(dto.getViewcnt());
		
	}
/*
	private static void create(MemoDAO dao) {
		MemoDTO dto = new MemoDTO();
		dto.setTitle("떼 돈 벌다");
		dto.setContent("100만원이나");
		if(dao.create(dto))
			p("등록 성공");
		else
			p("등록 실패");
	}
	*/
	

	private static void p(String string) {
		System.out.println(string);
		
	}



}
