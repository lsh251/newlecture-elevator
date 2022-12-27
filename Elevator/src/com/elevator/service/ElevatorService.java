package com.elevator.service;

import java.util.Scanner;
/*
 * *엘베
 * 엘베에 총 4명이 탑승 가능함
 * 엘베에 4개의 공간(배열)이 있는거야. 
 * 
 * 탑승할 때 엘베의 빈공간(-1)을 차지하고 목적층을 값으로 한다.
 * ex. 3층 선택한 경우 elevator[0] = 3;
 * 
 * *층수
 * 층수는 총 3개의 층
 * 
 * upAndDown이 false라면 내려가는거다.
 * 
 * - 층안내는 따로 메소드 만들자
 * - 근데 2차원배열로 해야하는거 아니냐? 엘베[3층][4명] 아 그럼 12명이지..
 */
public class ElevatorService {
	
	int[] elevator = new int[4];
	int count; // 탑승인원 카운트
	
	int[] floors = new int[3];
	int curFloor; // 현재 층
	
	boolean upAndDown;
	
	public ElevatorService() {
		for(int i=0; i<4; i++)
			elevator[i] = -1;
		
		count = 0;
		
		curFloor = 0;
		
		upAndDown = true;
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
		int floor;
		
		Scanner scan = new Scanner(System.in);
		
		if(count==4) {
			System.out.println("탑승할 공간이 없습니다.");
			return;
		}
		else {
			do {
				System.out.println("층 수를 선택해 주세요.");
				System.out.println("1.1층 2.2층 3.3층");
				System.out.print(">");
				floor = scan.nextInt()-1;
				
				
				if(curFloor==floor)
					System.out.println("목적지로 현재 층은 안됩니다.");
				else {
					if(upAndDown) {
						if(curFloor>floor) // upAndDown true인 경우 현재층보다 낮은층 고를 수 없어
							System.out.println("올라갑니다."); 
					} else {
						if(curFloor<floor) // upAndDown flase인 경우 현재층보다 높은층 고를 수 없어
							System.out.println("내려갑니다."); 
					}
				}
				
			} while(curFloor==floor || (upAndDown&&curFloor>floor) || (!upAndDown&&curFloor<floor));
			
			// 입력 층 안내
			System.out.printf("입력하신 층은 %d층입니다.\n", floor+1);
			// 탑승 시키기 
			for(int i=0; i<4; i++) // 4번 반복해서 -1이 있다면 해당 자리에 탑승 시키고 break 한다.
				if(elevator[i] == -1) {
					elevator[i] = floor;
					count++;
					break;
				} 
		}
		
	}

	public void move() {
		// 엘베를 이동시킨다.
		if(upAndDown) {
			curFloor++;
			if(curFloor==2)
				upAndDown = false;
		}
		else {
			curFloor--;
			if(curFloor==0)
				upAndDown = true;
		}
		
		// 현재 층을 안내한다.
		System.out.printf("현재 층수는 %d층 입니다.\n", curFloor+1);
		
		// 하차 안내한다. 
		int num = 0;
		for(int i=0; i<4; i++)
			if(curFloor==elevator[i]) {
				elevator[i] = -1;
				count--;
				num++;
			}
				
		System.out.printf("%d명이 하차하였습니다.", num);
	}


	public void status() {
		System.out.println("---- 탑승 현황 ----");
		
		// 탑승 인원을 안내한다.
		// 값이 -1이 아닌 경우 탑승인원이 있는 것이다.
		int count = 0;
		for(int i=0; i<4; i++)
			if(elevator[i] != -1)
				count++;
		System.out.printf("현재 탑승 인원은 %d명 입니다.\n", count);
		
		// 현재 층을 안내한다.
		System.out.printf("현재 층수는 %d층 입니다.\n", curFloor+1);
	}



}



























