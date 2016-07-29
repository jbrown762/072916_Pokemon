
public class Move
{
	private String moveName;
	private int damage;
	private String type;
	
	public Move(String moveName, String type, int damage)
	{
		this.moveName = moveName;
		this.damage = damage;
		this.type = type;
	}
	
	public String getMoveName()
	{
		return moveName;
	}
	
	public int getDamage()
	{
		return damage;
	}
}
