package server.lists.bank;

import client.po.BankPO;

public interface bankList {
	boolean addUser(BankPO po);
	
	BankPO find(String id);
	
	boolean delUser(BankPO po);
	
	boolean update(BankPO po);
}
