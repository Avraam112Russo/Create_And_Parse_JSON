package org.example;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("student.json");
        fileWriter.write(stringWriter().toString());
        fileWriter.close();

        JsonParser jsonParser = Json.createParser(new FileReader("student.json"));
        while (jsonParser.hasNext()){
            JsonParser.Event event = jsonParser.next();
            if (event.equals(JsonParser.Event.KEY_NAME) || event.equals(JsonParser.Event.VALUE_NUMBER) || event.equals(JsonParser.Event.VALUE_STRING)){
                System.out.println(jsonParser.getString());
            }
        }
    }
    public static StringWriter stringWriter(){
        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = Json.createGenerator(stringWriter);
        jsonGenerator.writeStartObject().writeStartObject("Student")
                .write("name", "Russo")
                .write("age", "23")
                .writeStartObject("Address")
                .write("city", "Moscow")
                .write("Street", "Tverskaya")
                .writeEnd()
                .writeStartArray("exams")
                .write("English")
                .write("Math")
                .write("Geographic")
                .writeEnd() // close array
                .writeEnd() // close student
                .writeEnd() // close first .writeStartObject()
                .close();
        return stringWriter;
    }
}