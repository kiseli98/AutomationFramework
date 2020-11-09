package support.ui_dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Expense {

    private String name = null;
    private String amount = null;
    private String frequency = null;

    public Expense(String name, String amount, String frequency) {
        this.name = name;
        this.amount = amount;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    // Getters and setters
}