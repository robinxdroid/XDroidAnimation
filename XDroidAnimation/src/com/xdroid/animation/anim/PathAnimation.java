package com.xdroid.animation.anim;

import com.xdroid.animation.anim.path.AnimatorPath;
import com.xdroid.animation.anim.path.PathEvaluator;
import com.xdroid.animation.anim.path.PathPoint;
import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Duration;
import com.xdroid.animation.utils.ViewHelper;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Path animation 
 * @author Robin
 * @since 2015-08-08 14:18:19
 *
 */
public class PathAnimation extends AnimationBase<PathAnimation> {

	private AnimatorPath path;
	
	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */
	
	public PathAnimation(View targetView) {
		this.targetView = targetView;
		interpolator = new AccelerateDecelerateInterpolator();
		duration = Duration.DURATION_LONG;
		listener = null;
	}
	

	public void setViewLoc(PathPoint newLoc) {
		targetView.setTranslationX(newLoc.mX);
		targetView.setTranslationY(newLoc.mY);
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
		ViewHelper.setClipChildren(targetView, false);

		if (path == null) {
			throw new IllegalArgumentException("You have to set up a AnimatorPath for PathAnimation!");
		}

		ObjectAnimator anim = ObjectAnimator.ofObject(this, "ViewLoc", new PathEvaluator(), path.getPoints().toArray());
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.play(anim);
		animatorSet.setDuration(duration);
		animatorSet.setInterpolator(interpolator);
		if (listener != null) {
			animatorSet.addListener(listener);
		}

		return animatorSet;
	}

	/*
	 * ================================================================== 
	 * Getter And Setter
	 * ==================================================================
	 */
	
	public PathAnimation setPath(AnimatorPath path) {
		this.path = path;
		
		return this;
	}
	
}
