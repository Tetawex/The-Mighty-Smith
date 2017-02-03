package org.tetawex.tms.ecs.misc.stats;

import org.tetawex.tms.util.WeaponNameGenerator;

/**
 * Created by tetawex on 28.01.17.
 */
public class WeaponStats extends Stats{
    public static final float DEFAULT_RANGE=16;
    public static final float DEFAULT_ATTACK_SPEED=1;

    public WeaponStats(float powerLevel, RawStats rawStats) {
        super(powerLevel, rawStats);
        if(rawStats.getEndurancePercentage()>70)
            if(rawStats.getAgilityPercentage()>=rawStats.getStrengthPercentage())
            {
                setName(WeaponNameGenerator.generateName("Shield"));
                setAttackDamage(0.5f*getRawStats().getStrengthPercentage()*powerLevel);
                setAttackRange(0.5f*DEFAULT_RANGE);
                setAttackSpeed(0.5f*getRawStats().getAgilityPercentage()*DEFAULT_ATTACK_SPEED);
                setMaxHealth(3f*getRawStats().getEndurancePercentage()*getPowerLevel());
            }
            else
            {
                setName(WeaponNameGenerator.generateName("Greatshield"));
                setAttackDamage(1f*getRawStats().getStrengthPercentage()*powerLevel);
                setAttackRange(0.3f*DEFAULT_RANGE);
                setAttackSpeed(0.2f*getRawStats().getAgilityPercentage()*DEFAULT_ATTACK_SPEED);
                setMaxHealth(4f*getRawStats().getEndurancePercentage()*getPowerLevel());
            }
        else
        {
            setName(WeaponNameGenerator.generateName("Longsword"));
            setAttackDamage(getRawStats().getStrengthPercentage()*powerLevel);
            setAttackRange(DEFAULT_RANGE);
            setAttackSpeed(getRawStats().getAgilityPercentage()*DEFAULT_ATTACK_SPEED);
            setMaxHealth(getRawStats().getEndurancePercentage()*getPowerLevel());
        }
    }
}
