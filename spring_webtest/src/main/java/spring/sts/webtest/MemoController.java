package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.memo.MemoDAO;
import spring.model.memo.MemoDTO;
import spring.model.memo.MemoService;
import spring.utility.webtest.utility;

@Controller
public class MemoController {

	@Autowired
	private MemoDAO dao;
	
	@Autowired
	private MemoService mgr;

	@RequestMapping(value="/memo/delete", method=RequestMethod.POST)
	public String delete(int memono, HttpServletRequest request,Model model) {
		
		model.addAttribute("col", request.getParameter("col"));
		model.addAttribute("word", request.getParameter("word"));
		model.addAttribute("nowPage", request.getParameter("nowPage"));
		
		if(dao.delete(memono)) {
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
	}

	@RequestMapping(value="/memo/delete", method=RequestMethod.GET)
	public String delete(int memono) {
		if(dao.checkRefnum(memono)) {
			return "/error/error";
		}else {
			return "/memo/delete";
		}
	}
	
	@RequestMapping(value="/memo/reply", method=RequestMethod.POST)
	public String reply(int memono, Model model, HttpServletRequest request, MemoDTO dto) {
	 	
	 	boolean flag = mgr.reply(dto);
		
		request.setAttribute("col", request.getParameter("col"));
		request.setAttribute("word", request.getParameter("word"));
		request.setAttribute("nowPage", request.getParameter("nowPage"));
		
		if(flag) {
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
	}

	@RequestMapping(value="/memo/reply", method=RequestMethod.GET)
	public String reply(int memono, Model model) {
		MemoDTO dto = dao.replyRead(memono);
		
		model.addAttribute("dto", dto);
		
		return "/memo/reply";
	}
	
	@RequestMapping(value = "/memo/update", method = RequestMethod.POST)
	public String update(int memono, Model model, HttpServletRequest request) {

		MemoDTO dto = dao.read(memono);
		
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		
		request.setAttribute("col", request.getParameter("col"));
		request.setAttribute("word", request.getParameter("word"));
		request.setAttribute("nowPage", request.getParameter("nowPage"));
		
		if(dao.update(dto)) {
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
	}

	@RequestMapping(value = "/memo/update", method = RequestMethod.GET)
	public String update(int memono, Model model) {
		MemoDTO dto = dao.read(memono);

		model.addAttribute("dto", dto);

		return "/memo/update";
	}

	@RequestMapping("/memo/read")
	public String read(int memono, HttpServletRequest request) {

		String col = request.getParameter("col");
		String word = request.getParameter("word");
		String nowPage = request.getParameter("nowPage");

		dao.updateviewcnt(memono);
		// 조회수 증가 end

		// 조회
		MemoDTO dto = dao.read(memono);
		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");

		request.setAttribute("dto", dto);
		request.setAttribute("content", content);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("memono", memono);

		return "/memo/read";
	}

	@RequestMapping(value = "/memo/create", method = RequestMethod.POST)
	public String create(HttpServletRequest request, Model model, MemoDTO dto) {

		if (dao.create(dto)) {
			return "redirect:/memo/list";
		} else {
			return "/error/error";
		}

	}

	@RequestMapping(value = "/memo/create", method = RequestMethod.GET)
	public String create() {

		return "/memo/create";
	}

	@RequestMapping("/memo/list")
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

		// DB에서 가져올 순번 생성
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage + 1;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		List<MemoDTO> list = dao.list(map);

		int totalRecord = dao.total(map);

		String paging = utility.paging3(totalRecord, nowPage, recordPerPage, col, word);

		request.setAttribute("paging", paging);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("list", list);

		return "/memo/list";
	}
}
