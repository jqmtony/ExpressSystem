package client.businesslogicservice.billsblservice;

import client.po.BillPO;
import client.vo.BillVO;
/**
 * 该接口提供利用单据编号查询单据的服务
 * @author Ma
 *
 */

public interface billFinder {
	
	public BillPO findBill(String id); 

}
