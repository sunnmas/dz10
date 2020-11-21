package com.example.dz10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.dz10.databinding.ActivityMainBinding;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private char CURRENT_ACTION = ' ';
    private String op1 = ""; boolean op1_undefined = false;
    private String op2 = "";
    private Pattern digit_pattern = Pattern.compile("[0-9\\.]");
    private DecimalFormat decimalFormat = new DecimalFormat("#.###");;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Button.OnClickListener OnButtonClick = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sym = (String) ((Button) v).getText();
                String equation = (String) binding.result.getText();
                binding.result.setText(equation + sym);
                if (digit_pattern.matcher(sym).find()) {
                    if ((op1.length() == 0) && (op2.length() == 0))
                        binding.result.setText(sym);
                    if ((sym.charAt(0) == '.') && (op1.indexOf('.') >=0)) {
                        binding.result.setText(equation);
                        return;
                    }
                    op1 += sym;
                } else if (sym.charAt(0) == '=') {
                    if (op1.length() == 0) {
                        binding.result.setText("0.0");
                        return;}
                    if (op2.length() == 0) return;
                    String result = decimalFormat.format(compute());
                    binding.result.setText((String) binding.result.getText() + result);
                    clear();
                } else if (sym.charAt(0) == 'C') {
                    clear();
                    binding.result.setText("0.0");
                } else {
                    if (op1.length() == 0) {
                        binding.result.setText("0.0");
                        return;}
                    if (CURRENT_ACTION != ' ') {
                        binding.result.setText(equation);
                        return;}
                    CURRENT_ACTION = sym.charAt(0);
                    op2 = op1; op1 = "";
                }
            }
        };
        binding.btDot.setOnClickListener(OnButtonClick);
        binding.bt0.setOnClickListener(OnButtonClick);
        binding.bt1.setOnClickListener(OnButtonClick);
        binding.bt2.setOnClickListener(OnButtonClick);
        binding.bt3.setOnClickListener(OnButtonClick);
        binding.bt4.setOnClickListener(OnButtonClick);
        binding.bt5.setOnClickListener(OnButtonClick);
        binding.bt6.setOnClickListener(OnButtonClick);
        binding.bt7.setOnClickListener(OnButtonClick);
        binding.bt8.setOnClickListener(OnButtonClick);
        binding.bt9.setOnClickListener(OnButtonClick);
        binding.btAdd.setOnClickListener(OnButtonClick);
        binding.btSub.setOnClickListener(OnButtonClick);
        binding.btMul.setOnClickListener(OnButtonClick);
        binding.btDiv.setOnClickListener(OnButtonClick);
        binding.btEq.setOnClickListener(OnButtonClick);
        binding.btClear.setOnClickListener(OnButtonClick);
    }
    private double compute() {
        double Op1 = Double.parseDouble(op2);
        double Op2 = Double.parseDouble(op1);
        if (CURRENT_ACTION == ADDITION) {return Op1 + Op2;}
        else if (CURRENT_ACTION == SUBTRACTION) {return Op1 - Op2;}
        else if (CURRENT_ACTION == MULTIPLICATION) {return Op1 * Op2;}
        else {return Op1 / Op2;}
    };
    private void clear() {
        op1 = ""; op2 = "";
        CURRENT_ACTION = ' ';
    }
}