package com.store.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.OrderitemMapper;
import com.store.dao.OrdersMapper;
import com.store.dao.ProductMapper;
import com.store.pojo.Orderitem;
import com.store.pojo.Orders;
import com.store.pojo.PageModel;
import com.store.pojo.Product;
import com.store.pojo.User;
import com.store.service.IOrdersService;


@Service
public class OrdersServic implements IOrdersService {
	@Autowired
	private OrdersMapper om;
	@Autowired
	private OrderitemMapper oim;
	@Autowired
	private ProductMapper pm;

	@Override
	public int deleteByPrimaryKey(String oid) {
		return om.deleteByPrimaryKey(oid);
	}

	@Override
	public int insert(Orders record) {
		return om.insert(record);
	}

	@Override
	public Orders selectByPrimaryKey(String oid) {
		return om.selectByPrimaryKey(oid);
	}

	@Override
	public List<Orders> selectAll() {
		return om.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Orders record) {
		return om.updateByPrimaryKey(record);
	}

	@Override
	public PageModel findMyOrdersWithPage(User user, int curNum) {
		// 1_创建PageModel对象,目的:计算并且携带分页参数
		// select count(*) from orders where uid=?
		String uid = user.getUid();
		int totalRecords = om.getTotalRecords(uid);
		PageModel p = new PageModel(curNum, totalRecords, 3);
		// 2_关联集合 select * from orders where uid=? limit ? ,?
		List<Orders> orders = om.findMyOrdersWithPage(uid, p.getStartIndex(), p.getPageSize());

		// 遍历所有订单
		for (Orders order :orders) {
			// 获取到每笔订单oid 查询每笔订单下的订单项以及订单项对应的商品信息
			String oid = order.getOid();
			List<Orderitem> orderitems = oim.selectByOid(oid);
			// 遍历list
			for (Orderitem orderItem : orderitems) {
				Product product=new Product();
				String pid = orderItem.getPid();
				product = pm.selectByPrimaryKey(pid);
				// 让每个订单项和商品发生关联关系
				orderItem.setProduct(product);
				// 将每个订单项存入订单下的集合中
				order.getList().add(orderItem);
			}
		}
		System.out.println(orders);
		p.setList(orders);
		// 3_关联url
		p.setUrl("findMyOrdersWithPage");
		return p;
	}

	@Override
	public void saveOrder(Orders order) {
		// 保存订单
		om.insert(order);
		// 保存订单项
		for (Orderitem item : order.getList()) {
			oim.insert(item);
		}
	}

	@Override
	public PageModel findAllOrdersByPage(int num, int state) {
		int totalRecords = om.findAllTotalRecordsByState(state);
		PageModel pm = new PageModel(num, totalRecords, 10);
		pm.setUrl("jsp/admin/findAllOrdersByPage?state=" + state);
		List<Orders> list = om.findMyOrdersWithPageByState(state, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		return pm;
	}

}
