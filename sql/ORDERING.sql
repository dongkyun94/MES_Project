create table ordering 
(
	comp_cd  number(10) not null,
	plant_cd number(10) not null,
	order_no varchar2(20) not null,
	order_dt date,
	item_cd number(10),
	barcode varchar2(50),
	manu_no varchar2(50),
	delivery_dt date not null,
	order_qty number(10),
	order_status varchar2(10),
	remark varchar2(255),
	finished_id varchar2(30),
	finished_dt date
	);
	