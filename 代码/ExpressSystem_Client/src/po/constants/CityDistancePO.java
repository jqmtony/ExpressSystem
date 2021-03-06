package po.constants;

import java.io.Serializable;
import java.rmi.Remote;

public class CityDistancePO implements Serializable,Remote{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7457460054259364116L;
	String city1;
	String city2;
	
	double distance;
	
	public CityDistancePO(String city1,String city2,double distance){
		this.city1=city1;
		this.city2=city2;
		this.distance=distance;
		
	}
	
	public double getDistance(){
		return distance;
	}
	
	public void setDistance(String distance){
		this.distance=Double.valueOf(distance);
	}
	
	public String getCity1(){
		return city1;
	}
	
	public String getCity2(){
		return city2;
	}
	
	/**
	 * 这个方法通过输入的城市判断是否是指的该对象
	 * 城市前后不影响结果
	 * @param city1
	 * @param city2
	 * @return
	 */
	public boolean isThis(String city1,String city2){
		if((this.city1.equals(city1)&&this.city2.equals(city2))
				||(this.city1.equals(city2)&&this.city2.equals(city1))){
			return true;
		}else
			return false;
	}
}
