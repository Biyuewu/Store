package com.store.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.store.pojo.User;
import com.store.service.IUserService;
import com.store.utils.MailUtils;
import com.store.utils.MyBeanUtils;
import com.store.utils.RandomValidateCode;
import com.store.utils.UUIDUtils;


@Controller
@SessionAttributes("loginUser")  //����model���浽session��
public class UserController {

	@Autowired
	private IUserService ius;

	/**
	 * ʵ�ֵ�¼����
	 * 
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping("UserLogin")
	public String userLogin(String username, String password,Model model) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User user02 = null;
		String msg = null;
		try {
			// �����û�����ѯ��������򸳸�user02
			user02 = ius.userLogin(user);
			if (user02 !=null && user02.getState() == 1) {
				// �û���¼�ɹ�,���û���Ϣ����session��
				model.addAttribute("loginUser", user02);
				return "forward:/goIndex";
			}else {
				//�����̨����ҳ��
				model.addAttribute("loginUser", user02);
				return "admin/home";
			}
			
		} catch (Exception e) {
			if (username != null) {
				// �û���¼ʧ��
				msg = e.getMessage();
				System.out.println(msg);
				// ��request����ʧ�ܵ���Ϣ
				model.addAttribute("msg", msg);
			}
			return "info";
		}
	}

	@RequestMapping("UserRegist")
	public String userRegist(HttpServletRequest request, Model model) throws Exception {
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		
		MyBeanUtils.populate(user, map);
		user.setUid(UUIDUtils.getId());
		user.setCode(UUIDUtils.getCode());
		user.setState(0);
		System.out.println(user);
		MailUtils.sendMail(user.getEmail(), user.getCode());
		model.addAttribute("msg", "ע��ɹ����뼤��");
		ius.userRegist(user);
		/*return ius.userRegist(user) > 0 ? "ok" : "error";*/
		return "info";
	}
	
	@RequestMapping("active")
	public String active(@RequestParam("code")String code,Model model) {
		System.out.println(code);
		User user = ius.findUserByCode(code);
		user.setState(1);
		user.setCode(null);
		ius.updateByPrimaryKey(user);
		model.addAttribute("msg", "����ɹ�");
		return "info";
	}
	
    /**
	 * ��ȡ������֤����ʾ�� UI ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/checkCode")
	public void checkCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		//������Ӧ����,������������������ΪͼƬ
        response.setContentType("image/jpeg");
        
        //������Ӧͷ��Ϣ�������������Ҫ���������
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//���ͼƬ����
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
