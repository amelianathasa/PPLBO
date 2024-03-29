package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostPageController {

	@RequestMapping("/page/index")
	public String getIndexPage() {
		return "Index";
	}
	
	@RequestMapping("/page/detail/{id}")
	public String getDetailPage(@PathVariable("id") int id, Model model) {
		model.addAttribute("id", id);
		return "detail";
	}
}
