/**
 * 
 */
package cn.nju.game.role;

/**
 * 竞技场参与中介
 * @author frank
 *
 */
public interface StagePartnerMediator {
	/**
	 * 发送攻击指令
	 * @param source 发送攻击指令的指令来源
	 */
	public void sendAttack(StagePartner source);
	
	/**
	 * 注册参与者
	 * @param partner 参与者
	 */
	public void register(StagePartner partner);
	
	/**
	 * 移除参与者
	 * @param partner 参与者
	 */
	public void unregister(StagePartner partner);
}
