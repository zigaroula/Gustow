package infsi351.gustow;

import infsi351.gustow.data.Formule;
import infsi351.gustow.data.Globals;
import infsi351.gustow.data.Plat;
import infsi351.gustow.data.TypePlat;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CarteMenu extends Activity {

	private Formule currentFormule;
	private Formule buildingFormule;

	// les rubriques à afficher dans l'ordre
	private final EnumSet<TypePlat> rubriques = EnumSet.of(TypePlat.Entree,
			TypePlat.PlatPrincipal, TypePlat.Dessert);

	// LinearLayout où on affiche la liste des plats par entree/plat/dessert
	private Map<TypePlat, LinearLayout> PlatLists;

	// FrameLayout où on affiche plus d'infos sur le plat sélectionné
	private Map<TypePlat, FrameLayout> PlatFrames;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_carte_menu);
		

		LinearLayout menuTypePlat = (LinearLayout) findViewById(R.id.menu_typeplat);
		for (final TypePlat type : TypePlat.values()) {
			Button b = buttonTypePlat(type);
			menuTypePlat.addView(b);
			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					setTypePlat(type);
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

	public void setTypePlat(TypePlat type) {
		LinearLayout produitsCarte = (LinearLayout) findViewById(R.id.produits_carte);
		produitsCarte.setLayoutParams(new LinearLayout.LayoutParams(100,
				ViewGroup.LayoutParams.MATCH_PARENT));
		produitsCarte.removeAllViews();
		for (Plat p : Globals.plats.getPlatsByType(type)) {
			Button b = buttonPlat(p);
			final int id = p.getId();
			final TypePlat t = type;

			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					setFrame(id, t);
				}
			});

			produitsCarte.addView(b);
		}
	}

	private void setFrame(int idPlat, TypePlat typePlat) {
		Globals.cart.add(idPlat);/*
		// selectionne la frame à modifier
		FrameLayout frame = PlatFrames.get(typePlat);
		Plat p = Globals.plats.get(idPlat);

		// remplit la frame
		frame.removeAllViews();

		TextView name = new TextView(this);
		name.setBackgroundResource(R.drawable.doge);
		name.setText(p.getNom());

		frame.addView(name);

		buildingFormule.setPlatOfType(typePlat, idPlat);*/
	}
	
	public void confirm(View view) {
		Formule f=new Formule();
		f.copyFormule(buildingFormule);
		Globals.cart.add(f);
		
		Intent intent = new Intent(this, CheckCart.class);
		startActivity(intent);
	}
	
	public Button buttonPlat(Plat p) {
		Button b = new Button(this);
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Bodoni 72.ttc");
		b.setText(p.getNom());
		b.setTypeface(tf);
		b.setBackgroundColor(Color.TRANSPARENT);
		return b;
	}
	
	public Button buttonFormule(Formule f) {
		Button b = new Button(this);
		b.setText(f.getNom());
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/SnellRoundhand.ttc");
		b.setTypeface(tf);
		b.setBackgroundColor(Color.TRANSPARENT);
		return b;
	}
	
	public Button buttonTypePlat(TypePlat type) {
		Button b = new Button(this);
		b.setText(type.toString());
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/SnellRoundhand.ttc");
		b.setTypeface(tf);
		b.setBackgroundColor(Color.TRANSPARENT);
		return b;
	}
	
}
