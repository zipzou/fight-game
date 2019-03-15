/**
 * 
 */
package cn.nju.game.fight;

import java.util.Observable;

import cn.nju.game.role.Damageable;

/**
 * 可被观察的受伤事件
 * @author frank
 *
 */
public abstract class ObservableDamagedEvent extends Observable implements Damageable {
	
}