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
@SessionAttributes("loginUser")  //设置model保存到session中
public class UserController {

	@Autowired
	private IUserService ius;

	/**
	 * 实现登录功能
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
			// 根据用户名查询，如过有则赋给user02
			user02 = ius.userLogin(user);
			if (user02 !=null && user02.getState() == 1) {
				// 用户登录成功,将用户信息放入session中
				model.addAttribute("loginUser", user02);
				return "forward:/goIndex";
			}else {
				//进入后台管理页面
				model.addAttribute("loginUser", user02);
				return "admin/home";
			}
			
		} catch (Exception e) {
			if (username != null) {
				// 用户登录失败
				msg = e.getMessage();
				System.out.println(msg);
				// 向request放入失败的信息
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
		model.addAttribute("msg", "注册成功，请激活");
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
		model.addAttribute("msg", "激活成功");
		return "info";
	}
	
    /**
	 * 获取生成验证码显示到 UI 界面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/checkCode")
	public void checkCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		//设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
