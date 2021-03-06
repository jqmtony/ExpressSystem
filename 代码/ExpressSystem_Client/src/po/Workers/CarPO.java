package po.Workers;

import java.io.Serializable;
import java.rmi.Remote;

import po.Institution.HallPO;

/**
 * 车辆的PO对象
 * 
 * @author rabook
 *
 */
public class CarPO implements Serializable, Remote {

	private String id;// 车辆编号，营业厅编号6位+3位流水号

	private String ChePai;// 车牌号

	private String UsingTime;// 服役时间

	private HallPO hall;// 所属营业厅

	public CarPO(String id, String ChePai, String UsingTime, HallPO hall) {
		this.id = id;
		this.ChePai = ChePai;
		this.UsingTime = UsingTime;
		this.hall = hall;
	}

	// Setters and Getters
	public String getChePai() {
		return ChePai;
	}

	public String getId() {
		return id;
	}

	public void setChePai(String chePai) {
		ChePai = chePai;
	}

	public String getUsingTime() {
		return UsingTime;
	}

	public void setUsingTime(String usingTime) {
		UsingTime = usingTime;
	}

	public void setHall(HallPO hall) {
		this.hall = hall;
	}

	public HallPO getHall() {
		return hall;
	}
}
