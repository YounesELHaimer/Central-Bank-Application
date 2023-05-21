package com.example.centralbank;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class rip_pdf extends AppCompatActivity {
    private RelativeLayout telecharger_btn;
    private static final int REQUEST_PICK_FILE = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int FILE_PICKER_REQUEST_CODE = 2;
    private String selectedDirectoryPath;
    private RelativeLayout layout;
    private ImageView arrow_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rip_pdf);
        telecharger_btn = findViewById(R.id.telecharger_btn);
        layout = findViewById(R.id.content_container);
        arrow_back = findViewById(R.id.arrow_back);


        arrow_back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                onBackPressed();


            }
        });



        telecharger_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = rip_pdf.this;
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted, request the permission
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                } else {
                    // Permission is already granted, proceed with generating the PDF
                    generatePdf(layout, "Visualisation");

                }
            }
        });

    }
    public void generatePdf(RelativeLayout relativeLayout, String fileName) {
        // Create a temporary HTML file to convert the RelativeLayout to HTML
        File htmlFile = new File(this.getCacheDir(), "temp.html");

        try {

            FileOutputStream htmlOutputStream = new FileOutputStream(htmlFile);
            String htmlContent = relativeLayoutToHtml(relativeLayout);
            htmlOutputStream.write(htmlContent.getBytes());
            htmlOutputStream.close();

            // Create the output PDF file
            File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName + ".pdf");

            // Convert the HTML file to PDF using iText
            FileOutputStream pdfOutputStream = new FileOutputStream(outputFile);
            HtmlConverter.convertToPdf(htmlContent, pdfOutputStream);
            pdfOutputStream.close();


            Toast.makeText(rip_pdf.this,"Downloaded successfully",Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(rip_pdf.this,"Download failed",Toast.LENGTH_LONG).show();
        } finally {
            // Delete the temporary HTML file
            htmlFile.delete();
        }
    }

    private String relativeLayoutToHtml(RelativeLayout relativeLayout) {
        // Convert the RelativeLayout to an HTML string using custom logic
        // Modify this method according to your specific requirements
        // You can use libraries like Jsoup to simplify the HTML generation process

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><body>");
        htmlBuilder.append("<div style=\"position: relative;\">");

        // Iterate over the RelativeLayout's child views
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            View child = relativeLayout.getChildAt(i);

            // Convert the child view to an HTML string representation
            // Modify this according to your specific requirements
            String childHtml = "<div style=\"position: absolute; left: " + child.getLeft() + "px; top: " + child.getTop() + "px;\">" +
                    viewToHtml(child) + "</div>";

            htmlBuilder.append(childHtml);
        }

        htmlBuilder.append("</div></body></html>");
        return htmlBuilder.toString();
    }

    private String viewToHtml(View view) {
        // Convert a view to an HTML string representation
        // Modify this according to your specific requirements

        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            return "<span style=\"color: " + String.format("#%06X", (0xFFFFFF & textView.getCurrentTextColor())) + "\">" + textView.getText() + "</span>";
        } else if (view instanceof ImageView) {
            // Convert the ImageView to an HTML img tag
            ImageView imageView = (ImageView) view;
            String src = getImageSrc(imageView); // Get the image source URL or base64 data
            return "<img src=\"" + src + "\">";
        }

        // Return an empty string for unsupported views or customize the conversion logic as needed
        return "";
    }

    private String getImageSrc(ImageView imageView) {
        // Get the image source URL or base64 data for the ImageView
        // Modify this according to your specific requirements

        // Example: return a placeholder image URL
        return "https://example.com/image.jpg";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with generating the PDF
                generatePdf(layout, "Visualisation");
            } else {
                Toast.makeText(rip_pdf.this, "Permission failed", Toast.LENGTH_LONG).show();
            }
        }
    }
}