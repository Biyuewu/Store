package com.store.pojo;

public class Category {

    private String cid;
    private String cname;
    
    public Category() {
		// TODO Auto-generated constructor stub
	}
	public Category(String cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	

    /**
     * @return the value of category.cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid the value for category.cid
     */
    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    /**
     * @return the value of category.cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname the value for category.cname
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}