package com.example.centralbank;

//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.pdf.PdfDocument;
//import android.os.Build;
//import android.print.PrintAttributes;
//import android.print.pdf.PrintedPdfDocument;
//import android.view.View;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class PdfGenerator {
//    private Context context;
//
//    public PdfGenerator(Context context) {
//        this.context = context;
//    }
//
//    public void generatePdf(View layout, String fileName,File directory) {
//        PdfDocument document = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            document = new PdfDocument();
//        }else {
//
//        }
//        PrintedPdfDocument printedPdfDocument = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            printedPdfDocument = new PrintedPdfDocument(context, new PrintAttributes.Builder().build());
//        }
//
//        int width = layout.getWidth();
//        int height = layout.getHeight();
//
//        PdfDocument.PageInfo pageInfo = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            pageInfo = new PdfDocument.PageInfo.Builder(width, height, 1).create();
//        }
//        PdfDocument.Page page = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            page = printedPdfDocument.startPage(pageInfo);
//        }
//        Canvas canvas = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            canvas = page.getCanvas();
//        }
//        canvas.drawColor(Color.WHITE);
//
//        layout.draw(canvas);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            printedPdfDocument.finishPage(page);
//        }
//
//        try {
//            File file = new File(directory, fileName + ".pdf");
//            FileOutputStream fos = new FileOutputStream(file);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                printedPdfDocument.writeTo(fos);
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                printedPdfDocument.close();
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                document.close();
//            }
//
//            // Show a toast or notify the user that the PDF file has been generated
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the exception
//        }
//
//    }
//}


import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGenerator extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private Context context;

    public PdfGenerator(Context context) {
        this.context = context;
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


            Toast.makeText(PdfGenerator.this,"Downloaded successfully",Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(PdfGenerator.this,"Download failed",Toast.LENGTH_LONG).show();
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



}