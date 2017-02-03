package org.tetawex.tms.ecs.misc.stats;

/**
 * Created by tetawex on 24.01.17.
 */
public class RawStats
{
    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getAgility() {
        return agility;
    }

    public void setAgility(float agility) {
        this.agility = agility;
    }

    public float getPerception() {
        return perception;
    }

    public void setPerception(float perception) {
        this.perception = perception;
    }

    public float getEndurance() {
        return endurance;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }


    public float getStrengthPercentage() {
        return strength/(getStatSum());
    }

    public float getAgilityPercentage() {
        return agility/(getStatSum());
    }

    public float getPerceptionPercentage() {
        return perception/(getStatSum());
    }

    public float getDurabilityPercentage() {
        return endurance /(getStatSum());
    }

    public float getStatSum()
    {
        return perception+ endurance +strength+agility;
    }

    public RawStats(float strength, float agility, float endurance, float perception) {
        this.strength = strength;
        this.agility = agility;
        this.endurance = endurance;
        this.perception = perception;
    }

    private float strength;
    private float agility;
    private float endurance;
    private float perception;
}
