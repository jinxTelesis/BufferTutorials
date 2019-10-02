package com.company;

import org.omg.IOP.Encoding;
import sun.nio.cs.ext.DoubleByte;

import java.io.*;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    static public void compressData(byte[] data, OutputStream out) throws IOException {
        Deflater d = new Deflater();
        DeflaterOutputStream dout = new DeflaterOutputStream(out,d);
        dout.write(data);
        dout.close();
    }

    static public byte[] decompressData(InputStream in) throws IOException{
        InflaterInputStream input = new InflaterInputStream(in);
        ByteArrayOutputStream bout = new ByteArrayOutputStream(512);

        int b;
        while((b = in.read()) != -1){
            bout.write(b);
        }
        in.close();
        bout.close();
        return bout.toByteArray();
    }

    private String zipCompression(String data) throws UnsupportedEncodingException, IOException{
        Deflater zipDeflater = new Deflater();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try{
            zipDeflater.setInput(getBytes(data)); // this might be wrong one
            zipDeflater.finish();
            byte[] buffer = new byte[1024];
            int count = 0;

            while(!zipDeflater.finished()){
                count = zipDeflater.deflate(buffer);
                stream.write(buffer, 0,count);
            }
            // this needs to be fixed
            return new String(Base64.getEncoder().encode(stream.toByteArray()), LOCAL_ENCODING);
        }finally {
            stream.close();
            zipDeflater.end();
        }
    }

    public static byte[] compressForzLIB(byte[] bytesToCompress){
        Deflater deflater = new Deflater();
        deflater.setInput(bytesToCompress);
        deflater.finish();

        byte[] bytesToCompressed = new byte[Short.MAX_VALUE];

        int numberOfBytesAfterCompression = deflater.deflate(bytesToCompress);

        byte[] returnValues = new byte[numberOfBytesAfterCompression];

        System.arraycopy(
                bytesToCompressed,
                0,
                returnValues,
                0,
                numberOfBytesAfterCompression
        );

        return returnValues;
    }


}
