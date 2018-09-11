package com.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.pojo.Category;
import com.store.service.impl.CategoryService;
import com.store.utils.JedisUtils;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService CategoryService;

	/**
	 * ��ѯ����������Ʒ����
	 * 
	 * @param req
	 * @return
	 */

	@RequestMapping(value = "findCategory", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findCategory(HttpServletRequest req) {
		// ��redis�л�ȡȫ��������Ϣ
		Jedis jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("allCats");
		if (null == jsonStr || "".equals(jsonStr)) {
			// ����ҵ����ȡȫ������
			List<Category> list = CategoryService.getAllCats();
			// ��ȫ������ת��ΪJSON��ʽ������
			jsonStr = JSONArray.fromObject(list).toString();
			System.out.println(jsonStr);
			// ����ȡ����JSON��ʽ�����ݴ���redis
			jedis.set("allCats", jsonStr);
			System.out.println("redis������û������");
		} else {
			// ����ҵ����ȡȫ������
			List<Category> list = CategoryService.getAllCats();
			// ��ȫ������ת��ΪJSON��ʽ������
			jsonStr = JSONArray.fromObject(list).toString();
			System.out.println(jsonStr);
			// ����ȡ����JSON��ʽ�����ݴ���redis
			jedis.set("allCats", jsonStr);
			System.out.println("redis������������");
		}
		JedisUtils.closeJedis(jedis);
		// ��ȫ��������Ϣ��Ӧ���ͻ���
		return jsonStr;
	}

}
