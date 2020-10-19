package com.query.example.querymodel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	private String productid;
	private String productname;
	private boolean isSaleable;

	public String getProductname() {
		return productname;
	}

	public boolean isSaleable() {
		return isSaleable;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public void setSaleable(boolean isSaleable) {
		this.isSaleable = isSaleable;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public Product(String productid, String productname, boolean isSaleable) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.isSaleable = isSaleable;
	}
	
	public Product() {
		
	}

	
}
