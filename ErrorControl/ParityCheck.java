package ErrorControl;

import java.util.Scanner;

public class ParityCheck {
    static int[] parityBitAdd(int [] a){
        int count = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] == 1){
                count++;
            }
        }
        if(count % 2 == 0){
            a[a.length-1] = 0;
        }
        else{
            a[a.length-1] = 1;
        }
        System.out.println("Receiver receives: ");
        for (int i = 0; i < a.length; i++){
            if(i == a.length - 1){
                System.out.print("|");
            }
            System.out.print(a[i]);
        }
        return a;
    }
    static void parityCheck(int[] a){
        int count = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] == 1) {
                count++;
            }
        }
        if(count % 2 == 0){
            System.out.println("The message is accepted.");
        }
        else{
            System.out.println("The message is discarded.");
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the message length: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.println("The sender sends: ");
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        a = parityBitAdd(a);
        System.out.println();
        parityCheck(a);
    }
}
