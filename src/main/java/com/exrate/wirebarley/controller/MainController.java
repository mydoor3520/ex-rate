package com.exrate.wirebarley.controller;
import com.exrate.wirebarley.service.ExRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ExRateService exRateService;

	/**
	 * 메인 - 최초 페이지
	 */
	@GetMapping("/index")
	public String index() {
		String path = "index";
		return path;
	}

	/**
	 * 교환 비율 반환
	 */
	@GetMapping("/ex-rate")
	public @ResponseBody ModelMap exRate(HttpServletResponse response, HttpServletRequest request) throws Exception {
		logger.debug("GET ex-rate >>>");
		String source = request.getParameter("source");
		String currency= request.getParameter("currency");

		ModelMap responseBody = exRateService.getExRate(source, currency);

		return responseBody;
	}

	/**
	 * 송금 금액을 받아 현재 환율 기준으로 계산
	 */
	@GetMapping("/calculate-ex-rate")
	public @ResponseBody ModelMap CalculateByExRate(HttpServletResponse response, HttpServletRequest request) throws Exception {
		logger.debug("GET calculated ex-rate >>>");
		String source = request.getParameter("source");
		String currency= request.getParameter("currency");
		Float amount = Float.parseFloat(request.getParameter("amount"));

		ModelMap responseBody = exRateService.CalculatedExRate(source, currency, amount);
		return responseBody;
	}
}