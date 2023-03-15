package init;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadFont {
    private Font font;
    public LoadFont() {
        // This font is < 35Kb.
        URL fontUrl = null;
        try {
            fontUrl = new URL("https://github.com/bkaradzic/dotvim/blob/master/fonts/fixedsys-excelsior-301.ttf");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        Font coolGraphicsFont = font.deriveFont(Font.PLAIN,20);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(coolGraphicsFont);
    }

    public Font getLoadFont(){
        return font;
    }
}
