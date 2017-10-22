import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AggregatorService {

    ArrayList<String> list = new ArrayList<String>();

    public JSONObject processMessage(String quotes) throws JSONException {
        //choose the best quote :)
        //String quotes = "[{\"interestRate\":3.6399999999999997,\"bank\":\"NordeaBank\",\"ssn\":1234566543},{\"interestRate\":5.5,\"bank\":\"BankOfNorrebro\",\"ssn\":1605789787}]";
        JSONArray newJArray = new JSONArray(quotes);
        System.out.println(newJArray.length());
        for (int i = 0; i < newJArray.length(); i++) {
            JSONObject obj = (JSONObject) newJArray.get(i);
            System.out.println(obj);
            list.add(obj.toString());
        }

        Double lowestInterestRate = Double.MAX_VALUE;
        JSONObject bestQuote = null;

        for (String o: list) {
            JSONObject obj = new JSONObject(o);

            if (lowestInterestRate > obj.getDouble("interestRate") ){
                lowestInterestRate = obj.getDouble("interestRate");
                bestQuote = obj;
            }
        }
        System.out.println("Lowest interest rate: " + lowestInterestRate);
        System.out.println("Best quote: " + bestQuote );
        return bestQuote;
    }

}
