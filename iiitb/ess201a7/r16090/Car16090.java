package iiitb.ess201a7.r16090;

import iiitb.ess201a7.a7base.*;

class Car16090 extends Car
{
	private Location location;
	private int Status;
	private Trip trip;
	public Car16090(int id,int speed)
	{
		super(id,speed);
		this.Status=Idle;
	}
	@java.lang.Override
	public int distSqrd(Location loc)
	{
		return ((this.location.getX()-loc.getX())*(this.location.getX()-loc.getX()))+((this.location.getY()-loc.getY())*(this.location.getY()-loc.getY()));
	}
	@java.lang.Override
	public void setLocation(Location location)
	{
		this.location=location;
	}
	@java.lang.Override
	public Location getLocation()
	{
		return this.location;
	}
	@java.lang.Override
	public void setStatus(int s)
	{
		this.Status=s;
	}
	@java.lang.Override
	public int getStatus()
	{
		return this.Status;
	}
	@java.lang.Override
	public void assignTrip(Trip trip)
	{
		this.trip=trip;
		this.Status=Booked;
	}
	@java.lang.Override
	public Trip getTrip()
	{
		return this.trip;
	}
	@java.lang.Override
	public Location getStart()
	{
		if(this.Status!=Idle)
		{
			return this.trip.getStart();
		}
		else
		{
			return null;
		}
	}
	@java.lang.Override
	public Location getDest()
	{
		if(this.Status!=Idle)
		{
			return this.trip.getDest();
		}
		else
		{
			return null;
		}
	}
	@java.lang.Override
	public void updateLocation(double deltaT)
	{
		if(this.Status==Booked)
		{
			double x_comp=this.trip.getStart().getX()-this.location.getX();
			double y_comp=this.trip.getStart().getY()-this.location.getY();
			double len=java.lang.Math.sqrt((x_comp*x_comp)+(y_comp*y_comp));
			if(distSqrd(this.trip.getStart())<getSpeed()*deltaT*getSpeed()*deltaT)
			{
				this.location=this.trip.getStart();
				this.Status=OnTrip;
			}
			else
			{
				if((this.location.getX()+((x_comp*getSpeed()*deltaT)/len))<0 && (this.location.getY()+((y_comp*getSpeed()*deltaT)/len))<0)
				{
					this.location.set(0,0);
				}
				else if((this.location.getX()+((x_comp*getSpeed()*deltaT)/len))>1000 && (this.location.getY()+((y_comp*getSpeed()*deltaT)/len))>1000)
				{
					this.location.set(1000,1000);
				}
				else if((this.location.getX()+((x_comp*getSpeed()*deltaT)/len))<0)
				{
					this.location.set(0,(int)java.lang.Math.round(this.location.getY()+((y_comp*getSpeed()*deltaT)/len)));
				}
				else if((this.location.getY()+((y_comp*getSpeed()*deltaT)/len))<0)
				{
					this.location.set((int)java.lang.Math.round(this.location.getX()+((x_comp*getSpeed()*deltaT)/len)),0);
				}
				else if((this.location.getX()+((x_comp*getSpeed()*deltaT)/len))>1000)
				{
					this.location.set(1000,(int)java.lang.Math.round(this.location.getY()+((y_comp*getSpeed()*deltaT)/len)));
				}
				else if((this.location.getY()+((y_comp*getSpeed()*deltaT)/len))>1000)
				{
					this.location.set((int)java.lang.Math.round(this.location.getX()+((x_comp*getSpeed()*deltaT)/len)),1000);
				}
				else
				{
					this.location.set((int)java.lang.Math.round(this.location.getX()+((x_comp*getSpeed()*deltaT)/len)),(int)java.lang.Math.round(this.location.getY()+((y_comp*getSpeed()*deltaT)/len)));
				}
			}
		}
		else if(this.Status==OnTrip)
		{
			double x_comp=this.trip.getDest().getX()-this.trip.getStart().getX();
			double y_comp=this.trip.getDest().getY()-this.trip.getStart().getY();
			double len=java.lang.Math.sqrt((x_comp*x_comp)+(y_comp*y_comp));
			if(distSqrd(this.trip.getDest())<getSpeed()*deltaT*getSpeed()*deltaT)
			{
				this.location=this.trip.getDest();
				this.trip=null;
				this.Status=Idle;
			}
			else
			{
				this.location.set((int)java.lang.Math.round(this.location.getX()+((x_comp*getSpeed()*deltaT)/len)),(int)java.lang.Math.round(this.location.getY()+((y_comp*getSpeed()*deltaT)/len)));
			}
		}
	}
}
