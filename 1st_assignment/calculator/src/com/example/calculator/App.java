package com.example.calculator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("ktc 최고의 계산기 : 시작을 원하시면 enter키, 총료를 원하시면 'exit'를 입력해주세요~!");
            String tmp = scanner.nextLine();

            if (tmp.equals("exit")) {
                break;
            }

            System.out.print("첫번째 양의 정수를 입력해주세요 : ");
            int x = scanner.nextInt();
            System.out.print("두번째 양의 정수를 입력해주세요 : ");
            int y = scanner.nextInt();
            System.out.print("적용할 사칙연산(+, -, *, /)중 하나를 입력해주세요 : ");
            String cal = scanner.next();


            switch (cal) {
                case "+":
                    System.out.println((double)(x + y));
                    break;
                case "-":
                    System.out.println((double)(x - y));
                    break;
                case "*":
                    System.out.println((double)(x * y));
                    break;
                case "/":
                    if (y == 0) {
                        throw new IllegalArgumentException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다!");
                    }
                    System.out.println(((double) x / (double) y));
                    break;
                default:
                    System.out.println("잘못된 연산이 입력되었습니다.");
            }


        }
    }
}
