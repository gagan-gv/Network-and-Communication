import java.util.*;

public class ISPProblem {
    static Scanner sc = new Scanner(System.in);

    public static int log2(int n){
        return (int) (Math.log(n) / Math.log(2));
    }

    public static void main(String[] args){
        int[] ipAddress = new int[4];
        System.out.println("Enter each octet of the granted block with spaces and don't use '.' " +
                "as a separator: ");
        for (int i = 0; i < 4; i++) {
            ipAddress[i] = sc.nextInt();
        }
        System.out.println("Enter the prefix value: ");
        int prefixValue = sc.nextInt();
        String ip = "";
        for (int i = 0; i < 4; i++) {
            ip += Integer.toString(ipAddress[i]);
            if(i != 3){
                ip += '.';
            }else{
                ip += "/";
            }
        }
        ip += Integer.toString(prefixValue);
        System.out.println("The starting address is: " + ip);
        System.out.println("Enter the number of groups: ");
        int group = sc.nextInt();
        int totalAddressUsed = 0;
        for (int i = 0; i < group; i++) {
            int[] temp = new int[4];
            for (int j = 0; j < 4; j++) {
                temp[j] = ipAddress[j];
            }
            System.out.println("Enter number of customers in group " + (i+1) + ": ");
            int customers = sc.nextInt();
            System.out.println("Enter number of addresses required: ");
            int addReq = sc.nextInt();
            int groupPrefixLength = 32 - log2(addReq);
            for (int j = 0; j < customers; j++) {
                temp[3] += addReq - 1;
                if(temp[3] == 255 && j != customers - 1){
                    temp[2] += 1;
                    temp[3] = 0;
                }else if(temp[3] != 255){
                    temp[3] += 1;
                }else{
                    temp[3] += 0;
                }
            }
            System.out.printf("Starting address of the group is %d.%d.%d.%d/%d \n",ipAddress[0],
                    ipAddress[1], ipAddress[2],ipAddress[3], groupPrefixLength);
            System.out.printf("Ending address of the group is %d.%d.%d.%d/%d \n",temp[0],temp[1],
                    temp[2],temp[3], groupPrefixLength);
            if(temp[3] == 255){
                temp[2] += 1;
                temp[3] = 0;
            }
            if(temp[2] == 255){
                temp[1] += 1;
                temp[2] = 0;
            }
            if(temp[1] == 255){
                temp[0] += 1;
                temp[1] = 0;
            }
            int total = customers * addReq;
            totalAddressUsed += total;
            System.out.println("Total address used in group " + (i+1) + ": " + total);
            for (int j = 0; j < 4; j++) {
                ipAddress[j] = temp[j];
            }
        }
        System.out.println("Number of allocated address = " + totalAddressUsed);
    }
}