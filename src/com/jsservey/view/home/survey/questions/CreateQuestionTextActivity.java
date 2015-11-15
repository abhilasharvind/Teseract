package com.jsservey.view.home.survey.questions;

import com.abx.jsservey.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateQuestionTextActivity extends Activity implements
		OnItemSelectedListener, OnClickListener, OnRatingBarChangeListener,
		OnCheckedChangeListener, OnSeekBarChangeListener {
	private Spinner spinner1, spinner2;
	private Button btnSubmit;
	private TextView progressValue;
	private RatingBar ratingBar;
	private View answerView;
	private int textAnserCount = 1;
	private TextView seekValueText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.create_question_type_layout);
		super.onCreate(savedInstanceState);
		addListenerOnButton();
		Spinner staticSpinner = (Spinner) findViewById(R.id.question_type_spinner);

		ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
				.createFromResource(this, R.array.question_array,
						android.R.layout.simple_spinner_item);

		staticAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		staticSpinner.setAdapter(staticAdapter);
	}

	public void addListenerOnButton() {

		spinner1 = (Spinner) findViewById(R.id.question_type_spinner);
		btnSubmit = (Button) findViewById(R.id.create);
		spinner1.setOnItemSelectedListener(this);
		btnSubmit.setOnClickListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
		String item = parent.getItemAtPosition(position).toString();
		LinearLayout questionLayout = (LinearLayout) findViewById(R.id.question_type_layout);
		if (position == 0) {
			answerView = getLayoutInflater()
					.inflate(R.layout.create_question_text_layout,
							questionLayout, false);
			questionLayout.removeAllViews();
			questionLayout.addView(answerView);
			ImageView add_answer = (ImageView) questionLayout
					.findViewById(R.id.add_text_answer);
			add_answer.setOnClickListener(this);
		}
		if (position == 1) {
			answerView = getLayoutInflater().inflate(
					R.layout.create_question_progress_layout, questionLayout,
					false);
			questionLayout.removeAllViews();
			questionLayout.addView(answerView);
			// RatingBar ratingBar = (RatingBar)
			// answerView.findViewById(R.id.ratingBar1);
			RadioGroup progressRadioGroup = (RadioGroup) answerView
					.findViewById(R.id.progress_radio_group);
			RadioButton fiveStar = (RadioButton) answerView
					.findViewById(R.id.five_star_button);
			RadioButton tenStar = (RadioButton) answerView
					.findViewById(R.id.ten_star_button);
			progressRadioGroup.setOnCheckedChangeListener(this);
			progressValue = (TextView) answerView
					.findViewById(R.id.selected_progress_value);
			CheckBox allowHalfRating = (CheckBox) answerView
					.findViewById(R.id.half_rating_chkbox);
			allowHalfRating.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (((CheckBox) v).isChecked()) {
						ratingBar.setStepSize((float) 0.5);
					} else {
						ratingBar.setStepSize((float) 1.0);
					}

				}
			});
			if (fiveStar.isChecked()) {
				answerView.findViewById(R.id.ratingBar2).setVisibility(
						View.GONE);
				ratingBar = (RatingBar) answerView
						.findViewById(R.id.ratingBar1);
				ratingBar.setVisibility(View.VISIBLE);
			} else if (tenStar.isChecked()) {
				ratingBar = (RatingBar) answerView
						.findViewById(R.id.ratingBar2);
				answerView.findViewById(R.id.ratingBar1).setVisibility(
						View.GONE);
				ratingBar.setVisibility(View.VISIBLE);
			}
			// if (allowHalfRating.isChecked()) {
			ratingBar.setStepSize((float) 1.0);
			// } else {
			// ratingBar.setStepSize((float) 0.5);
			// }

			ratingBar.setOnRatingBarChangeListener(this);
		}
		if (position == 2) {
			answerView = getLayoutInflater()
					.inflate(R.layout.create_question_seek_layout,
							questionLayout, false);
			questionLayout.removeAllViews();
			questionLayout.addView(answerView);
			SeekBar answerSeek = (SeekBar) answerView
					.findViewById(R.id.answer_seek);
			answerSeek.setOnSeekBarChangeListener(this);
			EditText seekMaxValue = (EditText) findViewById(R.id.max_value);
			EditText seekMinValue = (EditText) findViewById(R.id.min_value);
			seekValueText = (TextView) findViewById(R.id.selected_value_text);
		}
		if (position == 3) {
			answerView = getLayoutInflater().inflate(
					R.layout.create_question_smiley_layout, questionLayout,
					false);
			RadioGroup progressRadioGroup = (RadioGroup) answerView
					.findViewById(R.id.smily_radio_group);
			RadioButton fiveStar = (RadioButton) answerView
					.findViewById(R.id.type1_smiley);
			RadioButton tenStar = (RadioButton) answerView
					.findViewById(R.id.type2_smiley);
			progressRadioGroup.setOnCheckedChangeListener(this);
			questionLayout.removeAllViews();
			questionLayout.addView(answerView);
		}
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onClick(View view) {

		if (view.getId() == R.id.add_text_answer) {
			LinearLayout questionLayout = (LinearLayout) findViewById(R.id.question_type_layout);
			RelativeLayout textLayout1 = (RelativeLayout) answerView
					.findViewById(R.id.text_layout1);
			RelativeLayout textLayout2 = (RelativeLayout) answerView
					.findViewById(R.id.text_layout2);
			RelativeLayout textLayout3 = (RelativeLayout) answerView
					.findViewById(R.id.text_layout3);
			RelativeLayout textLayout4 = (RelativeLayout) answerView
					.findViewById(R.id.text_layout4);
			RelativeLayout textLayout5 = (RelativeLayout) answerView
					.findViewById(R.id.text_layout5);
			textAnserCount++;
			if (textAnserCount < 6) {
				if (textAnserCount == 2) {
					textLayout2.setVisibility(View.VISIBLE);
				} else if (textAnserCount == 3) {
					textLayout3.setVisibility(View.VISIBLE);
				} else if (textAnserCount == 4) {
					textLayout4.setVisibility(View.VISIBLE);
				} else if (textAnserCount == 5) {
					textLayout5.setVisibility(View.VISIBLE);
				}

			}

		}
	}

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {
		progressValue.setText(String.valueOf(rating));
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (group.getCheckedRadioButtonId() == R.id.five_star_button) {
			answerView.findViewById(R.id.ratingBar2).setVisibility(View.GONE);
			ratingBar = (RatingBar) answerView.findViewById(R.id.ratingBar1);
			ratingBar.setVisibility(View.VISIBLE);

		} else if (group.getCheckedRadioButtonId() == R.id.ten_star_button) {
			answerView.findViewById(R.id.ratingBar1).setVisibility(View.GONE);
			ratingBar = (RatingBar) answerView.findViewById(R.id.ratingBar2);
			ratingBar.setVisibility(View.VISIBLE);
		} else if (group.getCheckedRadioButtonId() == R.id.type1_smiley) {

		} else if (group.getCheckedRadioButtonId() == R.id.type2_smiley) {

		}

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromuser) {

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		int progessValue = seekBar.getProgress();
		seekValueText.setText(progessValue + "");
	}

}
