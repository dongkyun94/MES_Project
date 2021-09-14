package order.service;

import java.util.Map;

import order.model.Order;

public class OrderInsertRequest {
	
	private Order order;
	
	public OrderInsertRequest(Order order) {
		this.order = order;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(order.getComp_cd() == null) {
			errors.put("comp_cd", Boolean.TRUE);
		}
		if(order.getPlant_cd() == null) {
			errors.put("plant_cd", Boolean.TRUE);
		}
		if(order.getOrder_no() == null) {
			errors.put("order_no", Boolean.TRUE);
		}
	}

}
