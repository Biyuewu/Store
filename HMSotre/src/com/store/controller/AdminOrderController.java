package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.pojo.Orderitem;
import com.store.pojo.Orders;
import com.store.pojo.PageModel;
import com.store.pojo.Product;
import com.store.service.IOrderitemService;
import com.store.service.IOrdersService;
import com.store.service.IProductService;

@Controller
@RequestMapping("/jsp/admin/")
public class AdminOrderController {

	@Autowired
	private IOrdersService OrdersService;
	@Autowired
	private IOrderitemService OrderitemService;
	@Autowired
	private IProductService ProductService;
	
	@RequestMapping("findAllOrdersByPage")
	public String findAllOrdersByPage(@RequestParam("state")int state,@RequestParam("num")int num,Model model) {
		
		PageModel pm = OrdersService.findAllOrdersByPage(num,state);
		model.addAttribute("page", pm);
		model.addAttribute("allOrders", pm.getList());
		
		return "admin/order/list";
	}
	
	@RequestMapping("findOrderitem")
	public String findOrderitem(@ModelAttribute("form")Orders orders, Model model) {
		
		String oid = orders.getOid();
		System.out.println(oid);
		List<Orderitem> list = OrderitemService.selectByOid(oid);
		
		for (Orderitem orderitem : list) {
			String pid = orderitem.getPid();
			Product product = ProductService.findProductByPid(pid);
			orderitem.setProduct(product);
		}
		
		model.addAttribute("OrderItem", list);
		return "admin/order/orderItem";
	}
}
