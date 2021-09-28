package order.service;


public class ModifyStatusRequest {
	
	private String order_no;
	private String order_status;
	
	public ModifyStatusRequest(String order_no, String order_status) {
		this.order_no = order_no;
		this.order_status = order_status;
	}

	public String getOrder_no() {
		return order_no;
	}

	public String getOrder_status() {
		return order_status;
	}


}
