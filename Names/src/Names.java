/**********************************************

Workshop #4

Course:JAC444

Last Name: Khaikin

First Name: Sergey


Section:<section name>

This assignment represents my own work in accordance with Seneca Academic Policy.

Signature
Sergey Khaikin

Date: 2018-04-03

**********************************************/
import java.util.*;
import java.io.*;
import java.util.regex.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

public class Names {
	
	static int g = 0;
	static String gen = "";
	
	public static void main(String[] args) {
		List<String> topNames2017 = Arrays.asList(
				 "Amelia",
				 "Olivia",
				 "emily",
				 "Isla",
				 "Ava",
				 "oliver",
				 "Jack",
				 "Charlie",
				 "harry",
				 "Jacob"
		);
		

		//Task 1 a
		topNames2017.replaceAll(s -> s.substring(0, 1).toUpperCase() + s.substring(1));
		topNames2017.sort((s1, s2) -> s1.compareTo(s2));
		topNames2017.forEach(s -> System.out.println(s));
		

		//Task 1 b
		System.out.println();
		topNames2017.sort(String::compareTo); 		
		topNames2017.forEach(System.out::println);
	
		
		//Task 1 c	
		System.out.println();	
		topNames2017
			.stream()
			.map(s->s.substring(0, 1).toUpperCase() + s.substring(1))
			.sorted()
			.forEach(System.out::println);
			
		
		
		//Task 2
		System.out.println();
		
		Scanner input = new Scanner(System.in);
		char inquiry = 'y';
		AtomicInteger atomicInteger = new AtomicInteger(0);

		while(inquiry == 'y'){
			
			System.out.print("Enter the year: ");
			int year = input.nextInt();
			
			System.out.print("Enter the gender: ");
			char gender = input.next().charAt(0);
			gender = Character.toLowerCase(gender);
			
			System.out.print("Enter the name: ");
			String name = input.next();
			
			if (gender == 'm'){
				g = 1;
				gen = "Boy";
			}else if (gender == 'f'){
				g = 3;
				gen = "Girl";
			}else{
				System.out.println("Choose m or f for gender");
			}
	
			try{
				Pattern pattern = Pattern.compile("\\s+");
				Files
				    .lines(Paths.get("babynamesranking" + year + ".txt"))
				    .map(str -> pattern.split(str))
				    .filter(line -> line[g].equalsIgnoreCase(name))
				    .forEach(arr -> {
	                    atomicInteger.getAndIncrement();
	                    System.out.println(gen + " name " + arr[g] + " is ranked #" + arr[0] + " in year " + year);
	                 });
			}
			catch (IOException e){
				System.out.println("No File Found");
			}
			
			//message if name is not in the file
			if(atomicInteger.get() == 0)
				System.out.println("Name not found");
			atomicInteger.set(0);
			
			
			
			System.out.print("Enter another inquiry? ");
			inquiry = input.next().charAt(0);
			inquiry = Character.toLowerCase(inquiry);
		}
				
		input.close();
	}
	
}
