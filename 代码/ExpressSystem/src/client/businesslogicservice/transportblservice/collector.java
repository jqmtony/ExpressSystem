package client.businesslogicservice.transportblservice;

import client.vo.Bill;
import client.vo.Message;

public interface collector {
	
	public Bill collect(Message message);
	

}
