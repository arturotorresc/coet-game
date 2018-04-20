/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Random
 */
public class SwitchMusic {
    
    private Timer timer;
    private TimerTask task;
    private boolean looping;
    
    public SwitchMusic(SoundClip a, SoundClip b, boolean looping, int ms){
        this.looping = looping;
        timer = new Timer();
        if(this.looping){
            a.setLooping(false);
        }
        a.stop();
        b.play();
        task = new TimerTask() {
            public void run() {
                if(looping){
                    a.setLooping(true);
                }
                b.stop();
                a.play();
            }
        };
        // ms is milliseconds.
        timer.schedule(task, ms);
    }
    
    public TimerTask getTask() {
        return task;
    }

    public Timer getTimer() {
        return timer;
    }
}
