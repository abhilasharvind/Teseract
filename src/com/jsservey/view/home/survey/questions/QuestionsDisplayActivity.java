package com.jsservey.view.home.survey.questions;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.Answer;
import com.jsservey.model.Question;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class QuestionsDisplayActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_display_layout);
		LinearLayout questionLayout = (LinearLayout) findViewById(R.id.answer_type_layout);
		SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(getApplicationContext());
		String stringJsonObject=sqLiteHelper.getSurveyQuestions();
		Log.e("abx", ""+stringJsonObject);
		try {
			JSONObject jsonObject = new JSONObject(stringJsonObject);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			ArrayList<Question> questionsArray = new ArrayList<Question>();
			for (int i =0;i<jsonArray.length();i++) {
				JSONObject questionJsonParent = (JSONObject) jsonArray.get(i);
				JSONObject questionJson =questionJsonParent.getJSONObject("questions");
				
				Question question = new Question();
				question.setType_id(questionJson.getString("type_id"));
				question.setQuestion(questionJson.getString("type_id"));
				question.setQuestion_id(questionJson.getString("type_id"));
				question.setType_name(questionJson.getString("type_id"));
				question.setValue(questionJson.getString("type_id"));
				JSONArray answerList =questionJson.getJSONArray("answer");
				ArrayList<Answer> answerlist = new ArrayList<Answer>();
				for (int j =0;j<answerList.length();j++) {
					Answer answer = new Answer();
					JSONObject answerJson=(JSONObject) answerList.get(j);
					answer.setId(answerJson.getString("id"));
					answer.setAnswer_name("answer_name");			
					
					answerlist.add(answer);
				}
				question.setAnswerlist(answerlist);
				
				
				questionsArray.add(question);
				
				Toast.makeText(this, "total:"+questionsArray.size(), 2000).show();
				
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setContent(questionLayout,1);
	}

	
	private void setContent(LinearLayout questionLayout,int type) {
		//if()
		View answerView = getLayoutInflater().inflate(R.layout.question_type_text_layout,questionLayout, false);
		questionLayout.removeAllViews();
		questionLayout.addView(answerView);
	}
}
