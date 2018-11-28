package spring.model.member;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MemberTest {

	public static void main(String[] args) {
		
		Resource resource = new ClassPathResource("daoTest.xml");
		
		BeanFactory factory = new XmlBeanFactory(resource);
		
		MemberDAO dao = (MemberDAO)factory.getBean("dao");
		
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

	private static void readReply(MemberDAO dao) {
		MemberDTO dto= dao.readReply(1);
		p("번호" + dto.getNum());
		p("ref" + dto.getRef());
		p("indent" + dto.getIndent());
		p("ansnum" + dto.getAnsnum());
	}
	private static void checkPasswd(MemberDAO dao) {
		Map map = new HashMap();
		map.put("num", 1);
		map.put("passwd", "1234");
		
		if(dao.passwdCheck(map))
			p("비밀번호 일치");
		else
			p("비밀번호 불일치");
	}


	private static void increaseCnt(MemberDAO dao) {
		dao.viewCount(1);
		MemberDTO dto = dao.read(1);
		System.out.println("조회수 : "+dto.getCount());
	}

	private static void total(MemberDAO dao) {
		Map map = new HashMap();
		map.put("word", "");
		map.put("col", "name");
		System.out.println(dao.getTotal(map));
		
		
	}

	private static void list(MemberDAO dao) {
		Map map = new HashMap();
		map.put("word", "");
		map.put("col", "wname");
		map.put("sno", 1);
		map.put("eno", 10);
/*		p(dao.getList(map));*/
		
		List<MemberDTO> list = dao.list(map);
		
		Iterator<MemberDTO> iter = list.iterator();
		
		while(iter.hasNext()) {
			MemberDTO dto = iter.next();
			p(dto);
			p("==========================");
		}
		
	}

/*	private static void p(List<MemberDTO> list) {
		for(int i = 0; i<list.size(); i++) {
			MemberDTO dto = new MemberDTO();
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
	

	private static void delete(MemberDAO dao) {
		if(dao.delete(2))
			p("성공");
		else
			p("실패");
	}

	private static void update(MemberDAO dao) {
		MemberDTO dto = new MemberDTO();
		dto.setMname("임사장");
		dto.setSubject("장미빛 인생");
		dto.setContent("인생역전");
		dto.setFname("sdf.dc");
		dto.setFilesize(10);
		dto.setNum(1);
		
		if(dao.update(dto)) {
			p("썽공");
		} else {
			p("실패");
			
		}
	}	
	
	private static void read(MemberDAO dao) {
		p(dao.read(1));
	}
	
	

	private static void p(MemberDTO dto) {
		System.out.println(dto.getMemberno());
		System.out.println(dto.getWname());
		System.out.println(dto.getTitle());
		System.out.println(dto.getContent());
		System.out.println(dto.getWdate());
		System.out.println(dto.getViewcnt());
		System.out.println(dto.getFilename());
		System.out.println(dto.getFilesize());
		
	}

	private static void create(MemberDAO dao) {
		MemberDTO dto = new MemberDTO();
		dto.setId("user20");
		dto.setMname("오사장");
		dto.setEmail("user20");
		dto.setJob("A2");
		dto.setAddress1("123");
		dto.setAddress2("123");
		dto.setPasswd("1234");
		dto.setFname("ddfd.cdc");
		if(dao.create(dto))
			p("등록 성공");
		else
			p("등록 실패");
	}
	
	

	private static void p(String string) {
		System.out.println(string);
		
	}



}
