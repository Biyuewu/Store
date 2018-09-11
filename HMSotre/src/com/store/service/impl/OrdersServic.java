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
		// 1_����PageModel����,Ŀ��:���㲢��Я����ҳ����
		// select count(*) from orders where uid=?
		String uid = user.getUid();
		int totalRecords = om.getTotalRecords(uid);
		PageModel p = new PageModel(curNum, totalRecords, 3);
		// 2_�������� select * from orders where uid=? limit ? ,?
		List<Orders> orders = om.findMyOrdersWithPage(uid, p.getStartIndex(), p.getPageSize());

		// �������ж���
		for (Orders order :orders) {
			// ��ȡ��ÿ�ʶ���oid ��ѯÿ�ʶ����µĶ������Լ��������Ӧ����Ʒ��Ϣ
			String oid = order.getOid();
			List<Orderitem> orderitems = oim.selectByOid(oid);
			// ����list
			for (Orderitem orderItem : orderitems) {
				Product product=new Product();
				String pid = orderItem.getPid();
				product = pm.selectByPrimaryKey(pid);
				// ��ÿ�����������Ʒ����������ϵ
				orderItem.setProduct(product);
				// ��ÿ����������붩���µļ�����
				order.getList().add(orderItem);
			}
		}
		System.out.println(orders);
		p.setList(orders);
		// 3_����url
		p.setUrl("findMyOrdersWithPage");
		return p;
	}

	@Override
	public void saveOrder(Orders order) {
		// ���涩��
		om.insert(order);
		// ���涩����
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
