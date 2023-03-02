package puryfi;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageLoader {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    final APICaller apiCaller;

    public ImageLoader(APICaller apiCaller) {
        this.apiCaller = apiCaller;
    }

    public NSWF_Image convert(File file, boolean skipApiCall) {
        try {
            boolean tmp = false;
            File outputFile = file;
            if (file.length() > 500000L) {
                try {
                    File file2 = loadFileDownConvert(file);
                    if (file != file2) {//not same pointer/obj
                        tmp = true;
                        file = file2;
                    }
                } catch (Exception e) {
                    LOGGER.error("Issue Down Converting File, attempting to continue", e);
                }
            }

            if (skipApiCall) {
                return new NSWF_Image(file, 1.0D, null);
            }

            //verify parsable
            BufferedImage image = ImageIO.read(file);
            assert image != null;

            NSWF_Image nswf_image = apiCaller.makeApiCall(file, outputFile);
            if (tmp) {
                //noinspection ResultOfMethodCallIgnored
                outputFile.delete();
            }
            return nswf_image;

        } catch (UnsupportedEncodingException var52) {
            LOGGER.error("Encoding Error", var52);
        } catch (ParseException | IOException var53) {
            LOGGER.error("Other Api Error", var53);
        }

        return null;
    }

    private File loadFileDownConvert(File file) throws IOException {
        File outputFile = new File("output/temp/upload.jpg");

        try (InputStream is = new FileInputStream(file)) {
            BufferedImage image = ImageIO.read(is);
            convertToLossyJPG(outputFile, image);
        }

        //was lossy worth?
        if (outputFile.length() <= 4000000L) {
            return outputFile;
        } else {
            return file;
        }
    }

    private void convertToLossyJPG(File outputFile, BufferedImage image) throws IOException {
        try (FileOutputStream os = new FileOutputStream(outputFile)) {
            ImageIO.write(image, "jpg", os);
        }
    }
}

