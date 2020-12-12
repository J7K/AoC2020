package net.j7k.aoc2020;


public class Ship2 
{	
	int x=0;
	int y=0;
	
	int wayX = 0;
	int wayY =0;
	
	void rotateWaypoint(int heading)
	{
		int temp;
		switch(heading)
		{
		case 90:
			temp = wayY;
			wayY = -wayX;
			wayX = temp;
			break;
		case 180:
			wayX = -wayX;
			wayY = -wayY;
			break;
		case 270:
			temp = wayY;
			wayY = wayX;
			wayX = -temp;
			break;
		case 0:
			break;
		case 360:
			break;
			
			default: throw new IllegalArgumentException(""+heading);		
		}
	}
	
	public void execute(Order order)
	{
		switch (order.inst)
		{
		case Order.NORTH:
			wayY -= order.operand;
			break;
			
		case Order.EAST:
			wayX += order.operand;
			break;
			
		case Order.SOUTH:
			wayY += order.operand;
			break;
			
		case Order.WEST:
			wayX -= order.operand;
			break;
			
		case Order.RIGHT:
			rotateWaypoint(360-order.operand);
			break;
			
		case Order.LEFT:
			rotateWaypoint(order.operand);
			break;
			
		case Order.FORWARD:
			x += wayX * order.operand;
			y += wayY * order.operand;
			break;
		}		
	}
	
	public int L1DistToOrigin()
	{
		return Math.abs(x)+Math.abs(y);
	}	

	public Ship2(int wayX, int wayY)
	{
		this.wayX = wayX;
		this.wayY = wayY;
	}
}
