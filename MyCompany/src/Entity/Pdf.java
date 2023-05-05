/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import java.time.LocalDateTime;
import services.ServiceAchat;

/**
 *
 * @author HP
 */
public class Pdf {
    public void GeneratePdf(String filename, Achat f, int id) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        ServiceAchat sf = new ServiceAchat();
        document.add(new Paragraph("            Date  :"+LocalDateTime.now()));
        document.add(new Paragraph("            l'achat :"+f.getProduit()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

        document.add(new Paragraph("La quantite de produit :" + f.getQuantite()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("le prix du produit :" + f.getPrix()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("le Date d'achat de ce produit:" + f.getDateAchat()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Son taxe :" + f.getTaxe()));
        document.add(new Paragraph("                      "));

       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.add(new Paragraph("                              Ahmed Akermi"
                + "                     "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }

    
}
