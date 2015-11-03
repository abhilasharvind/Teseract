package com.jsservey.view.home.survey.questions;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.abx.jsservey.R;

public class CreateQuestionTextActivity extends Activity{
	private Spinner spinner1, spinner2;
	  private Button btnSubmit;

@Override
protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.create_question_type_layout);
	super.onCreate(savedInstanceState);
	Spinner staticSpinner = (Spinner) findViewById(R.id.question_type_spinner);
	 
    // Create an ArrayAdapter using the string array and a default spinner
    ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
            .createFromResource(this, R.array.question_array,
                    android.R.layout.simple_spinner_item);

    // Specify the layout to use when the list of choices appears
    staticAdapter
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Apply the adapter to the spinner
    staticSpinner.setAdapter(staticAdapter);
}


  // get the selected dropdown list value
  public void addListenerOnButton() {

	spinner1 = (Spinner) findViewById(R.id.question_type_spinner);
	btnSubmit = (Button) findViewById(R.id.create);
	spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			Toast.makeText(parent.getContext(), 
					"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
		}
	});

	btnSubmit.setOnClickListener(new OnClickListener() {


	@Override
	public void onClick(View arg0) {
		 Toast.makeText(CreateQuestionTextActivity.this,
					"OnClickListener : " + 
			                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) + 
			                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
						Toast.LENGTH_SHORT).show();		
	}

	});
  }
		
	
}
