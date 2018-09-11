package com.store.pojo;

public class Orderitem {
	private String itemid; // id
	private int quantity; // ����
	private double total; // С��
	private String pid;
	private String oid;

	// 1_�����Ӧ����
	// 2_product,orderЯ�����������
	private Product product;
	private Orders order;

	public String getPid() {
		return pid;
	}

	public void setPid() {
		this.pid = product.getPid();
	}

	public String getOid() {
		return oid;
	}

	public void setOid() {
		this.oid = order.getOid();
	}

	@Override
	public String toString() {
		return "Orderitem [itemid=" + itemid + ", quantity=" + quantity + ", total=" + total + ", pid=" + pid + ", oid="
				+ oid + ", product=" + product + "]";
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

}