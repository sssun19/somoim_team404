package test.com.moim.somoim.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;
import test.com.moim.member.model.MemberVO;
import test.com.moim.member.service.MemberService;
import test.com.moim.somoim.model.SomoimVO;
import test.com.moim.somoim.service.SomoimService;
import test.com.moim.userinfo.model.UserinfoVO;


@Slf4j
@Controller
public class SomoimController {
	
	@Autowired
	SomoimService service;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ServletContext sContext;

	@Autowired
	HttpSession session;


	@RequestMapping(value = "/som_selectAll.do", method = RequestMethod.GET)
	public String som_selectAll(SomoimVO vo, Model model) {
		log.info("som_selectAll.do().....");
		log.info("-------");

		List<SomoimVO> vos = service.selectAll(vo);

		model.addAttribute("vos",vos);

		return "board/som_selectAll";
	}

	@RequestMapping(value = "/som_selectOne.do", method = RequestMethod.GET)
	public String som_selectOne(SomoimVO vo, Model model) {
		log.info("som_selectOne.do().....{}", vo);
		
		SomoimVO vo2 = service.selectOne(vo);
		String user_id = (String)session.getAttribute("user_id")==null?"tester":(String)session.getAttribute("user_id");
		
		log.info("user_id : {}", user_id);
		
		UserinfoVO uvo = new UserinfoVO();
		uvo.setUser_id(user_id);
		
		log.info("이걸확인해!!{}", uvo.getUser_id());
		UserinfoVO uvo2 = service.searchSavename(uvo);
		
		log.info("이것도{}", uvo2.getSave_name());
		log.info("profile!!!:{}", uvo2.getSave_name());

		session.setAttribute("num",vo.getNum());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vo2", vo2);
		map.put("uvo2", uvo2);
		model.addAllAttributes(map);
		
		return "board/som_selectOne";
	}
	
	@RequestMapping(value = "/som_searchList.do", method = RequestMethod.GET)
	public String som_searchList(String searchKey, String searchWord, String category, Model model) {
		log.info("som_searchList.do().....{}, {}", searchKey, searchWord);
		log.info("------------{}", category);
		
		
		List<SomoimVO> vos = service.searchList(searchKey, searchWord, category);
		
		model.addAttribute("vos", vos);
		
		return "board/som_selectAll";
	}
	
	@RequestMapping(value = "/som_insert.do", method = RequestMethod.GET)
	public String som_insert() {

		log.info("som_insert.do().....");
		
		return "board/som_insert";
	}
	
	@RequestMapping(value = "/som_insertOK.do", method = RequestMethod.POST)
	public String som_insertOK(SomoimVO vo) throws IllegalStateException, IOException {

		log.info("som_insertOK.do().....{}", vo);
		
		int fileNameLength = vo.getFile().getOriginalFilename().length();
		String getOriginalFileName = vo.getFile().getOriginalFilename();

		log.info("getOriginalFilename : {}", getOriginalFileName);
		log.info("fileNameLength : {}", fileNameLength);
		
		vo.setSomoim_img(getOriginalFileName.length() == 0 ? "아이유.png" : getOriginalFileName);
		
		if (getOriginalFileName.length() == 0) {
			vo.setSomoim_img("아이유.png");
			
		} else {
			vo.setSomoim_img(getOriginalFileName);
			// 웹 어플리케이션이 갖는 실제 경로 : 이미지를 업로드할 대상 경로를 찾아서 파일 저장
			String realPath = sContext.getRealPath("resources/uploadimg");
			
			log.info("realPath : {}", realPath);

			File f = new File(realPath + "\\" + vo.getSomoim_img());

			vo.getFile().transferTo(f);

			//// create thumbnail image/////////
			BufferedImage original_buffer_img = ImageIO.read(f);
			BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D graphic = thumb_buffer_img.createGraphics();
			graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null);
			File thumb_file = new File(realPath + "/thumb_" + vo.getSomoim_img());
			String formatName = vo.getSomoim_img().substring(vo.getSomoim_img().lastIndexOf(".")+1);
			log.info("formatName : {}", formatName);
			ImageIO.write(thumb_buffer_img, formatName, thumb_file);

		} // end else
		
		log.info("{}", vo);
		int result = service.insert(vo);
		
//		MemberVO vo2 = new MemberVO();
//		vo2.setUser_id(vo.getSomoim_master());
//		vo2.setSom_title(vo.getSom_title());
//		vo2.setSomoim_num(vo.getNum());
//		log.info("==========================={}", vo.getNum());
//		
//		List<MemberVO> vos = memberService.searchSavename(vo.getSomoim_master());
//		log.info(".....save_name!!!!!!!!!!!!!!!!!!!{}", vos.get(0));
//		
//		
////		vo2.setSave_name(vo3.getSave_name());
//		
//		int result2 = memberService.insert(vo2);
//		if(result2==1) {
//			log.info("완료!");
//		} else
//			log.info("실패....");
		
		log.info("result : {}", result);
		if (result==1)
			return "redirect:som_selectAll.do";
		else
			return "redirect:som_insert.do";
		
	}
	
	
	@RequestMapping(value = "/som_update.do", method = RequestMethod.GET)
	public String som_update(SomoimVO vo) {
		log.info("som_update.do().....{}", vo);
		
		int result = service.update(vo);
		
		
		return "redirect:selectAll.do";
	}
	
	@RequestMapping(value = "/som_delete.do", method = RequestMethod.GET)
	public String som_delete(SomoimVO vo) {
		log.info("som_delete.do().....{}", vo);
		
		int result = service.delete(vo);
		
		
		return "redirect:selectAll.do";
	}

	
}
