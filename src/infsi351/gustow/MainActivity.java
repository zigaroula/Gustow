package infsi351.gustow;

import infsi351.gustow.data.Globals;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity {

	public final static String LANGUAGE = "infsi351.gustow.LANGUE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);

		// let's intialize the data classes
		Globals.init(getApplicationContext());

		// all the language buttons get their animation
		Button bEn = (Button) findViewById(R.id.button_en);
		Button bFr = (Button) findViewById(R.id.button_fr);
		Button bEs = (Button) findViewById(R.id.button_es);

		Animation ani = AnimationUtils.loadAnimation(this, R.anim.fadein);
		ani.setRepeatCount(Animation.INFINITE);
		bEn.setAnimation(ani);
		bFr.setAnimation(ani);
		bEs.setAnimation(ani);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	//callback for the buttons: it sets the right locale then moves to the next activity
	public void selectLanguage(View view) {
		Intent intent = new Intent(this, Home.class);
		String language = "";

		Configuration config = new Configuration();
		Locale locale = new Locale("fr");

		switch (view.getId()) {
		case R.id.button_fr:
			language = "FR";
			locale = new Locale("fr");
			break;
		case R.id.button_en:
			language = "EN";
			locale = new Locale("en");
			break;
		case R.id.button_es:
			language = "ES";
			locale = new Locale("es");
			break;
		default:
			break;
		}

		Locale.setDefault(locale);
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config,
				getBaseContext().getResources().getDisplayMetrics());

		intent.putExtra(LANGUAGE, language);
		startActivity(intent);
	}

}
