package com.elevator.service;

import java.util.Scanner;

public class ElevatorService {
	
	int[] elevator;
	int curFloor; // 현재 층
	int passengerCount; // 탑승인원 카운트
	boolean direction; // 위,아래 방향
	
	public ElevatorService() {
		elevator = new int[4]; // 최대 4명까지 탑승 가능함.
		for(int i=0; i<4; i++)
			elevator[i] = -1;
		
		curFloor = 0;
		passengerCount = 0;
		direction = true;
	}
	
	public void start() {
		System.out.println("---- Welcome Elevator ----");
		
		EXIT:
		while(true) {
			
			int menu = inputMenu();
			
			switch(menu) {
			case 1:
				join();
				break;
			case 2:
				move();
				break;
			case 3:
				status();
				break;
			case 4:
				System.out.println("엘리베이터 운행을 종료합니다.");
				break EXIT;
			}
		}
	}
	
	public int inputMenu() {
		int menu;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("메뉴를 선택하세요.");
		System.out.println("1.탑승 2.이동 3.탑승현황 4.종료");
		System.out.print(">");
		menu = scan.nextInt();
		
		return menu;
	}

	public void join() {
		int answer;
		
		Scanner scan = new Scanner(System.in);
		
		if(passengerCount==4) {
			System.out.println("탑승할 공간이 없습니다.");
			return;
		}
		else {
			do {
				System.out.println("층 수를 선택해 주세요.");
				System.out.println("1.1층 2.2층 3.3층");
				System.out.print(">");
				answer = scan.nextInt()-1;
				
				
				if(answer<0 || 2<answer)
					System.out.println("운행 범위를 벗어났습니다.");
				else if(curFloor==answer)
					System.out.printf("현재 %d층입니다.\n", answer+1);
				else {
					if(direction) {
						if(curFloor>answer) // direction true인 경우 현재층보다 낮은층 고를 수 없어
							System.out.println("올라갑니다."); 
					} else {
						if(curFloor<answer) // direction flase인 경우 현재층보다 높은층 고를 수 없어
							System.out.println("내려갑니다."); 
					}
				}
				
			} while((answer<0 || 2<answer) || curFloor==answer 
					|| (direction&&curFloor>answer) || (!direction&&curFloor<answer));
			
			// 입력 층 안내
			System.out.printf("입력하신 층은 %d층입니다.\n", answer+1);
			
			// 탑승 시키기 
			for(int i=0; i<4; i++) // 4번 반복해서 -1이 있다면 해당 자리에 탑승 시키고 break 한다.
				if(elevator[i] == -1) {
					elevator[i] = answer;
					passengerCount++; // 탑승인원 카운트를 증가시킨다.
					break;
				} 
		}
	}

	public void move() {
		// 엘베를 이동시킨다.
		if(direction) {
			curFloor++;
			if(curFloor==2)
				direction = false; // 최상층에서 방향을 바꾼다.
		}
		else {
			curFloor--;
			if(curFloor==0)
				direction = true; // 최하층에서 방향을 바꾼다.
		}
		
		// 현재 층을 안내한다.
		infoFloor();
		
		// 하차 안내한다. 
		int count = 0;
		for(int i=0; i<4; i++)
			if(curFloor==elevator[i]) { // 목적지가 현재층인 경우
				elevator[i] = -1;
				passengerCount--;
				count++;
			}
		System.out.printf("%d명이 하차하였습니다.\n", count);
	}


	public void status() {
		System.out.println("---- 탑승 현황 ----");
		
		// 탑승 인원을 안내한다.
		int count = 0;
		for(int i=0; i<4; i++)
			if(elevator[i] != -1) // 값이 -1이 아닌 경우 탑승인원이 있는 것이다.
				count++;
		System.out.printf("현재 탑승 인원은 %d명 입니다.\n", count);
		
		// 현재 층을 안내한다.
		infoFloor();
	}

	public void infoFloor() {
		System.out.printf("현재 층수는 %d층 입니다.\n", curFloor+1);
	}
}



























