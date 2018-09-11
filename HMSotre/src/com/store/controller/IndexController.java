
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
 * ����ҳ����ת
 * 
 * @author john
 */
@Controller
public class IndexController {

	@Autowired
	private IProductService ProductService;
	
	/**
	 * ������ҳ
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("goIndex")
	public String execute(Model model) {

		List<Product> list01 = ProductService.findHots();
		List<Product> list02 = ProductService.findNews();
		// ��2�����Ϸ��뵽request
		model.addAttribute("hots", list01);
		model.addAttribute("news", list02);
		// ת������ʵ����ҳ
		return "index";
	}

	/**
	 * ��ת����¼����
	 * 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("loginUI")
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "login";
	}

	/**
	 * ��ת��ע�����
	 * 
	 * @return
	 */
	@RequestMapping("registUI")
	public String registUI() {
		return "register";
	}

	/**
	 * �ǳ�
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("logOut")
	public String logOut(HttpServletRequest req) {
		// ��ȡsession��ע��
		req.getSession().invalidate();
		return "goindex";
	}
	
	

}