package com.ak.facedetectionapp;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ak.facedetectionapp.Helper.GraphicOverlay;
import com.ak.facedetectionapp.Helper.RectOverlay;
import com.camerakit.CameraKitView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceContour;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.google.mlkit.vision.face.FaceLandmark;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class Camera2 extends AppCompatActivity {

    private CameraKitView cameraKitView;
    private Button faceDetectButton;
    private GraphicOverlay graphicOverlay;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraKitView = findViewById(R.id.camera_view);
        faceDetectButton = (Button) findViewById(R.id.detect_btn);
        graphicOverlay = findViewById(R.id.graphic_overlay);
        alertDialog = new SpotsDialog.Builder().setContext(this)
                .setMessage("Please Wait, Loading...")
                .setCancelable(false)
                .build();
        faceDetectButton.setOnClickListener(photoonClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void processFacedetection(Bitmap bitmap) {
        //FirebaseVisionImage visionimage=(FirebaseVisionImage) FirebaseVisionImage.fromBitmap(bitmap);
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        FaceDetectorOptions highAccuracyOpts  =
                new FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                        .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                        .build();
        FaceDetector detector = FaceDetection.getClient(highAccuracyOpts );

        Task<List<Face>> result = detector.process(image).addOnSuccessListener(new OnSuccessListener<List<Face>>() {
            @Override
            public void onSuccess(List<Face> faces) {
                getFaceResults(faces);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Camera2.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getFaceResults(List<Face> faces) {
        int counter=0;
        for (Face faced:faces)
        {
            Rect bounds=faced.getBoundingBox();
            RectOverlay rectOverlay=new RectOverlay(graphicOverlay,bounds);

            graphicOverlay.add(rectOverlay);
            counter++;
        }
        alertDialog.dismiss();

    }

       private View.OnClickListener photoonClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //cameraKitView.onStart();
                cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                    @Override
                    public void onImage(CameraKitView cameraKitView, final byte[] capturedImage) {
                        alertDialog.show();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(capturedImage, 0, capturedImage.length);
                        bitmap = Bitmap.createScaledBitmap(bitmap, cameraKitView.getWidth(), cameraKitView.getHeight(), false);
                        cameraKitView.onStop();
                        processFacedetection(bitmap);
                    }
                });
            }
        };
    }

