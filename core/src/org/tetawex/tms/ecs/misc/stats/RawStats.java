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

    public float getDurability() {
        return durability;
    }

    public void setDurability(float durability) {
        this.durability = durability;
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
        return durability/(getStatSum());
    }

    public float getStatSum()
    {
        return perception+durability+strength+agility;
    }

    private float perception;
    private float durability;
    private float strength;
    private float agility;
}
