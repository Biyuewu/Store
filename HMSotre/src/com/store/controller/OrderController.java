package com.store.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.store.pojo.Cart;
import com.store.pojo.CartItem;
import com.store.pojo.Orderitem;
import com.store.pojo.Orders;
import com.store.pojo.PageModel;
import com.store.pojo.User;
import com.store.service.IOrdersService;
import com.store.utils.PaymentUtil;
import com.store.utils.UUIDUtils;


@Controller
public class OrderController {

	@Autowired
	private IOrdersService OrderService;

	@RequestMapping("saveOrder")
	@Transactional(rollbackFor = { Exception.class }, readOnly = false)
	public String saveOrder(HttpServletRequest req) {

		// ȷ���û���¼״̬
		User user = (User) req.getSession().getAttribute("loginUser");
		if (null == user) {
			req.setAttribute("msg", "���¼֮�����µ�");
			return "loginUI";
		}
		// ��ȡ���ﳵ
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		// ������������,Ϊ��������ֵ
		Orders order = new Orders();
		order.setOid(UUIDUtils.getCode());
		order.setOrdertime(new Date());
		order.setTotal(cart.getTotal());
		order.setState(1);
		order.setUser(user);
		order.setUid();
		// �����������ͬʱ,����������,Ϊ�����ֵ
		for (CartItem item : cart.getCartItems()) {
			Orderitem orderItem = new Orderitem();
			orderItem.setItemid(UUIDUtils.getCode());
			orderItem.setQuantity(item.getNum());
			orderItem.setTotal(item.getSubTotal());
			orderItem.setProduct(item.getProduct());
			// ���õ�ǰ�Ķ����������ĸ�����:����ĽǶ���충����Ͷ�����Ӧ��ϵ
			orderItem.setOrder(order);
			orderItem.setPid();
			orderItem.setOid();
			// ���ù���
			order.getList().add(orderItem);
			System.out.println(order);
		}

		// ����������,�û�������,���������еĶ�������ݵ���service��
		try {
			OrderService.saveOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // ����ִ������ع�
		}

		// ��չ��ﳵ
		cart.clearCart();
		// ����������req
		System.out.println(order);
		req.setAttribute("order", order);

		return "order_info";
	}

	@RequestMapping("findMyOrdersWithPage")
	public String findMyOrdersWithPage(HttpServletRequest req) {
		// ��ȡ�û���Ϣ
		User user = (User) req.getSession().getAttribute("loginUser");
		if (user != null) {
			// ��ȡ��ǰҳ
			int curNum = Integer.parseInt(req.getParameter("num"));
			// SELECT * FROM orders WHERE uid=? limit ? , ?
			// PageModel:1_��ҳ���� 2_url 3_��ǰ�û��ĵ�ǰҳ�Ķ���(����) ,ÿ�ʶ����϶�Ӧ�Ķ�����,�Լ��������Ӧ����Ʒ��Ϣ
			PageModel pm = OrderService.findMyOrdersWithPage(user, curNum);
			// ��PageModel����req
			req.setAttribute("page", pm);
			return "order_list";
		} else {
			String msg = "��¼ʧЧ�������µ�¼��";
			req.setAttribute("msg", msg);
			return "info";
		}
	}

	@RequestMapping("findOrderByOid")
	public String findOrderByOid(@RequestParam(value = "oid") String oid, Model m) {
		// ����ҵ��㹦��:���ݶ�����Ų�ѯ������Ϣ
		Orders order = OrderService.selectByPrimaryKey(oid);
		// ����������req
		m.addAttribute("order", order);
		return "order_info";
	}

	@RequestMapping("deleteOrderByOid")
	public String deleteOrderByOid(@RequestParam(value = "oid") String oid) {
		OrderService.deleteByPrimaryKey(oid);
		return "forward:/findMyOrdersWithPage?num=1";
	}

	@RequestMapping("payOrder")
	public ModelAndView payOrder(HttpServletRequest req) {

		// ��ȡ����oid,�ջ��˵�ַ,����,�绰,����
		String oid = req.getParameter("oid");
		String address = req.getParameter("address");
		String name = req.getParameter("name");
		String telephone = req.getParameter("telephone");
		String pd_FrpId = req.getParameter("pd_FrpId");
		// ���¶������ջ��˵ĵ�ַ,����,�绰
		Orders order = OrderService.selectByPrimaryKey(oid);
		order.setName(name);
		order.setTelephone(telephone);
		order.setAddress(address);
		OrderService.updateByPrimaryKey(order);
			// ���ױ�֧�����Ͳ���
			// �Ѹ�������Ҫ�Ĳ���׼����:
			String p0_Cmd = "Buy";
			// �̻����
			String p1_MerId = "10001126856";
			// �������
			String p2_Order = oid;
			// ���
			String p3_Amt = "0.01"; // order.getIntoal
			// ��������
			String p4_Cur = "CNY";
			String p5_Pid = "";
			String p6_Pcat = "";
			String p7_Pdesc = "";
			// ������Ӧ������Controller
			String p8_Url = "http://localhost:8080/HMSotre/callBack";
			String p9_SAF = "";
			String pa_MP = "";
			String pr_NeedResponse = "1";
			// ��˾����Կ
			String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
	
			// �����ױ��ļ����㷨,���������ݽ��м���,���ص���ǩ��
			String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
					p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
	
			StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
			sb.append("p0_Cmd=").append(p0_Cmd).append("&");
			sb.append("p1_MerId=").append(p1_MerId).append("&");
			sb.append("p2_Order=").append(p2_Order).append("&");
			sb.append("p3_Amt=").append(p3_Amt).append("&");
			sb.append("p4_Cur=").append(p4_Cur).append("&");
			sb.append("p5_Pid=").append(p5_Pid).append("&");
			sb.append("p6_Pcat=").append(p6_Pcat).append("&");
			sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
			sb.append("p8_Url=").append(p8_Url).append("&");
			sb.append("p9_SAF=").append(p9_SAF).append("&");
			sb.append("pa_MP=").append(pa_MP).append("&");
			sb.append("pd_FrpId=").append(pd_FrpId).append("&");
			sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
			sb.append("hmac=").append(hmac);
	
			System.out.println(sb.toString());
		// ʹ���ض���
		
		return	new ModelAndView(new RedirectView(sb.toString())); 
		//return "redirect:/" + sb.toString();
	}

	
	@RequestMapping("callBack")
	public String callBack(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		//�����ױ�֧��������
				// ��֤������Դ��������Ч��
				// �Ķ�֧���������˵��
				// System.out.println("==============================================");
				String p1_MerId = req.getParameter("p1_MerId");
				String r0_Cmd = req.getParameter("r0_Cmd");
				String r1_Code = req.getParameter("r1_Code");
				String r2_TrxId = req.getParameter("r2_TrxId");
				String r3_Amt = req.getParameter("r3_Amt");
				String r4_Cur = req.getParameter("r4_Cur");
				String r5_Pid = req.getParameter("r5_Pid");
				String r6_Order = req.getParameter("r6_Order");
				String r7_Uid = req.getParameter("r7_Uid");
				String r8_MP = req.getParameter("r8_MP");
				String r9_BType = req.getParameter("r9_BType");
				String rb_BankId = req.getParameter("rb_BankId");
				String ro_BankOrderId = req.getParameter("ro_BankOrderId");
				String rp_PayDate = req.getParameter("rp_PayDate");
				String rq_CardNo = req.getParameter("rq_CardNo");
				String ru_Trxtime = req.getParameter("ru_Trxtime");

				// hmac
				String hmac = req.getParameter("hmac");
				// ���ñ�����Կ�ͼ����㷨 ��������
				String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
				
				
				//��֤���ݺϷ���
				boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
						r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
						r8_MP, r9_BType, keyValue);
				if (isValid) {
					// ��Ч
					if (r9_BType.equals("1")) {
						// ������ض���
						
						//���֧���ɹ�,���¶���״̬
						Orders order=OrderService.selectByPrimaryKey(r6_Order);
						order.setState(2);
						OrderService.updateByPrimaryKey(order);
						//��req������ʾ��Ϣ
						req.setAttribute("msg", "֧���ɹ��������ţ�" + r6_Order + "��" + r3_Amt);
						//ת����/jsp/info.jsp
						return "info";
						
						
					} else if (r9_BType.equals("2")) {
						// �޸Ķ���״̬:
						// ��������Ե㣬�������ױ���֪ͨ
						System.out.println("�յ��ױ�֪ͨ���޸Ķ���״̬��");//
						// �ظ����ױ�success��������ظ����ױ���һֱ֪ͨ
						resp.getWriter().print("success");
					}

				} else {
					throw new RuntimeException("���ݱ��۸ģ�");
				}
				return null;
	}
	
}
