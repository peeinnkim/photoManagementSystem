package com.peeinn.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.peeinn.domain.MemberVO;
import com.peeinn.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	MemberService service;
	
	
	@RequestMapping(value="join", method=RequestMethod.GET)
	public void registMemberGET() {
		logger.info("----------registMember GET----------");
	}

	@RequestMapping(value="join", method=RequestMethod.POST)
	public String registMemberPOST(MemberVO vo) {
		logger.info("----------registMember POST----------");
		service.insertMember(vo);
		
		return "redirect:/member/login";
	}

	@RequestMapping(value="login", method=RequestMethod.GET)
	public void loginGET() {
		logger.info("----------login GET----------");
	}
	
	@RequestMapping(value="loginPost", method=RequestMethod.POST)
	public String loginPOST(Model model, MemberVO vo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("----------login POST----------");
		MemberVO dbVo = service.selectMember(vo.getmId());
		
		if(dbVo == null) {
			model.addAttribute("error", "존재하지 않는 아이디입니다.");
		} else {
			if(!dbVo.getmPw().equals(vo.getmPw())) {
				model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
			} else {
				request.getSession().setAttribute("Auth", vo.getmId());
				return "redirect:/photo/photoMng";
			}
		}
		
		return "/member/login";
		
	}
	        
}//MemberController
