package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Duration;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Alpha animation
 * 
 * @author Robin
 * @since2015-07-31 10:25:38
 * 
 */
public class AlphaAnimation extends AnimationBase<AlphaAnimation>{
	
	/** The gradient change of attribute values */
	protected float[] values;

	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */
	
	public AlphaAnimation(View targetView) {
		this.targetView = targetView;
		interpolator = new AccelerateDecelerateInterpolator();
		duration = Duration.DURATION_LONG;
		listener = null;
		
		values = new float[]{ 1.0f };
	}
	
	/*
	 * ==================================================================
	 * Override CombinableMethod
	 * ==================================================================
	 */

	@Override
	public void animate() {
		createAnimatorSet().start();
	}

	@Override
	public AnimatorSet createAnimatorSet() {
		targetView.setAlpha(0f);
		targetView.setVisibility(View.VISIBLE);

		AnimatorSet fadeSet = new AnimatorSet();
		fadeSet.play(ObjectAnimator.ofFloat(targetView, View.ALPHA, values));
		fadeSet.setInterpolator(interpolator);
		fadeSet.setDuration(duration);
		if (listener != null) {
			fadeSet.addListener(listener);
		}
	
		return fadeSet;
	}
	
	
	/*
	 * ==================================================================
	 * Getter And Setter
	 * ==================================================================
	 */

	public AlphaAnimation setValues(float[] values) {
		this.values = values;
		
		return this;
	}

}
