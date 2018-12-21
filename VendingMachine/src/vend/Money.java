package vend;
/**
 * Creates a money class for storing money types.
 * @author Farrukh Khan
 *
 */
public class Money 
{
	private int arrayNum;
	private String name;
	private Float denom;
	private int count;
	private String material;
	private String currency;
	
	/**
	 * Allows Money object to be set originally.
	 * @param seqNum- sequential number for selection.
	 * @param monName- Name of money type. String.
	 * @param monDenom- Denomination of money. Float.
	 * @param monCount- Number of currency denominations. int.
	 * @param monMat- Type of currency material. String.
	 * @param monCurrency- Type of currency($, euro, etc). String
	 */
	public void setMoney(int seqNum, String monName, Float monDenom,
			int monCount, String monMat, String monCurrency)
	{
		//Store passed values to each variable.
		arrayNum = seqNum;
		name = monName;
		denom = monDenom;
		count = monCount;
		material = monMat;
		currency = monCurrency;
	}
	
	/**
	 * 
	 * @return- Returns a String of the object. String.
	 */
	public String getMoney()
	{
		//Add all object properties to a String. 
		String monType = arrayNum + ". " + name + " Value: " + currency 
				+ denom + "'Count: " + count
				+ " Type: " + material;
		
		//Return the String
		return monType;
	}
	
	/**
	 * 
	 * @return- Returns the name property. String.
	 */
	public String getName()
	{
		//Return the name
		return name;
	}
	
	/**
	 * Adds money to the object.
	 * @param monCount- Number of coins/notes to add. int.
	 */
	public void addMoney(int monCount)
	{
		//Add money to current count
		count+=monCount;
	}
	
	/**
	 * Subtracts money from object.
	 * @param monCountNumber of coins/notes to subtract. int.
	 */
	public void subMoney(int monCount)
	{
		//Subtract money from current count
		count-=monCount;
	}
	
	/**
	 * Empties the money count.
	 */
	public void EmptyMoney()
	{
		//Set count to zero.
		count = 0;
	}
	
	/**
	 * Retuns the denomination of the money.
	 * @return- Denomination size. Floate
	 */
	public Float getDenom()
	{
		//Returns object denomination.
		return denom;
	}
	
	/**
	 * Returns the number of coins/notes.
	 * @return- The count of the object coins/notes. int.
	 */
	public int getCount()
	{
		//Return the object coin/note count
		return count;
	}

}
