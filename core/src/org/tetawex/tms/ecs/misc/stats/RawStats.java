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

    public float getEndurancePercentage() {
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
    public RawStats() {
        this(1,1,1,1);
    }
    public void increaseStat(int statNum,float amount){
        switch (statNum){
            case 0:
                strength+=amount;
                break;
            case 1:
                agility+=amount;
                break;
            case 2:
                endurance+=amount;
                break;
            default:
                perception+=amount;
                break;
        }
    }
    public void decrementStats(){
        if(strength>1)
            strength-=1;
        if(agility>1)
            agility-=1;
        if(endurance>1)
            endurance-=1;
        if(perception>1)
            perception-=1;
    }
    public void resetStats(){
        strength=1;
        agility=1;
        endurance=1;
        perception=1;
    }
    private float strength;
    private float agility;
    private float endurance;
    private float perception;
}
