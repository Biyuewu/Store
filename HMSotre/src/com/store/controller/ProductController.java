package com.store.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.pojo.PageModel;
import com.store.pojo.Product;
import com.store.service.IProductService;

@Controller
public class ProductController {
	@Autowired
	private IProductService ips;

	/**
	 * 分页查询
	 * 通过商品分类id查询商品，并将结果分页显示
	 * @param num
	 * @param cid
	 * @param m
	 * @return
	 */
	@RequestMapping("/findProductsByCidWithPage")
	public String findProductsByCidWithPage(@RequestParam(value = "num") int num,
			@RequestParam(value = "cid") String cid, Model m) {
		//将分页查询的数据保存到  页面模板中
		PageModel pm = ips.findProductsByCidWithPage(cid, num);
		//将数据 保存到request中
		m.addAttribute("page", pm);
		return "product_list";
	}

	/**
	 * 通过商品id查询商品
	 * @param pid
	 * @param m
	 * @return
	 */
	@RequestMapping("/findProductByPid")
	public String findProductByPid(@RequestParam("pid")String pid,Model m) {
		//查询产品
		Product product = ips.findProductByPid(pid);
		//将产品保存到request
		m.addAttribute("product", product);
		return "product_info";
	}
	
	@RequestMapping("/findProductLikeWithPage")
	public String findProductLikeWithPage(@Param("pname")String pname,@RequestParam("num")int num,Model m) {
		System.out.println("123:"+pname);
		PageModel pm = ips.findProductLikeWithPage(pname, num);
		m.addAttribute("page", pm);
		return "product_list";
	}
	
}
