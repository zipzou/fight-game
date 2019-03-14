package cn.nju.game.role;
/**
 * 可攻击接口
 */
public interface Attackable {

    /**
     * 攻击
     * @param target
     */
    public void attack(Target target);

}