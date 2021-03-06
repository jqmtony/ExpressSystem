package businesslogic.billsbl.OrderBillServer;

import java.text.DecimalFormat;

import businesslogic.constantbl.CityDistanceServerImpl;
import businesslogic.constantbl.PriceListServerImpl;
import businesslogicservice.constantblservice.CityDistanceServer;
import businesslogicservice.constantblservice.PriceListServer;
import po.bills.OrderBill;

public class OrderBill_ChargeCalculator {
	CityDistanceServer distanceServer;
	PriceListServer price;
	
	public OrderBill_ChargeCalculator(){
		distanceServer=new CityDistanceServerImpl();
		price=new PriceListServerImpl();
	}
	
	public double calculate(OrderBill bill){
		String departure=bill.getSenderLocation().substring(0, 2);
		String destination=bill.getReceiverLocation().substring(0,2);
		
		double result=0;
		double distance=distanceServer.getDistance(departure, destination);
		//地址输入有误
		if(distance==0){
			return 0;
		}
		distance/=1000;
		
		double v=Double.valueOf(bill.getGoodSize());
		
		double weight=Double.valueOf(bill.getGoodWeight());//获得重量
		
		if(weight<v/5000){
			weight=v/5000;
		}
		
		
		String kind=bill.getKind();

		System.out.println(distance+" "+weight);
		if(kind.equals("ecnomic")){
			//经济快递
			result=weight*distance*price.getEconomicPrice();
		}else if(kind.equals("standard")){
			//标准快递
			result=weight*distance*price.getStandardPrice();
		}else if(kind.equals("express")){
			//特快专递
			result=weight*distance*price.getExpressPrice();
		}else{
			return 0;
		}
		
		double bagggingFee=Double.valueOf(bill.getBagFee());
		System.out.println(result);
		result+=bagggingFee;
		
		DecimalFormat df = new DecimalFormat("0.00");
		result=Double.valueOf(df.format(result));
		return result;
	}  

}
