package com.betterlife.dell.ultimateenglishlearningapp.modals;

/**
 * Created by Dell on 5/23/2018.
 */

public class VocalLevels {

    private String levelName;
    private String levelIcon;


    public VocalLevels(String levelName, String levelIcon) {
        this.levelName = levelName;
        this.levelIcon = levelIcon;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getLevelIcon() {
        return levelIcon;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public void setLevelIcon(String levelIcon) {
        this.levelIcon = levelIcon;
    }
}
