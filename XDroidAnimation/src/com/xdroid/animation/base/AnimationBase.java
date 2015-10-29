package com.xdroid.animation.base;

import com.xdroid.animation.interfaces.CombinableMethod;
import com.xdroid.animation.utils.ViewHelper;

import android.animation.Animator.AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.View;

/**
 * 动画基类
 * 
 * @author Robin
 * @since 2015-07-22 14:44:30
 *
 */
public abstract class AnimationBase<T> implements CombinableMethod<T>{

	/** 目标动画view */
	protected View targetView;
	
	/** 插值器 */
	protected TimeInterpolator interpolator;
	
	/** 动画时间 */
	protected long duration;
	
	/** 动画执行回调 */
	protected AnimatorListener listener;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public T setInterpolator(TimeInterpolator interpolator) {
		this.interpolator = interpolator;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T setDuration(long duration) {
		this.duration = duration;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T setListener(AnimatorListener listener) {
		this.listener = listener;
		return (T) this;
	}


	@SuppressWarnings("unchecked")
	@Override
	public T setPivotX(int pivotX) {
		ViewHelper.setPivotX(targetView, pivotX);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T setPivotY(int pivotY) {
		ViewHelper.setPivotY(targetView, pivotY);
		return (T) this;
	}

	@Override
	public long getDuration() {
		return duration;
	}
	
}
