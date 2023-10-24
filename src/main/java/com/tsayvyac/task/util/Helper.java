package com.tsayvyac.task.util;

import com.tsayvyac.task.exception.LevelBoundsException;

public class Helper {

    private Helper() {

    }

    public static Integer checkLevelBounds(Integer level) {
        if (level >= 0 && level < 11)
            return level;
        else throw new LevelBoundsException("Level is out of bounds! The level limits are 0 - 10.");
    }
}
