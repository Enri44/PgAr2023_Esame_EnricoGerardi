import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.nio.file.*;
import java.util.ArrayList;

public class Reader {

    public static void leggi_mappe(ArrayList<Nodo> mappa1, ArrayList<Nodo> mappa2) {
        FileInputStream file;
        XMLInputFactory input = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        {//inizializzazione dello StreamReader
            try {
                file = new FileInputStream("Mappe/Mappe.xml");
                reader = input.createXMLStreamReader(file);
                reader.next();
            } catch (Exception e) {
                System.out.println("Errore nell'inizializzazione del reader:");
                System.out.println(e.getMessage());
            }
        }


        while (true) {//continua finché non viene lanciata un'eccezione quando sono finiti gli eventi dell'xml
            try {
                if (!reader.hasNext()) break;
                if (reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals("MAPPE")) {
                    reader.next();
                    reader.next();
                    reader.next();
                    reader.next();
                    while (reader.getLocalName().equals("NODO")) {
                        if (!reader.hasNext()) break;
                        int id = Integer.parseInt(reader.getAttributeValue(0));
                        mappa1.add(new Nodo(id));
                        reader.next();
                        reader.next();
                        reader.next();
                        reader.next();
                        reader.next();
                        reader.next();
                        if (reader.getLocalName().equals("COLLEGAMENTI")) {
                            reader.next();
                            reader.next();
                            while (reader.getLocalName().equals("COLLEGAMENTO")) {
                                if (!reader.hasNext()) break;
                                reader.next();
                                mappa1.get(id - 1).aggiungiCollegamento(Integer.parseInt(reader.getText()));
                                reader.next();
                                reader.next();
                                reader.next();
                            }
                            reader.next();
                            reader.next();
                        }
                        reader.next();
                        reader.next();
                    }

                    while (reader.getLocalName().equals("NODO")) {
                        if (!reader.hasNext()) break;
                        int id = Integer.parseInt(reader.getAttributeValue(0));
                        mappa2.add(new Nodo(id));
                        reader.next();
                        reader.next();
                        reader.next();
                        reader.next();
                        reader.next();
                        reader.next();
                        if (reader.getLocalName().equals("COLLEGAMENTI")) {
                            reader.next();
                            reader.next();
                            while (reader.getLocalName().equals("COLLEGAMENTO")) {
                                if (!reader.hasNext()) break;
                                reader.next();
                                mappa2.get(id - 1).aggiungiCollegamento(Integer.parseInt(reader.getText()));
                                reader.next();
                                reader.next();
                                reader.next();
                            }
                            reader.next();
                            reader.next();
                        }
                        reader.next();
                        reader.next();
                    }
                }
                reader.next();
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
        }
    }
}