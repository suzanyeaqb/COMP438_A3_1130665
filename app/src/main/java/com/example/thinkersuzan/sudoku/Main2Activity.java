package com.example.thinkersuzan.sudoku;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void startOnClick(View view){
        Intent myIntent = new Intent(Main2Activity.this, MainActivity.class);//to open new activity
        Main2Activity.this.startActivity(myIntent);//start new activity
    }
    public void aboutOnClick(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Sudoku Game");
        alertDialog.setMessage("The objective is to fill a 9x9 grid so" + "\n" +
                " that each column, each row," + "\n" +
                " and each of the nine 3x3 boxes (also called blocks or regions)"+ "\n" +" contains the digits from 1 to 9. \n"
                + "A cell is the smallest block in the game." + "\n" +
                "A row , column and region consists of 9 cells" + "\n" +
                " and the whole game consists of 81 cells." + "\n" +
                "A region has thicker lines surrounding it." + "\n" +
                " This simply makes it easier to play the game." + "\n" +
                "Created by Tasneem Barghouthi ");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }
    public void highScoreOnClick(View view){

    }
    public void exitOnClick(View view){
        this.finish();
    }
}
