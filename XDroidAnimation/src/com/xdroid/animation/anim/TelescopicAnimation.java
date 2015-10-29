package com.xdroid.animation.anim;

import com.xdroid.animation.base.AnimationBase;
import com.xdroid.animation.interfaces.Duration;
import com.xdroid.animation.utils.ViewHelper;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Telescopic animation, through dynamic change height and width, show hidden View
 * @author Robin
 * @since 2015-08-08 10:46:50
 *
 */
public class TelescopicAnimation extends AnimationBase<TelescopicAnimation> {
	
	int telescopicMode = TelescopicMode.IN;
	int telescopicTargetMode = TelescopicTargetMode.HEIGHT;
	int start,end;

	public interface TelescopicMode {
		public static final int IN = 0x01, OUT = 0x02;
	}
	
	public interface TelescopicTargetMode {
		public static final int WIDTH = 0x03, HEIGHT = 0x04;
	}
	
	/*
	 * ==================================================================
	 * Constructor
	 * ==================================================================
	 */
	
	public TelescopicAnimation(View targetView) {
		this.targetView = targetView;
		interpolator = new AccelerateDecelerateInterpolator();
		duration = Duration.DURATION_LONG;
		listener = null;
	
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
		ViewHelper.setClipChildren(targetView, true);
		
		final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        targetView.measure(widthSpec, heightSpec);
		
		switch (telescopicTargetMode) {
		case TelescopicTargetMode.WIDTH:
			switch (telescopicMode) {
			case TelescopicMode.IN:
				start = 0;
				if (end == 0) {
					end = targetView.getMeasuredWidth();
				}
				break;

			case TelescopicMode.OUT:
				start = targetView.getMeasuredWidth();
				end = 0;
				break;
			}

			break;

		case TelescopicTargetMode.HEIGHT:
			switch (telescopicMode) {
			case TelescopicMode.IN:
				start = 0;
				if (end == 0) {
					end = targetView.getMeasuredHeight();
				}
				break;

			case TelescopicMode.OUT:
				start = targetView.getMeasuredHeight();
				end = 0;
				break;
			}
			break;
		}

		
		ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = targetView.getLayoutParams();
                switch (telescopicTargetMode) {
				case TelescopicTargetMode.WIDTH:
				       layoutParams.width = value;
					break;

				case TelescopicTargetMode.HEIGHT:
				       layoutParams.height = value;
					break;
				}
         
                targetView.setLayoutParams(layoutParams);
            }
        });
        
        animator.addListener(new Animator.AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				switch (telescopicMode) {
				case TelescopicMode.IN:
					targetView.setVisibility(View.VISIBLE);
					break;

				case TelescopicMode.OUT:
					break;
				}
				
				if (listener != null) {
					listener.onAnimationStart(animation);
				}
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				if (listener != null) {
					listener.onAnimationRepeat(animation);
				}
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				switch (telescopicMode) {
				case TelescopicMode.IN:
					break;

				case TelescopicMode.OUT:
					targetView.setVisibility(View.GONE);
					break;
				}
				
				if (listener != null) {
					listener.onAnimationEnd(animation);
				}
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				if (listener != null) {
					listener.onAnimationCancel(animation);
				}
			}
		});
        
        AnimatorSet animatorSet =new AnimatorSet();
        animatorSet.play(animator);
        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(interpolator);
		
		return animatorSet;
	}


	
	/*
	 * ================================================================== 
	 * Getter And Setter
	 * ==================================================================
	 */
	
	public int getTelescopicMode() {
		return telescopicMode;
	}

	public TelescopicAnimation setTelescopicMode(int telescopicMode) {
		this.telescopicMode = telescopicMode;
		
		return this;
	}

	public int getTelescopicTargetMode() {
		return telescopicTargetMode;
	}

	public TelescopicAnimation setTelescopicTargetMode(int telescopicTargetMode) {
		this.telescopicTargetMode = telescopicTargetMode;
		
		return this;
	}

	public int getStart() {
		return start;
	}

	public TelescopicAnimation setStart(int start) {
		this.start = start;
		
		return this;
	}

	public int getEnd() {
		return end;
	}

	public TelescopicAnimation setEnd(int end) {
		this.end = end;
		
		return this;
	}


}
