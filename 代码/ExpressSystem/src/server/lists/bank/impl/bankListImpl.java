package server.lists.bank.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import client.po.BankPO;
import client.po.SystemUserPO;
import server.lists.bank.bankList;
import server.lists.system.impl.systemListImpl;

//尚未完成，BankPO未完成
public class bankListImpl implements bankList {
	final String path ="src/server/dataList/bankList.dat";
	private ArrayList<BankPO> banks;
	
	public bankListImpl() {
		load();
	}
	
	public boolean addUser(BankPO po) {
		banks.add(po);
		save();
		return true;
	}

	public BankPO find(String id) {
		
		return null;
	}

	public boolean delUser(BankPO po) {
		return false;
	}

	public boolean update(BankPO po) {
		
		return false;
	}
	
	private void save(){
		File list = new File(path);
		if(!list.exists())
			try {
				list.createNewFile();
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(list));
			oos.writeObject(banks);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void load(){
		File list = new File(path);
		if(!list.exists())
			try {
				list.createNewFile();
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(list));
			banks=(ArrayList<BankPO>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 测试用后期删除
	 */
	public void print(){
		
	}
	
	public static void main(String[] args) {
		bankList a = new bankListImpl();
		
	}

}
