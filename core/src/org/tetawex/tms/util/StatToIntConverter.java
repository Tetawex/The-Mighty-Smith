package org.tetawex.tms.util;

/**
 * Created by tetawex on 21.02.17.
 */
public class StatToIntConverter {
    public static int statToInt(StatType statType){
        switch (statType){
            case STRENGTH:
                return 0;
            case AGILITY:
                return 1;
            case ENDURANCE:
                return 2;
            default:
                return 3;
        }
    }
    public static StatType intToStat(int i){
        switch (i){
            case 0:
                return StatType.STRENGTH;
            case 1:
                return StatType.AGILITY;
            case 2:
                return StatType.ENDURANCE;
            default:
                return StatType.PERCEPTION;
        }
    }
}
