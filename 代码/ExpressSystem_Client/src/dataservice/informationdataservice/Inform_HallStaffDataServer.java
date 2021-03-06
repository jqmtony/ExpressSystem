package dataservice.informationdataservice;

import java.rmi.Remote;

import po.Workers.HallStaffPO;

/**
 * 营业厅业务员的数据层交互接口 增删改查
 * 
 * @author rabook
 *
 */
public interface Inform_HallStaffDataServer extends Remote {

	public void addStaff(HallStaffPO staff);

	public HallStaffPO find(String id);

	public void deleteStaff(HallStaffPO staff);

	public void update(HallStaffPO staff);

}
