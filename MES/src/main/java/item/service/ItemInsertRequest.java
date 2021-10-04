package item.service;

import java.util.Map;

public class ItemInsertRequest {
	private Integer comp_cd;
	private Integer plant_cd;
	private String acct_id;
	private Integer item_cd;
	private String item_nm;
	private String item_spec;
	private String item_spec2;
	private String item_color;
	private String cust_cd;
	private Integer acct_price;
	private String currency;
	private String unit_cd;
	private String remark;
	private String in_usr_id;
	
	public ItemInsertRequest(int comp_cd, int plant_cd, String acct_id, int item_cd, String item_nm, String item_spec,
			String item_spec2, String item_color, String cust_cd, int acct_price, String currency, String unit_cd,
			String remark, String in_usr_id) {
		super();
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.acct_id = acct_id;
		this.item_cd = item_cd;
		this.item_nm = item_nm;
		this.item_spec = item_spec;
		this.item_spec2 = item_spec2;
		this.item_color = item_color;
		this.cust_cd = cust_cd;
		this.acct_price = acct_price;
		this.currency = currency;
		this.unit_cd = unit_cd;
		this.remark = remark;
		this.in_usr_id = in_usr_id;
	}

	public int getComp_cd() {
		return comp_cd;
	}

	public void setComp_cd(int comp_cd) {
		this.comp_cd = comp_cd;
	}

	public int getPlant_cd() {
		return plant_cd;
	}

	public void setPlant_cd(int plant_cd) {
		this.plant_cd = plant_cd;
	}

	public String getAcct_id() {
		return acct_id;
	}

	public void setAcct_id(String acct_id) {
		this.acct_id = acct_id;
	}

	public int getItem_cd() {
		return item_cd;
	}

	public void setItem_cd(int item_cd) {
		this.item_cd = item_cd;
	}

	public String getItem_nm() {
		return item_nm;
	}

	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}

	public String getItem_spec() {
		return item_spec;
	}

	public void setItem_spec(String item_spec) {
		this.item_spec = item_spec;
	}

	public String getItem_spec2() {
		return item_spec2;
	}

	public void setItem_spec2(String item_spec2) {
		this.item_spec2 = item_spec2;
	}

	public String getItem_color() {
		return item_color;
	}

	public void setItem_color(String item_color) {
		this.item_color = item_color;
	}

	public String getCust_cd() {
		return cust_cd;
	}

	public void setCust_cd(String cust_cd) {
		this.cust_cd = cust_cd;
	}

	public int getAcct_price() {
		return acct_price;
	}

	public void setAcct_price(int acct_price) {
		this.acct_price = acct_price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUnit_cd() {
		return unit_cd;
	}

	public void setUnit_cd(String unit_cd) {
		this.unit_cd = unit_cd;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIn_usr_id() {
		return in_usr_id;
	}

	public void setIn_usr_id(String in_usr_id) {
		this.in_usr_id = in_usr_id;
	}

	public void validate(Map<String, Boolean> errors) {
		if(comp_cd == null) {
			errors.put("comp_cd", Boolean.TRUE);
		}
		if(plant_cd == null) {
			errors.put("plant_cd", Boolean.TRUE);
		}
		if(item_cd == null) {
			errors.put("item_cd", Boolean.TRUE);
		}
		
	}
	
}
