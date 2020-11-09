package support.utils.transformers;

import io.cucumber.cucumberexpressions.Transformer;

public class EmailTransformer implements Transformer<String> {

    public String transform(String s) throws Throwable {
        return s.concat("@gmail.com");
    }
}
