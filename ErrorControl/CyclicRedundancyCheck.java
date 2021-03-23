package ErrorControl;

import java.util.*;

public class CyclicRedundancyCheck {

    static int rem(int x, int y){
        if(x == y){
            return 0;
        }
        else{
            return 1;
        }
    }

    static int[] cRCBitAdd(int n, int m, int[] data, int[] divisor){
        int[] newData = new int[n+m];
        int[] cRC = new int[m];
        for (int i = 0; i < n+m-1; i++) {
            if(i < n){
                newData[i] = data[i];
            }
            else{
                newData[i] = 0;
            }
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < m; j++){
                cRC[j] = newData[i+j];
                if(cRC[0] == 1){
                    newData[i+j] = rem(newData[i+j], divisor[j]);
                }
                else{
                    newData[i+j] = rem(newData[i+j], 0);
                }
            }
        }
        System.out.println();
        System.out.println("The CRC is: ");
        for (int i = 0; i < m-1; i++) {
            System.out.print(cRC[i]);
        }
        return cRC;
    }

    static void receive(int[] received, int n, int m, int[] divisor){
        int[] rem = new int[m];
        int k = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < m; j++){
                rem[j] = received[i+j];
                if(rem[0] == 1){
                    received[i+j] = rem(received[i+j], divisor[j]);
                }
                else{
                    received[i+j] = rem(received[i+j], 0);
                }
                if(rem[j] == 0){
                    k++;
                }
            }
        }
        if(k != m - 1){
            System.out.println("The data is accepted.");
        }
        else{
            System.out.println("The data is rejected.");
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n, m;
        System.out.println("Enter data length: ");
        n = sc.nextInt();
        System.out.println("Enter divisor length: ");
        m = sc.nextInt();
        int[] data = new int[n], divisor = new int[m];
        System.out.println("Enter data bits: ");
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }
        System.out.println("Enter divisor bits: ");
        for (int i = 0; i < m; i++) {
            divisor[i] = sc.nextInt();
        }
        cRCBitAdd(n,m,data,divisor);
        int[] received = new int[n+m];
        System.out.println();
        System.out.println("The received is: ");
        for (int i = 0; i < n+m-1; i++) {
            received[i] = sc.nextInt();
        }
        System.out.println();
        receive(received,n,m,divisor);
    }
}
