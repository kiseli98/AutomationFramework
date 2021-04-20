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
public class Value {
    private String value;

    public static Value createValueFromTable(DataTable table, int index) {
        Map<String,String> rowData = table.asMaps().get(index);
        Value value = new Value();
        value.setValue(rowData.get("Value"));
        return value;
    }
}
