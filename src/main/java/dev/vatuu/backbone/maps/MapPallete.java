package dev.vatuu.backbone.maps;

import com.google.common.collect.Lists;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public final class MapPallete {

    private static final List<Color> PALETTE = Lists.newArrayList(
            new Color(0, 0, 0),
            new Color(127, 178, 56),
            new Color(247, 233, 163),
            new Color(199, 199, 199),
            new Color(255, 0, 0),
            new Color(160, 160, 255),
            new Color(167, 167, 167),
            new Color(0, 124, 0),
            new Color(255, 255, 255),
            new Color(164, 168, 184),
            new Color(151, 109, 77),
            new Color(112, 112, 112),
            new Color(64, 64, 255),
            new Color(143, 119, 72),
            new Color(255, 252, 245),
            new Color(216, 127, 51),
            new Color(178, 76, 216),
            new Color(102, 153, 216),
            new Color(229, 229, 51),
            new Color(127, 204, 25),
            new Color(242, 127, 165),
            new Color(76, 76, 76),
            new Color(153, 153, 153),
            new Color(76, 127, 153),
            new Color(127, 63, 178),
            new Color(51, 76, 178),
            new Color(102, 76, 51),
            new Color(102, 127, 51),
            new Color(153, 51, 51),
            new Color(25, 25, 25),
            new Color(250, 238, 77),
            new Color(92, 219, 213),
            new Color(74, 128, 255),
            new Color(0, 217, 58),
            new Color(129, 86, 49),
            new Color(112, 2, 0),
            new Color(209, 177, 161),
            new Color(159, 82, 36),
            new Color(149, 87, 108),
            new Color(112, 108, 138),
            new Color(186, 133, 36),
            new Color(103, 117, 53),
            new Color(160, 77, 78),
            new Color(57, 41, 35),
            new Color(135, 107, 98),
            new Color(87, 92, 92),
            new Color(122, 73, 88),
            new Color(76, 62, 92),
            new Color(76, 50, 35),
            new Color(76, 82, 42),
            new Color(142, 60, 46),
            new Color(37, 22, 16),
            new Color(189, 48, 49),
            new Color(148, 63, 97),
            new Color(92, 25, 29),
            new Color(22, 126, 134),
            new Color(58, 142, 140),
            new Color(86, 44, 62),
            new Color(20, 180, 133)
    );

    public static byte[] imageToBytes(Image image) {
        BufferedImage tmp = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = tmp.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        int[] pixels = new int[tmp.getWidth() * tmp.getHeight()];
        tmp.getRGB(0, 0, tmp.getWidth(), tmp.getHeight(), pixels, 0, tmp.getWidth());

        byte[] result = new byte[tmp.getWidth() * tmp.getHeight()];
        Arrays.stream(pixels).forEach(i -> result[i] = matchColor(new Color(i, true)));

        return result;
    }

    public static byte matchColor(Color c) {
        if(c.getAlpha() < 128)
            return 0;

        int index = 0;
        double b = -1;

        for(int i = 4; i < PALETTE.size(); i++) {
            double distance = getDistance(c, PALETTE.get(i));
            if(distance < b || b == -1) {
                b = distance;
                index = i;
            }
        }

        return (byte)(index < 128 ? index : -129 + (index - 127));
    }

    private static double getDistance(Color c1, Color c2) {
        double rmean = (double)(c1.getRed() + c2.getRed()) / 2.0D;
        double r = c1.getRed() - c2.getRed();
        double g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        double weightR = 2.0D + rmean / 256.0D;
        double weightG = 4.0D;
        double weightB = 2.0D + (255.0D - rmean) / 256.0D;
        return weightR * r * r + weightG * g * g + weightB * (double)b * (double)b;
    }
}
