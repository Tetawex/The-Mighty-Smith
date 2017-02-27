package org.tetawex.tms.util;

import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tetawex on 27.02.17.
 */
public class MusicPlayer {
    public interface MusicResetListener{
        void musicReset();
    }
    public Music currentMusic;
    private List<MusicResetListener> listeners;
    public MusicPlayer(){
        listeners=new ArrayList<MusicResetListener>();
    }

    public void loop(Music music){
        music.play();
        music.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                music.play();

            }
        });
    }
    public void addMusicResetListener(MusicResetListener mrl){
        listeners.add(mrl);
    }
    private void notifyListeners(){
        for (MusicResetListener l:listeners) {
            l.musicReset();
        }
    }
}
