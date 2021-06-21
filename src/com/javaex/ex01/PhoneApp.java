package com.javaex.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PhoneApp {

	public static void main(String[] args) throws IOException {
		
		//파일 읽기
		Reader fr = new FileReader("C:\\javaStudy\\workspace\\minipro\\PhoneDB.txt");
		BufferedReader br = new BufferedReader(fr);
		
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		
		List<Person> pList = new ArrayList<Person>();
		
		String str = "";
		while(true) {
			str = br.readLine();
			if(str == null) {
				break;
			}
			
			String[] pInfo = str.split(",");
			
			String name = pInfo[0];
			String hp = pInfo[1];
			String company = pInfo[2];
			
			Person person = new Person(name, hp, company);
			
			pList.add(person);
		}
		

		System.out.println("*******************************");
		System.out.println("*        전화번호 관리 시스템       *");
		System.out.println("*******************************");
		
		while(flag) {
			
			//시작화면
			System.out.println("");
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println("-------------------------------");
			System.out.print(">메뉴번호:");
			
			int menu = sc.nextInt();
			
			
			switch(menu) {
				case 1:
					//리스트 출력
					int count1 = 1;
					for(Person pp : pList) {
						System.out.print(count1 + ".   ");
						System.out.println(pp.showInfo());
						count1++;
					}
					break;
					
				case 2:
					//등록
					System.out.println("<2.등록>");
					
					sc.nextLine(); //int 입력 후 String 입력 시 발생하는 오류 해결
					
					System.out.print(">이름:");
					String name = sc.nextLine();
					
					System.out.print(">휴대전화:");
					String hp = sc.nextLine();
					
					System.out.print(">회사전화:");
					String company = sc.nextLine();
							
					System.out.println("[등록되었습니다.]");
					
					Person p = new Person(name, hp, company);
					pList.add(p);
					
					break;
					
				case 3:
					System.out.println("<3.삭제>");
					System.out.print(">번호:");
					
					int del = sc.nextInt();
					pList.remove((del-1));
					
					System.out.println("[삭제되었습니다.]");
					break;
					
				case 4:
					System.out.println("<4.검색>");
					System.out.print(">이름:");
					
					sc.nextLine();
					String keyword = sc.nextLine();
					
					int count2=1;
					for(Person pp : pList) {
						
						if(pp.getName().contains(keyword)) {
							System.out.print(count2 + ".   ");
							System.out.println(pp.showInfo());
						} 
						count2++;
					}
					
					break;
					
					
				
				case 5:
					System.out.println("*******************************");
					System.out.println("*           감사합니다           *");
					System.out.println("*******************************");
					flag = false;
					break;
					
				default:
					System.out.println("[다시 입력해 주세요.]");
					break;

			}
			
			//파일 쓰기
			Writer fw = new FileWriter("C:\\javaStudy\\workspace\\minipro\\PhoneDB.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			String data = "";
			for(Person p:pList) {
				data = p.write();
				if(data == null) {
					break;
				}
				bw.write(data);
				bw.newLine();
			}
			
			bw.close();
			br.close();
			
		}
		
		sc.close();
		
		

	}

}
