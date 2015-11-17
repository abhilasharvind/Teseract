package com.jsservey.view.home.survey.questions;

import com.abx.jsservey.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class QuestionsDisplayActivity extends Activity{
	private View answerView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_display_layout);
		LinearLayout questionLayout = (LinearLayout) findViewById(R.id.answer_type_layout);
		answerView = getLayoutInflater()
				.inflate(R.layout.question_type_text_layout,
						questionLayout, false);
		questionLayout.removeAllViews();
		questionLayout.addView(answerView);
	}

}
