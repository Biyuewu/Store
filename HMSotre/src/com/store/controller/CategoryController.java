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
	 * 查询导航栏的商品分类
	 * 
	 * @param req
	 * @return
	 */

	@RequestMapping(value = "findCategory", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findCategory(HttpServletRequest req) {
		// 在redis中获取全部分类信息
		Jedis jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("allCats");
		if (null == jsonStr || "".equals(jsonStr)) {
			// 调用业务层获取全部分类
			List<Category> list = CategoryService.getAllCats();
			// 将全部分类转换为JSON格式的数据
			jsonStr = JSONArray.fromObject(list).toString();
			System.out.println(jsonStr);
			// 将获取到的JSON格式的数据存入redis
			jedis.set("allCats", jsonStr);
			System.out.println("redis缓存中没有数据");
		} else {
			// 调用业务层获取全部分类
			List<Category> list = CategoryService.getAllCats();
			// 将全部分类转换为JSON格式的数据
			jsonStr = JSONArray.fromObject(list).toString();
			System.out.println(jsonStr);
			// 将获取到的JSON格式的数据存入redis
			jedis.set("allCats", jsonStr);
			System.out.println("redis缓存中有数据");
		}
		JedisUtils.closeJedis(jedis);
		// 将全部分类信息响应到客户端
		return jsonStr;
	}

}
