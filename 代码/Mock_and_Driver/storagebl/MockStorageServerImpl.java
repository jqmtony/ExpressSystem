package Mock_and_Driver.storagebl;

import po.GoodPO;
import po.Message;
import vo.BillVO;
import vo.StorageVO;
import businesslogic.storagebl.Storage;
import businesslogic.storagebl.StorageServerImpl;

public class MockStorageServerImpl extends StorageServerImpl{

	Storage storage=new MockStorage();
	public BillVO makeImportBill(Message message) {
		// TODO Auto-generated method stub
		storage.addGoods(new GoodPO("1", "1", "1"), "1");
		System.out.println("Import Bill is made!");
		return null;
	}

	public BillVO makeExportBill(Message message) {
		// TODO Auto-generated method stub
		storage.removeGoods("1");
		System.out.println("Export Bill is made!");
		return null;
	}

	public StorageVO getGoodsList() {
		// TODO Auto-generated method stub
		System.out.println("Goods List is got!");
		return null;
	}

	public Message getStorageHistory(String startTim, String endTime) {
		// TODO Auto-generated method stub
		System.out.println("Storage History is got!");
		return null;
	}

	public Message changeStorage(String id, String newLocation) {
		// TODO Auto-generated method stub
		System.out.println("Storage is changed!");
		return null;
	}
}
