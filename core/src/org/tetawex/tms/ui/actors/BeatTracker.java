package org.tetawex.tms.ui.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import org.tetawex.tms.ecs.misc.stats.RawStats;
import org.tetawex.tms.util.StatToIntConverter;
import org.tetawex.tms.util.StatType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tetawex on 14.02.17.
 */
public class BeatTracker extends Actor {
    public interface WeaponForgedListener{
        void onWeaponForged(RawStats rawStats,float powerLevel);
    }

    private List<WeaponForgedListener> listeners=new ArrayList<WeaponForgedListener>();

    private Beat[] beats;

    private Random random=new Random();

    private float elapsedTime;
    private float beatInterval;

    private int beatsToForge=16;
    private int beatsHit=0;

    private float powerLevel=1;
    private float powerLevelMultiplier=1;

    private boolean beatHit=false;
    private boolean beatsCharging;

    private RawStats weaponStats=null;

    public BeatTracker(Beat[] beats,float beatInterval){
        this.beats=beats;
        this.beatInterval=beatInterval;
        this.weaponStats=new RawStats();

        for (final Beat beat:beats) {
            beat.addBeatListener(new Beat.BeatListener() {
                @Override
                public void onBeatHit(StatType statType) {
                    beatsHit++;
                    if(!beatHit) {
                        beatHit = true;
                        powerLevel++;
                        powerLevelMultiplier+=0.01f;

                    }
                    else{
                        powerLevelMultiplier-=0.01f;
                        powerLevel--;
                    }
                }
            });
        }
    }
    public RawStats getWeaponRawStats(){
        return weaponStats;
    }
    public void act(float deltaTime){
        elapsedTime+=deltaTime;
        if(elapsedTime>=beatInterval*1.4){
            elapsedTime=deltaTime+beatInterval*0.4f;
            beatsCharging=false;
            dischargeBeats();
        }

        if(!beatsCharging&&elapsedTime>=beatInterval*0.6) {
            chargeBeats();
            beatHit=false;
            beatsCharging=true;
        }
    }
    private void weaponForged(){
        for (WeaponForgedListener wl :
                listeners) {
            wl.onWeaponForged(weaponStats,powerLevel*powerLevelMultiplier);
        }
    }
    private void chargeBeats(){
        int limit=2+random.nextInt(2);
        for (int i=0;i<limit;i++) {
            tempBeatArray[i]=true;
        }
        for (int i=limit;i<tempBeatArray.length;i++) {
            tempBeatArray[i]=false;
        }
        shuffleArray(tempBeatArray);
        for (int i=0;i<beats.length;i++) {
            if(tempBeatArray[i])
                beats[i].charge(beatInterval, StatToIntConverter.intToStat(random.nextInt(4)));
        }
    }
    private void dischargeBeats(){
        for (Beat beat:beats) {
            beat.discharge();
        }
    }

    public void addWeaponForgedlistener(WeaponForgedListener wl){
        listeners.add(wl);
    }
    //Looked this up on the web. Too dumb to implement it myself
    // Implementing Fisher–Yates shuffle
    private boolean[] tempBeatArray=new boolean[4];
    public void shuffleArray(boolean[] ar) {
        for (int i = ar.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Simple swap
            boolean a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
