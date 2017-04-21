import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String [] args){

        JSONParser parser = new JSONParser();
        List<Person> list = new ArrayList<>();

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            URLConnection connect = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));

            String line;
            while ((line = in.readLine()) != null){
                JSONArray array = (JSONArray) parser.parse(line);

                for (Object obj : array){
                    JSONObject json = (JSONObject) obj;

                    JSONArray address = (JSONArray) json.get("address");
                    JSONArray geo = (JSONArray) address.get(4);
                    JSONArray company = (JSONArray) json.get("company");


                    Person osoba = new Person(
                                    Integer.parseInt(json.get("ID").toString()),
                                    json.get("name").toString(),
                                    json.get("username").toString(),
                                    json.get("email").toString(),

                                    new Address(
                                            address.get(0).toString(),
                                            address.get(1).toString(),
                                            address.get(2).toString(),
                                            address.get(3).toString(),
                                            new Geo (geo.get(0).toString(), geo.get(1).toString())),

                                    json.get("phone").toString(),
                                    json.get("website").toString(),
                                    new Company(
                                            company.get(0).toString(),
                                            company.get(1).toString(),
                                            company.get(2).toString())
                    );
                    list.add(osoba);
                }
            }
        }
        catch (ParseException | IOException err) {
        }

    }
}
