package support.ui_dto;

import io.cucumber.datatable.DataTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessage {
    private String name;
    private String subject;
    private String message;
    private String email;

    public ContactMessage (DataTable table) {
        Map<String,String> rowData = table.asMaps().get(0);

        this.name = rowData.get("Name");
        this.email = rowData.get("Email");
        this.subject = rowData.get("Subject");
        this.message = rowData.get("Message");
    }

}