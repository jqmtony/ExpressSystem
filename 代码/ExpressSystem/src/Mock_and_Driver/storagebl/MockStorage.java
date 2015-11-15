package Mock_and_Driver.storagebl;

import client.businesslogic.storagebl.Storage;
import client.po.GoodPO;
import client.vo.Message;

public class MockStorage extends Storage {
	public void addGoods(GoodPO good,String location){
		System.out.println("A good is added into Storage!");
	}
	
	public void removeGoods(String id){
		System.out.println("A good is removed from Storage!");
	}

	public Message getGoodList() {
		return null;
	}
	
	public Message getIOHistory(String start,String end){
		return null;
	}
	
	public void changeStorage(String id,String newLocation){
		
	}
}
