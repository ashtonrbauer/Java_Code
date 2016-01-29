
import java.util.Scanner;import java.util.LinkedList;


//FORE-WARNING!!! ANY COMMAND EXECUTION WILL EXECUTE A Thread.sleep(2000);
//after any execution in order for user to be able to see the execution
//of their desired command. This means 2 seconds between any print out and
//executing another command.



//Simple object class to contain the Names and Phone Numbers of the directory.
class Person {
	private String fName;
	private String lName;
	private String number;
	//empty constructor
	public Person(){
		this.fName = null;
		this.lName = null;
		this.number = null;
	}
	//parameterized constructor
	public Person(String fN, String lN, String num){
		this.fName = fN;
		this.lName = lN; 
		this.number = num;
	}
	//get Last Name
	public String getFirstName(){
		return this.fName;
	}
	//set Last Name
	public void setFirstName(String fN){
		this.fName = fN;
	}
	//get Last Name
	public String getLastName(){
		return this.lName;
	}
	//set Last Name
	public void setLastName(String lN){
		this.lName = lN;
	}
	//get Phone Number
	public String getPhoneNum(){
		return this.number;
	}
	//set Phone Number
	public void setPhoneNum(String pN){
		this.number = pN;
	}
	//Convert Person to a String representation of all of its values concatenated.
	public String toString(){
		return this.fName + " " + this.lName + " " + this.number;
	}
	
}




/*Creating the realm of a personal phone directory. By convention a main
 * method generally won't have any post-conditions or pre-conditions. So, this
 * main method follows suit.
 * Pre-conditions : none
 * Post-conditions : none*/
public class phonedir {
	public static void main(String[] args)throws InterruptedException{
		Scanner ui = new Scanner(System.in);
		System.out.println("Welcome to the phone directory!");
		String ec = "Enter a command from the list above(q to quit):";
		String invC = "Invalid command.";
		//@charNotQ will be used to exit the while loop
		boolean charNotQ = true;
		//@directory will be our phone directory.
		LinkedList<Person> directory = new LinkedList<Person>();
		//@current will keep track of our current person throughout the program.
		Person current= new Person();
		//@index will be used to add a 'new Person(fN,lN,pN);' to our @directory.
		int index=0;
		//used for printf arguments
		String input,firstName,lastName,phoneNumber;
		//while loop iterate while command != 'q'; begins our phone directory menu.
		while(charNotQ){
			//menu option for the directory
			System.out.println();
			System.out.printf("%5s   %s %n","a","Show all records");
			System.out.printf("%5s   %s %n","d","Delete the current record.");
			System.out.printf("%5s   %s %n","f","Change the first name in the current record.");
			System.out.printf("%5s   %s %n","l","Change the last name in the current record.");
			System.out.printf("%5s   %s %n","n","Add a new record.");
			System.out.printf("%5s   %s %n","p","Change the phone number in the current record.");
			System.out.printf("%5s   %s %n","q","Quit.");
			System.out.printf("%5s   %s %n","s","Select a record from the record list to become the current"
					+ " record.");
			System.out.println("\n");
			System.out.print("Enter a command from the list above(q to quit):");
			//will take any input and turn it into a single char, this will further limit any user
			//attempts that could mess with the program.
			char command = ui.next().charAt(0);
			System.out.println();
			//handles any incorrect commands and loops until @command is valid.
			while(command !='a' && command !='d' && command !='f' && command !='l' &&
					 command !='n' && command !='p' && command !='q' && command !='s'){
				System.out.printf("%s %n%s",invC,ec);
				command = ui.next().charAt(0);
			}
			//quits the program
			if(command == 'q'){
				System.out.println("Exiting phone directory, goodbye.");
				charNotQ = false;
			}
			//The switch handles all valid @commands
			switch(command){
				//show all records
				case 'a':{
					//handles if directory is empty, will let user know if it is.
					if(directory.size() == 0){
						System.out.println("Your directory is empty.");
						Thread.sleep(2000);
						break;
					}else{
						//This will handle the printing of the entire directory if it's not empty.
						//The next three lines are the heading. After that, beginning with the for
						//loop, it will run through and print in a formatted fashion all elements
						//of our phone directory.
						String heading = "First Name",middle="Last Name",tail="Phone Number",
								divider="_________________________________________________________";
						System.out.printf("%-20s  %-20s %-20s %n%s%n",heading,middle,tail,divider);
						for(int i=0;i<directory.size();i++){
							firstName=directory.get(i).getFirstName();
							lastName=directory.get(i).getLastName();
							phoneNumber=directory.get(i).getPhoneNum();
							System.out.printf("%-21s %-21s %-20s%n",firstName,lastName,phoneNumber);
						}
						System.out.println("\n\nCurrent record is: "+current.toString());
						//sleep 2 seconds.
						Thread.sleep(2000);
						break;
					}
				}
				//delete current record
				case 'd':{
					//handles if there is no record.
					if(current.getLastName() == null){
						System.out.println("No current record.");
						Thread.sleep(2000);
						break;
					}
					//this searches for the element in our @directory that is equal to current
					//and deletes it. It then sets @current to a blank 'new Person();'
					for(int i = 0; i<directory.size();i++){
						if(directory.get(i).getFirstName().equals(current.getFirstName()) &&
								directory.get(i).getLastName().equals(current.getLastName()))
							directory.remove(i);		
					}
					current = new Person();
					Thread.sleep(2000);
					break;
				}
				//change first name in current record
				case 'f':{
					//handles if the directory is empty.
					if(current.getLastName() == null){
						System.out.println("No current record.");
						Thread.sleep(2000);
						break;
					}
					//changing the element's, which matches @current, first name.
					else{
						System.out.print("Enter new first name:");
						input = ui.next();
						//finds instance in LinkedList of the current first name and same last name
						// to further ensure accuracy, and sets it to the input(user given first name change).
						for(int i = 0; i<directory.size();i++){
							if(directory.get(i).getFirstName().equals(current.getFirstName())&&
									directory.get(i).getLastName().equals(current.getLastName()))
								directory.get(i).setFirstName(input);
						}
						current.setFirstName(input);
						
						System.out.println("Current record is: "+current.toString());
						Thread.sleep(2000);
						break;
					}	
					
				}
				//change the last name in current record
				case 'l':{
					//handles case of null @current
					if(current.getLastName() == null){
						System.out.println("No current record.");
						Thread.sleep(2000);
						break;
					}	
					else{
						System.out.print("Enter new last name:");
						input = ui.next();
						//removes the @current name from our directory, so it can be sorted.
						for(int i = 0; i<directory.size();i++){
							if(directory.get(i).getFirstName().equals(current.getFirstName())&&
									directory.get(i).getLastName().equals(current.getLastName()))
								directory.remove(i);
						}
						//changes @current last name
						current.setLastName(input);
						//this for loop is the largest algorithm besides the evaluateLast() method in this
						//program. It's what calls evaluateLast(currLN,dirLN) in order to arrange this 
						//directory alphabetically without sorting anything.
						for(int i=0;i<directory.size();i++){
							int n = evaluateLast(current.getLastName(), directory.get(i).getLastName());
							if(n == 1){
								index = i;
								directory.add(index,new Person(current.getFirstName(),current.getLastName(),
										current.getPhoneNum()));
								break;
							}
							else if(i==directory.size()-1){
								directory.add(new Person(current.getFirstName(),current.getLastName(),
										current.getPhoneNum()));
								break;
							}
						}
						System.out.println("Current record is: "+current.toString());
						Thread.sleep(2000);
						break;
					}	
				}
				//add a new record
				case 'n':{
					System.out.print("Enter first name: ");
					current.setFirstName(ui.next());
					System.out.println();
					System.out.print("Enter last name: ");
					current.setLastName(ui.next());
					System.out.println();
					System.out.print("Enter telephone number: ");
					current.setPhoneNum(ui.next());
					if(directory.size() == 0){
						directory.add(new Person(current.getFirstName(),current.getLastName(),
								current.getPhoneNum()));
						System.out.println("Current record is: "+current.toString());
						Thread.sleep(2000);
						break;
					}	
					//Same for loop algorithm to call evaluateLast() so that the @directory stays organized
					//alphabetically by last name.
					for(int i=0;i<directory.size();i++){
						int n = evaluateLast(current.getLastName(), directory.get(i).getLastName());
						if(n == 1){
							index = i;
							directory.add(index,new Person(current.getFirstName(),current.getLastName(),
									current.getPhoneNum()));
							break;
						}
						else if(i==directory.size()-1){
							directory.add(new Person(current.getFirstName(),current.getLastName(),
									current.getPhoneNum()));
							break;
						}
					}
					System.out.println("Current record is: "+current.toString());
					Thread.sleep(2000);
					break;
				}
				//change the phone number in the current record
				case 'p':{
					if(current.getLastName() == null){
						System.out.println("No current record.");
						Thread.sleep(2000);
						break;
					}	
					else{
						System.out.print("Enter new phone number:");
						input = ui.next();
						//similar algorithm as in the change first name, and change last name commands.
						for(int i = 0; i<directory.size();i++){
							if(directory.get(i).getPhoneNum().equals(current.getPhoneNum()) &&
									directory.get(i).getLastName().equals(current.getLastName()))
								directory.get(i).setPhoneNum(input);
						}
						current.setPhoneNum(input);
						System.out.println("Current record is: "+current.toString());
						Thread.sleep(2000);
						break;
					}	
				}
				//select a record to become current record
				case 's':{
					//handles empty directory
					if(directory.size() == 0){
						System.out.println("This directory is empty.");
						Thread.sleep(2000);
						break;
					}	
					System.out.print("Enter first name: ");
					String selectFN = ui.next();
					System.out.println();
					System.out.print("Enter last name: ");
					input = ui.next();
					System.out.println();
					//uses and inverse of previous algorithms to change the value of current.
					for(int i = 0;i < directory.size(); i++){
						if(directory.get(i).getFirstName().equals(selectFN) &&
								directory.get(i).getLastName().equals(input)){
							current=new Person(directory.get(i).getFirstName(),directory.get(i).getLastName()
									,directory.get(i).getPhoneNum());
							break;
						}
						if(i == directory.size()-1)
							System.out.println("No record found.");
					}
					System.out.println("Current record is: "+current.toString());
					Thread.sleep(2000);
					break;
				}
				default: break;	
				
			}
				
			
		}
		ui.close();
	}
	//Since we could not use sorting algorithms, this is a method to evaluate char by char, whether or not
	//the parameter last names are equal to each other or not. If not, depending on the value returned, 1
	//being the currLN belongs before dirLN, alphabetically, and 2 being the reverse.
	public static int evaluateLast(String currLN,String dirLN){
		int nameCheck = 0, min;
		if(currLN.length()>dirLN.length())
			min=dirLN.length();
		else
			min=currLN.length();
		for(int i = 0; i < min; i++){
			//evaluate which char at the given index is of lower value.
			if(currLN.charAt(i) < dirLN.charAt(i)){
				return 1;
			}
			//recursively call on itself with substrings of the given last names, to further evaluate which
			//is alphabetically of lower ordinal value.
			else if(currLN.charAt(i) == dirLN.charAt(i)){
				evaluateLast(currLN.substring(i+1),dirLN.substring(i+1));
			}	
			else
				return 2;
		}
		return nameCheck;
	}
}
