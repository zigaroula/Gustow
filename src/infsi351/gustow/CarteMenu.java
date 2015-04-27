package infsi351.gustow;

import infsi351.gustow.data.Globals;
import infsi351.gustow.data.Plat;
import infsi351.gustow.data.TypePlat;
import infsi351.gustow.design.ResizeAnimation;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CarteMenu extends Activity {

	private int couleurCourante;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		couleurCourante = 0;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_carte_menu);

		// for each type plat, display a button to select it
		LinearLayout menuTypePlat = (LinearLayout) findViewById(R.id.menu_typeplat);
		for (final TypePlat type : TypePlat.values()) {
			final int couleurTypePlat = couleurCourante;
			Button b = buttonTypePlat(type);
			menuTypePlat.addView(b);
			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					setTypePlat(type, couleurTypePlat);
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_compose_menu, menu);
		return true;
	}

	// callback for the TypePlat buttons
	public void setTypePlat(TypePlat type, final int couleur) {
		setFrame(0, couleur, true);

		// resize some of the views
		LinearLayout carteTypePlat = (LinearLayout) findViewById(R.id.carte_typeplat);
		LinearLayout menuTypePlat = (LinearLayout) findViewById(R.id.menu_typeplat);
		carteTypePlat.setBackgroundColor(Globals.couleurs.get(couleur));

		LinearLayout produitsCarte = (LinearLayout) findViewById(R.id.produits_carte);
		produitsCarte.removeAllViews();

		TextView blankSpace = (TextView) findViewById(R.id.blank);
		blankSpace.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

		TextView titreMenu = (TextView) findViewById(R.id.titre_carte);
		titreMenu.setText(type.toString());
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/SnellRoundhand.ttc");
		titreMenu.setTypeface(tf, Typeface.BOLD);
		titreMenu.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);

		setFrame(0, couleur, true);

		int childcount = menuTypePlat.getChildCount();
		for (int i = 0; i < childcount; i++) {
			View v = menuTypePlat.getChildAt(i);
			((Button) v).setText("");
		}

		for (final Plat p : Globals.plats.getPlatsByType(type)) {
			Button b = buttonPlat(p);
			final int id = p.getId();

			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					setFrame(id, couleur, false);
				}
			});

			produitsCarte.addView(b);
		}

		Button buttonViewCart = (Button) findViewById(R.id.button_carte_view_cart);
		Typeface buttonTF = Typeface.createFromAsset(getAssets(),
				"fonts/SnellRoundhand.ttc");
		buttonViewCart.setTypeface(buttonTF);
		buttonViewCart.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

		/*
		 * menuTypePlat.setLayoutParams(new LinearLayout.LayoutParams(100,
		 * ViewGroup.LayoutParams.MATCH_PARENT));
		 */
		ResizeAnimation resizeAnimation = new ResizeAnimation(menuTypePlat, 100);
		resizeAnimation.setDuration(300);
		menuTypePlat.startAnimation(resizeAnimation);
	}

	//adds the plat with id idPlat to the global cart
	private void addToCart(int idPlat, View v) {
		Globals.cart.add(idPlat);
		Animation animation = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.ondulate);
		v.startAnimation(animation);
	}

	//callback for the confirm button (with an animation first)
	public void confirm(View v) {
		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.feedback);
		anim.setDuration(200);
		v.startAnimation(anim);
		v.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(getApplicationContext(),
						CheckCart.class);
				startActivity(intent);
				finish();
			}
		}, 200);
	}

	//generates a button for Plat p
	public Button buttonPlat(Plat p) {
		Button b = new Button(this);
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/Bodoni 72.ttc");
		b.setText(p.getNom());
		b.setTypeface(tf);
		b.setBackgroundColor(Color.TRANSPARENT);
		b.setGravity(Gravity.CENTER);
		return b;
	}

	//generates a button for TypePlat type
	public Button buttonTypePlat(TypePlat type) {
		Button b = new Button(this);
		b.setText(type.toString());
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/SnellRoundhand.ttc");
		b.setTypeface(tf);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.weight = 1.0f;
		b.setLayoutParams(params);
		b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
		b.setBackgroundColor(Globals.couleurs.get(couleurCourante));
		couleurCourante = (couleurCourante + 1) % Globals.couleurs.size();
		return b;
	}

	//sets the frame at the bottom of the screen (title+picture+description of the selected Plat)
	private void setFrame(final int idPlat, int color, Boolean init) {
		TextView title = (TextView) findViewById(R.id.carte_desc_title);
		TextView desc = (TextView) findViewById(R.id.carte_description);
		Button button = (Button) findViewById(R.id.button_carte_addCart);

		FrameLayout frame = (FrameLayout) findViewById(R.id.carte_frame);

		Plat p = Globals.plats.get(idPlat);

		frame.removeAllViews();

		if (!init) { // when a Plat is selected, fills everything in the frame
			View photo = new View(this);
			String path = p.getPathImage();
			int id = getResources().getIdentifier(path, "drawable",
					getApplicationContext().getPackageName());
			photo.setBackgroundResource(id);

			frame.addView(photo);

			Typeface tf1 = Typeface.createFromAsset(getAssets(),
					"fonts/SnellRoundhand.ttc");
			Typeface tf2 = Typeface.createFromAsset(getAssets(),
					"fonts/Bodoni 72.ttc");
			title.setTypeface(tf1, Typeface.BOLD_ITALIC);
			title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
			title.setText(p.getNom() + " ~ " + p.getPrix() + "€ :");

			desc.setTypeface(tf2, Typeface.ITALIC);
			desc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			desc.setText(p.getDescription());

			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					addToCart(idPlat, v);
				}
			});
			button.setTypeface(tf1);
			button.setVisibility(LinearLayout.VISIBLE);

		} else { //when no Plat is selected, everything is empty
			title.setText("");
			desc.setText("");
			button.setVisibility(LinearLayout.GONE);
		}

		int idFrame = getResources().getIdentifier("frame" + color, "drawable",
				getApplicationContext().getPackageName());

		//put a frame on top of the picture
		View mask = new View(this);
		mask.setBackgroundResource(idFrame);
		mask.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		frame.addView(mask);

	}
	
	//callback for the confirm button
	public void viewCart(View v) {
		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.feedback);
		anim.setDuration(200);
		v.startAnimation(anim);
		v.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(getApplicationContext(),
						CheckCart.class);
				startActivity(intent);
				finish();
			}
		}, 200);
	}
}
