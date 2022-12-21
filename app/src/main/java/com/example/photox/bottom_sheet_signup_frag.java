package com.example.photox;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bottom_sheet_signup_frag# method to
 * create an instance of this fragment.
 */
public class bottom_sheet_signup_frag extends BottomSheetDialogFragment {
    FirebaseAuth mAuth;
    Button signUp;
    EditText email, pass;
    String emailStr;
    String passStr;
    FirebaseFirestore db;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bottom_sheet_signup_frag, container, false);
        mAuth = FirebaseAuth.getInstance();
        signUp = v.findViewById(R.id.signUpBtn);
        email =v.findViewById(R.id.userEmailSignUp);
        pass = v.findViewById(R.id.userPasswordSignUp);

         emailStr = email.getText().toString().trim();
         passStr = pass.getText().toString().trim();

        db = FirebaseFirestore.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getEditableText().toString().trim().isEmpty()){
                    email.requestFocus();
                    Toast.makeText(getContext(), "Required", Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().trim().isEmpty()){
                    pass.requestFocus();
                    Toast.makeText(getContext(),"Required", Toast.LENGTH_SHORT).show();
                }else if (email.getEditableText().toString().trim().isEmpty() ||
                        pass.getText().toString().trim().length()==0 ){
                    email.requestFocus();
                    pass.requestFocus();
                    Toast.makeText(getContext(), "Both Required", Toast.LENGTH_SHORT)
                            .show();

                } else {
                    registerUser();
                }
            }


        });

        return v;
    }

    private void registerUser() {
        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), pass.getText().toString().trim())
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser User = mAuth.getCurrentUser();
                        /*if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //        Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }*/
                        // Create a new user with a first and last name



                        db.collection("users")
                               // .whereEqualTo("email", email.getText().toString().trim())
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                        if (task.isSuccessful()) {

                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Toast.makeText(getContext(), "Email ID Exists", Toast.LENGTH_SHORT).show();
                                                Log.d("TAG", document.getId() + " => " + document.getData());
                                            }
                                            Log.d("TAG","Task successful");



                                            } else {
                                            Log.d("TAG", "Error getting documents: ", task.getException());

                                                Map<String, Object> user = new HashMap<>();
                                                user.put("email", email.getText().toString().trim());
                                                user.put("password", pass.getText().toString().trim());

                                                // Add a new document with a generated ID
                                                db.collection("users")
                                                        .add(user)
                                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {
                                                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                                                intent.putExtra("currentUser",  User);
                                                                startActivity(intent);
                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Log.w("TAG", "Error adding document", e);
                                                            }
                                                        });

                                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                                intent.putExtra("currentUser",  User);
                                                startActivity(intent);



                                        }

                                        }

                                });




                    }
                });
               /* .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Errror"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

}