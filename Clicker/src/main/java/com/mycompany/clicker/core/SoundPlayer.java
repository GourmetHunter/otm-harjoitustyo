/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clicker.core;

import com.mycompany.clicker.utility.SoundLoader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Olli K. Kärki
 */
public class SoundPlayer {

    // Variables ---------------------------------------------------------------
    private Media monsterDeath;
    private MediaPlayer monsterDeathPlayer;

    // Constructor -------------------------------------------------------------
    public SoundPlayer() throws MalformedURLException, URISyntaxException {
        SoundLoader soundLoader = new SoundLoader();
        this.initializeMonsterDeath(soundLoader);
    }

    // Initialization methods --------------------------------------------------
    private void initializeMonsterDeath(SoundLoader soundLoader) throws MalformedURLException, URISyntaxException {
        monsterDeath = soundLoader.loadMedia("/death.wav");
        monsterDeathPlayer = new MediaPlayer(monsterDeath);
    }

    // Public methods ----------------------------------------------------------
    /**
     * Stops the previously played deathsound, and starts a new one.
     */
    public void playMonsterDeath() {
        monsterDeathPlayer.stop();
        monsterDeathPlayer.play();
    }

}
