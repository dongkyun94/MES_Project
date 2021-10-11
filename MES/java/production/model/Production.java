package production.model;

import java.util.Date;

public class Production {
	
	private Integer comp_cd;
	private Integer plant_cd;
	private String order_no;
	private String wo_no;
	private String line_cd;
	private String equip_cd;
	private Date start_dt;
	private String start_shift;
	private Date end_dt;
	private String end_shift;
	private String flag_end;
	private Integer plan_qty;
	private String remark;
	
	public Production() {};
	
	public Production(Integer comp_cd, Integer plant_cd, String order_no, String wo_no, String line_cd, String equip_cd,
			Date start_dt, String start_shift, Date end_dt, String end_shift, String flag_end, Integer plan_qty,
			String remark) {
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.order_no = order_no;
		this.wo_no = wo_no;
		this.line_cd = line_cd;
		this.equip_cd = equip_cd;
		this.start_dt = start_dt;
		this.start_shift = start_shift;
		this.end_dt = end_dt;
		this.end_shift = end_shift;
		this.flag_end = flag_end;
		this.plan_qty = plan_qty;
		this.remark = remark;
	}

	public Integer getComp_cd() {
		return comp_cd;
	}

	public Integer getPlant_cd() {
		return plant_cd;
	}

	public String getOrder_no() {
		return order_no;
	}

	public String getWo_no() {
		return wo_no;
	}

	public String getLine_cd() {
		return line_cd;
	}

	public String getEquip_cd() {
		return equip_cd;
	}

	public Date getStart_dt() {
		return start_dt;
	}

	public String getStart_shift() {
		return start_shift;
	}

	public Date getEnd_dt() {
		return end_dt;
	}

	public String getEnd_shift() {
		return end_shift;
	}

	public String getFlag_end() {
		return flag_end;
	}

	public Integer getPlan_qty() {
		return plan_qty;
	}

	public String getRemark() {
		return remark;
	}
	
	

}
