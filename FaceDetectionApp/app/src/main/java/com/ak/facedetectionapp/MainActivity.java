//package com.ak.facedetectionapp;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.AlertDialog;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Rect;
//import android.os.Bundle;
//import android.os.Environment;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.ak.facedetectionapp.Helper.GraphicOverlay;
//import com.ak.facedetectionapp.Helper.RectOverlay;
//import com.camerakit.CameraKitView;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.ml.vision.common.FirebaseVisionImage;
//import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
//import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
//import com.google.mlkit.vision.common.InputImage;
//import com.google.mlkit.vision.face.Face;
//import com.google.mlkit.vision.face.FaceDetection;
//import com.google.mlkit.vision.face.FaceDetector;
//import com.google.mlkit.vision.face.FaceDetectorOptions;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.List;
//
//import dmax.dialog.SpotsDialog;
//
//public class MainActivity extends AppCompatActivity {
//
////     Button faceDetectButton;
////     GraphicOverlay graphicOverlay;
//     CameraKitView cameraKitView;
//    // AlertDialog alertDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
////        faceDetectButton=findViewById(R.id.detect_face_btn);
////        graphicOverlay=findViewById(R.id.graphic_overlay);
//        cameraKitView=findViewById(R.id.camera_view);
//
////        alertDialog=new SpotsDialog.Builder().setContext(this)
////                .setMessage("Please Wait, Loading...")
////                .setCancelable(false)
////                .build();
//
////        faceDetectButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                //cameraKitView.onStart();
////                cameraKitView.captureImage(new CameraKitView.ImageCallback() {
////            @Override
////            public void onImage(CameraKitView cameraView, final byte[] capturedImage) {
////                alertDialog.show();
//////                Bitmap bitmap=BitmapFactory.decodeByteArray(capturedImage,0,capturedImage.length);
//////                bitmap =Bitmap.createScaledBitmap(bitmap,cameraKitView.getWidth(),cameraKitView.getHeight(),false);
//////                cameraKitView.onStop();
//////                processFacedetection(bitmap);
////            }
////        });
////        graphicOverlay.clear();
////    }
////});
//
//    }
//
//    private void processFacedetection(Bitmap bitmap) {
////        FirebaseVisionImage visionImage=(InputImage) FirebaseVisionImage.fromBitmap(bitmap);
//            InputImage image = InputImage.fromBitmap(bitmap,0);
//        FaceDetectorOptions realTimeOpts =
//                new FaceDetectorOptions.Builder()
//                        .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
//                        .build();
//
//        FaceDetector detector = FaceDetection.getClient(realTimeOpts);
//
//        Task<List<Face>> result = detector.process(image).addOnSuccessListener(new OnSuccessListener<List<Face>>() {
//            @Override
//            public void onSuccess(List<Face> faces) {
//                getFaceResults(faces);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    private void getFaceResults(List<Face> faces) {
//        int counter=0;
//        for (Face faced:faces)
//        {
//            Rect bounds=faced.getBoundingBox();
//            RectOverlay rectOverlay=new RectOverlay(graphicOverlay,bounds);
//
//            graphicOverlay.add(rectOverlay);
//           counter=counter+1;
//        }
//        alertDialog.dismiss();
//
//    }
//
//    @Override
//    protected void onPause(){
//        cameraKitView.onPause();
//        super.onPause();
//    }
//    @Override
//    protected void onResume(){
//        super.onResume();
//        cameraKitView.onResume();
//    }
//    @Override
//    protected void onStop() {
//        cameraKitView.onStop();
//        super.onStop();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//}