package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportData {

    public static void exportToCSV(Table table) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setDialogTitle("Save CSV File");
        fc.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));

        int result = fc.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filePath = file.getAbsolutePath();
            if (!filePath.endsWith(".csv")) {
                filePath += ".csv";
            }
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"))) {
                writer.write('\uFEFF'); 
                // Write column headers
                for (int col = 0; col < table.getColumnCount(); col++) {
                    writer.write(escapeSpecialCharacters(table.getColumnName(col)));
                    if (col < table.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();

                // Write data rows
                for (int row = 0; row < table.getRowCount(); row++) {
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        Object value = table.getValueAt(row, col);
                        writer.write(escapeSpecialCharacters(value != null ? value.toString() : ""));
                        if (col < table.getColumnCount() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(table, new message("ບັນທຶກສຳເລັດແລ້ວ"));
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(table, new message("ເກີດຂໍ້ຜິດພາດ!!!"));
            }
        }
    }

    /**
     * Escapes special characters in the CSV data to ensure compatibility with
     * Excel.
     */
    private static String escapeSpecialCharacters(String data) {
        String escapedData = data;
        if (data.contains(",") || data.contains("\"") || data.contains("\n")) {
            escapedData = "\"" + data.replace("\"", "\"\"") + "\"";
        }
        return escapedData;
    }
}
