package businesslogicservice.transportblservice;

import po.bills.TransArrivalBill;

public interface Trans_TransArrivalServer {

	public TransArrivalBill makeBill(String tranStationID,String GoodID,String hallID,String date, String transOrderNum, String departure, String state);
}
