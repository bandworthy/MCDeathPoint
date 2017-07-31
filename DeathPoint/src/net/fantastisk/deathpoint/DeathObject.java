package net.fantastisk.deathpoint;

public class DeathObject 
{
	
	String Name;
	String PosX;
	String PosZ;
	String PosY;
	
	public DeathObject()
	{
		//no setters so dont use.
	}
	
	public DeathObject (String name,String X,String Z, String Y)
	{
			Name = name;
		    PosX = X;
		    PosZ = Z;
		    PosY = Y;
	}
	
	
	public String getName()
	{
		return this.Name;
	}
	
	public String getPosX()
	{
		return this.PosX;
	}

	public String getPosZ()
	{
		return this.PosZ;
	}

	public String getPosY()
	{
		return this.PosY;
	}
	
	public String getString()
	{
		String death = this.getName()+" died at "+"  X:"+this.PosX+"  Z:"+this.PosZ+"  Y:"+this.PosY;
		return death;
	}
	


}
