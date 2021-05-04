package FlowControl;
import java.util.*;

public class ARQTimer {
    Timer timer;
    public boolean timeOut;
    int frame;

    public ARQTimer(int seconds){
        timeOut = false;
        timer = new Timer();
        timer.schedule(new ARQTask1(), seconds*1000);
    }

    public ARQTimer(int seconds, int frame){
        timeOut = false;
        timer = new Timer();
        timer.schedule(new ARQTask2(), seconds*1000);
        this.frame = frame;
    }

    class ARQTask1 extends TimerTask{
        @Override
        public void run() {
            timeOut = true;
            System.out.println("Didn't receive acknowledgement");
            timer.cancel();
        }
    }
    class ARQTask2 extends TimerTask{
        @Override
        public void run(){
            timeOut = true;
            System.out.println("Didn't receive acknowledgement");
            System.out.println("Frame " + frame + " is sent again");
            timer.cancel();
        }
    }
    public void deleteARQTimer(){
        timer.cancel();
    }
}
