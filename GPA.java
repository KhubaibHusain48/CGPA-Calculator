package com.example.cgpacalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GPA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GPA extends Fragment {
    EditText gpa1, gpa2, gpa3, gpa4, gpa5, gpa6, gpa7, gpa8, ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8;
    Button calculate, reset;
    TextView Result;
    double result;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GPA() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GPA.
     */
    // TODO: Rename and change types and number of parameters
    public static GPA newInstance(String param1, String param2) {
        GPA fragment = new GPA();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_g_p_a, container, false);

        gpa1 = v.findViewById(R.id.gpa1);
        gpa2 = v.findViewById(R.id.gpa2);
        gpa3 = v.findViewById(R.id.gpa3);
        gpa4 = v.findViewById(R.id.gpa4);
        gpa5 = v.findViewById(R.id.gpa5);
        gpa6 = v.findViewById(R.id.gpa6);
        gpa7 = v.findViewById(R.id.gpa7);
        gpa8 = v.findViewById(R.id.gpa8);
        ch1 = v.findViewById(R.id.ch1);
        ch2 = v.findViewById(R.id.ch2);
        ch3 = v.findViewById(R.id.ch3);
        ch4 = v.findViewById(R.id.ch4);
        ch5 = v.findViewById(R.id.ch5);
        ch6 = v.findViewById(R.id.ch6);
        ch7 = v.findViewById(R.id.ch7);
        ch8 = v.findViewById(R.id.ch8);
        calculate = v.findViewById(R.id.calcButton);
        reset = v.findViewById(R.id.resetButton);
        Result = v.findViewById(R.id.resultShow);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CG();
                Result.setText("Your Semester GPA is " + String.format("%.2f", result));
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpa1.setText("");
                ch1.setText("");
                gpa2.setText("");
                ch2.setText("");
                gpa3.setText("");
                ch3.setText("");
                gpa4.setText("");
                ch4.setText("");
                gpa5.setText("");
                ch5.setText("");
                gpa6.setText("");
                ch6.setText("");
                gpa7.setText("");
                ch7.setText("");
                gpa8.setText("");
                ch8.setText("");
                Result.setText("");
            }
        });

        return v;
    }

    public void CG() {
        double totalCG = 0;
        double totalCH = 0;

        EditText[] gpas = {gpa1, gpa2, gpa3, gpa4, gpa5, gpa6, gpa7, gpa8};
        EditText[] chs = {ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8};

        for (int i = 0; i < gpas.length; i++) {
            String gpaStr = gpas[i].getText().toString().trim();
            String chStr = chs[i].getText().toString().trim();

            if (!gpaStr.isEmpty() && !chStr.isEmpty()) {
                try {
                    double gpaVal = Double.parseDouble(gpaStr);
                    double chVal = Double.parseDouble(chStr);
                    totalCG += (gpaVal * chVal);
                    totalCH += chVal;
                } catch (NumberFormatException e) {
                    // Optionally show a toast or message for wrong input
                    Result.setText("Invalid input detected in row " + (i + 1));
                    return;
                }
            }
        }

        if (totalCH > 0) {
            result = totalCG / totalCH;
            Result.setText("Your Semester GPA is: " + String.format("%.2f", result));
        } else {
            Result.setText("Please enter at least one valid GPA & Credit Hour pair.");
        }
    }

}