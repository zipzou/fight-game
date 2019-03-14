package cn.nju.game.role.factory;

import cn.nju.game.role.Commander;

/**
 * 召唤师工厂接口
 */
public interface CommanderFactory {


    /**
     * 创建召唤师
     * 
     * @param name 召唤师名称
     * @return 召唤师角色
     */
    public Commander produceCommander(String name);

}