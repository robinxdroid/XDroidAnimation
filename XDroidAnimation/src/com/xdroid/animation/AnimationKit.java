package com.xdroid.animation;

import com.xdroid.animation.anim.AlphaAnimation;
import com.xdroid.animation.anim.BlindAnimation;
import com.xdroid.animation.anim.ColorAnimation;
import com.xdroid.animation.anim.CombinationAnimation;
import com.xdroid.animation.anim.FlipAnimation;
import com.xdroid.animation.anim.FlipToAnimation;
import com.xdroid.animation.anim.PathAnimation;
import com.xdroid.animation.anim.PuffAnimation;
import com.xdroid.animation.anim.RotateAnimation;
import com.xdroid.animation.anim.ScaleAnimation;
import com.xdroid.animation.anim.ShakeAnimation;
import com.xdroid.animation.anim.SlideAnimation;
import com.xdroid.animation.anim.SlideUnderneathAnimation;
import com.xdroid.animation.anim.SvgAnimationView;
import com.xdroid.animation.anim.TelescopicAnimation;
import com.xdroid.animation.anim.TransferAnimation;

import android.content.Context;
import android.view.View;

public class AnimationKit {

	/*
	 * private static volatile AnimationKit INSTANCE = null;
	 * 
	 * public static AnimationKit getInstance() { if (INSTANCE == null) {
	 * synchronized (AnimationKit.class) { if (INSTANCE == null) { INSTANCE =
	 * new AnimationKit(); } } } return INSTANCE; }
	 */

	/*public static <T> void scale(View targetView, T... params) {
		if (targetView == null) {
			return;
		}
		ScaleAnimation mScaleInAnimation = new ScaleAnimation(targetView);

		if (params.length >= 0) {
			for (int i = 0; i < params.length; i++) {
				if (params[i] instanceof Integer) {
					mScaleInAnimation.setDuration((Integer) params[i]);
				} else if (params[i] instanceof TimeInterpolator) {
					mScaleInAnimation.setInterpolator((TimeInterpolator) params[i]);
				} else if (params[i] instanceof AnimatorListener) {
					mScaleInAnimation.setListener((AnimatorListener) params[i]);
				} else if (params[i] instanceof float[]) {
					mScaleInAnimation.setValuesX((float[]) params[i]).setValuesY((float[]) params[i]);
				}
			}
			mScaleInAnimation.animate();
		}

	}*/

	public static AlphaAnimation createAlphaAnimation(View targetView) {
		return new AlphaAnimation(targetView);
	}

	public static BlindAnimation createBlindAnimation(View targetView) {
		return new BlindAnimation(targetView);
	}

	public static ColorAnimation createColorAnimation(View targetView) {
		return new ColorAnimation(targetView);
	}

	public static CombinationAnimation createCombinationAnimation() {
		return new CombinationAnimation();
	}

	public static FlipAnimation createFlipAnimation(View targetView) {
		return new FlipAnimation(targetView);
	}

	public static FlipToAnimation createFlipToAnimation(View targetView) {
		return new FlipToAnimation(targetView);
	}

	public static PathAnimation createPathAnimation(View targetView) {
		return new PathAnimation(targetView);
	}

	public static PuffAnimation createPuffAnimation(View targetView) {
		return new PuffAnimation(targetView);
	}

	public static RotateAnimation createRotateAnimation(View targetView) {
		return new RotateAnimation(targetView);
	}

	public static ScaleAnimation createScaleAnimation(View targetView) {
		return new ScaleAnimation(targetView);
	}

	public static ShakeAnimation createShakeAnimation(View targetView) {
		return new ShakeAnimation(targetView);
	}

	public static SlideAnimation createSlideAnimation(View targetView) {
		return new SlideAnimation(targetView);
	}

	public static SlideUnderneathAnimation createSlideUnderneathAnimation(View targetView) {
		return new SlideUnderneathAnimation(targetView);
	}

	public static TelescopicAnimation createTelescopicAnimation(View targetView) {
		return new TelescopicAnimation(targetView);
	}

	public static TransferAnimation createTransferAnimation(View targetView) {
		return new TransferAnimation(targetView);
	}

	public static SvgAnimationView createSvgAnimationView(Context context) {
		return new SvgAnimationView(context);
	}

}
