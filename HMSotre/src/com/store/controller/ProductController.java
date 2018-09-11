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
	 * ��ҳ��ѯ
	 * ͨ����Ʒ����id��ѯ��Ʒ�����������ҳ��ʾ
	 * @param num
	 * @param cid
	 * @param m
	 * @return
	 */
	@RequestMapping("/findProductsByCidWithPage")
	public String findProductsByCidWithPage(@RequestParam(value = "num") int num,
			@RequestParam(value = "cid") String cid, Model m) {
		//����ҳ��ѯ�����ݱ��浽  ҳ��ģ����
		PageModel pm = ips.findProductsByCidWithPage(cid, num);
		//������ ���浽request��
		m.addAttribute("page", pm);
		return "product_list";
	}

	/**
	 * ͨ����Ʒid��ѯ��Ʒ
	 * @param pid
	 * @param m
	 * @return
	 */
	@RequestMapping("/findProductByPid")
	public String findProductByPid(@RequestParam("pid")String pid,Model m) {
		//��ѯ��Ʒ
		Product product = ips.findProductByPid(pid);
		//����Ʒ���浽request
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
