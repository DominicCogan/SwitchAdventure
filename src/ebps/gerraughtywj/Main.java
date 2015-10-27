package ebps.gerraughtywj;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Define scanner and variables
		Scanner keyboard = new Scanner(System.in);
		boolean isRunning = true;
		String place = "doorway";
		String tempPlace = place;
		String choice = "";
		//Begin the loop
		do {
			//Begin the switch
			switch (place) {
			//Change prompt based on the value of "place"
			case "doorway": System.out.println(Prompts.doorwayPrompt); //Print the doorway prompt
							tempPlace = place; //Save the place
							System.out.print("Choice: "); //Make your choice
							choice = keyboard.next().toLowerCase(); //Save your choice
							if (choice.equals("kitchen") || //If your choice is the next place,
									choice.equals("upstairs")) { //then make "place" equal to it
								place = choice;
							} else {
								place = "default"; //If the place is wrong, go to default
							}
							break; //Break the switch, continue to loop
			case "kitchen": System.out.println(Prompts.kitchenPrompt);
							tempPlace = place;
							System.out.print("Choice: ");
							choice = keyboard.next().toLowerCase();
							if (choice.equals("refrigerator") || 
									choice.equals("cabinet")) {
								place = choice;
							} else {
								place = "default";
							}
							break;
			case "upstairs": System.out.println(Prompts.upstairsPrompt);
							tempPlace = place;
							 System.out.print("Choice: ");
							 choice = keyboard.next().toLowerCase();
							 if (choice.equals("bedroom") || 
									 choice.equals("bathroom")) {
								 place = choice;
							 } else {
								 place = "default";
							 }
							 break;
			case "refrigerator": System.out.println(Prompts.fridgePrompt);
								  tempPlace = place;
			 				 	  System.out.print("Choice: ");
			 				 	  choice = keyboard.next().toLowerCase();
			 				 	  if (choice.equals("yes")) {
			 				 		  System.out.println(Prompts.fridgeYes);
			 				 		  isRunning = false;
			 				 	  } else if (choice.equals("no")) {
			 				 		  System.out.println(Prompts.fridgeNo);
			 				 		  isRunning = false;
			 				 	  } else {
			 				 		  place = "default";
			 				 	  }
			 				 	  break;
			case "cabinet": System.out.println(Prompts.cabinetPrompt);
							tempPlace = place;
			 				System.out.print("Choice: ");
			 				choice = keyboard.next().toLowerCase();
			 				if (choice.equals("yes")) {
			 					 System.out.println(Prompts.cabinetYes);
			 					isRunning = false;
			 				 } else if (choice.equals("no")) {
			 					 System.out.println(Prompts.cabinetNo);
			 					isRunning = false;
			 				 } else {
			 					 place = "default";
			 				 }
			 				break;
			case "bedroom": System.out.println(Prompts.bedroomPrompt);
							tempPlace = place;
			 				System.out.print("Choice: ");
			 				choice = keyboard.next().toLowerCase();
			 				if (choice.equals("yes")) {
			 					 System.out.println(Prompts.bedroomYes);
			 					isRunning = false;
			 				 } else if (choice.equals("no")) {
			 					 System.out.println(Prompts.bedroomNo);
			 					isRunning = false;
			 				 } else {
			 					 place = "default";
			 				 }
			 				 break;
			case "bathroom": System.out.println(Prompts.bathroomPrompt);
							 tempPlace = place;
			 				 System.out.print("Choice: ");
			 				 choice = keyboard.next().toLowerCase();
			 				 if (choice.equals("yes")) {
			 					 	System.out.println(Prompts.bathroomYes);
			 					 	isRunning = false;
			 				 } else if (choice.equals("no")) {
			 					 System.out.println(Prompts.bathroomNo);
			 					isRunning = false;
			 				 } else {
			 					 place = "default";
			 				 }
			 				 break;
			default: System.out.println("Not a valid answer!"); //Print when answer isn't valid
					 place = tempPlace; //Go back to previous place
					 break; //Go back to loop
				
			}
			
		} while (isRunning); //Loop until done
		
		System.out.println("\nThanks for playing!"); //Finishing text
		keyboard.close(); //Close scanner

	}

}
