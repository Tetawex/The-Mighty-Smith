package org.tetawex.tms.ecs.misc.stats;

import com.badlogic.gdx.Gdx;

/**
 * Created by Tetawex on 21.01.2017.
 */
public class Stats
{
    private float powerLevel;

    public float getCurrentHealth() {
        return currentHealth;
    }

    private float currentHealth;
    private float maxHealth;
    private float attackSpeed;
    private float elapsedTimeSinceAttack;
    private float attackDamage;

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    private float movementSpeed;
    public void attack(){
        elapsedTimeSinceAttack=0;
    }
    public boolean canAttack(){
        return elapsedTimeSinceAttack>=getAttackInterval();
    }
    public void receiveDamage(float damage){
        currentHealth-=damage;
    }
    public float getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(float powerLevel) {
        this.powerLevel = powerLevel;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public float getAttackInterval() {
        return 1f/attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
    }

    public float getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(float attackRange) {
        this.attackRange = attackRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RawStats getRawStats() {
        return rawStats;
    }

    public void setRawStats(RawStats rawStats) {
        this.rawStats = rawStats;
    }

    private float attackRange;
    private String name;
    private RawStats rawStats;
    public Stats(float powerLevel,RawStats rawStats,String name)
    {
        this.powerLevel=powerLevel;
        this.rawStats=rawStats;

        maxHealth=powerLevel*rawStats.getEndurancePercentage();
        currentHealth=maxHealth;

        attackSpeed=3f*rawStats.getAgilityPercentage()+powerLevel*0.00000001f*rawStats.getAgilityPercentage();
        attackRange=16+24*rawStats.getPerceptionPercentage();
        attackDamage=powerLevel*0.25f*rawStats.getStrengthPercentage();
        movementSpeed=5f+30f*rawStats.getAgilityPercentage()+powerLevel*0.000000001f*rawStats.getAgilityPercentage();
    }
    public Stats(float powerLevel,RawStats rawStats)
    {
        this(powerLevel,rawStats,"");
    }
    public void tick(float deltaTime){
        if(!canAttack())
            elapsedTimeSinceAttack+=deltaTime;
    }
}
