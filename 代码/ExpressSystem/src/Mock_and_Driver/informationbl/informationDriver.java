package Mock_and_Driver.informationbl;

import client.businesslogicservice.informationblservice.informationServer;
import client.vo.Message;

public class informationDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Message msg=new Message();
        informationServer informationServer=new MockInformServerImpl();
        
        informationServer.addCar(msg);
        informationServer.addInstitution(msg);
        informationServer.addWorker(msg);
        
        //其余方法类似不再添加
	}

}
