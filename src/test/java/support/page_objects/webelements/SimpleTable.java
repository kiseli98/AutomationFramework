package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import support.utils.Helpers;

import java.util.List;
import java.util.stream.Collectors;

@Log4j
public class SimpleTable extends CustomElement {
    public ElementsList<CustomElement> headerRepeater = new ElementsList<>(By.xpath(".//th"), null, this);


    public String rowLocator = ".//tbody/tr";

    public SimpleTable(By locator, String name, CustomElement parentElement) {
        super(locator, name != null ? name + " Table" : null, parentElement);
    }

    /**
     * @param index starts from 1
     * @return
     */
    public CustomElement getRow(int index) {
        String finalLocator = this.rowLocator + "[" + index + "]";
        return new CustomElement(By.xpath(finalLocator), null, this);
    }

    public ElementsList<CustomElement> getRows() {
        String finalLocator = this.rowLocator;
        return new ElementsList<>(By.xpath(finalLocator), null, this);
    }

    /**
     * @param row
     * @param colNum starts from 1
     * @return
     */
    public CustomElement getRowCell(CustomElement row, int colNum) {
        return row.element(By.xpath(".//td[" + colNum + "]"));
    }

    /**
     * @param rowNum starts from 1
     * @param colNum starts from 1
     * @return
     */
    public CustomElement getTableCell(int rowNum, int colNum) {
        log.info("Getting Table Cell rowNum: " + rowNum + " colNum:" + colNum);
        return this.getRowCell(this.getRow(rowNum), colNum);
    }

    public int getRowsCount() {
        log.info("Getting table rows count");
        return this.getRows().getCount();
    }

//    TODO
//    public int getAllRowsCount() {
//        log.info("Getting all rows count");
//        int limit = 30;
//        int scrollHeight = 300;
//
//    }

    public int getColumnIndex(String colName) {
        int colIndex = headerRepeater.getText().indexOf(colName);
        log.info("Column index:: " + colIndex);
        return colIndex;
    }

    public List<CustomElement> getColumnByField(String colName) {
        log.info("Getting column by name [" + colName + ']');
        int colIndex = getColumnIndex(colName);
        return Helpers.range(getRowsCount()).stream().map(i ->
                this.getRowCell(this.getRow(i + 1), colIndex + 1)).collect(Collectors.toList());

    }

    /**
     * @param colName
     * @param rowIndex starts from 1
     * @return
     */
    public CustomElement getCellByField(String colName, int rowIndex) {
        log.info("Getting cell by column name [" + colName + "] row: " + rowIndex);
        int colIndex = getColumnIndex(colName);
        return this.getRowCell(this.getRow(rowIndex), colIndex + 1);
    }


}
