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
// @SessionAttributes ����model���浽session��
public class CartController {
	@Autowired
	private IProductService ProductService;

	@RequestMapping("/addCartItemToCart")
	public String addCartItemToCart(HttpServletRequest req) {
		// ��session��ȡ���ﳵ
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if (null == cart) {
			// �����ȡ����,�������ﳵ����,����session��
			cart = new Cart();
			req.getSession().setAttribute("cart", cart);
		}
		// �����ȡ��,ʹ�ü���
		// ��ȡ����Ʒid,����
		String pid = req.getParameter("pid");
		int num = Integer.parseInt(req.getParameter("quantity"));
		// ͨ����Ʒid��ѯ����Ʒ����
		Product product = ProductService.findProductByPid(pid);
		// ��ȡ��������Ĺ�����
		CartItem cartItem = new CartItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);
		// ���ù��ﳵ�ϵķ���
		cart.addCartItemToCar(cartItem);

		return "cart";
	}

	@RequestMapping("removeCartItem")
	public String removeCartItem(HttpServletRequest req) {
		// ��ȡ��ɾ����Ʒpid
		String pid = req.getParameter("id");
		// ��ȡ�����ﳵ
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		// ���ù��ﳵɾ���������
		cart.removeCartItem(pid);
		return "cart";
	}

	@RequestMapping("clearCart")
	public String clearCart(HttpServletRequest req) {
		//��ȡ���ﳵ
				Cart cart=(Cart)req.getSession().getAttribute("cart");
				//���ù��ﳵ�ϵ���չ��ﳵ����
				cart.clearCart();
		return "cart";
	}
	
}
