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

}