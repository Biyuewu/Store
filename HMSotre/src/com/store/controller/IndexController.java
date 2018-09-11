
package com.store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.pojo.Category;
import com.store.pojo.Product;
import com.store.service.IProductService;
import com.store.service.impl.CategoryService;
import com.store.utils.JedisUtils;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * 负责页面跳转
 * 
 * @author john
 */
@Controller
public class IndexController {

	@Autowired
	private IProductService ProductService;
	
	/**
	 * 进入首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("goIndex")
	public String execute(Model model) {

		List<Product> list01 = ProductService.findHots();
		List<Product> list02 = ProductService.findNews();
		// 将2个集合放入到request
		model.addAttribute("hots", list01);
		model.addAttribute("news", list02);
		// 转发到真实的首页
		return "index";
	}

	/**
	 * 跳转到登录界面
	 * 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("loginUI")
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "login";
	}

	/**
	 * 跳转到注册界面
	 * 
	 * @return
	 */
	@RequestMapping("registUI")
	public String registUI() {
		return "register";
	}

	/**
	 * 登出
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("logOut")
	public String logOut(HttpServletRequest req) {
		// 获取session并注销
		req.getSession().invalidate();
		return "goindex";
	}
	
	

}