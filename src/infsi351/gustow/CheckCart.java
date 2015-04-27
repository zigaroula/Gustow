package infsi351.gustow;

import infsi351.gustow.data.Formule;
import infsi351.gustow.data.Globals;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckCart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_check_cart);

		TextView blankSpace = (TextView) findViewById(R.id.blank);
		blankSpace.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);

		TextView titre = (TextView) findViewById(R.id.title_cart);
		Button commander = (Button) findViewById(R.id.button_commande);
		Button bFormule = (Button) findViewById(R.id.button_cart_formule);
		Button bCarte = (Button) findViewById(R.id.button_cart_carte);
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/SnellRoundhand.ttc");
		titre.setTypeface(tf, Typeface.BOLD);
		commander.setTypeface(tf);
		bFormule.setTypeface(tf);
		bCarte.setTypeface(tf);
		displayCart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_check_cart, menu);
		return true;
	}

	private void displayCart() {
		LinearLayout itemList = (LinearLayout) findViewById(R.id.item_list);
		itemList.removeAllViews();
		itemList.addView(separator());
		
		//displays all the selected Formule
		for (final Formule f : Globals.cart.getFormules()) {
			LinearLayout formuleEtBouton = new LinearLayout(this);
			formuleEtBouton.setOrientation(LinearLayout.HORIZONTAL);
			formuleEtBouton.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f));
			formuleEtBouton.setPadding(0, 0, 0, 10);
			itemList.addView(formuleEtBouton);

			TextView text = new TextView(this);
			text.setTypeface(text.getTypeface(), Typeface.ITALIC);
			text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			text.setText(f.getNom() + " ~ " + f.getPrix() + "€\n   "
					+ Globals.plats.get(f.getEntree()).getNom() + "\n   "
					+ Globals.plats.get(f.getPlat()).getNom() + "\n   "
					+ Globals.plats.get(f.getDessert()).getNom());
			text.setHeight(200);
			formuleEtBouton.addView(text);

			Button b = new Button(this);
			b.setBackgroundColor(Color.TRANSPARENT);
			b.setText("X");
			b.setGravity(Gravity.RIGHT);
			b.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f));

			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					rmFormule(f);
				}
			});

			formuleEtBouton.addView(b);
			itemList.addView(separator());
		}

		//displays all the selected Plat
		for (final int id : Globals.cart.getPlats()) {
			LinearLayout formuleEtBouton = new LinearLayout(this);
			formuleEtBouton.setOrientation(LinearLayout.HORIZONTAL);
			formuleEtBouton.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f));
			formuleEtBouton.setPadding(0, 0, 0, 10);
			itemList.addView(formuleEtBouton);

			TextView text = new TextView(this);
			text.setText(" "+Globals.plats.get(id).getNom());
			text.setTypeface(text.getTypeface(), Typeface.ITALIC);
			text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			text.setHeight(70);
			formuleEtBouton.addView(text);

			Button b = new Button(this);
			b.setBackgroundColor(Color.TRANSPARENT);
			b.setText("X");
			b.setGravity(Gravity.RIGHT);
			b.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f));

			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					rmPlat(id);
				}
			});

			formuleEtBouton.addView(b);
			itemList.addView(separator());
		}

	}

	//creates a small view to draw a line
	private LinearLayout separator() {
		// un peu sale...
		LinearLayout separator = new LinearLayout(this);
		separator.setBackgroundColor(Color.BLACK);
		separator.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 2));
		return separator;
	}

	//removes f from the cart
	private void rmFormule(Formule f) {
		Globals.cart.rm(f);
		displayCart();
	}

	//removes the corresponding Plat from the cart
	private void rmPlat(int idPlat) {
		Globals.cart.rm(idPlat);
		displayCart();
	}

	//callback for a button at the bottom
	public void validerCommande(View v) {
		Intent intent = new Intent(this, CommandeValidee.class);
		startActivity(intent);
	}

	//callback for a button at the bottom
	public void retourFormule(View v) {
		Intent intent = new Intent(this, ComposeMenu.class);
		startActivity(intent);
		finish();
	}

	//callback for a button at the bottom
	public void retourCarte(View v) {
		Intent intent = new Intent(this, CarteMenu.class);
		startActivity(intent);
		finish();
	}
}
