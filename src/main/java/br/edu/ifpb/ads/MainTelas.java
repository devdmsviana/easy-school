package br.edu.ifpb.ads;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Font;
import br.edu.ifpb.ads.views.inicio.InicioGUI;

public class MainTelas {
    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes.flat");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        new InicioGUI().setVisible(true);
    }
}