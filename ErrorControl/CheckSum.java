package ErrorControl;

import java.util.*;

public class CheckSum {

    static int[] sum(int[] x, int[] y){
        int carry = 0;
        for (int i = 0; i < x.length; i++) {
            if(x[i] == 1 && y[i] == 1 && carry == 0){
                carry = 1;
                x[i] = 0;
            }
            else if(x[i] == 1 && y[i] == 1 && carry == 1){
                carry = 1;
                x[i] = 1;
            }
            else if(x[i] == 1 && y[i] == 0 && carry == 0){
                carry = 0;
                x[i] = 1;
            }
            else if(x[i] == 1 && y[i] == 0 && carry == 1)
            {
                carry=1;
                x[i] = 0;
            }
            else if(x[i] == 0 && y[i] == 1 && carry == 0)
            {
                carry=0;
                x[i] = 1;
            }
            else if(x[i] == 0 && y[i] == 1 && carry == 1)
            {
                carry=1;
                x[i] = 0;
            }
            else if(x[i] == 0 && y[i] == 0 && carry == 0)
            {
                carry=0;
                x[i] = 0;
            }
            else {
                carry = 0;
                x[i] = 1;
            }
        }
        return x;
    }

    static int complement(int x){
        if (x == 0){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of data's: ");
        int m = sc.nextInt();

        System.out.println("Enter data length: ");
        int n = sc.nextInt();

        System.out.println("Enter the data bits one by one.");
        int[][] data = new int[m][n];
        for(int i = 0; i < m; i++){
            System.out.println("Enter data " + (i+1) + ": ");
            for(int j = 0; j < n; j++){
                data[i][j] = sc.nextInt();
            }
        }
        int[] newData = new int[n];
        for(int i = 0; i < n; i++){
            newData[i] = 0;
        }
        int x = 0;
        while(x < m){
            newData = sum(newData, data[x]);
            x++;
        }
        int[] cSum = new int[n];
        for(int i = 0; i < n; i++){
            cSum[i] = complement(newData[i]);
        }
        System.out.println("Receiver's Side: ");
        for (int i = 0; i < m; i++) {
            System.out.println("Data " + (i+1));
            for (int j = 0; j < n; j++){
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
        System.out.println("Check Sum value: ");
        for (int i = 0; i < n; i++) {
            System.out.print(cSum[i]);
        }
        System.out.println();
        newData = sum(newData, cSum);
        int[] messageReceived = new int[n];
        for (int i = 0; i < n; i++) {
            messageReceived[i] = complement(newData[i]);
        }
        System.out.println("Message Complement: ");
        for (int i = 0; i < n; i++) {
            System.out.print(messageReceived[i]);
        }
    }
}
