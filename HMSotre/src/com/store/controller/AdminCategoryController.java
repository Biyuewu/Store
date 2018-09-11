package com.store.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.pojo.Category;
import com.store.pojo.PageModel;
import com.store.service.ICategoryService;
import com.store.utils.UUIDUtils;

@Controller
@RequestMapping("/jsp/admin/")
public class AdminCategoryController {

	@Autowired
	private ICategoryService CategoryService;

//	@RequestMapping("findAllCats")
//	public String findAllCats(Model model) {
//		// 获取全部分类信息
//		List<Category> list = CategoryService.getAllCats();
//		model.addAttribute("allCats", list);
//		System.out.println(list);
//		return "admin/category/list";
//	}

	@RequestMapping("findAllCatsByPage")
	public String findAllCatsByPage(@RequestParam("currPage")int currPage,Model model) {
		// 获取全部分类信息
		PageModel pm = CategoryService.findAllCatsByPage(currPage);
		model.addAttribute("pageBean", pm);
		model.addAttribute("allCats", pm.getList());
		return "admin/category/list";
	}
	
	@RequestMapping("deleteCategory")
	public String deleteCategory(@RequestParam(value = "cid") String cid) {
		// System.out.println(cid);
		int result = CategoryService.deleteCategory(cid);
		// System.out.println(result);
		return "forward:/admin/findAllCatsByPage?currPage=1";
	}

	@RequestMapping("addCategory")
	public String addCategory(@Param("cname") String cname) {
		// 创建分类ID
		String id = UUIDUtils.getId();
		Category c = new Category();
		c.setCid(id);
		c.setCname(cname);
		// 调用业务层添加分类功能
		int result = CategoryService.addCategory(c);
		System.out.println(result);
		return "forward:/admin/findAllCatsByPage?currPage=1";
	}

	@RequestMapping("editCategory")
	public String editCategory(@Param("cname") String cname, @RequestParam("cid") String cid) {
		// 调用业务层添加分类功能
		Category category = new Category(cid, cname);
		int result = CategoryService.updateByPrimaryKey(category);
		System.out.println(result);
		return "forward:/admin/findAllCatsByPage?currPage=1";
	}

}
