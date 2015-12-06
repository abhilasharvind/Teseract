package com.jsservey.view.home.survey.questions;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.abx.jsservey.R;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.Answer;
import com.jsservey.model.Question;
import com.jsservey.model.SurveySubmitData;
import com.jsservey.utils.Utility;
import com.jsservey.view.home.HomeActivity;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.QuestionRequestCreator;

public class QuestionsDisplayActivity extends Activity implements
		OnClickListener,OnRatingBarChangeListener,OnSeekBarChangeListener {
	LinearLayout questionLayout;
	ArrayList<Answer> answerlist;
	ArrayList<Question> questionsArray;
	ArrayList<SurveySubmitData> submitanswerArr = new ArrayList<SurveySubmitData>();
	int questionNumber = 0;
	int questionCount = 0;
	RatingBar ratingBar;
	TextView progressValue;
	
	Button backButton;int gocrazycount=0;
	String ratingValue="";
	String selectedAnsId="";
	private TextView seekValueText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.question_display_layout);
		
		questionLayout = (LinearLayout) findViewById(R.id.answer_type_layout);
		findViewById(R.id.submit).setOnClickListener(this);;
		backButton = (Button) findViewById(R.id.back);
		backButton.setOnClickListener(this);		 
		findViewById(R.id.company_title).setOnClickListener(this);
		
		SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(getApplicationContext());
		String stringJsonObject = sqLiteHelper.getSurveyQuestions();
		Log.e("abx", "" + stringJsonObject);
		try {
			JSONObject jsonObject = new JSONObject(stringJsonObject);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			questionsArray = new ArrayList<Question>();
			for (int i = 0; i < jsonArray.length(); i++) {

				Question question = new Question();
				try{
				JSONObject questionJsonParent = (JSONObject) jsonArray.get(i);
				JSONObject questionJson = questionJsonParent.getJSONObject("questions");		
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
				}catch(Exception e){
					e.printStackTrace();
				}
				
				}

		} catch (JSONException e) {
 			e.printStackTrace();
		}
		questionCount = questionsArray.size();
		if (!questionsArray.isEmpty()) {
			setContent(questionsArray.get(0).getType_id(), questionsArray.get(0).getAnswerlist(), 0); // first question and answers
		
		}
		
		backButton.setVisibility(View.GONE);
		

	}
@Override
public void onConfigurationChanged(Configuration newConfig) {
	super.onConfigurationChanged(newConfig);
	//do nothing
}
	private void setContent(String type, ArrayList<Answer> answerlist,
			int questionNumber) {
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

		} else if (type.equalsIgnoreCase("4")) {//rating
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
			} else{
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
			
		} else if (type.equalsIgnoreCase("5")) {//seek
			questionText.setText(questionsArray.get(questionNumber)
					.getQuestion());
			View answerView = getLayoutInflater().inflate(R.layout.question_type_seek_layout, questionLayout, false);
			questionLayout.removeAllViews();
			questionLayout.addView(answerView);
			seekValueText = (TextView) findViewById(R.id.seek_max);
			SeekBar answerSeek = (SeekBar) answerView
					.findViewById(R.id.answer_seek);
			answerSeek.setOnSeekBarChangeListener(this);
		} else if (type.equalsIgnoreCase("6")) {//smiley
			questionText.setText(questionsArray.get(questionNumber)
					.getQuestion());
			View answerView = getLayoutInflater()
					.inflate(R.layout.question_type_smiley_layout,questionLayout, false);
			questionLayout.removeAllViews();
			questionLayout.addView(answerView);
		}
	}

	private void enableAnswerTextList(View answerView,
			final ArrayList<Answer> answerlist) {
		int[] textViewIds = new int[] { R.id.answer1, R.id.answer2,
				R.id.answer3, R.id.answer4, R.id.answer5, R.id.answer6,
				R.id.answer7, R.id.answer8, R.id.answer9, R.id.answer10 };
		int[] layoutIds = new int[] { R.id.view1, R.id.view2,
				R.id.view3, R.id.view4, R.id.view5, R.id.view6,
				R.id.view7, R.id.view8, R.id.view9, R.id.view10 };

		for (int i = 0; i < answerlist.size(); i++) {
			((View) findViewById(layoutIds[i]))
					.setVisibility(View.VISIBLE);
			( findViewById(textViewIds[i])).setVisibility(View.VISIBLE);
			((TextView) findViewById(textViewIds[i])).setText(answerlist.get(i).getAnswer_name());
			final String ansid=answerlist.get(i).getId();
			 findViewById(textViewIds[i]).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stubs
					selectedAnsId=ansid;
					Log.e("a", selectedAnsId);
				}
			});
		}
	}

	@Override
	public void onClick(View view) {
		if (questionNumber < 0) {
			backButton.setVisibility(View.GONE);
		} else {
			backButton.setVisibility(View.VISIBLE);
		}

		int id = view.getId();
		switch (id) {
		case R.id.submit:
			if (questionNumber < questionCount - 1) {
				questionNumber++;
				//add to surveydata array
				
				Log.e("abx", questionsArray.get(questionNumber).getType_id());
				
				String type=questionsArray.get(questionNumber).getType_id();
				setSurveyData(type);
				
				setContent(questionsArray.get(questionNumber).getType_id(),
						questionsArray.get(questionNumber).getAnswerlist(),
						questionNumber);
				
				
				
				

			} else {
				Utility.startActivity(QuestionsDisplayActivity.this,
						CustomerInfoActivity.class);
				// load thank you page
				//
				QuestionRequestCreator questionRequestCreator = new QuestionRequestCreator(getApplicationContext());
				for (SurveySubmitData data : submitanswerArr) {
					new ApiRequester(getApplicationContext(), questionRequestCreator.surveyData(data, "123"), new ApiRequestListner() {
						
						@Override
						public String onSuccess(JSONObject result) {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public String onStarted() {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public String onFailed() {
							// TODO Auto-generated method stub
							return null;
						}
					}).execute("");
					
				}
			}

			break;
		case R.id.back:
			if (questionNumber > 0) {
				questionNumber--;

				setContent(questionsArray.get(questionNumber).getType_id(),
						questionsArray.get(questionNumber).getAnswerlist(),
						questionNumber);
				questionsArray.remove(questionNumber);
			}

			break;
		case R.id.company_title:
		gocrazycount++;
		if (gocrazycount ==7){
			findViewById(R.id.backdoor).setVisibility(View.VISIBLE);
			gocrazy_backdoor();
		}

			break;
		default:
			break;
		}

	}

	private void setSurveyData(String type) {
		SurveySubmitData data = new SurveySubmitData();
		data.setQues_id(questionsArray.get(questionNumber).getQuestion_id());
		if (type.equals("3")) {//text
			data.setAns_id(selectedAnsId);
			data.setAns_value("");

		} else if (type.equals("4")) {//rating
			data.setAns_id("");
			data.setAns_value(ratingValue);

		} else if (type.equals("5")) {//seek
			data.setAns_id("");
			
			//progressValue.gett

		} else if (type.equals("6")) {//smiley
			data.setAns_id("");

		}
		submitanswerArr.add(data);
	}

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating, boolean from) {
		progressValue.setText(String.valueOf(rating));
		ratingValue=String.valueOf(rating);
		
	}
	private void gocrazy_backdoor() {
		findViewById(R.id.gocrazy_bt).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				gocrazycount=0;
				EditText gEditText =(EditText) findViewById(R.id.gocrazy_ed);
				
				if(gEditText.getText().toString().equals("gocrazy001")){
					Utility.startActivity(QuestionsDisplayActivity.this, HomeActivity.class);	
				}
			}

			
		});
		
	}
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		int progessValue = seekBar.getProgress();
		seekValueText.setText(progessValue + "");
	}
	
}