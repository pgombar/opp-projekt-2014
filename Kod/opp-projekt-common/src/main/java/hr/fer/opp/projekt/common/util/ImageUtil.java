package hr.fer.opp.projekt.common.util;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public final class ImageUtil {
    private ImageUtil() {
    }

    public static byte[] loadBlob(Blob blob) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int read;
        byte[] data = new byte[16384];

        try {
            InputStream binaryStream = blob.getBinaryStream();
            while ((read = binaryStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, read);
            }

            buffer.flush();
            return buffer.toByteArray();
        } catch (IOException | SQLException e) {
            return new byte[0];
        }
    }

    public static byte[] imageToByteArray(BufferedImage image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try {
        ImageIO.write(image, "jpg", stream);
        stream.flush();

        return stream.toByteArray();
        } catch (IOException e) {
            return new byte[0];
        }
    }

    public static BufferedImage byteArrayToImage(byte[] image) {
        try {
            return ImageIO.read(new ByteArrayInputStream(image));
        } catch (IOException e) {
            return null;
        }
    }
}