package com.lfm.test.qrcode;

import com.google.zxing.WriterException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class QRTest {

    @Test
    public void createCode(){
        String content="https://drips.cc";
        String relPath = "build/resources/main/";
        String outFileUri=relPath +"qr.png";
        String logUri = relPath + "icon.png";
        int[] size=new int[]{430,430};
        String format = "png";
        try {
            new QRCodeFactory().CreatQrImage(content, format, outFileUri, logUri,size);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
