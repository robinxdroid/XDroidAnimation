package com.xdroid.animation.interfaces;

import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;

/**
 * Syntax function combination
 * 
 * @author Robin
 * @since 2015-07-22 15:42:43
 */
public interface CombinableMethod <T>{

	public void animate();

	public T setInterpolator(TimeInterpolator interpolator);

	public T setDuration(long duration);

	public T setListener(AnimatorListener listener);

	public AnimatorSet createAnimatorSet();
	
	public T setPivotX(int pivotX);
	
	public T setPivotY(int pivotY);

	public long getDuration();

}
