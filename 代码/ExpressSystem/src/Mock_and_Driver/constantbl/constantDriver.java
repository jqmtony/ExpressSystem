package Mock_and_Driver.constantbl;

import client.businesslogicservice.constantblservice.constantServer;
import client.vo.Message;

public class constantDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Message msg=new Message();
		
        constantServer constantServer=new MockConstantServerImpl();
        
        constantServer.setConstant(msg);
        constantServer.getConstant("123");
	}

}
