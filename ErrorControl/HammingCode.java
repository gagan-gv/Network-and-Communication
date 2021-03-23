package ErrorControl;

import java.util.Scanner;

public class HammingCode {
    static int getParity(int[] x, int power){
        int parity = 0;
        for(int i = 0; i < x.length; i++){
            if(x[i] != 2){
                int k = i + 1;
                String s = Integer.toBinaryString(k);
                int n = ((Integer.parseInt(s))/((int)Math.pow(10, power))) % 10;
                if (n == 1){
                    if(x[i] == 1){
                        parity = (parity + 1) % 2;
                    }
                }
            }
        }
        return parity;
    }

    static int[] genCode(int[] x){
        int[] y;
        int i = 0, parityCount = 0,j = 0, k = 0;
        while(i < x.length){
            if(Math.pow(2,parityCount) == i + parityCount + 1){
                parityCount++;
            }
            else{
                i++;
            }
        }
        y = new int[x.length + parityCount];
        for (i = 1; i <= y.length; i++) {
            if(Math.pow(2,j) == i){
                y[i-1] = 2;
                j++;
            }
            else{
                y[k+j] = x[k++];
            }
        }
        for(i = 0; i < parityCount; i++){
            y[((int)Math.pow(2,i))-1] = getParity(y, i);
        }
        return y;
    }

    static void receive(int[] x, int parityCount){
        int power;
        int[] parity = new int[parityCount];
        String error = new String();
        for (power = 0; power < parityCount; power++){
            for (int i = 0; i < x.length; i++) {
                int k = i + 1;
                String s = Integer.toBinaryString(k);
                int bit = ((Integer.parseInt(s))/((int)Math.pow(10, power))) % 10;
                if(bit == 1){
                    if(x[i] == 1){
                        parity[power] = (parity[power] + 1) % 2;
                    }
                }
            }
            error = parity[power] + error;
        }
        int errorLocation = Integer.parseInt(error,2);
        if (errorLocation != 0){
            System.out.println("Error is at location " + errorLocation);
            x[errorLocation-1] = (x[errorLocation - 1] + 1) % 2;
            System.out.println("Corrected Code is: ");
            for (int i = x.length - 1; i >= 0; i--){
                System.out.print(x[i]);
            }
            System.out.println();
        }
        else{
            System.out.println("The Code is received without an error.");
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of bits: ");
        int n = sc.nextInt();
        int[] data = new int[n];
        for (int i = n-1; i >= 0; i--) {
            data[i] = sc.nextInt();
        }

        int[] y = genCode(data);
        System.out.println("Generated Code is: ");
        for (int i = y.length - 1; i >= 0; i--){
            System.out.print(y[i]);
        }

        System.out.println();
        System.out.println("Enter a position to check for error detection/ zero for no error: ");
        int error = sc.nextInt();
        if(error != 0){
            y[error - 1] = (y[error - 1] + 1) % 2;
        }
        System.out.println("Sent Code: ");
        for (int i = 0; i < y.length; i++){
            System.out.print(y[i]);
        }
        System.out.println();
        receive(y, y.length - data.length);
    }
}
