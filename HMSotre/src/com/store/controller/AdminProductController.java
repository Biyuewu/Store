package com.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.store.pojo.Category;
import com.store.pojo.PageModel;
import com.store.pojo.Product;
import com.store.service.ICategoryService;
import com.store.service.IProductService;
import com.store.utils.UUIDUtils;

@Controller
@RequestMapping("/jsp/admin/")
public class AdminProductController {

	@Autowired
	private IProductService ProductService;
	@Autowired
	private ICategoryService CategoryService;

	@RequestMapping("findAllProductsWithPageUp")
	public String findAllProductsWithPageUp(@RequestParam("currPage") int currPage, Model model) {

		// 获取全部分类信息
		PageModel pm = ProductService.findAllProductsByPageUp(currPage);
		model.addAttribute("pageBean", pm);
		return "admin/product/list";
	}

	@RequestMapping("findAllProductsWithPageDown")
	public String findAllProductsWithPageDown(@RequestParam("currPage") int currPage, Model model) {
		// 获取全部分类信息
		PageModel pm = ProductService.findAllProductsByPageDown(currPage);
		model.addAttribute("page", pm);
		return "admin/product/pushDown_list";
	}

	@RequestMapping("editUI")
	public String editUI(Model model) {
		// 获取全部分类信息
		List<Category> list = CategoryService.getAllCats();
		model.addAttribute("allCats", list);
		return "admin/product/edit";
	}

	@RequestMapping("editProduct")
	public String editProduct(@ModelAttribute("form") Product product) {
		product.setPflag(0);
		product.setPdate(new Date());
		ProductService.updateByPrimaryKey(product);
		return "forward:/jsp/admin/findAllProductsWithPageUp?currPage=1";
	}

	@RequestMapping("addUI")
	public String addUI(Model model) {
		// 获取全部分类信息
		List<Category> list = CategoryService.getAllCats();
		model.addAttribute("allCats", list);
		return "admin/product/add";
	}

	@RequestMapping("addProduct")
	public String addProduct(@ModelAttribute("form") Product product, HttpServletRequest request)
			throws Exception, IOException {
		// 默认设置未下架商品
		product.setPflag(1);
		// 设置商品id
		product.setPid(UUIDUtils.getId());
		// 设置商品日期
		product.setPdate(new Date());

		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file2 = multiRequest.getFile(iter.next().toString());
				if (file2 != null) {
					// 获取文件名
					String fileName = file2.getOriginalFilename();
					// 获取后缀名
					String suffix = fileName.substring(fileName.lastIndexOf("."));
					// 设置上传目录，注意配置虚拟目录
					String url2 = request.getServletContext().getRealPath("/products/");
					// 拼接目录
					StringBuffer path2 = new StringBuffer("");
					path2.append(url2).append(product.getCid()).append("/");
					// 上传目录，不存在则创建
					File f2 = new File(path2.toString());
					if (!f2.exists()) {
						f2.mkdirs();
					} else {
						// 为文件获取id
						String imgid = UUIDUtils.getId();
						// 设置保存的文件名
						String img = path2.append(imgid).append(suffix).toString();
						// 设置product中的文件名属性
						product.setPimage("products/" + product.getCid() + "/" + imgid + suffix);
						// 上传
						file2.transferTo(new File(img));
					}
				}

			
			}

		}

		ProductService.addProduct(product);
		return "forward:/jsp/admin/findAllProductsWithPageDown?currPage=1";
	}

	@RequestMapping("downProduct")
	public String downProduct(@RequestParam("pid") String pid) {
		Product product = ProductService.findProductByPid(pid);
		// 1 表示下架 0 表示已上架
		product.setPflag(1);
		ProductService.updateByPrimaryKey(product);
		return "forward:/jsp/admin/findAllProductsWithPageUp?currPage=1";
	}

	@RequestMapping("upProduct")
	public String upProduct(@RequestParam("pid") String pid) {
		Product product = ProductService.findProductByPid(pid);
		// 1 表示下架 0 表示已上架
		product.setPflag(0);
		ProductService.updateByPrimaryKey(product);
		return "forward:/jsp/admin/findAllProductsWithPageDown?currPage=1";
	}

}
