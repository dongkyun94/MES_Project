package equipment.service;

import java.util.Date;
import java.util.Map;

public class EqLogInsertRequest {

	private Integer comp_cd;
	private Integer plant_cd;
	private String line_cd;
	private String equip_cd;
	private Integer index_cd;
	private Date start_time;
	private Date end_time;
	
	public EqLogInsertRequest(Integer comp_cd, Integer plant_cd, String line_cd, String equip_cd, Integer index_cd, 
			  Date start_time, Date end_time) {
		
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.line_cd = line_cd;
		this.equip_cd = equip_cd;
		this.index_cd = index_cd;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	

	
	public Integer getComp_cd() {
		return comp_cd;
	}
	public void setComp_cd(Integer comp_cd) {
		this.comp_cd = comp_cd;
	}


	public Integer getPlant_cd() {
		return plant_cd;
	}
	public void setPlant_cd(Integer plant_cd) {
		this.plant_cd = plant_cd;
	}


	public String getLine_cd() {
		return line_cd;
	}
	public void setLine_cd(String line_cd) {
		this.line_cd = line_cd;
	}


	public String getequip_cd() {
		return equip_cd;
	}
	public void setEquip_cd(String equip_cd) {
		this.equip_cd = equip_cd;
	}


	public Integer getIndex_cd() {
		return index_cd;
	}
	public void setindex_cd(Integer index_cd) {
		this.index_cd = index_cd;
	}
	
	

	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	
	public void validate(Map<String, Boolean> errors) {
		if(comp_cd == null) {
			errors.put("comp_cd", Boolean.TRUE);
		}
		if(plant_cd == null) {
			errors.put("plant_cd", Boolean.TRUE);
		}
		if(line_cd == null) {
			errors.put("line_cd", Boolean.TRUE);
		}
		
	}

}


