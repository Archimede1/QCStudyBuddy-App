package com.cornely.qcstudybuddy.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cornely.qcstudybuddy.CreateProfileActivity;
import com.cornely.qcstudybuddy.FragmentReplacerActivity;
import com.cornely.qcstudybuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {

    private EditText fullName, email, password, confirmPassword;
    private ProgressBar progressBar;
    private TextView loginTV;
    private Button signUpBtn;
    private FirebaseAuth auth;
    public static final String EMAIL_REGEX = "^(.+)@(.+)S";


    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initiate(view);
        clickListener();
    }


    private void initiate(View view){
        fullName =  view.findViewById(R.id.fullName);
        email =  view.findViewById(R.id.email);
        password =  view.findViewById(R.id.password);
        confirmPassword =  view.findViewById(R.id.confirmPassword);
        loginTV =  view.findViewById(R.id.loginTV);
        signUpBtn =  view.findViewById(R.id.signUpBtn);
        progressBar =  view.findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();
    }

    private void clickListener(){
        loginTV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((FragmentReplacerActivity)getActivity()).setFragment(new LoginFragment());
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullName.getText().toString();
                String eMail = email.getText().toString();
                String passWord = password.getText().toString();
                String conPassWord = confirmPassword.getText().toString();
                if(name.isEmpty() || name.equals(" ")){
                    fullName.setError("Please Enter Your Full Name");
                    return;
                }
                if(eMail.isEmpty() || !eMail.matches(EMAIL_REGEX)){
                    email.setError("Please Enter a Valid Email Address");
                    return;
                }
                if(passWord.isEmpty() || passWord.length() < 6){
                    password.setError("Please Enter a Valid Password");
                    return;
                }
                if(!passWord.equals(conPassWord)){
                    password.setError("The Password You Entered Does Not Match");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                signUp(name, eMail, passWord);
            }
        });
    }

    private void signUp(String name, String email, String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getContext(), "Email Verification Has Been Sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    uploadUser(user, name, email);
                }else{
                    progressBar.setVisibility(View.GONE);
                    String exception = task.getException().getMessage();
                    Toast.makeText(getContext(), "Error:" + exception, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadUser(FirebaseUser user, String name, String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        map.put("profileImage", " ");
        map.put("uid", user.getUid());
        FirebaseFirestore.getInstance().collection("User").document(user.getUid()).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    assert getActivity() != null;
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(getContext().getApplicationContext(), CreateProfileActivity.class));
                    getActivity().finish();
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Error: " + task, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}