
import java.util.Scanner; 
import java.io.*;

public class parkingApp {
	
	public static String [] parks = {};
	public static int loot;

	public static void main (String [] Args) {
						
		System.out.println("WELCOME TO  parkingAPP!");		
		Scanner in = new Scanner(System.in);
		System.out.println("Please choose type of input : ");
		System.out.println("1 . input from txt ");
		System.out.println("2 . input from parameter ");
        System.out.print("Input input type : ");

		int a = in.nextInt(); 		
		if (a > 2 || a < 0)  {			
			System.out.println("Please choose right input type "); 	
			System.out.println("You Chosse param : "+ a ); 
		
		}
								
        System.out.println("You Chosse param : "+ a ); 		

		if (a == 1 )  {
			textType();
		} else if (a == 2) {
			Scanner inLoot = new Scanner(System.in);
			System.out.print("create parking lot : "); 
			int loot = inLoot.nextInt(); 		
			parks = new String [loot];
			paramType(loot);
		}
						
	}
	
	public static void textType() {
		
		Scanner txtName = new Scanner(System.in);
		System.out.println("Example of directory : c:/user/file.txt"); 
		System.out.println("Please input path and filename : "); 
		String s = txtName.nextLine();         
		System.out.print("Searching file : " + s); 
		
		try {
			
			
		
		File f = new File(s);
		if(f.exists() && !f.isDirectory()) { 		
		
		BufferedReader r = new BufferedReader( new FileReader( s ) );
		String result = "", line = null;
			int i = 0; 
			int loot = 0;
			while ((line = r.readLine()) != null) {				
				System.out.print(line);					
				String [] scriptKey = line.split("\\s+");				
				i++;
						
						if (scriptKey[0].equals("park")) {
							startParking(loot , line , 1 );
						} else if (scriptKey[0].equals("leave")) {
							leaveParking(loot , line , 1);
						} else if (scriptKey[0].equals("status")) {
							checkStatus(loot , 1);							
						} else if (scriptKey[0].equals("exit")) {
							System.exit(0);
						} else if (scriptKey[0].equals("registration_numbers_for_cars_with_colour") || scriptKey[0].equals("slot_numbers_for_cars_with_colour")) {
							checkColor(loot , line , 1);
						} else if (scriptKey[0].equals("slot_number_for_registration_number")) {
							checkCarNumber(loot , line ,1);
						} else if (scriptKey[0].equals("create_parking_lot")) {
							loot = Integer.parseInt(scriptKey[1]);
							parks = new String [loot];
						} else {
							System.out.println("script row : " + i + " not correct syntax");
						}						
			
			}
		
			
		
		} else {
			System.out.print("file not found");
		}
		
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	public static void paramType(int loot) {
		Scanner txtName = new Scanner(System.in);								
		System.out.println("===================List of parameter================="); 				
		System.out.println("1.park space [car number] space [color] , this example of script :  park KA-01-HH-1234 White ");
		System.out.println("2.leave space [park number] , this example of script :  leave 4  ");
		System.out.println("3.status , this example of script :  status  ");
		System.out.println("4.registration_numbers_for_cars_with_colour space [color] , this example of script :  registration_numbers_for_cars_with_colour White  ");
		System.out.println("5.slot_numbers_for_cars_with_colour spase [color] , this example of script :  slot_numbers_for_cars_with_colour white  ");
		System.out.println("6.slot_number_for_registration_number  spase [car number] , this example of script :  slot_number_for_registration_number  KA-01-HH-3141 ");
		System.out.println("7.exit");	
		System.out.println("===================List of parameter================="); 		
		System.out.println("");		
		System.out.print("input your script :  "); 			
		
		String s = txtName.nextLine();         		
		String [] scripts = {"create_parking_lot space" , "park" , "leave" , "status" , "registration_numbers_for_cars_with_colour" , "slot_numbers_for_cars_with_colour" , "slot_number_for_registration_number" , "exit"};
		
		
		boolean state = false;		
		String [] scriptKey = s.split("\\s+");
		for (String checkString : scripts) {
			if (checkString.equals(scriptKey[0])) {
				state = true;				
			}			
		}
		
		if (state == false) {			
			System.out.println("Please reinput right script : "); 	
			String reinput = txtName.nextLine();         
			
		}
				
		if (scriptKey[0].equals("park")) {
			startParking(loot , s , 2);
		} else if (scriptKey[0].equals("leave")) {
			leaveParking(loot , s , 2);
		} else if (scriptKey[0].equals("status")) {
			checkStatus(loot , 2);							
		} else if (scriptKey[0].equals("exit")) {
			System.exit(0);
		} else if (scriptKey[0].equals("registration_numbers_for_cars_with_colour") || scriptKey[0].equals("slot_numbers_for_cars_with_colour")) {
			checkColor(loot , s , 2);
		} else if (scriptKey[0].equals("slot_number_for_registration_number")) {
			checkCarNumber(loot , s , 2);
		}
					
	}
	
	
	
	public static void checkCarNumber(int loot  , String param , int inputType) {							
		String [] splitParam = param.split("\\s+");	
		int	i = 0;	
		for (String tempReport : parks) {						
			i++;
			String [] splitData = tempReport.split("\\|");			
			if (splitData[0].equals(splitParam[1])) {
				System.out.println("data : " + i);
				break;
			} 	
		}	
		
		if (inputType == 2 ){
			paramType(loot);	
		}
		
		
		
		
	}
	
	public static void checkColor(int loot  , String param , int inputType) {					
		String alldata = "  ";		
		String [] splitParam = param.split("\\s+");	
		int	i = 0;	
		for (String tempReport : parks) {						
			if (tempReport == null) {
				continue;
			}
			
			i++;								
			String [] splitData = tempReport.split("\\|");			
			if (splitData[1].equals(splitParam[1]) && splitParam[0].equals("registration_numbers_for_cars_with_colour")) {
				alldata = alldata + splitData[0] + ",";
			} else if (splitData[1].equals(splitParam[1]) && splitParam[0].equals("slot_numbers_for_cars_with_colour")){
				alldata =  alldata + i + ",";
			}			
		}	
		
		System.out.println("data : " + alldata);
		
		if (inputType == 2 ){
			paramType(loot);	
		}
		
		
	}
	
	public static void checkStatus(int loot , int inputType) {						
		System.out.printf("%-15s %15s  %15s %n", "Slot No.", "Registration No" , "Color");		
		int i = 0;
		for (String tempReport : parks) {
			if (tempReport == null) {
				continue;
			}			
			
			
			i++;
			String [] split = tempReport.split("\\|");			
			System.out.printf("%-15s %15s %15s %n", i , split[0] , split[1]);			
		}			
		
		if (inputType == 2 ){
			paramType(loot);	
		}
	}
	
	
	public static void leaveParking(int loot , String param , int inputType) {		
		String [] splitparam = param.split("\\s+");		
		if (parks[Integer.parseInt(splitparam[1]) - 1 ] == null) {
			System.out.println("Slot number "+ splitparam[1] +" is already free");
		}
				
		for (int i = 0 ; i < parks.length ; i++) {
			if (i == Integer.parseInt(splitparam[1]) - 1 ) {
				parks[i] = null;
			}								
		}
					
		System.out.println("Slot number "+ splitparam[1] +" is free");
		if (inputType == 2 ){
			paramType(loot);	
		}
				
		
	}
	
	
	public static void startParking(int loot , String param , int inputType) {			
		System.out.println("LOOT : " + loot);
		String [] splitparam = param.split("\\s+");								
		boolean isParkingFull = true;
		for (int i = 0 ; i < parks.length ; i ++) {			
			if (parks[i] == null) {
				parks[i] =  ""+ splitparam[1] +"|" + splitparam[2];
				isParkingFull = false;
				break;
			}
		}
		
		if (isParkingFull) {
			System.out.println("Sorry, parking lot is full");
		}
		
		if (inputType == 2 ){
			paramType(loot);	
		}				
		
	}
	
	
	
}