import java.util.*;
/*
 * 
 * 
 * There are four classes created for this assignment in my project: bank.java,
 * Customer.java, timer.java, and Teller.java. The main method and user interface
 * is within bank.java. Customer.java is used to create a customer object with
 * a random time property and a name. The timer.java simply uses the System's current
 * time and evaluates elapsed time through basic arithmetic. The Teller.java is an 
 * object used to evaluate the bank's employees. The teller has a name, availability,
 * amount of customers helped, time occupied, and a customer they're currently helping.
 * 
 * In bank.java the user interface is encountered. This is where the user will actually run
 * the simulation and see the output in real time. I built my program to keep track of the 
 * time and print out every 10 seconds how much time is remaining and then at 10 seconds, 
 * print the remaining time every second. The user interface is built using while loops 
 * evaluating the user's input of yes or no. While running, the simulation initializes its
 * beginning 5 tellers, gives them each one random customer with a time property between 20
 * and 25 seconds, and then continues on into the countdown. For the next 120 seconds, every
 * second the program evaluates if a new customer is needed, evaluated through a boolean. 
 * If a new customer is needed it creates a random time between 2 and 6 seconds for when the
 * customer will arrive. It will print out the remaining time in seconds if the time is equal 
 * to zero when modulus with 10, or if it's less than ten. A for loop then goes through and 
 * evaluates the tellers. If the teller is occupied, increase the time occupied; decrement the
 * current customer's time; remove the customer if their requested time has passed; if someone
 * is in the line, add them to a random teller whom is available. The program will print out if
 * a customer is being helped, has been helped, enters the bank, or if a teller is initialized.
 * The illusion of time is acquired by using Thread.sleep(1000) to delay execution for 1 second.
 * If the countdown to a new customer is 0, a new random customer will enter the bank, they'll
 * be added to the bank line, and the need for a new customer is added. The randomName() method
 * at the end is used to randomly generate four uppercase chars and append them to a blank string
 * in order to create random names for customers. By default the main method has no pre or post-
 * conditions, or invariants. The randomName() method has no pre-conditions besides being called, 
 * the post conditions are that four random characters will be returned, and it contains no
 * invariants.
 * 
 * Customer.java is a simple object class utilized to keep track of two values necessary to the
 * customer of the bank. It has a random time value and a name. Their are also means to both 
 * retrieve these values and decrement the time value by 1 each time. Math.random() is utilized
 * in order to generate a random number for the time property. 
 * Preconditions: Must be initialized with a String, or a String and an int.
 * Postconditions: None.
 * Invariants: None.
 * 
 * The timer.java class is a simple object utilized to keep track of time for a countdown in the 
 * simulation. It utilizes the time by setting the initial time to the System's current time in 
 * milliseconds. Then to evaluate elapsed time it return the (initial time - current time)/1000 in
 * order to display the difference in seconds. This class contains no preconditions, postconditions,
 * or invariants.
 * 
 * The Teller.java has a little more to it than the previous two classes. It is used to keep track
 * of the time the teller's name(String), availability(boolean), customer's helped (int), time
 * occupied(int), and current customer(Customer). There is also a toString() method utilized to print
 * out he results of the Teller. The Teller class has the ability to retrieve and set all of these 
 * values. Functionally it can set a new customer, output this new customer, remove the customer,
 * output the customer has been helped, and keep track of its time and person count. 
 * Precondition: A string name must be passed upon initialization.
 * Post conditions: none
 * Invariants: None*/
 
//Customer identity
class Customer {
	//How long they'll occupy the teller
	private int time_property;
	//Their name.
	private String name;
	
	public Customer(String n){
		//Random time property
		this.time_property = (int)(Math.random() * 5 + 2);
		this.name = n;
	}
	//If the user specifies a rough time, randomly generate
	//an int from that number to that number + 5
	public Customer(String n, int t){
		this.name = n;
		this.time_property = (int)(Math.random()*6 + t);
	}
	//Return their time
	public int getTime(){
		return this.time_property;
	}
	//Return their name
	public String getName(){
		return this.name;
	}
	//Decrease the time until they are helped.
	public void decrementTime(){
		this.time_property--;
	}
	
	
}



//Our bank teller.
class Teller {
	//Is the teller helping someone?
	private boolean availability;
	//Name of teller
	private String name;
	//How many customers have they helped?
	private int customers_helped;
	//Which customer are they helping?
	private Customer being_helped;
	//How long total have they been occupied?
	int time_occupied = 0;
	
	//Initial constructor
	public Teller(String n){
		this.name = n;
		this.availability = true;
		this.customers_helped = 0;
		this.time_occupied = 0;
	}
	//Return name
	public String getName(){
		return this.name;
	}
	//Return their availabiliy
	public boolean getAvail(){
		return this.availability;
	}
	//Return their count
	public int getCustHelped(){
		return this.customers_helped; 
	}
	//Return current customer
	public Customer getCustomer(){
		return this.being_helped;
	}
	//Increment how many customers they've helped
	public void increment(){
		this.customers_helped++;
	}
	//Increment their time occupied
	public void increaseTime(){
		this.time_occupied++;
	}
	//Remove their current customer, make them available again.
	public void removeCustomer(){
		System.out.println("Customer " + this.being_helped.getName() + " has been helped.");
		this.availability = true;
		increment();
	}
	//Set their availability.
	public void setAvail(boolean b){
		this.availability = b;
	}
	//Set current customer
	public void setCustomer(Customer c){
		this.being_helped = c;
		this.availability = false;
		System.out.println(this.name + " is helping sample customer: " + c.getName());
	}
	//Print out the teller's information.
	public String toString(){
		return this.name + " helped " + this.customers_helped + " customers. This teller was occupied for "
				+ this.time_occupied + " seconds.";
	}
}



//Stopwatch will be used to evaluate time in our program.
class timer {
	private int start;
	
	public timer(){
		//This evaluates the current time of your system.
		//Allows for more accurate calculations of time.
		this.start = (int)System.currentTimeMillis();
	}
	
	public int getElapsed(){
		//Return the time elapsed in seconds.
		return ((int)System.currentTimeMillis() - this.start)/1000;
	}

} 
 

public class bank {
	
	public static void main(String[] args) throws InterruptedException{
		Scanner ui = new Scanner(System.in);	
		
		//Initial user prompt
		System.out.println("Would you like to run a simulation? (y/n)");
		String input = ui.next();
		
		//Error in input checker.
		while(!input.startsWith("y") && !input.startsWith("n")){
			System.out.println("Error in input. Please answer yes or no.");
			System.out.println("Would you like to run a simulation? (y/n)");
			input = ui.next();
		}
		
		//User Interface, y/yes to start, n/no to quit.
		while(input.startsWith("y")){
			//Stopwatch/Timer object i built. Keeps track of time.
			timer t = new timer();
			//Counts needed for program optimization.
			int total_cust = 5, time_till = 0, customers_not_helped =5;
			//Used to check if a new_cust is needed.
			boolean new_cust = true;
			//The Line in the bank.
			Queue<Customer> bank_line =  new LinkedList<Customer>();
			//Our 5 tellers.
			Teller[] employees = new Teller[5];
			//Initializing our five tellers. Also, giving them our initial 5 customers.
			for(int i = 0; i < employees.length; i++){
				employees[i] = new Teller("Teller " + (i+1));
				employees[i].setCustomer(new Customer(randomName(), 20));
			}
			//While our stopwatch's elapsed time does not equal two minutes
			while(t.getElapsed() != 120){
				//If a new customer is needed create a new random time for next arrival.
				if(new_cust){
					time_till = (int)(Math.random() * 5 + 2);
					new_cust = false;
				}
				//Print out time remaining.
				if((120-t.getElapsed())%10 == 0)
					System.out.println("\n" + (120-t.getElapsed()) + " seconds remaining in simulation.\n");
				else if((120-t.getElapsed()) < 10)
					System.out.println("\n" + (120-t.getElapsed()) + " seconds remaining in simulation.\n");
				
				Thread.sleep(1000); //one second passes.
				
				//Check our tellers for availability and evaluate our customers being helped.
				for(int i = 0; i < employees.length; i++){
					//If the teller is occupied, increment their time occupied.
					if(!employees[i].getAvail())
						employees[i].increaseTime();
					//Every second decrement the time property of the customer.
					employees[i].getCustomer().decrementTime();
					//If the customers time is zero, remove them, and decrement our count of
					//unhelped customers.
					if(employees[i].getCustomer().getTime() == 0){
						employees[i].removeCustomer();
						customers_not_helped--;
					}
					//Randomly generate which teller will receive the next customer
					//Prevents the informatio from being skewed to Teller's one and two.
					int random_t = (int)(Math.random()*5);
					if(employees[random_t].getAvail() && !bank_line.isEmpty())
						employees[random_t].setCustomer(bank_line.remove());
				}
				//Decrement the time until new customer arrives.
				time_till--;
				//Add a new customer with a random Name to our Bank Line.
				if(time_till == 0){
					new_cust = true;
					Customer newbie = new Customer(randomName());
					bank_line.add(newbie);
					System.out.println("Sample customer: " + newbie.getName() + " entered the bank.");
					total_cust++;
					customers_not_helped++;
				}
				
			}
			//Print out results.
			System.out.println("Simulation is complete.");
			System.out.println("Total number of customers that entered the bank: " + total_cust);
			for(int i = 0; i < employees.length; i++)
				System.out.println(employees[i].toString());
			System.out.println("Customers that were not helped: "+customers_not_helped);
			System.out.println("\n\n\n");
			
			//Ask user if they would like another simulation, exit if not.
			System.out.println("Would you like to run another simulation? (y/n)");
			input = ui.next();
			while(!input.startsWith("y") && !input.startsWith("n")){
				System.out.println("Error in input. Please answer yes or no.");
				System.out.println("Would you like to run another simulation? (y/n)");
				input = ui.next();
			}
				
		}
		//Adios, amigo.
		System.out.println("\nGoodbye!");
		ui.close();
		
	}
	//Generate 4 random uppercase characters, to be used for our Customer names.
	static String randomName(){
		String r_name = "";
		for(int i = 0; i < 4; i++)
			r_name += ((char)((int)(Math.random()*26 + 65))) + "";
		return r_name;
	}
}
