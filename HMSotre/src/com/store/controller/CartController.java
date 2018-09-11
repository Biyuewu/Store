package com.store.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.store.pojo.Cart;
import com.store.pojo.CartItem;
import com.store.pojo.Product;
import com.store.service.IProductService;

@Controller
// @SessionAttributes 设置model保存到session中
public class CartController {
	@Autowired
	private IProductService ProductService;

	@RequestMapping("/addCartItemToCart")
	public String addCartItemToCart(HttpServletRequest req) {
		// 从session获取购物车
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if (null == cart) {
			// 如果获取不到,创建购物车对象,放在session中
			cart = new Cart();
			req.getSession().setAttribute("cart", cart);
		}
		// 如果获取到,使用即可
		// 获取到商品id,数量
		String pid = req.getParameter("pid");
		int num = Integer.parseInt(req.getParameter("quantity"));
		// 通过商品id查询都商品对象
		Product product = ProductService.findProductByPid(pid);
		// 获取到待购买的购物项
		CartItem cartItem = new CartItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);
		// 调用购物车上的方法
		cart.addCartItemToCar(cartItem);

		return "cart";
	}

	@RequestMapping("removeCartItem")
	public String removeCartItem(HttpServletRequest req) {
		// 获取待删除商品pid
		String pid = req.getParameter("id");
		// 获取到购物车
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		// 调用购物车删除购物项方法
		cart.removeCartItem(pid);
		return "cart";
	}

	@RequestMapping("clearCart")
	public String clearCart(HttpServletRequest req) {
		//获取购物车
				Cart cart=(Cart)req.getSession().getAttribute("cart");
				//调用购物车上的清空购物车方法
				cart.clearCart();
		return "cart";
	}
	
}
