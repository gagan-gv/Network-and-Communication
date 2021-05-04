package FlowControl;
import java.util.*;

public class StopAndWait {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of frames to be sent: ");
        int noOfFrames = sc.nextInt();
        int sent = 0;
        while(sent < noOfFrames){
            System.out.println("Frame " + sent + " is sent.");
            System.out.println("Enter 1 as acknowledgement is frame received else 0: ");
            int ack = sc.nextInt();
            if(ack == 1 || ack == 0){
                if(ack == 1){
                    sent++;
                }
            }
        }
        System.out.println("All frames are received.");
    }
}
