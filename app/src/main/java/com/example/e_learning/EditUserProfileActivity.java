package com.example.e_learning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class EditUserProfileActivity extends AppCompatActivity {

    private EditText etNama, etNim, etJurusan, etSemester, etEmail, etIpk, etSks;
    private ImageView imageView;
    private Button btnSaveData;
    private Uri imageUri;
    Boolean isPhotoUpdate = false;
    private String avatarUrl;
    FirebaseAuth mAuth;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseFirestore mStore;
    DocumentReference documentReference;
    String userID;
    String fileName;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        mAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        userID = mAuth.getCurrentUser().getUid();
        mStore = FirebaseFirestore.getInstance();
        imageView = findViewById(R.id.profile_image_edit_user);
        etNama = findViewById(R.id.et_name_edit_user);
        btnSaveData = findViewById(R.id.btn_save_data);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });


        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateProfile();

            }
        });

        // Storing data into SharedPreferences
        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);

            isPhotoUpdate = true;
        }
    }

    private void uploadPicture() {

        final URL avatar;

        fileName = UUID.randomUUID().toString() + "." + getFileExtension(imageUri);

        // initial filename and path to upload
        StorageReference imageRef = storageReference.child("images" + "/" + fileName);

        // upload image file
        imageRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()) {

//                    Toast.makeText(getApplicationContext(),"Upload Gagal".length())
                }
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        avatarUrl = String.valueOf(uri);


                        // Creating an Editor object to edit(write to the file)
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();

                        // Storing the key and its value as the data fetched from edittext
                        myEdit.putString("avatarUrl", avatarUrl);
//                        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));

                        // Once the changes have been made,
                        // we need to commit to apply those changes made,
                        // otherwise, it will throw an error
                        myEdit.commit();

                    }
                });
            }
        });
        Log.e("fullImageUrl", String.valueOf(avatarUrl));
    }

    // Get file extension
    private String getFileExtension(Uri uri)
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Return file Extension
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    private void UpdateProfile() {

        String fullName;
        fullName = etNama.getText().toString();
//        email = etEmail.getText().toString();
//        nim = etNim.getText().toString();

        userID = mAuth.getCurrentUser().getUid();

        // Validations for name
        if (TextUtils.isEmpty(fullName)) {
            Toast.makeText(getApplicationContext(), "Please enter Name!!", Toast.LENGTH_LONG).show();
            return;
        }

        Log.d("UserId", userID);

        if(isPhotoUpdate){
            uploadPicture();
        }

        DocumentReference documentReference = mStore.collection("Users").document(userID);
        mStore.runTransaction(new Transaction.Function<Void>() {
                    @Override
                    public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                        DocumentSnapshot snapshot = transaction.get(documentReference);

                        // Note: this could be done without a transaction
                        //       by updating the population using FieldValue.increment()

                        Log.e("Filename", fileName);

                        transaction.update(documentReference, "fullName", fullName);
                        if(isPhotoUpdate) {

                            String avatar = sharedPreferences.getString("avatarUrl", "");

                            Log.e("Avatar", avatar );

                            transaction.update(documentReference, "avatar", avatar);

                        }

                        // Success
                        return null;
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"User Profile updated",Toast.LENGTH_LONG).show();
                        Log.d("Update Status", "Transaction success!");
                        Intent intent = new Intent(EditUserProfileActivity.this, UserProfile.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Update Status", "Transaction failure.", e);
                    }
                });
    }
}
