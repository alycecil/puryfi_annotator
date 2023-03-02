//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package puryfi;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused") //we're a dirty bean
public class NSWF_Image {
    List<NSFW_BoundingBox> results = new ArrayList<>();
    Double nsfw_score;
    File image;
    JSONObject json;
    String alias = "image_" + this.hashCode();
    boolean ignore = false;
    boolean edited = false;
    File editedsourcefileimage = null;
    File editedsourcefiletxt = null;
    BufferedImage img;
    BufferedImage cache_resized = null;
    BufferedImage blurbuff = null;

    public NSWF_Image(File image, Double nsfw_score, JSONObject json) {
        this.image = image;
        this.nsfw_score = nsfw_score;
        this.json = json;
    }

    public List<NSFW_BoundingBox> getResults() {
        return this.results;
    }

    public void setResults(List<NSFW_BoundingBox> results) {
        this.results = results;
    }

    public File getImage() {
        return this.image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public Double getNsfw_score() {
        return this.nsfw_score;
    }

    public void setNsfw_score(Double nsfw_score) {
        this.nsfw_score = nsfw_score;
    }

    public boolean isIgnore() {
        return this.ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public JSONObject getJson() {
        return this.json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public boolean isEdited() {
        return this.edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public File getEditedsourcefileimage() {
        return this.editedsourcefileimage;
    }

    public void setEditedsourcefileimage(File editedsourcefileimage) {
        this.editedsourcefileimage = editedsourcefileimage;
    }

    public File getEditedsourcefiletxt() {
        return this.editedsourcefiletxt;
    }

    public void setEditedsourcefiletxt(File editedsourcefiletxt) {
        this.editedsourcefiletxt = editedsourcefiletxt;
    }

    public BufferedImage getBufferedImage() {
        if (this.img != null) {
            return this.img;
        } else {
            try {
                this.img = ImageIO.read(this.image);
                return this.img;
            } catch (IOException var2) {
                Logger.getLogger(NSWF_Image.class.getName()).log(Level.SEVERE, null, var2);
                return null;
            }
        }
    }

    public BufferedImage getBufferedImageCopy() {
        if (this.img != null) {
            return copyImage(this.img);
        } else {
            try {
                this.img = ImageIO.read(this.image);
                return copyImage(this.img);
            } catch (IOException var2) {
                Logger.getLogger(NSWF_Image.class.getName()).log(Level.SEVERE, null, var2);
                return null;
            }
        }
    }

    public BufferedImage getCensoredImage(boolean isPixels, boolean isBlackBar) {
        BufferedImage paintedImage = this.getBufferedImageCopy();
        Graphics g = paintedImage.getGraphics();
        List<Rectangle> blurboxes = new ArrayList<>();

        for (NSFW_BoundingBox result : this.results) {
            Rectangle bounding_box = result.getBounding_box();

            boolean shouldCensor = result.isCensored();

            if (shouldCensor) {
                if (isPixels) {
                    this.pixelate(paintedImage, bounding_box);
                } else if (isBlackBar) {
                    g.setColor(Color.BLACK);
                    g.fillRect(bounding_box.x, bounding_box.y, bounding_box.width, bounding_box.height);
                } else {
                    blurboxes.add(bounding_box);
                }
            }

            if (result.getSticker() != null) {
                this.paintSticker(g, result.getSticker(), bounding_box);
            }
        }

        if (NSWFAPI.blurButton.isSelected()) {
            this.blurbuff = copyImage(paintedImage);
            fastblur(this.blurbuff, Math.min(500, (Integer) NSWFAPI.jSpinner1.getValue()));
            this.blur(paintedImage, blurboxes);
        }

        g.dispose();
        return paintedImage;
    }

    public BufferedImage getCachedResizedImage(JLabel label) {
        try {
            if (this.cache_resized == null) {
                BufferedImage org = ImageIO.read(this.image);
                this.cache_resized = NSWFAPI.rsize(org, label);
                return this.cache_resized;
            } else {
                return this.cache_resized;
            }
        } catch (IOException var3) {
            Logger.getLogger(NSWF_Image.class.getName()).log(Level.SEVERE, null, var3);
            return this.cache_resized;
        }
    }

    public BufferedImage getPaintedImage() {
        BufferedImage org = this.getBufferedImageCopy();
        Graphics2D g = (Graphics2D) org.getGraphics();
        float thickness = 2.0F;
        g.setStroke(new BasicStroke(thickness));

        for (NSFW_BoundingBox result : this.results) {
            if (result.checkOptions()) {
                Rectangle bounding_box = result.getBounding_box();
                g.setColor(this.getColor(result.getConfidence()));
                g.drawRect(bounding_box.x, bounding_box.y, bounding_box.width, bounding_box.height);
                String s = result.getHeadline();
                g.setFont(new Font("Arial", Font.BOLD, 12));
                FontMetrics fm = g.getFontMetrics();
                int x = bounding_box.x;
                int y = bounding_box.y - fm.getHeight() + 8;
                dupeCode(g, s, x, y);
            }
        }

        g.dispose();
        return org;
    }

    private void dupeCode(Graphics2D g, String s, int x, int y) {
        String[] splitText = s.split("\n");
        String[] var11 = splitText;
        int var12 = splitText.length;

        int var13;
        for (var13 = 0; var13 < var12; ++var13) {
            String var10000 = var11[var13];
            y -= g.getFontMetrics().getHeight();
        }

        var11 = splitText;
        var12 = splitText.length;

        for (var13 = 0; var13 < var12; ++var13) {
            String line = var11[var13];
            y += g.getFontMetrics().getHeight();
            g.setColor(Color.WHITE);
            g.drawString(line, this.ShiftWest(x, 1), this.ShiftNorth(y, 1));
            g.drawString(line, this.ShiftWest(x, 1), this.ShiftSouth(y, 1));
            g.drawString(line, this.ShiftEast(x, 1), this.ShiftNorth(y, 1));
            g.drawString(line, this.ShiftEast(x, 1), this.ShiftSouth(y, 1));
            g.setColor(Color.BLACK);
            g.drawString(line, x, y);
        }
    }

    public BufferedImage getResizedPaintedImage(JLabel viewport) {
        BufferedImage org = this.getBufferedImage();
        BufferedImage org_r = NSWFAPI.rsize(org, viewport);
        double scalex = (double) viewport.getWidth() / (double) org.getWidth();
        double scaley = (double) viewport.getHeight() / (double) org.getHeight();
        double scale = Math.min(scalex, scaley);
        Graphics2D g = (Graphics2D) org_r.getGraphics();
        float thickness = 2.0F;
        g.setStroke(new BasicStroke(thickness));

        for (NSFW_BoundingBox result : this.results) {
            if (result.checkOptions()) {
                Rectangle bounding_box = result.getBounding_box();
                if (result.equals(NSWFAPI.del_buf)) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(this.getColor(result.getConfidence()));
                }

                g.drawRect((int) ((double) bounding_box.x * scale), (int) ((double) bounding_box.y * scale), (int) ((double) bounding_box.width * scale), (int) ((double) bounding_box.height * scale));
                String s = result.getHeadline();
                g.setFont(new Font("Arial", Font.BOLD, 12));
                FontMetrics fm = g.getFontMetrics();
                int x = (int) ((double) bounding_box.x * scale);
                int y = (int) ((double) bounding_box.y * scale) - fm.getHeight() + 8;
                dupeCode(g, s, x, y);
            }
        }

        g.dispose();
        return org_r;
    }

    @SuppressWarnings("SameParameterValue")
    int ShiftNorth(int p, int distance) {
        return p - distance;
    }

    @SuppressWarnings("SameParameterValue")
    int ShiftSouth(int p, int distance) {
        return p + distance;
    }

    @SuppressWarnings("SameParameterValue")
    int ShiftEast(int p, int distance) {
        return p + distance;
    }

    @SuppressWarnings("SameParameterValue")
    int ShiftWest(int p, int distance) {
        return p - distance;
    }

    public Color getColor(double score) {
        if (score > 0.8D) {
            return Color.RED;
        } else if (score > 0.65D) {
            return Color.BLUE;
        } else {
            return score > 0.5D ? Color.GREEN : Color.YELLOW;
        }
    }

    public void saveCensoredImage(boolean isPixels, boolean isBlackBar) {
        try {
            File dir = new File(NSWFAPI.output_folder + "/censored");
            if (!dir.exists()) {
                //noinspection ResultOfMethodCallIgnored
                dir.mkdirs();
            }

            BufferedImage censoredImage = this.getCensoredImage(isPixels, isBlackBar);
            File outputFile = new File(NSWFAPI.output_folder + "/censored/" + this.alias + ".png");
            ImageIO.write(censoredImage, "png", outputFile);
        } catch (IOException var4) {
            Logger.getLogger(NSWF_Image.class.getName()).log(Level.SEVERE, null, var4);
        }

    }

    public void saveImage(boolean isPixels, boolean isBlackBar) {
        this.saveCensoredImage(isPixels, isBlackBar);

        File jdir;
        BufferedImage censoredImage;
        File outputfile;
        try {
            jdir = new File(NSWFAPI.output_folder + "/identified");
            if (!jdir.exists()) {
                //noinspection ResultOfMethodCallIgnored
                jdir.mkdir();
            }

            censoredImage = this.getPaintedImage();
            outputfile = new File(NSWFAPI.output_folder + "/identified/" + this.alias + ".png");
            ImageIO.write(censoredImage, "png", outputfile);
        } catch (IOException var32) {
            Logger.getLogger(NSWF_Image.class.getName()).log(Level.SEVERE, null, var32);
        }

        try {
            jdir = new File(NSWFAPI.output_folder + "/source");
            if (!jdir.exists()) {
                //noinspection ResultOfMethodCallIgnored
                jdir.mkdir();
            }

            censoredImage = this.getBufferedImage();
            outputfile = new File(NSWFAPI.output_folder + "/source/" + this.alias + ".png");
            ImageIO.write(censoredImage, "png", outputfile);
        } catch (IOException var31) {
            Logger.getLogger(NSWF_Image.class.getName()).log(Level.SEVERE, null, var31);
        }

        try {
            jdir = new File(NSWFAPI.output_folder + "/source");
            if (!jdir.exists()) {
                //noinspection ResultOfMethodCallIgnored
                jdir.mkdir();
            }

            PrintWriter writer = new PrintWriter(NSWFAPI.output_folder + "/source/" + this.alias + ".txt", "UTF-8");

            for (NSFW_BoundingBox get : this.results) {
                Rectangle bounding_box = get.bounding_box;
                int index = get.getType().ordinal();
                BufferedImage bufferedImage = this.getBufferedImage();
                int x_center = bounding_box.x + bounding_box.width / 2;
                int y_center = bounding_box.y + bounding_box.height / 2;
                double x_p = (double) x_center / (double) bufferedImage.getWidth();
                double y_p = (double) y_center / (double) bufferedImage.getHeight();
                double w_p = (double) bounding_box.width / (double) bufferedImage.getWidth();
                double h_p = (double) bounding_box.height / (double) bufferedImage.getHeight();
                String hit = index + " " + x_p + " " + y_p + " " + w_p + " " + h_p;
                writer.println(hit);
            }

            writer.close();
        } catch (IOException var35) {
            Logger.getLogger(NSWF_Image.class.getName()).log(Level.SEVERE, null, var35);
        }

        if (this.json != null) {
            jdir = new File(NSWFAPI.output_folder + "/json");
            if (!jdir.exists()) {
                //noinspection ResultOfMethodCallIgnored
                jdir.mkdir();
            }

            try {
                FileWriter file = new FileWriter(NSWFAPI.output_folder + "/json/" + this.alias + ".json");
                Throwable var39 = null;

                try {
                    file.write(this.json.toString());
                } catch (Throwable var30) {
                    var39 = var30;
                    throw var30;
                } finally {
                    if (var39 != null) {
                        try {
                            file.close();
                        } catch (Throwable var29) {
                            var39.addSuppressed(var29);
                        }
                    } else {
                        file.close();
                    }

                }
            } catch (IOException var34) {
                Logger.getLogger(NSWFAPI.class.getName()).log(Level.SEVERE, null, var34);
            }
        }

    }

    public void pixelate(BufferedImage img, Rectangle boundingbox) {
        int PIX_SIZE = (Integer) NSWFAPI.jSpinner1.getValue();
        Raster src = img.getData();
        WritableRaster dest = src.createCompatibleWritableRaster();

        int gap = 3;
        for (int y = 0; y < src.getHeight(); y += PIX_SIZE) {
            for (int x = 0; x < src.getWidth(); x += PIX_SIZE) {
                double[] pixel = new double[3];
                pixel = src.getPixel(x, y, pixel);

                int y1 = y + PIX_SIZE;
                int x1 = x + PIX_SIZE;
                for (int yd = y; yd < y1 && yd < dest.getHeight(); ++yd) {
                    for (int xd = x; xd < x1 && xd < dest.getWidth(); ++xd) {
                        if (xd >= boundingbox.x && xd <= boundingbox.x + boundingbox.width && yd >= boundingbox.y && yd <= boundingbox.y + boundingbox.height) {
                            if (xd % PIX_SIZE < gap) {
                                defaultPixel(src, dest, yd, xd);
                            } else if (yd % PIX_SIZE < gap) {
                                defaultPixel(src, dest, yd, xd);
                            } else {
                                dest.setPixel(xd, yd, pixel);
                            }
                        } else {
                            defaultPixel(src, dest, yd, xd);
                        }
                    }
                }
            }
        }

        img.setData(dest);
    }

    private void defaultPixel(Raster src, WritableRaster dest, int yd, int xd) {
        double[] pix = new double[3];
        dest.setPixel(xd, yd, src.getPixel(xd, yd, pix));
    }

    private static BufferedImage blurBorder(BufferedImage input, double border) {
        int w = input.getWidth();
        int h = input.getHeight();
        BufferedImage output = new BufferedImage(w, h, 2);
        Graphics2D g = output.createGraphics();
        g.drawImage(input, 0, 0, null);
        g.setComposite(AlphaComposite.DstOut);
        Color c0 = new Color(0, 0, 0, 255);
        Color c1 = new Color(0, 0, 0, 0);
        g.setPaint(new GradientPaint(new java.awt.geom.Point2D.Double(0.0D, border), c0, new java.awt.geom.Point2D.Double(border, border), c1));
        g.fill(new java.awt.geom.Rectangle2D.Double(0.0D, border, border, (double) h - border - border));
        g.setPaint(new GradientPaint(new java.awt.geom.Point2D.Double((double) w - border, border), c1, new java.awt.geom.Point2D.Double(w, border), c0));
        g.fill(new java.awt.geom.Rectangle2D.Double((double) w - border, border, border, (double) h - border - border));
        g.setPaint(new GradientPaint(new java.awt.geom.Point2D.Double(border, 0.0D), c0, new java.awt.geom.Point2D.Double(border, border), c1));
        g.fill(new java.awt.geom.Rectangle2D.Double(border, 0.0D, (double) w - border - border, border));
        g.setPaint(new GradientPaint(new java.awt.geom.Point2D.Double(border, (double) h - border), c1, new java.awt.geom.Point2D.Double(border, h), c0));
        g.fill(new java.awt.geom.Rectangle2D.Double(border, (double) h - border, (double) w - border - border, border));
        g.setPaint(new RadialGradientPaint(new java.awt.geom.Rectangle2D.Double(0.0D, 0.0D, border + border, border + border), new float[]{0.0F, 1.0F}, new Color[]{c1, c0}, CycleMethod.NO_CYCLE));
        g.fill(new java.awt.geom.Rectangle2D.Double(0.0D, 0.0D, border, border));
        g.setPaint(new RadialGradientPaint(new java.awt.geom.Rectangle2D.Double((double) w - border - border, 0.0D, border + border, border + border), new float[]{0.0F, 1.0F}, new Color[]{c1, c0}, CycleMethod.NO_CYCLE));
        g.fill(new java.awt.geom.Rectangle2D.Double((double) w - border, 0.0D, border, border));
        g.setPaint(new RadialGradientPaint(new java.awt.geom.Rectangle2D.Double(0.0D, (double) h - border - border, border + border, border + border), new float[]{0.0F, 1.0F}, new Color[]{c1, c0}, CycleMethod.NO_CYCLE));
        g.fill(new java.awt.geom.Rectangle2D.Double(0.0D, (double) h - border, border, border));
        g.setPaint(new RadialGradientPaint(new java.awt.geom.Rectangle2D.Double((double) w - border - border, (double) h - border - border, border + border, border + border), new float[]{0.0F, 1.0F}, new Color[]{c1, c0}, CycleMethod.NO_CYCLE));
        g.fill(new java.awt.geom.Rectangle2D.Double((double) w - border, (double) h - border, border, border));
        g.dispose();
        return output;
    }

    public void blur(BufferedImage im, List<Rectangle> blurboxes) {
        List<BufferedImage> masks = new ArrayList<>();

        for (Rectangle box : blurboxes) {
            BufferedImage mask = new BufferedImage(im.getWidth(), im.getHeight(), 2);
            Graphics2D g2d = mask.createGraphics();
            Color transparent = new Color(255, 0, 0, 0);
            Color fill = Color.RED;
            //noinspection IntegerDivisionInFloatingPointContext intentional floor by integer in fp
            RadialGradientPaint rgp = new RadialGradientPaint(new java.awt.geom.Point2D.Double(box.x + box.width / 2, box.y + box.height / 2), (float) (box.width / 2 + box.height / 2), new float[]{0.0F, (Float) NSWFAPI.jSpinner2.getValue(), 1.0F}, new Color[]{transparent, transparent, fill});
            g2d.setPaint(rgp);
            AffineTransform tr2 = new AffineTransform();
            double scale_x = box.width > box.height ? 1.0D : 1.0D / ((double) box.height / (double) box.width);
            double scale_y = box.height > box.width ? 1.0D : 1.0D / ((double) box.width / (double) box.height);
            int center_x = box.x + box.width / 2;
            int center_y = box.y + box.height / 2;
            int new_center_x = (int) ((double) center_x * scale_x);
            int new_center_y = (int) ((double) center_y * scale_y);
            int sx = center_x - new_center_x;
            int sy = center_y - new_center_y;
            int sxm = -((int) ((double) sx / scale_x));
            int sym = -((int) ((double) sy / scale_y));
            tr2.translate(sx, sy);
            tr2.scale(scale_x, scale_y);
            g2d.setTransform(tr2);
            double r_x = 1.0D / scale_x;
            double r_y = 1.0D / scale_y;
            g2d.fill(new Rectangle(sxm, sym, (int) ((double) mask.getWidth() * r_x), (int) ((double) mask.getHeight() * r_y)));
            g2d.dispose();
            masks.add(mask);
        }

        BufferedImage masked = new BufferedImage(im.getWidth(), im.getHeight(), 2);
        Graphics2D g2d = masked.createGraphics();
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, masked.getWidth(), masked.getHeight());
        g2d.drawImage(im, 0, 0, null);
        g2d.setComposite(AlphaComposite.DstIn);

        for (BufferedImage mask : masks) {
            g2d.drawImage(mask, 0, 0, null);
        }

        g2d.dispose();
        g2d = this.blurbuff.createGraphics();
        g2d.drawImage(masked, 0, 0, null);
        g2d.dispose();
        Graphics2D g2 = im.createGraphics();
        g2.drawImage(this.blurbuff, 0, 0, im.getWidth(), im.getHeight(), null);
        g2.dispose();
    }

    public static void fastblur(BufferedImage img, int radius) {
        if (radius >= 1) {
            int w = img.getWidth();
            int h = img.getHeight();
            int wm = w - 1;
            int hm = h - 1;
            int wh = w * h;
            int div = radius + radius + 1;
            int[] r = new int[wh];
            int[] g = new int[wh];
            int[] b = new int[wh];
            int[] vmin = new int[Math.max(w, h)];
            int[] vmax = new int[Math.max(w, h)];
            int[] pix = new int[w * h];
            pix = img.getRGB(0, 0, w, h, pix, 0, w);
            int[] dv = new int[256 * div];

            int i;
            for (i = 0; i < 256 * div; ++i) {
                dv[i] = i / div;
            }

            int yi = 0;
            int yw = 0;

            int rsum;
            int gsum;
            int bsum;
            int x;
            int y;
            int p1;
            int p2;
            for (y = 0; y < h; ++y) {
                bsum = 0;
                gsum = 0;
                rsum = 0;

                for (i = -radius; i <= radius; ++i) {
                    int p = pix[yi + Math.min(wm, Math.max(i, 0))];
                    rsum += (p & 16711680) >> 16;
                    gsum += (p & '\uff00') >> 8;
                    bsum += p & 255;
                }

                for (x = 0; x < w; ++x) {
                    r[yi] = dv[rsum];
                    g[yi] = dv[gsum];
                    b[yi] = dv[bsum];
                    if (y == 0) {
                        vmin[x] = Math.min(x + radius + 1, wm);
                        vmax[x] = Math.max(x - radius, 0);
                    }

                    p1 = pix[yw + vmin[x]];
                    p2 = pix[yw + vmax[x]];
                    rsum += (p1 & 16711680) - (p2 & 16711680) >> 16;
                    gsum += (p1 & '\uff00') - (p2 & '\uff00') >> 8;
                    bsum += (p1 & 255) - (p2 & 255);
                    ++yi;
                }

                yw += w;
            }

            for (x = 0; x < w; ++x) {
                bsum = 0;
                gsum = 0;
                rsum = 0;
                int yp = -radius * w;

                for (i = -radius; i <= radius; ++i) {
                    yi = Math.max(0, yp) + x;
                    rsum += r[yi];
                    gsum += g[yi];
                    bsum += b[yi];
                    yp += w;
                }

                yi = x;

                for (y = 0; y < h; ++y) {
                    pix[yi] = -16777216 | dv[rsum] << 16 | dv[gsum] << 8 | dv[bsum];
                    if (x == 0) {
                        vmin[y] = Math.min(y + radius + 1, hm) * w;
                        vmax[y] = Math.max(y - radius, 0) * w;
                    }

                    p1 = x + vmin[y];
                    p2 = x + vmax[y];
                    rsum += r[p1] - r[p2];
                    gsum += g[p1] - g[p2];
                    bsum += b[p1] - b[p2];
                    yi += w;
                }
            }

            img.setRGB(0, 0, w, h, pix, 0, w);
        }
    }

    public static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    private void paintSticker(Graphics g, BufferedImage sticker, Rectangle bounding_box) {
        Dimension stickerdim = new Dimension(sticker.getWidth(), sticker.getHeight());
        Dimension box = new Dimension(bounding_box.width, bounding_box.height);
        Dimension scaledDimension = this.getScaledDimension(stickerdim, box);
        g.drawImage(sticker, bounding_box.x + (box.width - scaledDimension.width) / 2, bounding_box.y + (box.height - scaledDimension.height) / 2, scaledDimension.width, scaledDimension.height, null);
    }

    Dimension getScaledDimension(Dimension imageSize, Dimension boundary) {
        double widthRatio = boundary.getWidth() / imageSize.getWidth();
        double heightRatio = boundary.getHeight() / imageSize.getHeight();
        double ratio = Math.min(widthRatio, heightRatio);
        return new Dimension((int) ((double) imageSize.width * ratio), (int) ((double) imageSize.height * ratio));
    }
}
