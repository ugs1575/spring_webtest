package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.gallery.GalleryDAO;
import spring.model.gallery.GalleryDTO;
import spring.utility.webtest.utility;

@Controller
public class GalleryController {

	@Autowired
	GalleryDAO dao = new GalleryDAO();
	
	@RequestMapping(value="/gallery/updatePasswd", method=RequestMethod.POST)
	public String updatePasswd(int gno, String pw, String newpw) {
		Map map = new HashMap();
		map.put("gno", gno);
		map.put("pw", pw);
		map.put("newpw", newpw);
		
		boolean gflag = dao.passwdCheck(map);
		boolean flag = dao.updatePasswd(map);
		if(gflag) {
			if(flag) {
				return "redirect:/gallery/list";
			}else {
				return "/error/error";
			}
			
		}else {
			return "/error/passwdError";
		}
		
	}
	
	@RequestMapping(value="/gallery/updatePasswd", method=RequestMethod.GET)
	public String updatePasswd(int gno, HttpServletRequest request) {
		request.setAttribute("gno", gno);
		
		return "/gallery/updatePasswd";
	}
	
	@RequestMapping(value="/gallery/delete", method=RequestMethod.POST)
	public String delete(int gno, String fname, String pw, HttpServletRequest request) {

		String upDir=request.getRealPath("/gallery/storage");

		Map map = new HashMap();
		map.put("gno", gno);
		map.put("pw", pw);
		
		boolean pflag= dao.passwdCheck(map);
		
		if(pflag){
			if(dao.delete(gno) && fname != "noimage.jpg") {
				utility.deleteFile(upDir, fname);
				return "redirect:/gallery/list";
			}else {
				return "/error/error";
			}
		}else {
			return "/error/passwdError";
		}

	}		

	@RequestMapping(value="/gallery/delete", method=RequestMethod.GET)
	public String delete(int gno, String fname, HttpServletRequest request) {
		
		request.setAttribute("gno", gno);
		request.setAttribute("fname", fname);
		
		return "/gallery/delete";
	}

	@RequestMapping(value="/gallery/update", method=RequestMethod.POST)
	public String update(int gno, String oldfile, GalleryDTO dto, HttpServletRequest request) {
		
		String upDir = request.getRealPath("/gallery/storage");

		String fname = "";

		if (dto.getFnameMF().getSize() > 0) {			
			fname = utility.saveFileSpring(dto.getFnameMF(), upDir);
		} else if (dto.getFnameMF().getSize()==0) {
			fname = oldfile;
		}
		Map map = new HashMap();
		Map pmap = new HashMap();
		
		map.put("gno", dto.getGno());
		map.put("fname", fname);
		map.put("title", dto.getTitle());
		map.put("writer", dto.getWriter());
		map.put("content", dto.getContent());
		
		pmap.put("pw", dto.getPw());
		pmap.put("gno", dto.getGno());
		
		boolean pflag= dao.passwdCheck(pmap);
		boolean flag = false;

		if(pflag) {
			if(dao.updateInfo(map)) {
				utility.deleteFile(upDir, oldfile);
				return "redirect:/gallery/list";
			}else {
				return "/error/error";
			}
		}else {
			return "/error/passwdError";
		}
	}
	
	@RequestMapping(value="/gallery/update", method=RequestMethod.GET)
	public String update(int gno, HttpServletRequest request, String oldfile) {
		
		GalleryDTO dto = dao.read(gno);
		
		request.setAttribute("oldfile", oldfile);
		request.setAttribute("gno", gno);
		request.setAttribute("dto", dto);
		
		return "/gallery/update";
	}
	
	@RequestMapping("/gallery/read")
	public String read(int gno, HttpServletRequest request) {
		
		dao.upViewCnt(gno);

		GalleryDTO dto = dao.read(gno);
		
		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");
		dto.setContent(content);
		
		List list = dao.readList(gno);
		
		int[] lGno = (int[]) list.get(0);
		String[] lFname = (String[]) list.get(1);
		
		request.setAttribute("dto", dto);
		request.setAttribute("list", list);
		request.setAttribute("lGno", lGno);
		request.setAttribute("lFname", lFname);

		return "/gallery/read";
	}

	@RequestMapping(value = "/gallery/create", method = RequestMethod.POST)
	public String create(HttpServletRequest request, GalleryDTO dto) {

		String upDir = request.getRealPath("/gallery/storage");

		int size = (int) dto.getFnameMF().getSize();

		String fname = null;
		if (size > 0) {
			fname = utility.saveFileSpring(dto.getFnameMF(), upDir);
		} else {
			fname = "noimage.jpg";
		}

		dto.setFname(fname);

		if (dao.create(dto)) {
			return "redirect:/gallery/list";
		} else {
			if (!fname.equals("")) {
				utility.deleteFile(upDir, fname);
			}
			return "/error/error";
		}
	}

	@RequestMapping(value = "/gallery/create", method = RequestMethod.GET)
	public String create() {

		return "/gallery/create";
	}

	@RequestMapping("/gallery/list")
	public String list(HttpServletRequest request) {
		String col = utility.checkNull(request.getParameter("col"));
		String word = utility.checkNull(request.getParameter("word"));

		if (col.equals("total"))
			word = "";

		// paging관련
		int nowPage = 1;
		int recordPerPage = 5;

		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}

		// DB에서 가져올 레코드의 순번
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		int totalRecord = dao.total(map);
		String paging = utility.paging3(totalRecord, nowPage, recordPerPage, col, word);

		List<GalleryDTO> list = dao.list(map);

		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);

		return "/gallery/list";
	}
}
