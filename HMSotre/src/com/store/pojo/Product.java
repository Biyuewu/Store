package com.store.pojo;

import java.util.Date;

/**
 * @mbg.generated Thu Aug 16 14:56:43 CST 2018
 */

public class Product {
	/**
	 * ��Ʒ���
	 */
	private String pid;

	/**
	 * ��Ʒ����
	 */
	private String pname;

	/**
	 * ��Ʒ�г��۸�
	 */
	private Double marketPrice;

	/**
	 * ��Ʒ�̳��۸�
	 */
	private Double shopPrice;

	/**
	 * ��ƷͼƬ·��
	 */
	private String pimage;

	/**
	 * ��Ʒ�ϼ�����
	 */
	private Date pdate;

	/**
	 * ��Ʒ�Ƿ�����
	 */
	private Integer isHot;

	/**
	 * ��Ʒ����
	 */
	private String pdesc;

	/**
	 * ��Ʒ�Ƿ��ڻ����� 0:�ڻ����� 1:�¼�
	 */
	private Integer pflag;

	/**
	 * ��Ʒ���ڷ���id
	 */
	private String cid;

	/**
	 * @return the value of product.pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the value for product.pid
	 */
	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	/**
	 * @return the value of product.pname
	 */
	public String getPname() {
		return pname;
	}

	/**
	 * @param pname
	 *            the value for product.pname
	 */
	public void setPname(String pname) {
		this.pname = pname == null ? null : pname.trim();
	}

	/**
	 * @return the value of product.market_price
	 */
	public Double getMarketPrice() {
		return marketPrice;
	}

	/**
	 * @param marketPrice
	 *            the value for product.market_price
	 */
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	/**
	 * @return the value of product.shop_price
	 */
	public Double getShopPrice() {
		return shopPrice;
	}

	/**
	 * @param shopPrice
	 *            the value for product.shop_price
	 */
	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	/**
	 * @return the value of product.pimage
	 */
	public String getPimage() {
		return pimage;
	}

	/**
	 * @param pimage
	 *            the value for product.pimage
	 */
	public void setPimage(String pimage) {
		this.pimage = pimage == null ? null : pimage.trim();
	}

	/**
	 * @return the value of product.pdate
	 */
	public Date getPdate() {
		return pdate;
	}

	/**
	 * @param pdate
	 *            the value for product.pdate
	 */
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	/**
	 * @return the value of product.is_hot
	 */
	public Integer getIsHot() {
		return isHot;
	}

	/**
	 * @param isHot
	 *            the value for product.is_hot
	 */
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	/**
	 * @return the value of product.pdesc
	 */
	public String getPdesc() {
		return pdesc;
	}

	/**
	 * @param pdesc
	 *            the value for product.pdesc
	 */
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc == null ? null : pdesc.trim();
	}

	/**
	 * @return the value of product.pflag
	 */
	public Integer getPflag() {
		return pflag;
	}

	/**
	 * @param pflag
	 *            the value for product.pflag
	 */
	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}

	/**
	 * @return the value of product.cid
	 */
	public String getCid() {
		return cid;
	}

	/**
	 * @param cid
	 *            the value for product.cid
	 */
	public void setCid(String cid) {
		this.cid = cid == null ? null : cid.trim();
	}
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(String pid, String pname, double marketPrice, double shopPrice, String pimage, Date pdate,
			int isHot, String pdesc, int pflag, String cid) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.marketPrice = marketPrice;
		this.shopPrice = shopPrice;
		this.pimage = pimage;
		this.pdate = pdate;
		this.isHot = isHot;
		this.pdesc = pdesc;
		this.pflag = pflag;
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", marketPrice=" + marketPrice + ", shopPrice=" + shopPrice
				+ ", pimage=" + pimage + ", pdate=" + pdate + ", isHot=" + isHot + ", pdesc=" + pdesc + ", pflag="
				+ pflag + ", cid=" + cid + "]";
	}

}