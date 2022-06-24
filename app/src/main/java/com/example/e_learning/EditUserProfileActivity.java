package com.example.e_learning;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EditUserProfileActivity extends AppCompatActivity {

    private EditText etNama, etNim, etJurusan, etSemester, etEmail, etIpk, etSks;
    private ImageView imageView;
    private Button btnSaveData;
    private Uri imageUri;
    Boolean isPhotoUpdate = false;
    Uri avatarUrl;
    FirebaseAuth mAuth;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseFirestore mStore;
    DocumentReference documentReference;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        mAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        mStore = FirebaseFirestore.getInstance();
        imageView = findViewById(R.id.profile_image_edit_user);
        etNama = findViewById(R.id.et_name_edit_user);
        etNim = findViewById(R.id.et_nim_edit_user);
        etEmail = findViewById(R.id.et_email_edit_user);
        btnSaveData = findViewById(R.id.btn_save_data);
//        etJurusan = findViewById(R.id.et_jurusan_edit_user);
//        etSemester = findViewById(R.id.et_semester_edit_user);
//        etIpk = findViewById(R.id.et_ipk_edit_user);
//        etSks = findViewById(R.id.et_sks_edit_user);

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);


           // Log.e("imageUri", String.valueOf(imageUri));

            isPhotoUpdate = true;

          // uploadPicture();

        }
    }

    private void uploadPicture() {

       // final String randomKey = UUID.randomUUID().toString();

        // Create a reference to "mountains.jpg"
       // StorageReference mountainsRef = storageReference.child("mountains.jpg");

        // Create a reference to 'images/mountains.jpg'
           //     StorageReference mountainImagesRef = storageReference.child("images/" + randomKey);

        // While the file names are the same, the references point to different files
         //       mountainsRef.getName().equals(mountainImagesRef.getName());    // true
         //       mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

//        // instance firebase storage
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference();

        // initial filename and path to upload
        StorageReference imageRef = storageReference.child("images" + "/" + UUID.randomUUID().toString() + "." + getFileExtension(imageUri));

        // upload image file
        imageRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()) {

                    // after finish and success, get download url
                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            avatarUrl = uri;
                            Log.i("fullImageUrl", String.valueOf(uri));
                        }
                    });

                }
            }
        });
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

        String email, fullName, nim;
        email = etEmail.getText().toString();
        fullName = etNama.getText().toString();
        nim = etNim.getText().toString();

        userID = mAuth.getCurrentUser().getUid();

        Log.d("UserId", userID);

        if(isPhotoUpdate) {
            uploadPicture();
        }

        DocumentReference documentReference = mStore.collection("Users").document(userID);

        mStore.runTransaction(new Transaction.Function<Void>() {
                    @Override
                    public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                        DocumentSnapshot snapshot = transaction.get(documentReference);

                        // Note: this could be done without a transaction
                        //       by updating the population using FieldValue.increment()

                        transaction.update(documentReference, "fullName", fullName);
                        if(isPhotoUpdate) {
                            transaction.update(documentReference, "avatar", avatarUrl);
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
