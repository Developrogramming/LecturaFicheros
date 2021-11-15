package developrogramming.com;

//Librerías JAVA
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//Librerías POI
import org.apache.poi.xwpf.converter.core.XWPFConverterException;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Principal
{

	public static void main(String[] args) {
		
		File archivoWord = new File("C:\\ficherosParaLeer\\fichero1.docx");
		File archivoPDF = new File("C:\\ficherosParaLeer\\fichero1.pdf");

		// Se lee el contenido del fichero Word y se guarda en un objeto especial POI
		// para su posterior conversión
		XWPFDocument document = leerDocx(archivoWord);

		// Se convierte el contenido del fichero Word a PDF
		convertirPDF(archivoPDF, document);

	}

	static XWPFDocument leerDocx(File archivoWord) {

		XWPFDocument documentoWord = null;

		try {
			// Se prepara el archivo para su tratamiento
			InputStream texto = new FileInputStream(archivoWord);

			// Creamos documento especial POI para su posterior conversión
			documentoWord = new XWPFDocument(texto);

		} catch (IOException e) {
			System.out.println("Error leyendo el fichero de Word");
			e.printStackTrace();
		}
		return documentoWord;

	}

	static void convertirPDF(File archivoPDF, XWPFDocument documentWord) {

		try {
			OutputStream out = new FileOutputStream(archivoPDF);
			PdfOptions options = PdfOptions.create();
			PdfConverter.getInstance().convert(documentWord, out, options);

			// Mostramos mensaje de éxito
			System.out.println("El fichero de Word se ha convertido a PDF con éxito");

		} catch (XWPFConverterException e) {
			System.out.println("Error en la conversión");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error creando el fichero PDF");
			e.printStackTrace();
		}

	}

}
