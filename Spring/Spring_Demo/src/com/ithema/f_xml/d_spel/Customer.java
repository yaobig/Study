package com.ithema.f_xml.d_spel;

public class Customer {
	private String cname = "jake";
	private Double pi;//= Math.PI;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Double getPi() {
		return pi;
	}

	public void setPi(Double pi) {
		this.pi = pi;
	}

	@Override
	public String toString() {
		return "Customer [cname=" + cname + ", pi=" + pi + "]";
	}
	
}
