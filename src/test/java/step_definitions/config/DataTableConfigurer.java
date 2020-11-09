package step_definitions.config;

import java.util.Locale;
import java.util.Map;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;
import support.ui_dto.Expense;
import support.ui_dto.User;

/*
 * Maps datatables in feature files to custom domain objects.
 */
public class DataTableConfigurer implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry registry) {

        registry.defineDataTableType(new DataTableType(Expense.class, new TableEntryTransformer<Expense>() {
            @Override
            public Expense transform(Map<String, String> entry) {
                return new Expense(entry.get("name"), entry.get("amount"), entry.get("frequency"));
            }
        }));

//        registry.defineDataTableType(new DataTableType(User.class, new TableEntryTransformer<User>() {
//            @Override
//            public User transform(Map<String, String> entry) {
//                return new User(entry.get("Username"), entry.get("Password"));
//            }
//        }));
    }

}
