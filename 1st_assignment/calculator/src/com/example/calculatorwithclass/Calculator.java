package com.example.calculatorwithclass;
import java.util.ArrayList;

public class Calculator {
    private int x, y;
    private String cal;
    // 컬렉션 결과 저장
    private ArrayList<Double> arrayList = new ArrayList<>();

    //캡슐화 이전 코드
//    Calculator(int x, int y, String cal) {
//        this.x = x;
//        this.y = y;
//        this.cal = cal;
//    }


    public double calculate() {
        if (cal.equals("+")) {
            arrayList.add((double)(x+y));
            return x + y;
        }
        else if (cal.equals("-")) {
            arrayList.add((double)(x-y));
            return x - y;
        }
        else if (cal.equals("*")) {
            arrayList.add((double)(x*y));
            return x * y;
        }

        else if (cal.equals("/")) {
            if (y == 0) {
                throw new IllegalArgumentException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다!");
            }
            arrayList.add(((double)x/(double)y));
            return (double) x / (double) y;
        }
        else {
            System.out.println("잘못된 연산이 입력되었습니다!");
            return Double.POSITIVE_INFINITY;
        }
    }

    // 컬렉션 삭제 코드
    public void removeResult() {
        arrayList.remove(arrayList.get(0));
    }


    public int getNum1() {
        return x;
    }
    public int getNum2() {
        return y;
    }
    public String getCal() {
        return cal;
    }
    public ArrayList<Double> getArrayList() {
        return arrayList;
    }

    public void setElement(int x, int y, String cal) {
        this.x = x;
        this.y = y;
        this.cal = cal;
    }


}
