package com.jsservey.view.home.survey.questions;

import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.model.QuestionAPojo;
import com.jsservey.model.QuestionBPojo;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

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
import android.widget.Toast;

public class CreateQuestionTextActivity extends Activity implements
		OnItemSelectedListener, OnClickListener, OnRatingBarChangeListener,
		OnCheckedChangeListener, OnSeekBarChangeListener {
	private Spinner spinner1;//, spinner2;
	//private Button btnSubmit;
	private TextView progressValue;
	private RatingBar ratingBar;
	private View answerView;
	private int textAnserCount = 1;
	private TextView seekValueText;
	private int questionType = 0;
	private int isHalfRating = 0;
	private int maxValue = 5;

	String survey_id="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.create_question_type_layout);
		super.onCreate(savedInstanceState);
		loddingIndicator(View.GONE);
		setListner();
		Bundle bundle =getIntent().getExtras();
		survey_id=bundle.getString("survey_id");
		Spinner staticSpinner = (Spinner) findViewById(R.id.question_type_spinner);

		ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
				.createFromResource(this, R.array.question_array,
						android.R.layout.simple_spinner_item);

		staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		staticSpinner.setAdapter(staticAdapter);
	}

	public void setListner() {

		spinner1 = (Spinner) findViewById(R.id.question_type_spinner);
		 findViewById(R.id.create).setOnClickListener(this);;
		spinner1.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
		questionType = position;

		LinearLayout questionLayout = (LinearLayout) findViewById(R.id.question_type_layout);
		if (position == 0) {
			answerView = getLayoutInflater()
					.inflate(R.layout.create_question_text_new_layout,
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
						isHalfRating = 1;
					} else {
						ratingBar.setStepSize((float) 1.0);
						isHalfRating = 0;
					}

				}
			});
			if (fiveStar.isChecked()) {
				answerView.findViewById(R.id.ratingBar2).setVisibility(
						View.GONE);
				ratingBar = (RatingBar) answerView
						.findViewById(R.id.ratingBar1);
				ratingBar.setVisibility(View.VISIBLE);
				maxValue = 5;
			} else if (tenStar.isChecked()) {
				ratingBar = (RatingBar) answerView
						.findViewById(R.id.ratingBar2);
				answerView.findViewById(R.id.ratingBar1).setVisibility(
						View.GONE);
				ratingBar.setVisibility(View.VISIBLE);
				maxValue = 10;
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
			seekValueText = (TextView) findViewById(R.id.selected_value_text);
		}
		if (position == 3) {
			answerView = getLayoutInflater().inflate(
					R.layout.create_question_smiley_layout, questionLayout,
					false);
			RadioGroup progressRadioGroup = (RadioGroup) answerView
					.findViewById(R.id.smily_radio_group);
			RadioButton fiveStar = (RadioButton) answerView.findViewById(R.id.type1_smiley);
			RadioButton tenStar = (RadioButton) answerView	.findViewById(R.id.type2_smiley);
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

			EditText textLayout1 = (EditText) answerView.findViewById(R.id.answer1);
			EditText textLayout2 = (EditText) answerView.findViewById(R.id.answer2);
			EditText textLayout3 = (EditText) answerView.findViewById(R.id.answer3);
			EditText textLayout4 = (EditText) answerView.findViewById(R.id.answer4);
			EditText textLayout5 = (EditText) answerView.findViewById(R.id.answer5);
			EditText textLayout6 = (EditText) answerView.findViewById(R.id.answer6);
			EditText textLayout7 = (EditText) answerView.findViewById(R.id.answer7);
			EditText textLayout8 = (EditText) answerView.findViewById(R.id.answer8);
			EditText textLayout9 = (EditText) answerView.findViewById(R.id.answer9);
			EditText textLayout10 = (EditText) answerView.findViewById(R.id.answer10);
			textAnserCount++;
			if (textAnserCount < 11) {
				if (textAnserCount == 2) {
					textLayout2.setVisibility(View.VISIBLE);
				} else if (textAnserCount == 3) {
					textLayout3.setVisibility(View.VISIBLE);
				} else if (textAnserCount == 4) {
					textLayout4.setVisibility(View.VISIBLE);
				} else if (textAnserCount == 5) {
					textLayout5.setVisibility(View.VISIBLE);
				} else if (textAnserCount == 6) {
					textLayout6.setVisibility(View.VISIBLE);
				} else if (textAnserCount == 7) {
					textLayout7.setVisibility(View.VISIBLE);
				} else if (textAnserCount == 8) {
					textLayout8.setVisibility(View.VISIBLE);
				} else if (textAnserCount == 9) {
					textLayout9.setVisibility(View.VISIBLE);
				} else if (textAnserCount == 10) {
					textLayout10.setVisibility(View.VISIBLE);
				}

			}

		} else if (view.getId() == R.id.create) {
			createQuestions();
		}
	}

	private void createQuestions() {
		EditText questionText = (EditText) findViewById(R.id.create_question);
		
		if (questionType == 0 && null != questionText) {
			EditText questionName = (EditText) findViewById(R.id.question_name_ed);
			LinearLayout questionLayout = (LinearLayout) findViewById(R.id.question_type_layout);
			createTextTypeQuestions(questionText, questionName, questionLayout);
		
		}
			else if (questionType != 0 && null != questionText) {
			createOtherTypeQuestions(questionText);
			

			
		}
	}

	private void createOtherTypeQuestions(EditText questionText) {
		QuestionAPojo questionAPojo = new QuestionAPojo();
		questionAPojo.setQuestionName(questionText.getText().toString());
		questionAPojo.setQuestionText(questionText.getText().toString());
		questionAPojo.setQuestionType(questionType+2);
		switch (questionType) {
		case 1: {//rating
			questionAPojo.setMaxValue(maxValue);
			questionAPojo.setOtherValue(isHalfRating);
			break;
		}
		case 2: {//seek
			questionAPojo.setMaxValue(Integer.parseInt(seekValueText.getText().toString()));
			questionAPojo.setOtherValue(1);
			break;
		}
		
		case 3: {//smiley
			questionAPojo.setMaxValue(maxValue);
			questionAPojo.setOtherValue(1);
			break;
		}
		default:
			break;
		}
		RequestCreator requestCreator = new RequestCreator(getApplicationContext());
		new ApiRequester(getApplicationContext(), requestCreator.createQuestionA(survey_id, questionAPojo.getQuestionName(), ""+questionAPojo.getQuestionText(), ""+questionAPojo.getQuestionType(), ""+questionAPojo.getOtherValue(), ""+questionAPojo.getMaxValue()), new ApiRequestListner() {
			
			@Override
			public String onSuccess(JSONObject result) {
				loddingIndicator(View.GONE);
				onBackPressed();
				Toast.makeText(getApplicationContext(), "Question created", 1500).show();
				return null;
			}
			
			@Override
			public String onStarted() {
				loddingIndicator(View.VISIBLE);
				return null;
			}
			
			@Override
			public String onFailed() {
				loddingIndicator(View.GONE);
				Toast.makeText(getApplicationContext(), "Unable to create the question", 1500).show();
				return null;
			}
		}).execute("");
	}

	private void createTextTypeQuestions(EditText questionText, EditText questionName, LinearLayout questionLayout) {
		QuestionBPojo textPojo = new QuestionBPojo();//////////////////////////
		EditText opt1 = (EditText) questionLayout.findViewById(R.id.answer1);
		EditText opt2 = (EditText) questionLayout.findViewById(R.id.answer2);
		EditText opt3 = (EditText) questionLayout.findViewById(R.id.answer3);
		EditText opt4 = (EditText) questionLayout.findViewById(R.id.answer4);
		EditText opt5 = (EditText) questionLayout.findViewById(R.id.answer5);
		EditText opt6 = (EditText) questionLayout.findViewById(R.id.answer6);
		EditText opt7 = (EditText) questionLayout.findViewById(R.id.answer7);
		EditText opt8 = (EditText) questionLayout.findViewById(R.id.answer8);
		EditText opt9 = (EditText) questionLayout.findViewById(R.id.answer9);
		EditText opt10 = (EditText) questionLayout.findViewById(R.id.answer10);
		
		textPojo.setQuestionName(questionName.getText().toString());
		textPojo.setQuestionText(questionText.getText().toString());
		textPojo.setQuestionType(questionType+3);
		textPojo.setOption1(opt1.getText().toString().equals("") ? "null" : opt1.getText().toString());
		textPojo.setOption2(opt2.getText().toString().equals("") ? "null" : opt2.getText().toString());
		textPojo.setOption3(opt3.getText().toString().equals("") ? "null" : opt3.getText().toString());
		textPojo.setOption4(opt4.getText().toString().equals("") ? "null" : opt4.getText().toString());
		textPojo.setOption5(opt5.getText().toString().equals("") ? "null" : opt5.getText().toString());
		textPojo.setOption6(opt6.getText().toString().equals("") ? "null" : opt6.getText().toString());
		textPojo.setOption7(opt7.getText().toString().equals("") ? "null" : opt7.getText().toString());
		textPojo.setOption8(opt8.getText().toString().equals("") ? "null" : opt8.getText().toString());
		textPojo.setOption9(opt9.getText().toString().equals("") ? "null" : opt9.getText().toString());
		textPojo.setOption10(opt10.getText().toString().equals("")  ? "null" : opt10.getText().toString());
		RequestCreator requestCreator = new RequestCreator(getApplicationContext());
		new ApiRequester(getApplicationContext(), requestCreator.createQuestionB(textPojo,survey_id, textPojo.getQuestionName(), ""+textPojo.getQuestionText(), ""+textPojo.getQuestionType()), new ApiRequestListner() {
			
			@Override
			public String onSuccess(JSONObject result) {
				loddingIndicator(View.GONE);
				onBackPressed();
				Toast.makeText(getApplicationContext(), "Question created", 1500).show();
				return null;
			}
			
			@Override
			public String onStarted() {
				loddingIndicator(View.VISIBLE);
				return null;
			}
			
			@Override
			public String onFailed() {
				loddingIndicator(View.GONE);
				Toast.makeText(getApplicationContext(), "Unable to create the question", 1500).show();
				return null;
			}
		}).execute("");
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

			answerView.findViewById(R.id.smiley_Set_2).setVisibility(View.GONE);
			answerView.findViewById(R.id.smiley_Set_1).setVisibility(View.VISIBLE);
			maxValue = 5;

		} else if (group.getCheckedRadioButtonId() == R.id.type2_smiley) {
			answerView.findViewById(R.id.smiley_Set_1).setVisibility(View.GONE);
			answerView.findViewById(R.id.smiley_Set_2).setVisibility(View.VISIBLE);
			maxValue = 10;
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
	private void loddingIndicator(int visibility) {
		findViewById(R.id.home_pg_rl).setVisibility(visibility);
	}
}
