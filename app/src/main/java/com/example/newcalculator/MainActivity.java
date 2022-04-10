package com.example.newcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView screen;
    private String input, answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.screen);
        Button AC = findViewById(R.id.ac);
        Button power = findViewById(R.id.power);
        Button back = findViewById(R.id.back);
        Button div = findViewById(R.id.div);
        Button mul = findViewById(R.id.multiply);
        Button sub = findViewById(R.id.subtract);
        Button add = findViewById(R.id.add);
        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        Button zero = findViewById(R.id.zero);
        Button ans = findViewById(R.id.answer);
        Button decimal = findViewById(R.id.decimal);
        Button equals = findViewById(R.id.equals);

    }
    public void ButtonClick(View view){
//        OnClick has been assigned in XML code and there we have passed this ButtonClick method.

        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data){
            case"AC":
                input="";
//                clear();
                break;
            case "Ans":
                input+=answer;
            case"*":
                solve();
//                Solve method has to be written above the line - input+="_", so that the result of the previous operation can be obtained, like if the user presses 1+2, and then he presses -, then the equation will be 1+2-. Instead of letting the equation become this, the solve is written first so that the equation becomes : 3-.
                input+="*";
                break;
            case "^":
                solve();
                input+="^";
                break;
            case"=":
                solve();
                answer = input;
                break;
            case"«":
                String newText = input.substring(0,input.length()-1);
                input = newText;
                break;
            default:
                if (input == null){
                    input = "";
                }
                if (data.equals("+") || data.equals("-") || data.equals("/")){
                    solve();
                }
                input+=data;
        }
        screen.setText(input);
    }
    public void solve(){
        if(input.split("\\*").length==2){
//             This means that if the operation to be performed (say 2*3) is to be performed, then we split the String from '*' and if the length becomes 2, then the compiler will go into this block
            String[] numbers =input.split("\\*");
// We are storing the split String(which is split by '*') into an array of Strings.
            try{
                double mul=Double.parseDouble(numbers[0])*Double.parseDouble(numbers[1]);
                input=mul+"";
            }
            catch (Exception e){
                //Display error
                Toast.makeText(MainActivity.this, "error: "+ e, Toast.LENGTH_SHORT).show();
            }
        }
        else if(input.split("/").length==2){

            String[] numbers =input.split("/");
            try{
                double div=Double.parseDouble(numbers[0])/Double.parseDouble(numbers[1]);
                input=div+"";
            }
            catch (Exception e){
                //Display error
            }
        }
        else if(input.split("\\^").length==2){
            String numbers[]=input.split("\\^");
            try{
                double pow=Math.pow(Double.parseDouble(numbers[0]),Double.parseDouble(numbers[1]));
                input=pow+"";
            }
            catch (Exception e){
                //Display error
            }
        }
        else if(input.split("\\+").length==2){
            String numbers[]=input.split("\\+");
            try{
                double sum=Double.parseDouble(numbers[0])+Double.parseDouble(numbers[1]);
                input=sum+"";
            }
            catch (Exception e){
                //Display error
            }
        }
        else if(input.split("\\-").length>1){
            String numbers[]=input.split("\\-");
            if(numbers[0]=="" && numbers.length==2){
                numbers[0]=0+"";
            }
            try{
                double sub=0;
                if(numbers.length==2) {
//                    if the string is split in two parts, then it means that the equation is like: 2-3.
                    sub = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                }
                else if(numbers.length==3){
//                    if the string is split in 3, then it means that we are trying to subtract a negative number from a negative number.
                    sub = -Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[2]);
                }
                input=sub+"";
            }
            catch (Exception e){
                //Display error
            }
        }
        String n[]=input.split("\\.");
        if(n.length>1){
            if(n[1].equals("0")){
                input=n[0];
            }
//            This block of code will convert answers like 11.0 into 11.
        }
        screen.setText(input);
    }
//    public void clear(){
//        player2 = MediaPlayer.create(this,R.raw.sound2);
//        player2.start();
//    }
}
