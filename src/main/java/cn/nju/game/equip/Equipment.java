package cn.nju.game.equip;
/**
 * 装备
 */
public abstract class Equipment {

    /**
     * Default constructor
     */
    public Equipment() {
    }

    /**
     * 装备描述
     */
    public String description;

    /**
     * 无力攻击
     */
    public int physicalDamage;

    /**
     * 魔法攻击
     */
    public int magicDamage;

    /**
     * 护甲值
     */
    public int armor;

    /**
     * 魔法抗性
     */
    public int magicalResistance;

    /**
     * 装备名称
     */
    public String name;

    /**
     * 
     */
    public int price;

    /**
     * 
     */
    public float recoverHealth;

    /**
     * 
     */
    public float recoverEnergy;




    /**
     * @param superEquipment
     */
    public void compose(Equipment superEquipment) {
        // TODO implement here
    }

    /**
     * 计算物理伤害
     */
    public void computePhysicalDamage() {
        // TODO implement here
    }

    /**
     * 计算魔法伤害
     */
    public void computeMagicDamage() {
        // TODO implement here
    }

    /**
     * 计算护甲值
     */
    public void computeArmor() {
        // TODO implement here
    }

    /**
     * 计算魔法抗性
     */
    public void computeMagicalResistance() {
        // TODO implement here
    }

    /**
     * 计算装备价格
     */
    public void computePrice() {
        // TODO implement here
    }

}