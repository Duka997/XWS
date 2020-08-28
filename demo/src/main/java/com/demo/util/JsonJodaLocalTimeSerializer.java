package com.demo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

public class JsonJodaLocalTimeSerializer extends JsonSerializer<LocalDate> {
    private static DateTimeFormatter formatter = ISODateTimeFormat.date();

    @Override
    public void serialize(LocalDate dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(formatter.print(dateTime));
    }
}
