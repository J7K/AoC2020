package net.j7k.aoc2020;

public class Ship 
{
	public int heading=0; 
	
	int x=0;
	int y=0;
	
	void execute(Order order)
	{
		switch (order.inst)
		{
		case Order.NORTH:
			y -= order.operand;
			break;
			
		case Order.EAST:
			x += order.operand;
			break;
			
		case Order.SOUTH:
			y += order.operand;
			break;
			
		case Order.WEST:
			x -= order.operand;
			break;
			
		case Order.RIGHT:
			heading = (heading - order.operand + 360) % 360;
			break;
			
		case Order.LEFT:
			heading += order.operand;
			heading %= 360;
			break;
			
		case Order.FORWARD:
			switch (heading)
			{
			case 0 : 
				execute(new Order(Order.EAST, order.operand));
				break;
			case 90 : 
				execute(new Order(Order.NORTH, order.operand));
				break;
			case 180 : 
				execute(new Order(Order.WEST, order.operand));
				break;
			case 270 : 
				execute(new Order(Order.SOUTH, order.operand));
				break;
			}
			break;
		}		
	}
	
	public int L1DistToOrigin()
	{
		return Math.abs(x)+Math.abs(y);
	}	
}
