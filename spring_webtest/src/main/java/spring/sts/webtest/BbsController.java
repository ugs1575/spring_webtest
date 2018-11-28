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

import spring.model.bbs.BbsDAO;
import spring.model.bbs.BbsDTO;
import spring.model.bbs.BbsService;
import spring.model.bbs.ReplyDAO;
import spring.model.bbs.ReplyDTO;
import spring.utility.webtest.utility;

@Controller
public class BbsController {
	
	@Autowired
	private BbsDAO dao;
	
	@Autowired
	private BbsService mgr;
	
	@Autowired
	private ReplyDAO rdao;
	
	@RequestMapping("/bbs/rdelete")
	public String rdelete(int rnum, int bbsno, int nowPage,
			int nPage, String col, String word, Model model) {
		int total =rdao.total(bbsno);
		int totalPage = (int)(Math.ceil((double)total/3));
		if(rdao.delete(rnum)) {
			if(nPage!=1&&nPage==totalPage&&total%3==1) {
				nPage=nPage-1;
			}
			model.addAttribute("bbsno", bbsno);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("nPage", nPage);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			
		} else {
			return "/error/error";
		}
		
		return "redirect:/bbs/read";
	}
	
	@RequestMapping("/bbs/rupdate")
	public String rupdate(ReplyDTO dto, int nowPage, int nPage, String col,
			String word, Model model) {
		if(rdao.update(dto)) {
			model.addAttribute("bbsno", dto.getBbsno());
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("nPage", nPage);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
		} else {
			return "/error/error";
		}
		
		return "redirect:/bbs/read";
	}
	
	@RequestMapping("/bbs/rcreate")
	public String rcreate(ReplyDTO dto, int nowPage, String col,
			String word, Model model) {
	
	if(rdao.create(dto)) {
		model.addAttribute("bbsno", dto.getBbsno());
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("col", col);
		model.addAttribute("word", word);
	} else {
		return "/error/error";
	}
	
	return "redirect:/bbs/read";
	}

	
	
	@RequestMapping(value="bbs/delete", method=RequestMethod.POST)
	public String delete(BbsDTO dto,String passwd, int bbsno,
			Model model, HttpServletRequest request, String oldfile) {
		
		String upDir = request.getRealPath("/bbs/storage");
		
		Map map = new HashMap();
		map.put("bbsno", bbsno);
		map.put("passwd", passwd);
		
		boolean pflag = dao.passCheck(map);
		boolean flag = false;

		if(pflag) {
			try{
				mgr.delete(bbsno);
				utility.deleteFile(upDir, oldfile);
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("col", request.getParameter("col"));
			 
			return "redirect:/bbs/list";
			} catch(Exception e) {
			e.printStackTrace();
			
			return "/bbs/error/error";
			}
		}else {
			return "/bbs/error/passwdError";
		}
	}
	
	@RequestMapping(value="bbs/delete", method=RequestMethod.GET)
	public String delete(int bbsno, HttpServletRequest request) {
		
		boolean flag = dao.checkRefnum(bbsno);
		
		request.setAttribute("flag", flag);
		
		return "/bbs/delete";
	}
	
	@RequestMapping(value="/bbs/reply", method=RequestMethod.GET)
	public String reply(int bbsno, Model model) {
		
		BbsDTO dto = dao.replyRead(bbsno);
		
		model.addAttribute("dto",dto);
		
		return "/bbs/reply";
	}
	@RequestMapping(value="/bbs/reply", method=RequestMethod.POST)
	public String reply(BbsDTO dto, HttpServletRequest request) {
		
		String upDir = request.getRealPath("/bbs/storage");
		
		int filesize = (int)dto.getFilenameMF().getSize();
		String filename = "";
		if(filesize>0) {
			filename = utility.saveFileSpring(dto.getFilenameMF(), upDir);
		}
		
		dto.setFilename(filename);
		dto.setFilesize(filesize);
		
		try{
			mgr.reply(dto);
			return "redirect:/bbs/list";
 		} catch (Exception e) {
		e.printStackTrace();
		utility.deleteFile(upDir, filename);
				
		return "/error/error";
		}
	}
	
	@RequestMapping(value="/bbs/update", method=RequestMethod.POST)
	public String update(BbsDTO dto, Model model, HttpServletRequest request, String oldfile) {

		String upDir = request.getRealPath("/bbs/storage");
		
		Map map = new HashMap();
		map.put("bbsno", dto.getBbsno());
		map.put("passwd", dto.getPasswd());
		
		int filesize = (int)dto.getFilenameMF().getSize();
		String filename = "";
		boolean pflag = dao.passCheck(map);
		boolean flag = false;
		
		if(pflag) {
			if(filesize>0) {
				utility.deleteFile(upDir, oldfile);
			}
			filename = utility.saveFileSpring(dto.getFilenameMF(), upDir);
			dto.setFilename(filename);
			dto.setFilesize(filesize);
			
			if(dao.update(dto)) {
				return "redirect:/bbs/list";
			}else {
				return "/error/error";
			}
		}else {
			return "/error/passwdError";
		}
	}

	@RequestMapping(value="/bbs/update", method=RequestMethod.GET)
	public String update(int bbsno, Model model) {
		
		BbsDTO dto = dao.read(bbsno);
		
		model.addAttribute("dto", dto);
		
		return "/bbs/update";
	}
	
	@RequestMapping("/bbs/read")
	public String read(int nowPage, String col, String word,
			int bbsno, Model model, HttpServletRequest request) {
		
		dao.updateviewcnt(bbsno);
		BbsDTO dto = dao.read(bbsno);
		String content = dto.getContent().replaceAll("\r\n", "<br>");
		dto.setContent(content);
		model.addAttribute("dto", dto);
		
		//댓글 관련 시작
		String url="/read";
		int nPage=1;
		
		if(request.getParameter("nPage")!=null) {
			nPage = Integer.parseInt(request.getParameter("nPage"));
		}
		
		int recordPerPage = 3;
		
		int sno = ((nPage-1)*recordPerPage)+1;
		int eno = nPage*recordPerPage;
		Map map = new HashMap();
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("bbsno", bbsno);
		
		List<ReplyDTO> list = rdao.list(map);
		
		int total = rdao.total(bbsno);
		String paging = utility.paging(total, nPage, recordPerPage, url, bbsno, nowPage, col, word);

		model.addAttribute("rlist", list);
		model.addAttribute("paging", paging);
		model.addAttribute("nPage", nPage);
		//댓글 관련 끝
		
		return "/bbs/read";
	}
	
	@RequestMapping(value="/bbs/create", method=RequestMethod.GET)
	public String create() {
		return "/bbs/create";
	}
	
	@RequestMapping(value="/bbs/create", method=RequestMethod.POST)
	public String create(BbsDTO dto, HttpServletRequest request) {

		String upDir = request.getRealPath("/bbs/storage");
		
		int filesize = (int)dto.getFilenameMF().getSize();
		String filename = "";
		if(filesize>0) {
			filename= utility.saveFileSpring(dto.getFilenameMF(), upDir);
		}
		
		dto.setFilesize(filesize);
		dto.setFilename(filename);
		
		
		boolean flag = dao.create(dto);
		
		
		if(flag) {
			return "redirect:/bbs/list";
		}else {
			if(!filename.equals("")){
				utility.deleteFile(upDir, filename);
			}
			return "/error/error";
		}
	}
	
	@RequestMapping("/bbs/list")
	public String list(HttpServletRequest request, Model model) {
		
		String col = utility.checkNull(request.getParameter("col"));
		String word = utility.checkNull(request.getParameter("word"));

		if (col.equals("total")) {
			word = "";
		}

		int nowPage = 1;
		int recordPerPage = 5;

		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage")); //위험함! numberformatException 가능성	
		}

		int sno = ((nowPage - 1) * recordPerPage) + 1; //공식임 ㅎ
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		List<BbsDTO> list = dao.list(map);
		//전체 레코드 개수(col, word)
		int totalRecord = dao.total(map);

		String paging = utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("rdao", rdao);
		
		return "/bbs/list";
	}
	
}
