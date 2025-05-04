package com.example.calculatorwithclass;

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

            CalculatorLv2 cal_tmp = new CalculatorLv2();
            cal_tmp.setElement(x, y, cal);

            //게터 사용 코드
            //System.out.println(cal_tmp.getNum2());

            double answer = cal_tmp.calculate();
            //컬렉션 삭제 코드
//            System.out.println(cal_tmp.getArrayList());
//            cal_tmp.removeResult();
//            System.out.println(cal_tmp.getArrayList());

            if (answer != Double.POSITIVE_INFINITY) {
                System.out.println(answer);
            }

        }
    }
}
