package org.tetawex.tms.util;

import java.util.Random;

/**
 * Created by tetawex on 28.01.17.
 */
public class WeaponNameGenerator {
    //public static final String[] materials=new String[]{"Adamantium, Mithrill,Titanium,Obsidian,Blurium,Atellamite,Javium," +
            //"Gold,Silver,Steel,Osmium,Tungsten,Bronze,Copper,Tin"};
    public static final Random r=new Random();
    public static final String[] prefixes=new String[]{"Blessed ","Cursed ","Allmigthy ","Insane ","Slightly Buffed ","Plain ",
    "Special ","Navy ","Augmented ","Flawless ","King's "};
    public static final String[] postfixes=new String[]{" of God's Blessing"," of Luck","of Might ","of Insanity",
            " of Bad Luck"," of Magic Penetration",
            " of Swift Movement"," of Disaster"," of Doom","  of Marksmanship"," of Precision"};
    public static String generateName(String name) {
        return prefixes[r.nextInt(prefixes.length)]+name+postfixes[r.nextInt(postfixes.length)];
    }
}
