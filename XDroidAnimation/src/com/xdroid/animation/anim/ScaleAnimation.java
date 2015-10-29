package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Duration;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Scale animation, the default 0.0 - > 1.0 f f
 * 
 * @author Robin
 * @since 2015-07-21 14:40:02
 * 
 */
public class ScaleAnimation extends AnimationBase<ScaleAnimation>{
	
	/** X direction change of attribute values */
	protected float[] valuesX;
	
	/** Y direction change of attribute values */
	protected float[] valuesY;

	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */

	public ScaleAnimation(View targetView) {
		this.targetView = targetView;
		interpolator = new AccelerateDecelerateInterpolator();
		duration = Duration.DURATION_LONG;
		listener = null;
		
		valuesX = new float[]{ 0.0f , 1.0f };
		valuesY = new float[]{ 0.0f , 1.0f };
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
		/*targetView.setScaleX(0f);
		targetView.setScaleY(0f);*/
		targetView.setVisibility(View.VISIBLE);

		AnimatorSet scaleSet = new AnimatorSet();
		scaleSet.playTogether(ObjectAnimator.ofFloat(targetView, View.SCALE_X, valuesX ),
				ObjectAnimator.ofFloat(targetView, View.SCALE_Y, valuesY ));
		scaleSet.setInterpolator(interpolator);
		scaleSet.setDuration(duration);
		if (listener != null) {
			scaleSet.addListener(listener);
		}
		
		return scaleSet;
	}


	/*
	 * ==================================================================
	 * Getter And Setter
	 * ==================================================================
	 */

	public ScaleAnimation setValuesX(float[] valuesX) {
		this.valuesX = valuesX;
		
		return this;
	}

	public ScaleAnimation setValuesY(float[] valuesY) {
		this.valuesY = valuesY;
		
		return this;
	}


}
