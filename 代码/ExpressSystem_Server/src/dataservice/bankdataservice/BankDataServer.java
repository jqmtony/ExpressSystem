package dataservice.bankdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.BankPO;

public interface BankDataServer extends Remote {

	public BankPO find(String id) throws RemoteException;

	public void insert(BankPO po) throws RemoteException;

	public void delete(BankPO po) throws RemoteException;

	public void update(BankPO po) throws RemoteException;

	public ArrayList<BankPO> getAllBank() throws RemoteException;

}
