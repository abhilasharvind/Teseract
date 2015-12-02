package com.jsservey.view.home.survey.questions;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.Answer;
import com.jsservey.model.Question;
import com.jsservey.utils.Utility;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class QuestionsDisplayActivity extends Activity implements
		OnClickListener,OnRatingBarChangeListener {
	LinearLayout questionLayout;
	ArrayList<Answer> answerlist;
	ArrayList<Question> questionsArray;
	int questionNumber = 0;
	int questionCount = 0;
	RatingBar ratingBar;
	TextView progressValue;
	Button submitButton,backButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.question_display_layout);
		
		questionLayout = (LinearLayout) findViewById(R.id.answer_type_layout);
		submitButton = (Button) findViewById(R.id.submit);
		backButton = (Button) findViewById(R.id.back);
		backButton.setOnClickListener(this);
		submitButton.setOnClickListener(this);
		SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(getApplicationContext());
		String stringJsonObject = sqLiteHelper.getSurveyQuestions();
		Log.e("abx", "" + stringJsonObject);
		try {
			JSONObject jsonObject = new JSONObject(stringJsonObject);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			questionsArray = new ArrayList<Question>();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject questionJsonParent = (JSONObject) jsonArray.get(i);
				JSONObject questionJson = questionJsonParent.getJSONObject("questions");

				Question question = new Question();
				question.setType_id(questionJson.getString("type_id"));
				question.setQuestion(questionJson.getString("question"));
				question.setQuestion_id(questionJson.getString("question_id"));
				question.setType_name(questionJson.getString("type_name"));
				question.setValue(questionJson.getString("value"));
				question.setHalf_rating(questionJson.getString("half_rating"));
				JSONArray answerList = questionJson.getJSONArray("answer");
				answerlist = new ArrayList<Answer>();
				for (int j = 0; j < answerList.length(); j++) {
					Answer answer = new Answer();
					JSONObject answerJson = (JSONObject) answerList.get(j);
					answer.setId(answerJson.getString("id"));
					answer.setAnswer_name(answerJson.getString("answer_name"));
					answerlist.add(answer);
				}
				question.setAnswerlist(answerlist);
				questionsArray.add(question);
				//Toast.makeText(this, "total:" + questionsArray.size(), 2000).show();
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		questionCount = questionsArray.size();
		if (!questionsArray.isEmpty()) {
			setContent(questionsArray.get(0).getType_id(), questionsArray.get(0).getAnswerlist(), 0); // first question and answers
		}
		
		backButton.setVisibility(View.GONE);
		

	}

	private void setContent(String type, ArrayList<Answer> answerlist,
			int questionNumber) {
		// RelativeLayout mainLayout = (RelativeLayout)
		// findViewById(R.layout.question_display_layout);
		TextView questionText = (TextView) findViewById(R.id.question_text);
		String value = questionsArray.get(questionNumber).getValue();
		String halfRating = questionsArray.get(questionNumber).getHalf_rating();
		if (type.equalsIgnoreCase("3")) {
			questionText.setText(questionsArray.get(questionNumber)
					.getQuestion());
			View answerView = getLayoutInflater().inflate(
					R.layout.question_type_text_layout, questionLayout, false);
			questionLayout.removeAllViews();
			questionLayout.addView(answerView);
			enableAnswerTextList(answerView, answerlist);

		} else if (type.equalsIgnoreCase("4")) {
			questionText.setText(questionsArray.get(questionNumber)	.getQuestion());
			View answerView = getLayoutInflater().inflate(R.layout.question_type_progess_layout, questionLayout,
					false);
			progressValue = (TextView) answerView.findViewById(R.id.selected_progress_value);
			Log.d("abx", value);   
			if (value.equalsIgnoreCase("10")) {
				ratingBar = (RatingBar) answerView.findViewById(R.id.ratingBar2);
				answerView.findViewById(R.id.ratingBar1).setVisibility(	View.GONE);
				ratingBar.setVisibility(View.VISIBLE);
				if(halfRating.equalsIgnoreCase("0")){
					ratingBar.setStepSize((float) 1.0);
				}else{
					ratingBar.setStepSize((float) 0.5);
				}
				questionLayout.removeAllViews();
				questionLayout.addView(answerView);
				ratingBar.setOnRatingBarChangeListener(this);	
			} else if (value.equalsIgnoreCase("5")) {
				ratingBar = (RatingBar) answerView.findViewById(R.id.ratingBar1);
				answerView.findViewById(R.id.ratingBar2).setVisibility(View.GONE);
				ratingBar.setVisibility(View.VISIBLE);
				if(halfRating.equalsIgnoreCase("0")){
					ratingBar.setStepSize((float) 1.0);
				}else{
					ratingBar.setStepSize((float) 0.5);
				}
				questionLayout.removeAllViews();
				questionLayout.addView(answerView);
				ratingBar.setOnRatingBarChangeListener(this);
			}
			
		} else if (type.equalsIgnoreCase("5")) {
			questionText.setText(questionsArray.get(questionNumber)
					.getQuestion());
			View answerView = getLayoutInflater().inflate(R.layout.question_type_seek_layout, questionLayout, false);
			questionLayout.removeAllViews();
			questionLayout.addView(answerView);
		} else if (type.equalsIgnoreCase("6")) {
			questionText.setText(questionsArray.get(questionNumber)
					.getQuestion());
			View answerView = getLayoutInflater()
					.inflate(R.layout.question_type_smiley_layout,
							questionLayout, false);
			questionLayout.removeAllViews();
			questionLayout.addView(answerView);
		}
	}

	private void enableAnswerTextList(View answerView,
			ArrayList<Answer> answerlist) {
		int[] textViewIds = new int[] { R.id.answer1, R.id.answer2,
				R.id.answer3, R.id.answer4, R.id.answer5, R.id.answer6,
				R.id.answer7, R.id.answer8, R.id.answer9, R.id.answer10 };

		for (int i = 0; i < answerlist.size(); i++) {
			((TextView) findViewById(textViewIds[i]))
					.setVisibility(View.VISIBLE);
			((TextView) findViewById(textViewIds[i])).setText(answerlist.get(i)
					.getAnswer_name());
		}
	}

	@Override
	public void onClick(View view) {
		if(questionNumber < 0){
			backButton.setVisibility(View.GONE);
		}else{
			backButton.setVisibility(View.VISIBLE);
		}
		if (view.getId() == R.id.submit) {
			if (questionNumber < questionCount - 1) {
				questionNumber++;
				setContent(questionsArray.get(questionNumber).getType_id(),
						questionsArray.get(questionNumber).getAnswerlist(),
						questionNumber);
				
			}else{
				Utility.startActivity(QuestionsDisplayActivity.this, CustomerInfoActivity.class);
				//load thank you page
			}
		} else if (view.getId() == R.id.back) {
			if (questionNumber > 0) {
				questionNumber--;
				setContent(questionsArray.get(questionNumber).getType_id(),
						questionsArray.get(questionNumber).getAnswerlist(),
						questionNumber);
			}
		}
	}

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating, boolean from) {
		progressValue.setText(String.valueOf(rating));
		
	}
}