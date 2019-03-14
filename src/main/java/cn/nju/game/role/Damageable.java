package cn.nju.game.role;

/**
 * 可受伤害接口
 */
public interface Damageable {

    /**
     * 减少健康程度
     * @param healthVal 健康值
     */
    public void reduceHealth(int healthVal);

    /**
     * 是否存活
     */
    public boolean isAlive();

}