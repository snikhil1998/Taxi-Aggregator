package iiitb.ess201a7.r16090;

import iiitb.ess201a7.a7base.*;

public class Fleet16090 extends Fleet
{
	private int lc=4;
	private java.util.ArrayList<Car> cars_list=new java.util.ArrayList<Car>();
	public Fleet16090(String colour)
	{
		super(6090,colour);
	}
	@java.lang.Override
	public void addCar(int speed)
	{
		Car16090 car=new Car16090(getId(),speed);
		//car.setLocation(new Location(this.lc*2,this.lc*3));
		this.cars_list.add(car);
		this.lc+=3;
	}
	@java.lang.Override
	public Car findNearestCar(Location loc)
	{
		Car temp_car=this.cars_list.get(0);
		for(Car car:this.cars_list)
		{
			if((java.lang.Math.pow(loc.getX()-car.getLocation().getX(),2)+java.lang.Math.pow(loc.getY()-car.getLocation().getY(),2)<java.lang.Math.pow(loc.getX()-temp_car.getLocation().getX(),2)+java.lang.Math.pow(loc.getY()-temp_car.getLocation().getY(),2)) && car.getStatus()==car.Idle)
			{
				temp_car=car;
			}
		}
		return temp_car;
	}
	@java.lang.Override
	public java.util.ArrayList<Car> getCars()
	{
		return this.cars_list;
	}
}
