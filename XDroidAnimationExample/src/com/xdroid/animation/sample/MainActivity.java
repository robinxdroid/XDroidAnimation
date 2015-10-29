package com.xdroid.animation.sample;

import com.xdroid.animation.AnimationKit;
import com.xdroid.animation.anim.BlindAnimation.BlindMode;
import com.xdroid.animation.anim.CombinationAnimation;
import com.xdroid.animation.anim.FlipAnimation;
import com.xdroid.animation.anim.FlipToAnimation;
import com.xdroid.animation.anim.PuffAnimation.PuffMode;
import com.xdroid.animation.anim.RotateAnimation;
import com.xdroid.animation.anim.SlideAnimation.SlideMode;
import com.xdroid.animation.anim.SvgAnimationView;
import com.xdroid.animation.anim.SvgAnimationView.AnimatorBuilder.ListenerEnd;
import com.xdroid.animation.anim.SvgAnimationView.AnimatorBuilder.ListenerStart;
import com.xdroid.animation.anim.TelescopicAnimation.TelescopicMode;
import com.xdroid.animation.anim.TelescopicAnimation.TelescopicTargetMode;
import com.xdroid.animation.anim.path.AnimatorPath;
import com.xdroid.animation.interfaces.Direction;
import com.xdroid.animation.interfaces.Orientation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		svgAnimation();
		interpolate();
		slideUnderneathAnimation();
		slideAnimation();
		rotateAnimation();
		flipAnimation();
		scaleAnimation();
		alphaAnimation();
		shakeAnimationHorizontal();
		shakeAnimationVertical();
		blindAnimation();
		flipToAnimation();
		puffAnimation();
		colorAnimation();
		telescopicAnimation();
		pathAnimation();
		combinationAnimation();
		transferAnimation();

	}

	private void svgAnimation() {


		final SvgAnimationView svgView = (SvgAnimationView) findViewById(R.id.svg_view);
		
		svgView.setSvgResource(R.raw.monitor);
		svgView.setPathColor(Color.BLACK);
		svgView.setPathWidth(2);
		svgView.setBackgroundColor(Color.TRANSPARENT);
		
		svgView.getPathAnimator()
	    .delay(100)
	    .duration(5000)
	    .listenerStart(new ListenerStart() {
			
			@Override
			public void onAnimationStart() {
				Toast.makeText(MainActivity.this, "SVG执行开始", Toast.LENGTH_SHORT).show();
			}
		})
	    .listenerEnd(new ListenerEnd() {
			
			@Override
			public void onAnimationEnd() {
				Toast.makeText(MainActivity.this, "SVG执行完毕", Toast.LENGTH_SHORT).show();
			}
		})
	    .interpolator(new AccelerateDecelerateInterpolator())
	    .start();
		
		svgView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				svgView.getPathAnimator()
				.delay(100)
			    .duration(3000)    
			    .interpolator(new AccelerateDecelerateInterpolator())
			    .listenerEnd(new ListenerEnd() {
					
					@Override
					public void onAnimationEnd() {
						AnimationKit.createAlphaAnimation(svgView).setDuration(1000).setInterpolator(new AccelerateInterpolator()).setValues(new float[]{0.1f,1.0f}).animate();
					}
				})
			    .start();
				svgView.setFillAfter(true);
			}
		});
		
/*     final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(length / 4f, 0.0f);
        path.lineTo(length, height / 2.0f);
        path.lineTo(length / 4f, height);
        path.lineTo(0.0f, height);
        path.lineTo(length * 3f / 4f, height / 2f);
        path.lineTo(0.0f, 0.0f);
        path.close();

        svgView.setPath(path);*/

	}

	private void interpolate() {
		final Button button = (Button) findViewById(R.id.btn_interpolate);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, InterpolateActivity.class);
				startActivity(intent);

			}

		});
	}

	private void slideUnderneathAnimation() {
		final Button button = (Button) findViewById(R.id.btn_slide_underneath);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 执行 位移 移入
				AnimationKit.createSlideUnderneathAnimation(button).setDuration(1000).setInterpolator(new DecelerateInterpolator())
						.setDirection(Direction.DIRECTION_UP).setSlideMode(SlideMode.IN)
						.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

						// 执行 位移 移出+渐变动画
						AnimationKit.createSlideUnderneathAnimation(button).setDuration(1000)
								.setInterpolator(new DecelerateInterpolator()).setDirection(Direction.DIRECTION_RIGHT)
								.setSlideMode(SlideMode.OUT).setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								super.onAnimationEnd(animation);
								// 执行 位移 移入+渐变动画
								AnimationKit.createSlideUnderneathAnimation(button).setDuration(1000)
										.setInterpolator(new DecelerateInterpolator())
										.setDirection(Direction.DIRECTION_RIGHT).setSlideMode(SlideMode.IN).animate();
								AnimationKit.createAlphaAnimation(button).setDuration(1000)
										.setInterpolator(new DecelerateInterpolator())
										.setValues(new float[] { 0.0f, 1.0f }).animate();
							}
						}).animate();
						AnimationKit.createAlphaAnimation(button).setDuration(1000).setInterpolator(new DecelerateInterpolator())
								.setValues(new float[] { 1.0f, 0.0f }).animate();

					}
				}).animate();
			}
		});
	}

	private void slideAnimation() {
		final Button button = (Button) findViewById(R.id.btn_slide);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 执行 位移 移入
				AnimationKit.createSlideAnimation(button).setDuration(1000).setInterpolator(new DecelerateInterpolator())
						.setDirection(Direction.DIRECTION_DOWN).setSlideMode(SlideMode.IN)
						.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

						// 执行 位移 移出+渐变动画
						AnimationKit.createSlideAnimation(button).setDuration(1000).setInterpolator(new DecelerateInterpolator())
								.setDirection(Direction.DIRECTION_RIGHT).setSlideMode(SlideMode.OUT)
								.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								super.onAnimationEnd(animation);
								// 执行 位移 移入+渐变动画
								AnimationKit.createSlideAnimation(button).setDuration(1000)
										.setInterpolator(new DecelerateInterpolator())
										.setDirection(Direction.DIRECTION_RIGHT).setSlideMode(SlideMode.IN).animate();
								AnimationKit.createAlphaAnimation(button).setDuration(1000)
										.setInterpolator(new DecelerateInterpolator())
										.setValues(new float[] { 0.0f, 1.0f }).animate();
							}
						}).animate();
						AnimationKit.createAlphaAnimation(button).setDuration(1000).setInterpolator(new DecelerateInterpolator())
								.setValues(new float[] { 1.0f, 0.0f }).animate();

					}
				}).animate();
			}
		});
	}

	private void rotateAnimation() {
		final Button button = (Button) findViewById(R.id.btn_rotate);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnimationKit.createRotateAnimation(button).setDuration(1000).setInterpolator(new DecelerateInterpolator()).setPivotX(0)
						.setPivotY(0).setDegrees(360).setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

						AnimationKit.createRotateAnimation(button).setDuration(1000).setInterpolator(new OvershootInterpolator(1.5f))
								.setPivot(RotateAnimation.PIVOT_BOTTOM_RIGHT).setDegrees(-360).animate();
					}
				}).animate();
			}
		});
	}

	private void flipAnimation() {
		final Button button = (Button) findViewById(R.id.btn_flip);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnimationKit.createFlipAnimation(button).setDuration(500).setInterpolator(new DecelerateInterpolator())
						.setPivot(FlipAnimation.PIVOT_LEFT).setOrientation(Orientation.HORIZONTAL).setDegrees(720)
						.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

						AnimationKit.createFlipAnimation(button).setDuration(500)
								.setInterpolator(new AccelerateDecelerateInterpolator())
								.setPivot(FlipAnimation.PIVOT_CENTER).setOrientation(Orientation.HORIZONTAL)
								.setDegrees(-720).setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								super.onAnimationEnd(animation);
								AnimationKit.createFlipAnimation(button).setDuration(1000)
										.setInterpolator(new OvershootInterpolator(1.5f))
										.setPivot(FlipAnimation.PIVOT_BOTTOM).setOrientation(Orientation.VERTICAL)
										.setDegrees(720).animate();
							}
						}).animate();
					}
				}).animate();
			}
		});
	}

	private void scaleAnimation() {

		final Button button = (Button) findViewById(R.id.btn_scale);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnimationKit.createScaleAnimation(button).setInterpolator(new OvershootInterpolator(2.0f)).setDuration(2000).setListener( new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

						// AnimationKit.scale( textView , new
						// float[]{0.0f,1.0f,0.5f,2.0f} );
						AnimationKit.createScaleAnimation(button).setDuration(2000).setInterpolator(new DecelerateInterpolator())
								.setPivotX(0).setPivotY(0).setValuesX(new float[] { 0.0f, 1.0f, 0.5f, 2.0f, 1.0f })
								.setValuesY(new float[] { 0.0f, 1.0f, 0.5f, 2.0f, 1.0f }).animate();

					}
				}).animate();;

			}
		});

	}

	private void alphaAnimation() {
		final Button button = (Button) findViewById(R.id.btn_alpha);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnimationKit.createAlphaAnimation(button).setDuration(3000).setInterpolator(new LinearInterpolator()).setPivotX(0)
						.setPivotY(0).setValues(new float[] { 1.0f, 0.0f, 2.0f, 0.5f })
						.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();
						AnimationKit.createAlphaAnimation(button).setDuration(2000).setInterpolator(new DecelerateInterpolator())
								.setValues(new float[] { 1.0f, 0.0f, 1.0f }).animate();
					}
				}).animate();
			}
		});
	}

	private void shakeAnimationHorizontal() {
		final Button button = (Button) findViewById(R.id.btn_shake);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnimationKit.createShakeAnimation(button).setDuration(300).setInterpolator(new AccelerateDecelerateInterpolator())
						.setNumOfShakes(3).setShakeDistance(30).setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();
					}
				}).animate();
			}
		});
	}

	private void shakeAnimationVertical() {
		final Button button = (Button) findViewById(R.id.btn_shake_vertical);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnimationKit.createShakeAnimation(button).setDuration(300).setInterpolator(new AccelerateDecelerateInterpolator())
						.setNumOfShakes(3).setShakeDistance(30).setOrientation(Orientation.VERTICAL)
						.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();
					}
				}).animate();
			}
		});
	}

	private void blindAnimation() {
		final Button button = (Button) findViewById(R.id.btn_blind);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 执行 顶部移出
				AnimationKit.createBlindAnimation(button).setDuration(2000).setInterpolator(new LinearInterpolator())
						.setBlindMode(BlindMode.OUT).setDirection(Direction.DIRECTION_UP)
						.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

						// 执行 底部移入
						AnimationKit.createBlindAnimation(button).setDuration(2000).setInterpolator(new LinearInterpolator())
								.setBlindMode(BlindMode.IN).setDirection(Direction.DIRECTION_DOWN)
								.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								super.onAnimationEnd(animation);
								Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

								// 执行 左边移出
								AnimationKit.createBlindAnimation(button).setDuration(2000).setInterpolator(new LinearInterpolator())
										.setBlindMode(BlindMode.OUT).setDirection(Direction.DIRECTION_LEFT)
										.setListener(new AnimatorListenerAdapter() {
									@Override
									public void onAnimationEnd(Animator animation) {
										super.onAnimationEnd(animation);
										Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

										// 执行 右边移入
										AnimationKit.createBlindAnimation(button).setDuration(2000)
												.setInterpolator(new LinearInterpolator()).setBlindMode(BlindMode.IN)
												.setDirection(Direction.DIRECTION_RIGHT)
												.setListener(new AnimatorListenerAdapter() {
											@Override
											public void onAnimationEnd(Animator animation) {
												super.onAnimationEnd(animation);
												Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();
											}
										}).animate();

									}
								}).animate();
							}
						}).animate();

					}
				}).animate();
			}
		});
	}

	private void flipToAnimation() {
		final Button buttonOriginal = (Button) findViewById(R.id.btn_flip_to_original);
		final Button buttonToView = (Button) findViewById(R.id.btn_flip_to_view);
		buttonOriginal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnimationKit.createFlipToAnimation(buttonOriginal).setDuration(1000).setOrientation(Orientation.HORIZONTAL)
						.setDirection(Direction.DIRECTION_LEFT).setFlipToView(buttonToView)
						.setInterpolator(new LinearInterpolator()).setPivot(FlipToAnimation.PIVOT_CENTER)
						.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();
						AnimationKit.createFlipToAnimation(buttonToView).setDuration(1000).setOrientation(Orientation.VERTICAL)
								.setDirection(Direction.DIRECTION_DOWN).setFlipToView(buttonOriginal)
								.setInterpolator(new LinearInterpolator()).setPivot(FlipToAnimation.PIVOT_TOP)
								.animate();
					}
				}).animate();
			}
		});
	}

	private void puffAnimation() {
		final Button button = (Button) findViewById(R.id.btn_puff);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnimationKit.createPuffAnimation(button).setDuration(500).setInterpolator(new AccelerateInterpolator())
						.setPuffMode(PuffMode.OUT).setPivotX(button.getWidth() / 2).setPivotY(button.getHeight() / 2)
						.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

						AnimationKit.createPuffAnimation(button).setDuration(500).setInterpolator(new AccelerateInterpolator())
								.setPuffMode(PuffMode.IN).setPivotX(0).setPivotY(0).animate();
					}
				}).animate();
			}
		});
	}

	private void colorAnimation() {
		final Button button = (Button) findViewById(R.id.btn_color);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnimationKit.createColorAnimation(button).setDuration(2000).setInterpolator(new LinearInterpolator())
						.setPropertiesName("textColor")
						.setValues(new int[] { Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.BLACK })
						.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

						AnimationKit.createColorAnimation(button).setDuration(2000).setInterpolator(new LinearInterpolator())
								.setPropertiesName("backgroundColor")
								.setValues(new int[] { Color.GRAY, Color.RED, Color.BLUE, Color.GREEN, Color.GRAY })
								.animate();
					}
				}).animate();
			}

		});

	}

	private void telescopicAnimation() {
		final Button button = (Button) findViewById(R.id.btn_telescopic);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnimationKit.createTelescopicAnimation(button).setDuration(1000).setInterpolator(new AccelerateInterpolator())
						.setTelescopicMode(TelescopicMode.OUT).setTelescopicTargetMode(TelescopicTargetMode.HEIGHT)
						.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

						AnimationKit.createTelescopicAnimation(button).setDuration(1000).setInterpolator(new AccelerateInterpolator())
								.setTelescopicMode(TelescopicMode.IN)
								.setTelescopicTargetMode(TelescopicTargetMode.HEIGHT)
								// .setEnd((int)dpToPx(MainActivity.this, 45))
								.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								super.onAnimationEnd(animation);

								AnimationKit.createTelescopicAnimation(button).setDuration(1000)
										.setInterpolator(new AccelerateInterpolator())
										.setTelescopicMode(TelescopicMode.OUT)
										.setTelescopicTargetMode(TelescopicTargetMode.WIDTH)
										.setListener(new AnimatorListenerAdapter() {
									@Override
									public void onAnimationEnd(Animator animation) {
										super.onAnimationEnd(animation);

										AnimationKit.createTelescopicAnimation(button).setDuration(1000)
												.setInterpolator(new AccelerateInterpolator())
												.setTelescopicMode(TelescopicMode.IN)
												// .setEnd((int)dpToPx(MainActivity.this,
												// 180))
												.setTelescopicTargetMode(TelescopicTargetMode.WIDTH).animate();

									}
								}).animate();

							}
						}).animate();

					}
				}).animate();
			}

		});

	}

	private void pathAnimation() {
		final Button button = (Button) findViewById(R.id.btn_path);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnimatorPath path = new AnimatorPath();
				path.moveTo(0, 0);
				path.lineTo(0, 200);
				path.curveTo(0, 200, 400, 0, 0, -200);
				path.curveTo(0, -200, -400, 0, 0, 200);
				path.lineTo(0, 0);

				AnimationKit.createPathAnimation(button).setDuration(3000).setInterpolator(new LinearInterpolator()).setPath(path)
						.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

						/*AnimatorPath path = new AnimatorPath();
						path.curveTo(0, 0, 600, 1600, 0, 0);
						new PathAnimation(button).setDuration(2000).setInterpolator(new LinearInterpolator())
								.setPath(path).animate();*/
					}
				}).animate();

			}

		});
	}

	private void combinationAnimation() {
		final Button button = (Button) findViewById(R.id.btn_combination);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				CombinationAnimation combinationAnimation = AnimationKit.createCombinationAnimation();
				combinationAnimation.add(
						AnimationKit.createSlideAnimation(button).setSlideMode(SlideMode.OUT).setDirection(Direction.DIRECTION_UP));
				combinationAnimation.add(AnimationKit.createAlphaAnimation(button).setValues(new float[] { 1.0f, 0.0f }));
				combinationAnimation.add(AnimationKit.createRotateAnimation(button).setDegrees(360));
				combinationAnimation.add(AnimationKit.createScaleAnimation(button).setValuesX(new float[] { 1.0f, 0.0f })
						.setValuesY(new float[] { 1.0f, 0.0f }));
				combinationAnimation.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();

						CombinationAnimation combinationAnimation = AnimationKit.createCombinationAnimation();
						combinationAnimation.add(AnimationKit.createSlideAnimation(button).setSlideMode(SlideMode.IN));
						combinationAnimation.add(AnimationKit.createAlphaAnimation(button).setValues(new float[] { 0.0f, 1.0f }));
						combinationAnimation.add(AnimationKit.createRotateAnimation(button).setDegrees(360));
						combinationAnimation.add(AnimationKit.createScaleAnimation(button).setValuesX(new float[] { 0.0f, 1.0f })
								.setValuesY(new float[] { 0.0f, 1.0f }));
						combinationAnimation.setDuration(1000);
						combinationAnimation.setInterpolator(new DecelerateInterpolator());
						combinationAnimation.animate();
					}
				});
				combinationAnimation.setDuration(1000);
				combinationAnimation.setInterpolator(new AccelerateInterpolator());
				combinationAnimation.animate();
			}

		});
	}

	boolean isBack = false;
	View destinationView;

	private void transferAnimation() {
		final Button button = (Button) findViewById(R.id.btn_transfer);
		final RelativeLayout original = (RelativeLayout) findViewById(R.id.rl_transfer_original); // 初始位置
		final RelativeLayout destination = (RelativeLayout) findViewById(R.id.rl_transfer_destination); // 目标位置

		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isBack) {
					destinationView = original;
				} else {
					destinationView = destination;
				}
				Toast.makeText(MainActivity.this, "开始执行", Toast.LENGTH_SHORT).show();
				AnimationKit.createTransferAnimation(button).setDuration(800).setInterpolator(new AccelerateDecelerateInterpolator())
						.setDestinationView(destinationView).setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();
						isBack = !isBack;
					}
				}).animate();
			}
		});

		destination.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isBack) {
					destinationView = original;
				} else {
					destinationView = destination;
				}
				AnimationKit.createTransferAnimation(button).setDuration(800).setInterpolator(new AccelerateDecelerateInterpolator())
						.setDestinationView(destinationView).setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Toast.makeText(MainActivity.this, "执行完毕", Toast.LENGTH_SHORT).show();
						isBack = !isBack;
					}
				}).animate();
			}
		});

	}

	public static float dpToPx(Context context, float dp) {
		if (context == null) {
			return -1;
		}
		return dp * context.getResources().getDisplayMetrics().density;
	}

}
