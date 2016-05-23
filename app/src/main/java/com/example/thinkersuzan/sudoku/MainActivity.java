package com.example.thinkersuzan.sudoku;

        import android.content.Intent;
        import android.os.Handler;
        import android.os.SystemClock;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText[][] editTextsForNumbers = new EditText[9][9];
    int[][] data = new int[9][9];
    String set;
    int m = 0;
    Button check;
    Button pause;
    Button solution;
    TextView time;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    boolean stopTimer = false;
    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            String localtime = "" + mins + ":" + String.format("%02d", secs)
                    + ":" + String.format("%03d", milliseconds);
            time.setText(localtime);
            if (!stopTimer)
                customHandler.postDelayed(this, 0);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0; i < data.length; i++){
            for (int j = 0; j < data[i].length; j++){
                data[i][j] = 0;
            }
        }
        time = (TextView) findViewById(R.id.timeTxtView);
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
        check = (Button) findViewById(R.id.checkBtn);
        pause = (Button) findViewById(R.id.exitBtn);
        findViewsById();
        fillingRandomData();
    }

    public void checkBtnOnClick(View v){
        data = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!(editTextsForNumbers[i][j].getText().toString().equals(""))) {
                    data[i][j] = Integer.parseInt(editTextsForNumbers[i][j].getText().toString());
                } else {
                    data[i][j] = 0;
                }
            }
        }
        if (check() && m == 45) {
            Toast msg = Toast.makeText(getBaseContext(), " Winn! Play again! :D ", Toast.LENGTH_LONG);
            msg.show();
        } else if (!set.isEmpty()) {
            Toast msg = Toast.makeText(getBaseContext(), " Good done! Complete :D ", Toast.LENGTH_LONG);
            msg.show();
        } else {
            Toast msg = Toast.makeText(getBaseContext(), " Failed! Try again! :) ", Toast.LENGTH_LONG);
            msg.show();
        }
    }
    public void exitOnClick(View view){
        Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);//to open new activity
        MainActivity.this.startActivity(myIntent);//start new activity
    }
    private void findViewsById(){
        editTextsForNumbers[0][0] = (EditText) findViewById(R.id.txtCell100);
        editTextsForNumbers[0][1] = (EditText) findViewById(R.id.txtCell101);
        editTextsForNumbers[0][2] = (EditText) findViewById(R.id.txtCell102);
        editTextsForNumbers[0][3] = (EditText) findViewById(R.id.txtCell110);
        editTextsForNumbers[0][4] = (EditText) findViewById(R.id.txtCell111);
        editTextsForNumbers[0][5] = (EditText) findViewById(R.id.txtCell112);
        editTextsForNumbers[0][6] = (EditText) findViewById(R.id.txtCell120);
        editTextsForNumbers[0][7] = (EditText) findViewById(R.id.txtCell121);
        editTextsForNumbers[0][8] = (EditText) findViewById(R.id.txtCell122);
        editTextsForNumbers[1][0] = (EditText) findViewById(R.id.txtCell200);
        editTextsForNumbers[1][1] = (EditText) findViewById(R.id.txtCell201);
        editTextsForNumbers[1][2] = (EditText) findViewById(R.id.txtCell202);
        editTextsForNumbers[1][3] = (EditText) findViewById(R.id.txtCell210);
        editTextsForNumbers[1][4] = (EditText) findViewById(R.id.txtCell211);
        editTextsForNumbers[1][5] = (EditText) findViewById(R.id.txtCell212);
        editTextsForNumbers[1][6] = (EditText) findViewById(R.id.txtCell220);
        editTextsForNumbers[1][7] = (EditText) findViewById(R.id.txtCell221);
        editTextsForNumbers[1][8] = (EditText) findViewById(R.id.txtCell222);
        editTextsForNumbers[2][0] = (EditText) findViewById(R.id.txtCell300);
        editTextsForNumbers[2][1] = (EditText) findViewById(R.id.txtCell301);
        editTextsForNumbers[2][2] = (EditText) findViewById(R.id.txtCell302);
        editTextsForNumbers[2][3] = (EditText) findViewById(R.id.txtCell310);
        editTextsForNumbers[2][4] = (EditText) findViewById(R.id.txtCell311);
        editTextsForNumbers[2][5] = (EditText) findViewById(R.id.txtCell312);
        editTextsForNumbers[2][6] = (EditText) findViewById(R.id.txtCell320);
        editTextsForNumbers[2][7] = (EditText) findViewById(R.id.txtCell321);
        editTextsForNumbers[2][8] = (EditText) findViewById(R.id.txtCell322);
        editTextsForNumbers[3][0] = (EditText) findViewById(R.id.txtCell400);
        editTextsForNumbers[3][1] = (EditText) findViewById(R.id.txtCell401);
        editTextsForNumbers[3][2] = (EditText) findViewById(R.id.txtCell402);
        editTextsForNumbers[3][3] = (EditText) findViewById(R.id.txtCell410);
        editTextsForNumbers[3][4] = (EditText) findViewById(R.id.txtCell411);
        editTextsForNumbers[3][5] = (EditText) findViewById(R.id.txtCell412);
        editTextsForNumbers[3][6] = (EditText) findViewById(R.id.txtCell420);
        editTextsForNumbers[3][7] = (EditText) findViewById(R.id.txtCell421);
        editTextsForNumbers[3][8] = (EditText) findViewById(R.id.txtCell422);
        editTextsForNumbers[4][0] = (EditText) findViewById(R.id.txtCell500);
        editTextsForNumbers[4][1] = (EditText) findViewById(R.id.txtCell501);
        editTextsForNumbers[4][2] = (EditText) findViewById(R.id.txtCell502);
        editTextsForNumbers[4][3] = (EditText) findViewById(R.id.txtCell510);
        editTextsForNumbers[4][4] = (EditText) findViewById(R.id.txtCell511);
        editTextsForNumbers[4][5] = (EditText) findViewById(R.id.txtCell512);
        editTextsForNumbers[4][6] = (EditText) findViewById(R.id.txtCell520);
        editTextsForNumbers[4][7] = (EditText) findViewById(R.id.txtCell521);
        editTextsForNumbers[4][8] = (EditText) findViewById(R.id.txtCell522);
        editTextsForNumbers[5][0] = (EditText) findViewById(R.id.txtCell600);
        editTextsForNumbers[5][1] = (EditText) findViewById(R.id.txtCell601);
        editTextsForNumbers[5][2] = (EditText) findViewById(R.id.txtCell602);
        editTextsForNumbers[5][3] = (EditText) findViewById(R.id.txtCell610);
        editTextsForNumbers[5][4] = (EditText) findViewById(R.id.txtCell611);
        editTextsForNumbers[5][5] = (EditText) findViewById(R.id.txtCell612);
        editTextsForNumbers[5][6] = (EditText) findViewById(R.id.txtCell620);
        editTextsForNumbers[5][7] = (EditText) findViewById(R.id.txtCell621);
        editTextsForNumbers[5][8] = (EditText) findViewById(R.id.txtCell622);
        editTextsForNumbers[6][0] = (EditText) findViewById(R.id.txtCell700);
        editTextsForNumbers[6][1] = (EditText) findViewById(R.id.txtCell701);
        editTextsForNumbers[6][2] = (EditText) findViewById(R.id.txtCell702);
        editTextsForNumbers[6][3] = (EditText) findViewById(R.id.txtCell710);
        editTextsForNumbers[6][4] = (EditText) findViewById(R.id.txtCell711);
        editTextsForNumbers[6][5] = (EditText) findViewById(R.id.txtCell712);
        editTextsForNumbers[6][6] = (EditText) findViewById(R.id.txtCell720);
        editTextsForNumbers[6][7] = (EditText) findViewById(R.id.txtCell721);
        editTextsForNumbers[6][8] = (EditText) findViewById(R.id.txtCell722);
        editTextsForNumbers[7][0] = (EditText) findViewById(R.id.txtCell800);
        editTextsForNumbers[7][1] = (EditText) findViewById(R.id.txtCell801);
        editTextsForNumbers[7][2] = (EditText) findViewById(R.id.txtCell802);
        editTextsForNumbers[7][3] = (EditText) findViewById(R.id.txtCell810);
        editTextsForNumbers[7][4] = (EditText) findViewById(R.id.txtCell811);
        editTextsForNumbers[7][5] = (EditText) findViewById(R.id.txtCell812);
        editTextsForNumbers[7][6] = (EditText) findViewById(R.id.txtCell820);
        editTextsForNumbers[7][7] = (EditText) findViewById(R.id.txtCell821);
        editTextsForNumbers[7][8] = (EditText) findViewById(R.id.txtCell822);
        editTextsForNumbers[8][0] = (EditText) findViewById(R.id.txtCell900);
        editTextsForNumbers[8][1] = (EditText) findViewById(R.id.txtCell901);
        editTextsForNumbers[8][2] = (EditText) findViewById(R.id.txtCell902);
        editTextsForNumbers[8][3] = (EditText) findViewById(R.id.txtCell910);
        editTextsForNumbers[8][4] = (EditText) findViewById(R.id.txtCell911);
        editTextsForNumbers[8][5] = (EditText) findViewById(R.id.txtCell912);
        editTextsForNumbers[8][6] = (EditText) findViewById(R.id.txtCell920);
        editTextsForNumbers[8][7] = (EditText) findViewById(R.id.txtCell921);
        editTextsForNumbers[8][8] = (EditText) findViewById(R.id.txtCell922);
    }
    private void fillingRandomData(){
        int count = 0, r, c, num;
        r = (int) (Math.random() * 8);
        c = (int) (Math.random() * 8);
        num = 1 + (int) (Math.random() * 9);
        data[r][c] = num;
        solve();
        Random rn = new Random();
        while (count < 55) {
            int m = (int) rn.nextInt((8 - 0) + 1) + 0;
            int n = (int) rn.nextInt((8 - 0) + 1) + 0;
            data[m][n] = 0;
            count++;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (data[i][j] != 0) {
                    editTextsForNumbers[i][j].setText(data[i][j] + "");
                    editTextsForNumbers[i][j].setEnabled(false);
                } else {
                    editTextsForNumbers[i][j].setText("");
                    editTextsForNumbers[i][j].setEnabled(true);
                }
            }
        }
    }
    public boolean solve() {
        int[][] status = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] > 0) {
                    status[i][j] = 2;
                } else {
                    status[i][j] = 0;
                }
            }
        }
        return solve(status, 0, 0);
    }
    private boolean solve(int[][] status, int x, int y) {
        if (x == 9) {
            int count = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (status[i][j] > 0) {
                        count += 1;
                    } else {
                        count += 0;
                    }
                }
            }
            if (count == 81) {
                return true;
            } else {
                return false;
            }
        }
        if (status[x][y] >= 1) {
            int nextX = x;
            int nextY = y + 1;
            if (nextY == 9) {
                nextX = x + 1;
                nextY = 0;
            }
            return solve(status, nextX, nextY);
        } else {
            boolean[] used = new boolean[9];
            for (int i = 0; i < 9; i++) {
                if (status[x][i] >= 1) {
                    used[data[x][i] - 1] = true;

                }
            }
            for (int i = 0; i < 9; i++) {
                if (status[i][y] >= 1) {
                    used[data[i][y] - 1] = true;
                }
            }
            for (int i = x - (x % 3); i < x - (x % 3) + 3; i++) {
                for (int j = y - (y % 3); j < y - (y % 3) + 3; j++) {
                    if (status[i][j] >= 1) {
                        used[data[i][j] - 1] = true;
                    }
                }
            }
            for (int i = 0; i < used.length; i++) {
                if (!used[i]) {
                    status[x][y] = 1;
                    data[x][y] = i + 1;
                    int nextX = x;
                    int nextY = y + 1;
                    if (nextY == 9) {
                        nextX = x + 1;
                        nextY = 0;
                    }
                    if (solve(status, nextX, nextY)) {
                        return true;
                    }
                    for (int j = 0; j < 9; j++) {
                        for (int k = 0; k < 9; k++) {
                            if ((j > x) || (j == x && k >= y)) {
                                if (status[j][k] == 1) {
                                    status[j][k] = 0;
                                    data[j][k] = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean check() {
        String setValues = "123456789";
        for (int i = 0; i < data.length; i++) {
            set = setValues;
            m = 0;
            for (int j = 0; j < data.length; j++) {
                set = set.replace("" + data[i][j], "");
                m += data[i][j];
            }
            if (m > 45) {
                return false;
            }
        }
        for (int j = 0; j < data.length; j++) {
            set = setValues;
            m = 0;
            for (int i = 0; i < data.length; i++) {
                set = set.replace("" + data[i][j], "");
                m += data[i][j];
            }
            if (m > 45) {
                return false;
            }
        }
        for (int h = 0; h < data.length; h += 3) {
            for (int v = 0; v < data[h].length; v += 3) {
                set = setValues;
                m = 0;
                for (int i = h; i < h + 3; i++) {
                    for (int j = v; j < v + 3; j++) {
                        set = set.replace("" + data[i][j], "");
                        m += data[i][j];
                    }
                }
                if (m > 45) {
                    return false;
                }
            }
        }
        return true;
    }
}
