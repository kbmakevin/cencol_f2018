package com.cencol.kevinma_comp304_001_hands_on_test1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ExerciseSelection extends AppCompatActivity {

    // displayed in Toast when user clicks Next btn
    private String _displayText;
    private int _xPos;
    private int _startY;
    private int _endY;
    //
    private Paint _paint;
    private Bitmap _bitmap;
    private Canvas _canvas;
    private ImageView _zigZagImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_selection);

        // Draw zigzag line on an image view
        // init vars
        this._paint = new Paint();
        this._paint.setColor(Color.GREEN);
        this._paint.setStrokeWidth(10);
        //creating a bitmap as content view for the image
        this._bitmap = Bitmap.createBitmap(getWindowManager()
                .getDefaultDisplay().getWidth(), 200, Bitmap.Config.ARGB_8888);
        this._canvas = new Canvas(this._bitmap);
        this._canvas.drawColor(Color.BLACK); //background

        this._zigZagImgView = findViewById(R.id.ImageViewForDrawing);
        this._zigZagImgView.setImageBitmap(this._bitmap);
        this._zigZagImgView.setVisibility(View.VISIBLE);

        // draw zigzag line
        int yPosCounter = 0;
        while (this._xPos < getWindowManager().getDefaultDisplay().getWidth()) {

            this._startY = yPosCounter % 2 == 0 ? Integer.parseInt((getResources().getStringArray(R.array.zig_zag_y_coords_val))[0]) : Integer.parseInt((getResources().getStringArray(R.array.zig_zag_y_coords_val))[1]);

            this._endY = yPosCounter % 2 == 0 ? Integer.parseInt((getResources().getStringArray(R.array.zig_zag_y_coords_val))[1]) : Integer.parseInt((getResources().getStringArray(R.array.zig_zag_y_coords_val))[0]);

            yPosCounter++;

            this._canvas.drawLine(this._xPos, this._startY, this._xPos += getResources().getInteger(R.integer.zig_zag_line_x_increment), this._endY, this._paint);
        }

        // Attach Event Listeners
        RadioGroup exerciseSelectionRadioGrp = findViewById(R.id.exSelRadioGrp);
        exerciseSelectionRadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int idChecked) {

                switch (idChecked) {
                    case R.id.exRadioButton:
                        _displayText = getResources().getString(R.string.second_act_radio_btn_text);
                        break;
                    case R.id.exRadioButton2:
                        _displayText = getResources().getString(R.string.second_act_radio_btn_text2);
                        break;
                    case R.id.exRadioButton3:
                        _displayText = getResources().getString(R.string.second_act_radio_btn_text3);
                        break;
                    case R.id.exRadioButton4:
                        _displayText = getResources().getString(R.string.second_act_radio_btn_text4);
                        break;
                }
            }
        });

        Button nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ExerciseSelection.this, _displayText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // helper method
    private void _drawLine(Canvas canvas, Paint paint, int startX, int startY, int endY) {
        // as per documented requirements x inc by 100px
        int endX = startX + 100;
        // as per documented requirements y is read from a string array from strings.xml

        canvas.drawLine(startX, startY, endX, endY, paint);
    }
}
