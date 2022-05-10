package com.exrate.wirebarley.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	/**
	 * 메인 - 최초 페이지
	 */
	@GetMapping("/index")
	public String index(Model model) {
		String path = "index";
		return path;
	}
}