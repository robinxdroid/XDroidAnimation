package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Duration;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Color Animation ,can set up a View of the text color or background color transformation, etc
 * @author Robin
 * @since 2015-08-07 10:31:40
 *
 */
public class ColorAnimation extends AnimationBase<ColorAnimation>{
	
	private int[] values;
	private String propertiesName;
	
	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */
	
	public ColorAnimation(View targetView) {
		this.targetView = targetView;
		interpolator = new LinearInterpolator();
		duration = Duration.DURATION_LONG;
		listener = null;
		
		values = new int[]{ Color.BLACK,Color.WHITE };
		propertiesName = "backgroundColor";
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
		ValueAnimator colorAnim = ObjectAnimator.ofInt(targetView, propertiesName, values);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        AnimatorSet colorSet = new AnimatorSet();
        colorSet.play(colorAnim);
        if (listener != null) {
			colorSet.addListener(listener);
		}
		return colorSet;
	}


	
	/*
	 * ==================================================================
	 * Getter And Setter
	 * ==================================================================
	 */

	public int[] getValues() {
		return values;
	}

	public ColorAnimation setValues(int[] values) {
		this.values = values;
		
		return this;
	}

	public String getPropertiesName() {
		return propertiesName;
	}

	public ColorAnimation setPropertiesName(String propertiesName) {
		this.propertiesName = propertiesName;
		
		return this;
	}
	
	
	

}
