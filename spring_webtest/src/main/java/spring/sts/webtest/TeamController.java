package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.team.TeamDAO;
import spring.model.team.TeamDTO;
import spring.model.team.TeamService;
import spring.utility.webtest.utility;

@Controller
public class TeamController {
	
	@Autowired
	TeamDAO dao = new TeamDAO();
	
	@Autowired
	TeamService mgr = new TeamService();

	@RequestMapping(value = "/team/reply", method=RequestMethod.POST)
	public String reply(TeamDTO dto, Model model) {
		if(mgr.reply(dto)) {
			return "redirect:/team/list";
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping(value = "/team/reply", method=RequestMethod.GET)
	public String reply(int no, Model model) {
		TeamDTO dto = dao.replyRead(no);
		model.addAttribute("dto", dto);
		
		return "/team/reply";
	}
	
	@RequestMapping("/team/delete")
	public String delete(int no, Model model, HttpServletRequest request) {
	
		if(!dao.checkRefnum(no)) {
			if(dao.delete(no)) {
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				return "redirect:/team/list";
			}else {
				return "/error/error";
			}
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping("/team/read")
	public String read(HttpServletRequest request, int no, Model model) {
		
		String col = request.getParameter("col");
		String word = request.getParameter("word");
		String nowPage = request.getParameter("nowPage");
		
		TeamDTO dto = dao.read(no);
		
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		model.addAttribute("dto", dto);
		
		return "/team/read";
	}
	
	@RequestMapping(value="/team/update", method=RequestMethod.POST)
	public String update(TeamDTO dto, HttpServletRequest request, Model model) {
		
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setSkill(request.getParameterValues("skill"));
		dto.setHobby(request.getParameter("hobby"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setAddress2(request.getParameter("address2"));
		dto.setPhone(request.getParameter("phone"));
		
		if(dao.update(dto)) {
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			return "redirect:/team/list";
		}else {
			return "/error/error";
		}
		
	}
	
	@RequestMapping(value="/team/update", method=RequestMethod.GET)
	public String update(int no, Model model) {
		TeamDTO dto = dao.read(no);
		
		model.addAttribute("dto", dto);
		return "/team/update";
	}
	
	@RequestMapping(value="/team/create", method=RequestMethod.POST)
	public String create(TeamDTO dto, HttpServletRequest request, Model model) {
		
		dto.setName(request.getParameter("name"));
		dto.setGender(request.getParameter("gender"));
		dto.setSkill(request.getParameterValues("skill"));
		dto.setHobby(request.getParameter("hobby"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setAddress2(request.getParameter("address2"));
		dto.setPhone(request.getParameter("phone"));
		
		if(dao.create(dto)) {
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			return "redirect:/team/list";
		}else {
			return "/error/error";
		}
	}

	@RequestMapping(value="/team/create", method=RequestMethod.GET)
	public String create() {
		return "/team/create";
	}
	
	@RequestMapping("/team/list")
	public String list(HttpServletRequest request, Model model) {
		
		String col = utility.checkNull(request.getParameter("col"));
		String word = utility.checkNull(request.getParameter("word"));

		if (col.equals("total"))
			word = "";

		// PAGING관련 처리
		int nowPage = 1;
		int recordPerPage = 5;

		if (request.getParameter("nowPage") != null)
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		// DB관련 처리
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		List<TeamDTO> list = dao.list(map);

		int totalRecord = dao.total(map);
		
		String paging = utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		
		return "/team/list";
		
	}
}
