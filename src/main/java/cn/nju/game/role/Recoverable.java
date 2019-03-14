package cn.nju.game.role;
/**
 * 健康值可恢复接口
 */
public interface Recoverable {

    /**
     * 加血
     * @param healthVal 健康值
     */
    public void improveHealth(int healthVal);
    
    /**
     * 加能量
     * @param energyVal 能量值
     */
    public void improveEnergy(int energyVal);

}