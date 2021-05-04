package FlowControl;
import java.util.*;

public class SelectiveRepeatARQ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the window size: ");
        int window = sc.nextInt();

        boolean loop = true;
        int sent = 0;
        for (int i = 0; i < window; i++) {
            System.out.println("Frame " + sent + " is sent");
            sent++;
            if(sent == window){
                break;
            }
        }
        while (loop){
            System.out.println("Enter the frame not received: ");
            ARQTimer arqTimer = new ARQTimer(1);
            if(sent != window) {
                System.out.println("Frame " + sent + " is sent again");
            }
            int ack = sc.nextInt();
            sent = ack;
            arqTimer.deleteARQTimer();
            if(sent == window){
                System.out.println("All the frames have been received.");
                loop = false;
            }
        }
    }
}
