package com.example.viewbindingapp7_001;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.example.viewbindingapp7_001.databinding.ActivityMainBinding;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.concurrent.ExecutionException;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

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

            // wykonanie zdjęcia
            activityMainBinding.bt1.setOnClickListener(new View.OnClickListener() {
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

            File dir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photos");
            dir.mkdir();
            boolean isDirectoryCreated = dir.exists() || dir.mkdirs();

            Log.d("xxx", String.valueOf(isDirectoryCreated));
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/photos/", "x.jpg");

            ImageCapture.OutputFileOptions outputFileOptions2 = new ImageCapture.OutputFileOptions.Builder(file)
                    .build();

            // to nie działa

            activityMainBinding.bt2.setOnClickListener(new View.OnClickListener() {
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

            activityMainBinding.bt3.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(View view) {
                    if (recording == false) {
                        activityMainBinding.bt3.setTextColor(Color.RED);
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
                        activityMainBinding.bt3.setTextColor(Color.BLACK);
                        recording = false;
                        videoCapture.stopRecording();
                    }
                }
            });
        }
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
