package vend;
/**
 * This class stores the product types
 * @author Farrukh Khan
 *
 */
public class Products 
{
	private int arrayNum;
	private String name;
	private Float cost;
	private int quant;
	
	/**
	 * Sets the original properties of the product
	 * @param seqNum- Sequential number to identify the object. int.
	 * @param prodName- Name of the product. String.
	 * @param prodCost- Cost of the product. Float.
	 * @param prodQuant- Number of products available. int
	 */
	public void setProduct(int seqNum, String prodName, Float prodCost, 
			int prodQuant)
	{
		//Store passed variables to property
		arrayNum = seqNum;
		name = prodName;
		cost = prodCost;
		quant = prodQuant;
	}
	
	/*
	 * Returns the product properties as a string.
	 */
	public String getProduct()
	{
		//Store the properties to a string
		String prodType = arrayNum + ". " + name + " Cost: " +
				cost + " Quantity: " + quant;
		
		//Return the string
		return prodType;
	}
	
	/**
	 * Reduces the product quantity by 1.
	 */
	public void VendProd()
	{
		//Subtract 1 from quantity
		quant-=1;
	}
	
	/*
	 * Returns the quantity of the product
	 */
	public int getQuant()
	{
		//Return quantity
		return quant;
	}
	
	/**
	 * Returns the cost of the product. Float.
	 * @return
	 */
	public Float getCost()
	{
		//Return cost of product
		return cost;
	}
	
	/**
	 * Returns the name of the product
	 * @return- Product name. String.
	 */
	public String getName()
	{
		//Return product name
		return name;
	}

}
