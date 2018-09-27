package ratefinderjobs.lambda;

import java.net.HttpURLConnection;
import java.net.URL;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);

        System.out.println("Starting on input "+ input.toString());
        // TODO: implement your handler
        try {
            triggerUrl();
        } catch (Exception ex){
            System.out.print(ex);
        }

        return "Hello "+input;
    }


    private void triggerUrl() throws Exception {

        String urlString ="somesampleurl.com/someservice";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        String statusMessage = con.getResponseMessage();

        System.out.println("Status code "+ status + " status message " + statusMessage);
    }

}
