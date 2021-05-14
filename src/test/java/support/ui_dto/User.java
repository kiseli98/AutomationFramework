package support.ui_dto;

import io.cucumber.datatable.DataTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
    private String username;
    private String password;

    public User (DataTable table) {
        Map<String,String> rowData = table.asMaps().get(0);

        this.username = rowData.get("Username");
        this.password = rowData.get("Password");
    }

    public static User createUserFromTable(DataTable table, int index) {
        Map<String,String> rowData = table.asMaps().get(index);
        User user = new User();
        user.setUsername("Username");
        user.setPassword("Password");
        return user;
    }

}

