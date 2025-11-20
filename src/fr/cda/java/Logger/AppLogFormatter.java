package fr.cda.java.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * FormatterLog
 *
 * <p>Description : Un logger avec un message complet.</p>
 *
 * @author CDA-09
 * @version 1.0
 * @since 30/10/2025
 */
public class AppLogFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder result = new StringBuilder();
        result.append(format.format(LocalDate.now()));
        result.append(" Level : ");
        result.append(record.getLevel());
        result.append(" / Message : ");
        result.append(record.getMessage());
        result.append(" / Classe : ");
        result.append(record.getSourceClassName());
        result.append(" / Méthode : ");
        result.append(record.getSourceMethodName());
        result.append("\n");
        return result.toString();

    }
}
