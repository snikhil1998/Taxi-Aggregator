import iiitb.ess201a7.a7base.Car;
import iiitb.ess201a7.a7base.Fleet;
import iiitb.ess201a7.a7base.Trip;

public class Platform
{
	private java.util.ArrayList<Fleet> fleets_list;
	public Platform()
	{
		this.fleets_list=new java.util.ArrayList<Fleet>();
	}
	public void addFleet(Fleet fleet)
	{
		this.fleets_list.add(fleet);
	}
	public java.util.ArrayList<Fleet> getFleetsList()
	{
		return this.fleets_list;
	}
	public Car assignCar(Trip trip)
	{
		java.util.ArrayList<Car> templist=new java.util.ArrayList<Car>();
		for(Fleet fleet:this.fleets_list)
		{
			templist.add(fleet.findNearestCar(trip.getStart()));
		}
		Car temp_car=templist.get(0);
		for(Car car:templist)
		{
			if(car.distSqrd(trip.getStart())<temp_car.distSqrd(trip.getStart()) && car.getStatus()==car.Idle)
			{
				temp_car=car;
			}
		}
		temp_car.assignTrip(trip);
		return temp_car;
	}
	public java.util.ArrayList<Car> findCars()
	{
		java.util.ArrayList<Car> cars_list=new java.util.ArrayList<Car>();
		for(int i=0;i<this.fleets_list.size();i++)
		{
			for(Car car:this.fleets_list.get(i).getCars())
			{
				cars_list.add(car);
			}
		}
		return cars_list;
	}
}
