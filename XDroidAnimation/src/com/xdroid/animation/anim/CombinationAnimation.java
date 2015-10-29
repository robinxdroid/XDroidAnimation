package com.xdroid.animation.anim;

import java.util.ArrayList;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.CombinableMethod;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;

/**
 * Combination of animation
 * 
 * @author Robin
 * @since 2015-08-06 19:12:06
 * 
 */
public class CombinationAnimation extends AnimationBase<CombinationAnimation>{

	ArrayList<CombinableMethod<?>> combinableList;

	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */
	
	public CombinationAnimation() {
		interpolator = null;
		duration = 0;
		combinableList = new ArrayList<CombinableMethod<?>>();
		listener = null;
	}

	public CombinationAnimation add(CombinableMethod<?> combinable) {
		combinableList.add(combinable);
		return this;
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
		ArrayList<Animator> animatorList = new ArrayList<Animator>();
		for (int i = 0; i < combinableList.size(); i++) {
			if (duration > 0) {
				combinableList.get(i).setDuration(duration);
			}
			animatorList.add(combinableList.get(i).createAnimatorSet());
		}

		AnimatorSet parallelSet = new AnimatorSet();
		parallelSet.playTogether(animatorList);

		if (interpolator != null) {
			parallelSet.setInterpolator(interpolator);
		}

		parallelSet.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				if (listener != null) {
					listener.onAnimationEnd(animation);
				}
			}
		});
		return parallelSet;
	}


}
