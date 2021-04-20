package support.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import support.managers.FileReaderManager;
import support.ui_dto.Customer;

public class JsonDataReader {
    private static final String customerFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataJsonsResourcePath() + "Customers.json";
    private List<Customer> customerList;


    public JsonDataReader() {
        customerList = getCustomerData();
    }

    private List<Customer> getCustomerData() {
        Gson gson = new Gson();
        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(new FileReader(customerFilePath));
            Customer[] customers = gson.fromJson(bufferReader, Customer[].class);
            return Arrays.asList(customers);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Json file not found at path : " + customerFilePath);
        } finally {
            try {
                if (bufferReader != null) bufferReader.close();
            } catch (IOException ignore) {
            }
        }
    }

    public final Customer getCustomerByName(String customerName) {
        return customerList.stream().filter(x -> x.firstName.equalsIgnoreCase(customerName)).findAny().get();
    }

    public static void main(String[] args) throws FileNotFoundException {
//        Gson gson = new Gson();
//        JsonObject jsonObject = gson.fromJson( customerFilePath, JsonObject.class);
//        System.out.println(jsonObject.get("firstName").getAsString());
        BufferedReader bufferReader = null;
        bufferReader = new BufferedReader(new FileReader(customerFilePath));
        String myJSONString = "{'test': '100.00'}";
        JsonObject jobj = new Gson().fromJson(bufferReader, JsonObject.class);

        String result = jobj.get("firstName").toString();
        System.out.println(result);
    }
}
