package po.bills;

import java.io.Serializable;
import java.rmi.Remote;
/**
 * 营业厅装车单的po对象
 */
import java.util.ArrayList;
import java.util.Iterator;

import po.Message;

public class HallEntruckBill implements Serializable, Remote {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6406491278553726793L;
	private String date;// 装车日期
	private String hallID;// 本营业厅编号
	private String transNum;// 汽运编号，营业厅编号6位+日期8位+5位流水号
	private String destination;// 到达地
	private String carNum;// 车辆代号
	private String supervisor;// 监装员姓名
	private String transportor;// 押运员姓名
	private ArrayList<String> orderNumbers;// 所有订单编号

	private double payment;// 运费,自动计算
	private String id;// 本单据的id，同汽运编号

	private BillApproverPO approveBill;

	public HallEntruckBill(Message billInfor, Iterator<String> orderList) {
		// TODO Auto-generated constructor stub
		this.date = billInfor.getInform(0);
		this.hallID = billInfor.getInform(1);
		this.transNum = billInfor.getInform(2);
		this.destination = billInfor.getInform(3);
		this.carNum = billInfor.getInform(4);
		this.supervisor = billInfor.getInform(5);
		this.transportor = billInfor.getInform(6);

		this.orderNumbers = new ArrayList<String>();
		while (orderList.hasNext()) {
			orderNumbers.add(orderList.next());
		}

		this.payment = 0;// 自动根据出发地目的地生成运费
		this.id = transNum;

		approveBill = new BillApproverPO();
	}
	// Getters

	public String getDate() {
		return date;
	}

	public String getHallID() {
		return hallID;
	}

	public String getTransNum() {
		return transNum;
	}

	public String getDestination() {
		return destination;
	}

	public String getCarNum() {
		return carNum;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public String getTransportor() {
		return transportor;
	}

	public Iterator<String> getOrderNum() {
		return orderNumbers.iterator();
	}

	public double getPayment() {
		return payment;
	}

	public String getId() {
		return id;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public BillApproverPO submit() {
		approveBill.setState("Submit");

		approveBill.setEaseInform(date, id, "营业厅装车单");

		approveBill.addInform("装车单编号:" + id);
		approveBill.addInform("装车日期:" + date);
		approveBill.addInform("营业厅编号:" + hallID);
		approveBill.addInform("汽运编号:" + transNum);
		approveBill.addInform("到达地:" + destination);
		approveBill.addInform("车辆编号:" + carNum);
		approveBill.addInform("装运员编号:" + supervisor);
		approveBill.addInform("押运员姓名：" + transportor);
		 approveBill.addInform("运费"+payment);
		approveBill.addInform("全部托运单条形码号：");
       
		Iterator<String> it = orderNumbers.iterator();
		while (it.hasNext()) {
			approveBill.addInform("托运单编号:" + it.next());
		}

		return approveBill;

	}
}
