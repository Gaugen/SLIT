package no.uia.slit.filetransfer;

import no.uia.slit.entity.SlitFile;
import java.io.IOException;
import java.io.InputStream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author Tor
 */
@Named("uploadbean")
@RequestScoped
public class FileUploadBean {

    private boolean processedPart;
    private Part part;
    private SlitFile file;

    public FileUploadBean() {
    }

    public void upload() throws IOException {
        System.out.println("FileUploadBean.upload()");
        readFile();
    }

    protected void readFile() throws IOException {
        System.out.println("FileUploadBean.readFile()");

        String fileName = getFilename(part);
        String contentType = part.getContentType();
        int size = (int) part.getSize();
        byte[] buf = new byte[(int) size];
        InputStream in = part.getInputStream();

        System.out.println("filesize=" + size);
        int total = 0;
        int read = 0;
        while ((read = in.read(buf, total, (size - total))) != -1) {
            total += read;
            if (read == 0) {
                break;
            }
            System.out.format("read=%d total=%d\n", read, total);
        }
        if (total != size) {
            System.err.println("Incorrect file size");
        }
        file = new SlitFile(fileName, contentType, size, buf);
        processedPart = true;
    }

    private String getFilename(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("***** partHeader: " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

    public Part getPart() {
        System.out.println("FileUploadBean.getPart()");
        return part;
    }

    public void setPart(Part part) {
        System.out.println("FileUploadBean.setPart()");
        this.part = part;
        processedPart = false;
    }

    public SlitFile getFile() {
        System.out.println("FileUploadBean.getFile()");
        if (!processedPart) {
            try {
                readFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
