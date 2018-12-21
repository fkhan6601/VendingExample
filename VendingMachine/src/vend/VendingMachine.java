package vend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * This class acts as the vending machine. It displays the 
 * product, vends the products, takes money from user, and
 * returns the change.
 * @author Farrukh Khan
 *
 */
public class VendingMachine {
	
	/**
	 * The main method asks for the file locations of products
	 * and money. It also passes data types to other methods.
	 * @param args- no args used
	 * @throws FileNotFoundException- thrown when file location 
	 * is not correct
	 */
	public static void main(String[] args) throws FileNotFoundException 
	{
		//Create some variable to use later
		String productFileLoc;  //File location of products
		int prodFileLength = 0;  //Length of products file
		String moneyFileLoc;  //File location of money file
		int moneyFileLength = 0;  //Length of money file
		Boolean endVend = false;  //Bool to end program
		int userChoice = 0;  //Stores user choice action
		
		//Use scanner to get user input
		Scanner userInput = new Scanner(System.in);
		
		//Ask for and store product and money file location
		System.out.println("Enter location of vending products list:");
		productFileLoc = userInput.nextLine();
		
		System.out.println("Enter location of vending money list:");
		moneyFileLoc = userInput.nextLine();
		
		//Use scanner to read money file
		File moneyFile = new File(moneyFileLoc);
		Scanner moneyFileCheck = new Scanner(moneyFile);
		
		//Determine money file line numbers
		while(moneyFileCheck.hasNextLine())
		{
			moneyFileCheck.nextLine();
			moneyFileLength += 1;
		}
		
		//Use line size to create money array
		Money[] vendMon = new Money[moneyFileLength/6];
		
		//Use scanner to read product file
		File prodFile = new File(productFileLoc);
		Scanner prodFileCheck = new Scanner(prodFile);
		
		//Determine product file line numbers
		while(prodFileCheck.hasNextLine())
		{
			prodFileCheck.nextLine();
			prodFileLength += 1;
		}
		
		//Use line numbers to create product array
		Products[] vendProd = new Products[prodFileLength/4];
		
		//Load the vending machine with products and money
		LoadVending(productFileLoc, vendProd, moneyFileLoc, vendMon);
		
		//Keep running a loop unless user ends program
		while(endVend == false)
		{
			//Print user options
			PrintOptions();
			
			//Store user choice number
			userChoice = userInput.nextInt();
			
			//If user ends the program, terminate loop
			if(userChoice == 4)
			{
				endVend = true;
				
				System.out.println("--------------------------");
				System.out.println("Good Bye");
				System.out.println("--------------------------");
			}
			
			//Pass other user options to methods
			else
			{
				//Go to the method user chooses
				switch(userChoice)
				{
				case 1: PrintProd(vendProd); break;
				case 2: PrintMon(vendMon); break;
				case 3: VendProd(vendProd, vendMon); break;
				
				//Communicate wrong number pressed
				default: System.out.println("Invalid Choice, try again.");
				}
			}
		}
	}
	
	/**
	 * This method takes the cash and vends the product.
	 * Then it call the refund method.
	 * @param prodMap- An array of the product objects
	 * @param monMap- An array of the Money objects
	 */
	public static void VendProd(Products[] prodMap, Money[] monMap)
	{
		//Create variables to store to
		int userMoneyName;  //Money number chosen by user
		int userMoneyCount;  //Money count chosen by user
		int userProdName;  //Product number chosen by user
		Float userProductCost;  //Cost of a product
		Float userRefund;  //Amount of refund
		Float userMoneyValue = (float) 0;  //Value of user money
		int userSelection = 0;  //Function selected by user
		
		//Create a user money array
		Money[] userMoney = new Money[50];
		
		//Clone the money array to user money array
		userMoney = monMap.clone();
		
		//Reduce money count of user money to zero
		for(int i=0; i<userMoney.length; i++)
		{
			userMoney[i].EmptyMoney();
		}
		
		//Use scanner to get user input
		Scanner vendInput = new Scanner(System.in);
		
		//While user is adding money
		while(userSelection != 2)
		{
			//Print the user money array
			PrintMon(userMoney);
			
			//Ask for user input
			System.out.println("");
			System.out.println("");
			System.out.println("Select an option:");
			System.out.println("1: Add money");
			System.out.println("2: Vend");
			
			//Store user input
			userSelection = vendInput.nextInt();
			
			//If user selects to add money
			if(userSelection == 1)
			{
				//As user for money type and store it
				System.out.println("Enter your money number:");
				userMoneyName = vendInput.nextInt();
				
				//Check if money type exists
				if(userMoneyName > (userMoney.length -1) || userMoneyName < 0)
				{
					System.out.println("Money type is not valid."
							+ " See chart. Try again.");
				}
				
				//If money type exists
				else
				{
					//Ask user for quantity
					System.out.println("Enter money quantity: ");
					userMoneyCount = vendInput.nextInt();
					
					//Add money to user money array
					userMoney[userMoneyName].addMoney(userMoneyCount);
						
				}
			}
			
			//If user wants to buy product
			else if(userSelection == 2)
			{
				//Print product types
				PrintProd(prodMap);
				
				//Ask for product type
				System.out.println("Select a product by number:");
				userProdName = vendInput.nextInt();
				
				//Check if product exists
				if(userProdName > prodMap.length || userProdName < 0)
				{
					System.out.println("Product out of stock. Your money was returned.");
				}
				
				//If product exists
				else
				{
					//Determine user money value
					for(int i=0; i< userMoney.length; i++)
					{
						userMoneyValue+=userMoney[i].getCount()*userMoney[i].getDenom();
						
					}
					
					//Check if user has enough money for purchase
					if(prodMap[userProdName].getCost()>userMoneyValue)
					{
						System.out.println("Insufficient funds. Your money was returned.");
					}
					
					//If user has enough money, vend
					else
					{
						//Determine refund amount
						userProductCost=prodMap[userProdName].getCost();
						userRefund = userMoneyValue - userProductCost;
						
						//Move money from user array money to machine money
						for(int i=0; i<userMoney.length; i++)
						{
							monMap[i].addMoney(userMoney[i].getCount());
						}
						
						//Reduce the product count
						prodMap[userProdName].VendProd();
						
						//Ask user to take product
						System.out.println("Please take your " 
								+ prodMap[userProdName].getName());
						
						//Refund user money left over
						VendRefund(monMap, userRefund);
					}
				}
			}
		}
	}
	
	/**
	 * Refunds user money based on denomination
	 * @param monMap- Money array for machine.
	 * @param refund- Refund amount for user.
	 */
	public static void VendRefund(Money[] monMap, Float refund)
	{
		//Create a refund number for coin/note count
		int refundNum;
		
		//Communicate to user about refund
		System.out.println("Your change is:");
		
		//Refund user
		for(int i=0; i< monMap.length; i++)
		{
			refundNum = 0;
			
			//While some refund amount is left
			while(refund >= monMap[i].getDenom())
			{
				//If the money type runs out, skip loop
				if(monMap[i].getCount() == 0)
				{
					break;
				}
				
				//Reduce refund amount
				refund-=monMap[i].getDenom();
				
				//Subtract from money array
				monMap[i].subMoney(1);
				
				//Add to refund number
				refundNum+=1;
			}
			
			//Check if money type issued refund
			if(refundNum > 0)
			{
				//Print refund amount
				System.out.println(refundNum + " X " + monMap[i].getName());
			}
		}
		
		//If all money can't be refunded
		if(refund>0)
		{
			//Ask user to contact support
			System.out.println("");
			System.out.println("Refund not correct" +
			"please call 1-800-REFUND-ME");
		}
		
	}
	
	/**
	 * This method prints the product aray
	 * @param prodMap- Array of products
	 */
	public static void PrintProd(Products[] prodMap)
	{
		System.out.println("");
		
		//Cycle through and print each object and property
		for(int i=0; i<prodMap.length; i++)
		{
			System.out.println(prodMap[i].getProduct());
			System.out.println("-----------------------------------");
		}
	}
	
	/**
	 * Prints the money array
	 * @param monMap- Array of money objects
	 */
	public static void PrintMon(Money[] monMap)
	{
		System.out.println("");
		
		//Cycles through and prints money objects and properties
		for(int i=0; i<monMap.length; i++)
		{
			System.out.println(monMap[i].getMoney());
			System.out.println("-----------------------------------");
		}
	}
	
	/**
	 * Prints options to screen
	 */
	public static void PrintOptions()
	{
		//User options with white spece
		System.out.println("");
		System.out.println("");
		System.out.println("Select an option:");
		System.out.println("1: Print available products");
		System.out.println("2: Print vending machine cash");
		System.out.println("3: Vend a product");
		System.out.println("4: Quit");
		
	}
	
	/*
	 * Loads the vending machine. Throws file not found if product 
	 * or money file is not found.
	 * @param- takes files location for products and money and arrays for both
	 * 
	 */
	public static void LoadVending(String prodLoc, Products[] prodMap,
			String monLoc, Money[] monMap) throws FileNotFoundException
	{
		//Create some variables to store to
		String readLine;  //Stores a whole line
		String[] nameLine; //Stores variable name
		String[] val1Line;  //Stores a property
		String[] val2Line;  //Stores a property
		String[] val3Line;  //Stores a property
		String[] val4Line;  //Stores a property
		
		//Use scanner to read the product file
		File prodFile = new File(prodLoc);
		Scanner prodInput = new Scanner(prodFile);
		
		//Store products in file
		for(int i=0; i<prodMap.length; i++)
		{
			//Read and store each property of the product
			readLine = prodInput.nextLine();
			nameLine = readLine.split(" ");
			
			System.out.println(readLine);
			
			readLine = prodInput.nextLine();
			val1Line = readLine.split(" ");
			
			readLine = prodInput.nextLine();
			val2Line = readLine.split(" ");
			
			//Create a temporary product object
			Products tempProd = new Products();
			
			//Fill the product object
			tempProd.setProduct(i, nameLine[1], Float.valueOf(val1Line[1]), 
					Integer.valueOf(val2Line[1]));
			
			//Store the product object
			prodMap[i]=tempProd;
			
			//Skip the empty line
			prodInput.nextLine();
		}
		
		//Use scanner to read money file
		File monFile = new File(monLoc);
		Scanner fileInput = new Scanner(monFile);
		
		//Store each money object in array
		for(int i=0; i<monMap.length; i++)
		{
			//Read and split the file contents
			readLine = fileInput.nextLine();
			nameLine = readLine.split(" ");
			
			readLine = fileInput.nextLine();
			val1Line = readLine.split(" ");
			
			readLine = fileInput.nextLine();
			val2Line = readLine.split(" ");
			
			readLine = fileInput.nextLine();
			val3Line = readLine.split(" ");
			
			readLine = fileInput.nextLine();
			val4Line = readLine.split(" ");
			
			//Create a money object
			Money tempMon = new Money();
			
			//Fill the money object
			tempMon.setMoney(i, nameLine[1], Float.valueOf(val1Line[1]),
					Integer.valueOf(val2Line[1]), val3Line[1], val4Line[1]);
			
			//Store the money object in array
			monMap[i]=tempMon;
			
			//Skip the empty line
			fileInput.nextLine();
		}
	}

}
