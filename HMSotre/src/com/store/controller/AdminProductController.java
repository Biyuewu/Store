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

		// ��ȡȫ��������Ϣ
		PageModel pm = ProductService.findAllProductsByPageUp(currPage);
		model.addAttribute("pageBean", pm);
		return "admin/product/list";
	}

	@RequestMapping("findAllProductsWithPageDown")
	public String findAllProductsWithPageDown(@RequestParam("currPage") int currPage, Model model) {
		// ��ȡȫ��������Ϣ
		PageModel pm = ProductService.findAllProductsByPageDown(currPage);
		model.addAttribute("page", pm);
		return "admin/product/pushDown_list";
	}

	@RequestMapping("editUI")
	public String editUI(Model model) {
		// ��ȡȫ��������Ϣ
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
		// ��ȡȫ��������Ϣ
		List<Category> list = CategoryService.getAllCats();
		model.addAttribute("allCats", list);
		return "admin/product/add";
	}

	@RequestMapping("addProduct")
	public String addProduct(@ModelAttribute("form") Product product, HttpServletRequest request)
			throws Exception, IOException {
		// Ĭ������δ�¼���Ʒ
		product.setPflag(1);
		// ������Ʒid
		product.setPid(UUIDUtils.getId());
		// ������Ʒ����
		product.setPdate(new Date());

		// ����ǰ�����ĳ�ʼ���� CommonsMutipartResolver ���ಿ�ֽ�������
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// ���form���Ƿ���enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// ��request��ɶಿ��request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// ��ȡmultiRequest �����е��ļ���
			Iterator iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// һ�α��������ļ�
				MultipartFile file2 = multiRequest.getFile(iter.next().toString());
				if (file2 != null) {
					// ��ȡ�ļ���
					String fileName = file2.getOriginalFilename();
					// ��ȡ��׺��
					String suffix = fileName.substring(fileName.lastIndexOf("."));
					// �����ϴ�Ŀ¼��ע����������Ŀ¼
					String url2 = request.getServletContext().getRealPath("/products/");
					// ƴ��Ŀ¼
					StringBuffer path2 = new StringBuffer("");
					path2.append(url2).append(product.getCid()).append("/");
					// �ϴ�Ŀ¼���������򴴽�
					File f2 = new File(path2.toString());
					if (!f2.exists()) {
						f2.mkdirs();
					} else {
						// Ϊ�ļ���ȡid
						String imgid = UUIDUtils.getId();
						// ���ñ�����ļ���
						String img = path2.append(imgid).append(suffix).toString();
						// ����product�е��ļ�������
						product.setPimage("products/" + product.getCid() + "/" + imgid + suffix);
						// �ϴ�
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
		// 1 ��ʾ�¼� 0 ��ʾ���ϼ�
		product.setPflag(1);
		ProductService.updateByPrimaryKey(product);
		return "forward:/jsp/admin/findAllProductsWithPageUp?currPage=1";
	}

	@RequestMapping("upProduct")
	public String upProduct(@RequestParam("pid") String pid) {
		Product product = ProductService.findProductByPid(pid);
		// 1 ��ʾ�¼� 0 ��ʾ���ϼ�
		product.setPflag(0);
		ProductService.updateByPrimaryKey(product);
		return "forward:/jsp/admin/findAllProductsWithPageDown?currPage=1";
	}

}
