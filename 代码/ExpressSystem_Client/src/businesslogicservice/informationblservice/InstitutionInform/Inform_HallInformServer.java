package businesslogicservice.informationblservice.InstitutionInform;

import java.util.Iterator;

import po.Institution.HallPO;
import po.Workers.HallStaffPO;

public interface Inform_HallInformServer {

	public HallPO addHall(String place,String name);

	public boolean removeHall(String id);

	public boolean addStaff(String HallID, String StaffID);

	public boolean removeStaff(String HallID, String StaffID);

	public Iterator<HallStaffPO> getAllStaff(String HallID);

	public Iterator<HallPO> getByLocation(String place);
	
	public HallPO getHall(String id);
}
