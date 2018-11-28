package spring.model.board
;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BreplyTest {

	public static void main(String[] args) {
		
		Resource resource = new ClassPathResource("daoTest.xml");
		
		BeanFactory factory = new XmlBeanFactory(resource);
		
		BreplyDAO dao = (BreplyDAO)factory.getBean("rdao");
		
		//create(dao);
		//read(dao);
		//update(dao);
		delete(dao);
		//list(dao);
		//total(dao);
		//increaseCnt(dao);
		//checkPasswd(dao);
	//	readReply(dao);
	}

//	private static void readReply(BreplyDAO dao) {
//		BreplyDTO dto= dao.readReply(1);
//		p("번호" + dto.getNum());
//		p("ref" + dto.getRef());
//		p("indent" + dto.getIndent());
//		p("ansnum" + dto.getAnsnum());
//	}
//	private static void checkPasswd(BreplyDAO dao) {
//		Map map = new HashMap();
//		map.put("num", 1);
//		map.put("passwd", "1234");
//		
//		if(dao.passwdCheck(map))
//			p("비밀번호 일치");
//		else
//			p("비밀번호 불일치");
//	}
//
//
//	private static void increaseCnt(BreplyDAO dao) {
//		dao.viewCount(1);
//		BreplyDTO dto = dao.read(1);
//		System.out.println("조회수 : "+dto.getCount());
//	}
//
//	private static void total(BreplyDAO dao) {
//		Map map = new HashMap();
//		map.put("word", "");
//		map.put("col", "name");
//		System.out.println(dao.getTotal(map));
//		
//		
//	}
//
//	private static void list(BreplyDAO dao) {
//		Map map = new HashMap();
//		map.put("word", "");
//		map.put("col", "name");
//		map.put("sno", 1);
//		map.put("eno", 10);
///*		p(dao.getList(map));*/
//		
//		List<BreplyDTO> list = dao.getList(map);
//		
//		Iterator<BreplyDTO> iter = list.iterator();
//		
//		while(iter.hasNext()) {
//			BreplyDTO dto = iter.next();
//			p(dto);
//			p("==========================");
//		}
//		
//	}

/*	private static void p(List<BreplyDTO> list) {
		for(int i = 0; i<list.size(); i++) {
			BreplyDTO dto = new BreplyDTO();
			System.out.println("-----------------------");
			System.out.println(dto.getNum());
			System.out.println(dto.getName());
			System.out.println(dto.getSubject());
			System.out.println(dto.getRegdate());
			System.out.println(dto.getIndent());
			System.out.println(dto.getCount());
			System.out.println(dto.getFilename());
			System.out.println("-----------------------");
		}
		
	}
	*/
	

	private static void delete(BreplyDAO dao) {

		if(dao.delete(1))
			p("성공");
		else
			p("실패");
	}
//
//	private static void update(BreplyDAO dao) {
//		BreplyDTO dto = new BreplyDTO();
//		dto.setName("임사장");
//		dto.setSubject("장미빛 인생");
//		dto.setContent("인생역전");
//		dto.setFilename("sdf.dc");
//		dto.setFilesize(10);
//		dto.setNum(1);
//		
//		if(dao.update(dto)) {
//			p("썽공");
//		} else {
//			p("실패");
//			
//		}
//	}	
//	
//	private static void read(BreplyDAO dao) {
//		p(dao.read(1));
//	}
//	
//	
//
//	private static void p(BreplyDTO dto) {
//		System.out.println(dto.getNum());
//		System.out.println(dto.getName());
//		System.out.println(dto.getSubject());
//		System.out.println(dto.getContent());
//		System.out.println(dto.getRegdate());
//		System.out.println(dto.getIp());
//		System.out.println(dto.getCount());
//		System.out.println(dto.getFilename());
//		System.out.println(dto.getFilesize());
//		
//	}
//
//	private static void create(BreplyDAO dao) {
//		BreplyDTO dto = new BreplyDTO();
//		dto.setName("오사장");
//		dto.setSubject("떼 돈 벌다");
//		dto.setContent("100만원이나");
//		dto.setPasswd("1234");
//		dto.setIp("127.0.0.1");
//		dto.setFilename("ddfd.cdc");
//		dto.setFilesize(30);
//		if(dao.create(dto))
//			p("등록 성공");
//		else
//			p("등록 실패");
//	}
//	
//	

	private static void p(String string) {
		System.out.println(string);
		
	}



}
