package cn.nju.game.role;

import cn.nju.game.skill.ComposedSkill;

/**
 * 可攻击接口
 */
public interface Attacker {

    /**
     * 攻击
     */
    public void attack();
    
//    /**
//     * 使用技能攻击
//     * @param skills 所使用的技能
//     */
//    public void attack(ComposedSkill skills);

}