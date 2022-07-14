package ca.georgiancollege.copr.comp1011;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class WorkingWithJson {

    static void task2(){

        Shoe shoe = new Shoe("Plain", 2022);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String text = gson.toJson(shoe);
        System.out.println(text);

    }


    static void usingTheJsonReader() throws IOException {
        String text = "{\"Shoe Name\":\"plain\",\"Shoe Release Year\":2022}";

        JsonReader reader = new JsonReader(new StringReader(text));


        while(reader.hasNext()) {

            //get the token
            JsonToken token = reader.peek(); //where are we in this process
            System.out.println(token);

            if(token.toString().equals("BEGIN_OBJECT")){
                reader.beginObject();
            }
            else if(token.toString().equals("NAME")){
                String nameOfToken = reader.nextName(); //key name
                System.out.println(nameOfToken);
            }
            else if(token.toString().equals("STRING")){
                String valueOfKey = reader.nextString(); //Value of key
                System.out.println(valueOfKey);
            }
            else if(token.toString().equals("NUMBER")){
                int valueOfNumber = reader.nextInt(); //Value of key
                System.out.println(valueOfNumber);
            }



        }

    }


    static void multipleValuesPart1(){


        ArrayList<Shoe> shoesList = new ArrayList<>();
        shoesList.add(new Shoe("First", 2001));
        shoesList.add(new Shoe("Second", 2005));
        shoesList.add(new Shoe("Third", 2008));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String text = gson.toJson(shoesList);
        System.out.println();
        System.out.println(text);
        System.out.println();
        System.out.println();
        try {
            passingInfoToTheJsonReader(text);
        }
        catch (Exception e){
            System.err.println(e);
        }

    }

    //use the reader to get all values get in our multipleValuesPart1


    static void passingInfoToTheJsonReader(String text) throws IOException {

        JsonReader reader = new JsonReader(new StringReader(text));

        reader.beginArray();
        while(reader.hasNext()) {
            reader.beginObject();
            while (reader.hasNext()) {

                //get the token
                JsonToken token = reader.peek(); //where are we in this process
                //System.out.println(token); // which token are we on?

                if (token.toString().equals("BEGIN_OBJECT")) {
                } else if (token.toString().equals("NAME")) { // name of Token: Shoe Name, Shoe Release Year
                    String nameOfToken = reader.nextName(); //Name of token String number
                    System.out.println(nameOfToken);
                } else if (token.toString().equals("STRING")) {
                    String valueOfKey = reader.nextString(); //Value of key
                    System.out.println(valueOfKey);
                } else if (token.toString().equals("NUMBER")) {
                    int valueOfNumber = reader.nextInt(); //Value of key
                    System.out.println(valueOfNumber);
                }


            }
            reader.endObject();
        }
        reader.endArray();
    }

    static void jsonPart1(){

        /*
        JSON
            Java
            Script
            Object
            Notation

                { }
                    just data
                    name-value pairs *
                    key-value

                    {"hi", "there", "everyone", 'A', 1, 2.2}
                    {"first": "hi", "second":"there", "third": "everyone"}

                    '{"first": "hi", "second":"there", "third": "everyone"}'

                   String JSONValue =  "{\"first\": \"hi\", \"second\":\"there\", \"third\": \"everyone\"}";

                   {"action":"Cool Action Movie", "comedy":"funny movie"}



                    GSON
         */


    }

    static void createJson1(){

        Car firstCar = new Car("Honda", "Civic", 2010);

        Gson gson = new Gson();
        String jsonText = gson.toJson(firstCar);
        System.out.println(jsonText);
        createObjectFromJson(jsonText);
    }

    static void createObjectFromJson(String jsonText){

        Gson gson = new Gson();
        Car car = gson.fromJson(jsonText, Car.class);
        System.out.println(car);
    }
    static void jsonWithReadableFormatting(){

        Car car = new Car("Dodge", "Caravan", 2015);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String text = gson.toJson(car);
        System.out.println(text);

    }
    static void excludingFields(){
        //use the annotation of @Expose(serialization = true|false, deserialization = true|false)
        //mark a field as transient
        Car car = new Car("Dodge", "Caravan", 2015);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        //builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        String text = gson.toJson(car);
        System.out.println(text);

    }

    public static void main(String[] args) {
        //createJson1();
        //jsonWithReadableFormatting();
        //excludingFields();

        //task2();
        /*
        try {
            usingTheJsonReader();

        }
        catch (Exception e){
            System.err.println(e);
        }

         */

        multipleValuesPart1();

        /*
            transform an object to a json string
                flat string that can be read by any language
                    serialize
                    serialization

            transform string to an object
                deserialize
                deserialization


         */


        /*
        Create a class with two fields
            mark one of the fields as "transient" or Exposed to be false
            add a constructor
            add a toString method

        Create an instance of this class
            serialize it
            pass it to another method

        from this new method, re-create the class and output its values using toString
         */
    }
}
