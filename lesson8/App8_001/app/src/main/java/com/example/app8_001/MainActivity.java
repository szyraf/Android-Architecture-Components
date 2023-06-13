package com.example.app8_001;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.core.VideoCapture;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app8_001.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.Console;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private String[] REQUIRED_PERMISSIONS = new String[] {
            "android.permission.CAMERA",
            "android.permission.RECORD_AUDIO",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE" };

    private int PERMISSIONS_REQUEST_CODE = 100;

    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private ImageCapture imageCapture;

    private VideoCapture videoCapture;

    private boolean recording = false;

    private String lastPhotoPath = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        ActivityCompat.requestPermissions( this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.MANAGE_EXTERNAL_STORAGE
                }, 1
        );

        /*
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
        */

        Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.93:3000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        API api = retrofit.create(API.class);

        activityMainBinding.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        activityMainBinding.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<PhotoJSON>> call = api.getAll();

                call.enqueue(new Callback<List<PhotoJSON>>() {
                    @Override
                    public void onResponse(Call<List<PhotoJSON>> call, Response<List<PhotoJSON>> response) {
                        Log.d("xxx", "response");
                        if (!response.isSuccessful()) {
                            Log.d("xxx", String.valueOf(response.code()));
                            return;
                        } else {
                            //PhotoJSON json = response.body();

                            Gson gson = new Gson();
                            String json = gson.toJson(response.body());

                            showDialogString(json);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PhotoJSON>> call, Throwable t) {
                        Log.d("xxx", "failure: " + t.getMessage());
                        showDialogString("error");
                        t.getMessage();
                    }
                });
            }
        });


        if (!checkIfPermissionsGranted()) {
            requestPermissions(REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
        } else {
            cameraProviderFuture = ProcessCameraProvider.getInstance(MainActivity.this);
            cameraProviderFuture.addListener(() -> {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider);
                } catch (InterruptedException | ExecutionException e) {
                    // No errors need to be handled for this Future. This should never be reached.
                }
            }, ContextCompat.getMainExecutor(this));

            // określenie nazwy i typu zdjęcia
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "file_name_from_date");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");

            // opcje zapisu - w galerii (MediaStore)

            ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(
                    getContentResolver(),
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues).build();

            activityMainBinding.btUploadLastPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (lastPhotoPath != "") {
                        Log.d("xxx", "upload");

                        //String fileUri = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + timestamp + ".jpg";
                        String fileUri = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/photos/x.jpg";

                        File file = new File(fileUri);

                        RequestBody fileRequest = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        //MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), fileRequest);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile", "upload.jpg", fileRequest);

                        RequestBody album = RequestBody.create(MultipartBody.FORM, "album123");

                        Call<PhotoJSON> call = api.uploadPhoto(album, body);

                        call.enqueue(new Callback<PhotoJSON>() {
                            @Override
                            public void onResponse(Call<PhotoJSON> call, Response<PhotoJSON> response) {
                                Log.d("xxx", "response");
                                if (!response.isSuccessful()) {
                                    Log.d("xxx", String.valueOf(response.code()));
                                    return;
                                } else {
                                    //PhotoJSON json = response.body();

                                    Gson gson = new Gson();
                                    String json = gson.toJson(response.body());

                                    showDialogString(json);
                                }
                            }

                            @Override
                            public void onFailure(Call<PhotoJSON> call, Throwable t) {
                                Log.d("xxx", "failure: " + t.getMessage());
                                showDialogString("error");
                                t.getMessage();
                            }
                        });

                    }
                }
            });

            // wykonanie zdjęcia
            activityMainBinding.bt5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(MainActivity.this),
                            new ImageCapture.OnImageSavedCallback() {
                                @Override
                                public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                                    // photo saved!!!
                                    Log.d("xxx", "saved 1");
                                }

                                @Override
                                public void onError(@NonNull ImageCaptureException exception) {
                                    // error
                                    Log.d("xxx", "error 1");
                                }
                            });
                }
            });

            //string picturesPath = Environment.GetFolderPath(Environment.SpecialFolder.MyPictures);

            Log.d("xxx", "path");
            Log.d("xxx", getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath());
            Log.d("xxx", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());


            String externalFilesDir = getExternalFilesDir(null).getAbsolutePath();
            String picturesPath = new File(externalFilesDir, Environment.DIRECTORY_PICTURES).getAbsolutePath();
            Log.d("xxx", picturesPath);

            //File dir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photos");
            File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "photos");

            dir.mkdir();
            boolean isDirectoryCreated = dir.exists() || dir.mkdirs();

            Log.d("xxx", String.valueOf(isDirectoryCreated));
            //File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/photos/", "x.jpg");
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/photos/", "x.jpg");


            ImageCapture.OutputFileOptions outputFileOptions2 = new ImageCapture.OutputFileOptions.Builder(file)
                    .build();

            // to nie działa

            activityMainBinding.bt6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageCapture.takePicture(outputFileOptions2, ContextCompat.getMainExecutor(getBaseContext()),
                            new ImageCapture.OnImageSavedCallback() {
                                @Override
                                public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                                    // photo saved!!!
                                    Log.d("xxx", "saved 2");
                                }

                                @Override
                                public void onError(@NonNull ImageCaptureException exception) {
                                    // error
                                    Log.d("xxx", "error 2");
                                }
                            });
                }
            });

            ContentValues contentValues3 = new ContentValues();
            contentValues3.put(MediaStore.MediaColumns.DISPLAY_NAME, "video_name");
            contentValues3.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");

            activityMainBinding.bt7.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(View view) {
                    if (recording == false) {
                        activityMainBinding.bt7.setTextColor(Color.RED);
                        recording = true;

                        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            // ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            // public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            // int[] grantResults)
                            // to handle the case where the user grants the permission. See the
                            // documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        videoCapture.startRecording(
                                new VideoCapture.OutputFileOptions.Builder(
                                        getContentResolver(),
                                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                                        contentValues3).build(),
                                ContextCompat.getMainExecutor(MainActivity.this),
                                new VideoCapture.OnVideoSavedCallback() {
                                    @Override
                                    public void onVideoSaved(
                                            @NonNull VideoCapture.OutputFileResults outputFileResults) {
                                        // video saved
                                    }

                                    @Override
                                    public void onError(int videoCaptureError, @NonNull String message,
                                            @Nullable Throwable cause) {
                                        // error
                                    }
                                });
                    } else {
                        activityMainBinding.bt7.setTextColor(Color.BLACK);
                        recording = false;
                        videoCapture.stopRecording();
                    }
                }
            });
        }

    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.toast_item, null);
        dialogBuilder.setView(dialogView);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) final TextInputEditText editText = dialogView.findViewById(R.id.editText);

        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String enteredText = editText.getText().toString();
                // Perform save operation with the entered text
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void showDialogString(String message) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        String formattedJson = gson.toJson(parser.parse(message));

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(formattedJson).setTitle("response");

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private boolean checkIfPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // tak
                } else {
                    // nie
                }
                break;
            case 101:

                break;
        }
    }

    @SuppressLint("RestrictedApi")
    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();

        imageCapture = new ImageCapture.Builder()
                .setTargetRotation(activityMainBinding.previewView.getDisplay().getRotation())
                .build();

        videoCapture = new VideoCapture.Builder()
                .setTargetRotation(activityMainBinding.previewView.getDisplay().getRotation())
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(activityMainBinding.previewView.getSurfaceProvider());

        // cameraProvider.bindToLifecycle(this, cameraSelector, imageCapture, preview);
        cameraProvider.bindToLifecycle(this, cameraSelector, imageCapture, videoCapture, preview);
    }
}