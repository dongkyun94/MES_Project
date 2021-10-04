package equipment.model;

import java.util.Date;

public class EquimentLog {
	private Integer comp_cd;
	private Integer plant_cd;
	private String line_cd;
	private String equip_cd;
	private Integer index_cd;
	private Date start_time;
	private Date end_time;
	
public EquimentLog() {
		
	}
	
	public EquimentLog(Integer comp_cd, Integer plant_cd, String line_cd, String equip_cd, Integer index_cd, 
			  Date start_time, Date end_time) {
		super();
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

	public Integer getPlant_cd() {
		return plant_cd;
	}

	public String getLine_cd() {
		return line_cd;
	}

	public String equip_cd() {
		return equip_cd;
	}
	public Integer index_cd() {
		return index_cd;
	}
	

	public Date getStart_time() {
		return start_time;
	}


	
	public Date getEnd_time() {
		return end_time;
	}

	
}



