package com.wooyoo.wechat.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;

public final class QRCodeUtil {
    private QRCodeUtil() {
    }

    /**
     * 关于Ansi Color的信息，参考：http://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html
     *
     * @param text
     */
    public static void printQRCodeInTerminal(String text) {
        int qrcodeWidth = 400;
        int qrcodeHeight = 400;
        HashMap<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, qrcodeWidth, qrcodeHeight, hints);

            // 将像素点转成格子
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();

            for (int y = 30; y < height - 30; y += 10) {
                for (int x = 30; x < width - 20; x += 10) {
                    boolean isBlack = bitMatrix.get(x, y);
                    System.out.print(isBlack ? "  " : "\u001b[47m  \u001b[0m");
                }
                System.out.println();
            }
        }
        catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
