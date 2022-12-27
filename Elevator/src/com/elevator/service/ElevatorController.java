package com.elevator.service;

public class ElevatorController {

	public static void main(String[] args) {
		ElevatorService es = new ElevatorService();
		
		System.out.println("---- Welcome Elevator ----");
	
		EXIT:
		while(true) {
			
			int menu = es.inputMenu();
			
			switch(menu) {
			case 1:
				es.join();
				break;
			case 2:
				es.move();
				break;
			case 3:
				es.status();
				break;
			case 4:
				System.out.println("종료합니다.");
				break EXIT;
				
			}
			
		}
		
	}

}
