package com.example.cgpacalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CGPA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CGPA extends Fragment {
    EditText[] gpaInputs, creditInputs;
    Button calculateBtn, resetBtn;
    TextView result;
    double cgpa;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CGPA() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CGPA.
     */
    // TODO: Rename and change types and number of parameters
    public static CGPA newInstance(String param1, String param2) {
        CGPA fragment = new CGPA();
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
        View v= inflater.inflate(R.layout.fragment_c_g_p_a, container, false);

        gpaInputs = new EditText[]{
                v.findViewById(R.id.gp1),
                v.findViewById(R.id.gp2),
                v.findViewById(R.id.gp3),
                v.findViewById(R.id.gp4),
                v.findViewById(R.id.gp5),
                v.findViewById(R.id.gp6),
                v.findViewById(R.id.gp7),
                v.findViewById(R.id.gp8)
        };

        creditInputs = new EditText[]{
                v.findViewById(R.id.c1),
                v.findViewById(R.id.c2),
                v.findViewById(R.id.c3),
                v.findViewById(R.id.c4),
                v.findViewById(R.id.c5), v.findViewById(R.id.c6),
                v.findViewById(R.id.c7),
                v.findViewById(R.id.c8)
        };

        calculateBtn = v.findViewById(R.id.calcBtn);
        resetBtn = v.findViewById(R.id.resetBtn);
        result = v.findViewById(R.id.resultText);

        // Calculate CGPA button logic
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCGPA();
            }
        });

        // Reset all fields
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < gpaInputs.length; i++) {
                    gpaInputs[i].setText("");
                    creditInputs[i].setText("");
                }
                result.setText("");
            }
        });

        return v;
    }

    public void calculateCGPA() {
        double totalWeightedGPA = 0;
        double totalCreditHours = 0;

        for (int i = 0; i < gpaInputs.length; i++) {
            String gpaText = gpaInputs[i].getText().toString().trim();
            String chText = creditInputs[i].getText().toString().trim();

            if (!gpaText.isEmpty() && !chText.isEmpty()) {
                try {
                    double gpa = Double.parseDouble(gpaText);
                    double ch = Double.parseDouble(chText);

                    totalWeightedGPA += (gpa * ch);
                    totalCreditHours += ch;

                } catch (NumberFormatException e) {
                    result.setText("Invalid input at Semester " + (i + 1));
                    return;
                }
            }
        }

        if (totalCreditHours > 0) {
            cgpa = totalWeightedGPA / totalCreditHours;
            result.setText("Your CGPA is: " + String.format("%.2f", cgpa));
        } else {
            result.setText("Please enter at least one GPA and Credit Hour pair.");
        }
    }

}


