package infsi351.gustow;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Intent intent = getIntent();
		String language = intent.getStringExtra(MainActivity.LANGUAGE);
		System.out.println(language);
		setContentView(R.layout.activity_home);
		LinearLayout page2 = (LinearLayout) findViewById(R.id.page2);

		Button bFormules = (Button) findViewById(R.id.button_formules);
		Button bCarte = (Button) findViewById(R.id.button_carte);
		// custom font
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/SnellRoundhand.ttc");
		bFormules.setTypeface(tf, Typeface.BOLD);
		bCarte.setTypeface(tf, Typeface.BOLD);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	public void selectMenu(View v) {

		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.feedback);
		anim.setDuration(200);
		v.startAnimation(anim);
		v.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(getApplicationContext(),
						ComposeMenu.class);
				startActivity(intent);
			}
		}, 200);

	}

	public void selectCarte(View v) {
		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.feedback);
		anim.setDuration(200);
		v.startAnimation(anim);
		v.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(getApplicationContext(),
						CarteMenu.class);
				startActivity(intent);
			}
		}, 200);
	}

}
