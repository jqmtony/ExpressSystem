package businesslogic.storagebl;

import java.util.ArrayList;
import java.util.Iterator;

import businesslogic.systembl.SystemHelper;
import businesslogicservice.storageblservice.StorageManager;
import client.RMIHelper;
import dataservice.informationdataservice.Inform_KeeperDataServer;
import dataservice.informationdataservice.Inform_StorageDataServer;
import dataservice.transportdataservice.TransportDataServer;
import po.GoodPO;
import po.Institution.StoragePO;
import po.Institution.storageAssist.Record;
import po.Institution.storageAssist.StorageInfo;
import po.Institution.storageAssist.StoreList;

/**
 * 
 * @author nick
 * 
 */
public class StorageManagerImpl implements StorageManager {
	Inform_StorageDataServer storageServer;
	TransportDataServer goodServer;

	Inform_KeeperDataServer keeperServer;

	String storageID;

	public StorageManagerImpl(String storageID) {

		// RMI
		this.storageID = storageID;
		storageServer = RMIHelper.getStorageData();
		goodServer = RMIHelper.getTransportData();
		keeperServer = RMIHelper.getKeeperData();

	}

	public StorageManagerImpl() {
		// RMI
		storageServer = RMIHelper.getStorageData();
		goodServer = RMIHelper.getTransportData();
		keeperServer = RMIHelper.getKeeperData();

		storageID = keeperServer.find(SystemHelper.getUserID()).getStorage()
				.getID();// 一个可怕的级联调用

	}

	@Override
	public String ImportGood(String goodID, String location, String date) {
		StoragePO storage = storageServer.find(storageID);
		GoodPO good = goodServer.find(goodID);
		try {
			String result = storage.importGood(good, location, date);
			if (result.equals("入库成功")) {
				storageServer.update(storage);
			}
			return result;
		} catch (NullPointerException e) {
			if (storage == null) {
				System.out.println("该仓库不存在");
				return "该仓库不存在";
			}
			if (good == null) {
				System.out.println("该货物不存在");
				return "该货物不存在";

			}
			return "入库失败";
		}

	}

	@Override
	public boolean ExportGood(String ID, String date) {
		StoragePO storage = storageServer.find(storageID);
		String location = storage.getLocation(ID);
		if (location != "null") {
			ExportGood(ID, location, date);
			return true;
		} else {
			// id对应的货物不在
			return false;
		}
	}

	public boolean ExportGood(String goodID, String location, String date) {
		StoragePO storage = storageServer.find(storageID);
		GoodPO good = goodServer.find(goodID);

		try {
			if (storage.exportGood(good, location, date)) {
				storageServer.update(storage);
				return true;
			}
			return false;
		} catch (NullPointerException e) {
			if (storage == null)
				System.out.println("该仓库不存在");
			if (good == null)
				System.out.println("该货物不存在");
			return false;
		}
	}

	@Override
	public StorageInfo[] getGoodsList(int area, int row, int shelf) {
		StoragePO storage = storageServer.find(storageID);
		return storage.getStorageInfo(area, row, shelf);
	}

	@Override
	public Iterator<Record> getStorageHistory(String startTime, String endTime) {
		StoragePO storage = storageServer.find(storageID);
		return storage.getIORecord(startTime, endTime).iterator();
	}

	@Override
	public boolean changeStorage(String oldLocation, String newLocation) {
		StoragePO storage = storageServer.find(storageID);
		if (storage.change(oldLocation, newLocation)) {
			storageServer.update(storage);
			return true;
		}
		return false;
	}

	public ArrayList<StoreList> getList() {
		StoragePO storage = storageServer.find(storageID);
		return storage.getAllList();
	}

}
