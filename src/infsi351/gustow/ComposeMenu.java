package infsi351.gustow;

import infsi351.gustow.data.Formule;
import infsi351.gustow.data.Globals;
import infsi351.gustow.data.Plat;
import infsi351.gustow.data.TypePlat;
import infsi351.gustow.design.ResizeAnimation;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ComposeMenu extends Activity {

	private Formule currentFormule;
	private Formule buildingFormule;

	// les rubriques à afficher dans l'ordre
	private final EnumSet<TypePlat> rubriques = EnumSet.of(TypePlat.Entree,
			TypePlat.PlatPrincipal, TypePlat.Dessert);

	// LinearLayout où on affiche la liste des plats par entree/plat/dessert
	private Map<TypePlat, LinearLayout> PlatLists;

	// FrameLayout où on affiche plus d'infos sur le plat sélectionné
	private Map<TypePlat, FrameLayout> PlatFrames;

	private int couleurCourante;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		couleurCourante = 0;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_compose_menu);

		Button confirmButton = (Button) findViewById(R.id.button_confirm_formule);
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/SnellRoundhand.ttc");
		confirmButton.setTypeface(tf);
		confirmButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		// BIEN POUR LE PREMIER MENU MAIS MOCHE POUR LES AUTRES
		// TextView titreMenu = (TextView) findViewById(R.id.titre_menu);
		// titreMenu.setX((int) titreMenu.getX() - 50);

		LinearLayout menuFormule = (LinearLayout) findViewById(R.id.menu_formule);
		for (final Formule f : Globals.plats.getFormules()) {
			final int couleurFormule = couleurCourante;

			Button b = buttonFormule(f);

			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					selectFormule(f, couleurFormule);
				}
			});

			menuFormule.addView(b);
		}

		buildingFormule = new Formule();

		PlatLists = new EnumMap<TypePlat, LinearLayout>(TypePlat.class);
		PlatLists.put(TypePlat.Entree,
				(LinearLayout) findViewById(R.id.menu_plat_entree));
		PlatLists.put(TypePlat.PlatPrincipal,
				(LinearLayout) findViewById(R.id.menu_plat_plat));
		PlatLists.put(TypePlat.Dessert,
				(LinearLayout) findViewById(R.id.menu_plat_dessert));

		PlatFrames = new EnumMap<TypePlat, FrameLayout>(TypePlat.class);
		PlatFrames.put(TypePlat.Entree,
				(FrameLayout) findViewById(R.id.frame_entree));
		PlatFrames.put(TypePlat.PlatPrincipal,
				(FrameLayout) findViewById(R.id.frame_plat));
		PlatFrames.put(TypePlat.Dessert,
				(FrameLayout) findViewById(R.id.frame_dessert));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_compose_menu, menu);
		return true;
	}

	public void selectFormule(Formule f, final int couleur) {
		if (currentFormule == f)
			return;

		currentFormule = f;
		buildingFormule.copyFormule(f);

		LinearLayout menuFormule = (LinearLayout) findViewById(R.id.menu_formule);

		int childcount = menuFormule.getChildCount();
		for (int i = 0; i < childcount; i++) {
			View v = menuFormule.getChildAt(i);
			((Button) v).setText("");
		}

		LinearLayout menuPlat = (LinearLayout) findViewById(R.id.menu_plat);
		menuPlat.setBackgroundColor(Globals.couleurs.get(couleur));

		TextView blankSpace = (TextView) findViewById(R.id.blank);
		blankSpace.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
		TextView titreMenu = (TextView) findViewById(R.id.titre_menu);
		titreMenu.setText(f.getNom()+" ~ "+f.getPrix()+"€");
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/SnellRoundhand.ttc");
		titreMenu.setTypeface(tf);
		titreMenu.setTypeface(titreMenu.getTypeface(), Typeface.BOLD);
		titreMenu.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);

		for (TypePlat type : rubriques) {

			LinearLayout layout = PlatLists.get(type);
			layout.removeAllViews();
			FrameLayout frame = PlatFrames.get(type);
			frame.removeAllViews();

			setFrame(1, type, couleur, true, false);

			// pour chaque plat, on cree le bouton
			for (int i : f.getPlatsOfType(type)) {
				Plat p = Globals.plats.get(i);
				final Button b = buttonPlat(p);

				final int id = p.getId();
				final TypePlat t = type;

				b.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						setFrame(id, t, couleur, false, true);
						b.setTypeface(b.getTypeface(), Typeface.BOLD_ITALIC);
					}
				});

				layout.addView(b);
			}
		}

		/*
		 * menuFormule.setLayoutParams(new LinearLayout.LayoutParams(100,
		 * ViewGroup.LayoutParams.MATCH_PARENT));
		 */
		ResizeAnimation resizeAnimation = new ResizeAnimation(menuFormule, 100);
		resizeAnimation.setDuration(300);
		menuFormule.startAnimation(resizeAnimation);
	}

	private void setFrame(final int idPlat, final TypePlat typePlat,
			final int color, Boolean init, Boolean reset) {
		LinearLayout layout = PlatLists.get(typePlat);
		if (reset) {
			int childCount = layout.getChildCount();
			for (int i = 0; i < childCount; i++) {
				Button v = (Button) layout.getChildAt(i);
				v.setTypeface(v.getTypeface(), Typeface.ITALIC);
				// ((Button) v).setBackgroundColor(Globals.couleurs.get(4));
			}
		}
		// selectionne la frame à modifier
		FrameLayout frame = PlatFrames.get(typePlat);
		Plat p = Globals.plats.get(idPlat);

		// remplit la frame
		frame.removeAllViews();

		if (!init) {
			View photo = new View(this);
			String path = p.getPathImage();
			int id = getResources().getIdentifier(path, "drawable",
					getApplicationContext().getPackageName());
			photo.setBackgroundResource(id);

			frame.addView(photo);

		}

		int idFrame = getResources().getIdentifier("frame" + color, "drawable",
				getApplicationContext().getPackageName());

		View mask = new View(this);
		mask.setBackgroundResource(idFrame);
		mask.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		frame.addView(mask);

		if (!init) {
			mask.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					frameDescription(idPlat, typePlat, color);
				}
			});
		}

		buildingFormule.setPlatOfType(typePlat, idPlat);
	}

	public void confirm(View v) {
		Formule f = new Formule();
		f.copyFormule(buildingFormule);
		Globals.cart.add(f);

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

	public Button buttonPlat(Plat p) {
		Button b = new Button(this);
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/Bodoni 72.ttc");
		b.setText(p.getNom());
		b.setTypeface(tf, Typeface.ITALIC);
		b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		b.setBackgroundColor(Color.TRANSPARENT);
		return b;
	}

	public Button buttonFormule(Formule f) {
		Button b = new Button(this);
		b.setText(f.getNom()+" ~ "+f.getPrix()+"€");
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

	public void frameDescription(final int idPlat, final TypePlat typePlat,
			final int color) {
		FrameLayout frame = PlatFrames.get(typePlat);
		Plat p = Globals.plats.get(idPlat);

		// remplit la frame
		frame.removeAllViews();

		View photo = new View(this);
		String path = p.getPathImage();
		int id = getResources().getIdentifier(path, "drawable",
				getApplicationContext().getPackageName());
		photo.setBackgroundResource(id);

		frame.addView(photo);

		TextView desc = new TextView(this);
		desc.setText(p.getDescription());
		desc.setBackgroundColor(Color.argb(150, 0, 0, 0));
		desc.setTextColor(Color.WHITE);
		desc.setGravity(Gravity.CENTER);
		desc.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f));
		desc.setPadding(50, 50, 50, 50);
		frame.addView(desc);

		int idFrame = getResources().getIdentifier("frame" + (color),
				"drawable", getApplicationContext().getPackageName());

		View mask = new View(this);
		mask.setBackgroundResource(idFrame);
		mask.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		frame.addView(mask);

		mask.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setFrame(idPlat, typePlat, color, false, false);
			}
		});

	}
}
