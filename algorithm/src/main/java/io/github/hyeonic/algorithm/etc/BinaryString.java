package io.github.hyeonic.algorithm.etc;

public class BinaryString {

    public static void main(String[] args) {
        System.out.println(BinaryString.valueOf(1));
        System.out.println(BinaryString.valueOf(2));
        System.out.println(BinaryString.valueOf(3));
        System.out.println(BinaryString.valueOf(4));
        System.out.println(BinaryString.valueOf(5));
        System.out.println(BinaryString.valueOf(6));
        System.out.println(BinaryString.valueOf(7));
        System.out.println(BinaryString.valueOf(8));
        System.out.println(BinaryString.valueOf(9));
        System.out.println(BinaryString.valueOf(10));
    }

    public static String valueOf(final int number) {
        int temp = number;
        StringBuilder stringBuilder = new StringBuilder();
        while (temp != 0) {
            stringBuilder.append(temp % 2);
            temp /= 2;
        }

        return stringBuilder.reverse().toString();
    }
}
