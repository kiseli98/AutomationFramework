package support.page_objects.webelements;

import org.openqa.selenium.By;
import support.utils.Helpers;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleTable extends WebElementX {
    public ElementsList<WebElementX> headerRepeater = new ElementsList<>(By.xpath(".//th"), null, this);


    public String rowLocator = ".//tbody/tr";

    public SimpleTable(By locator, String name, WebElementX parentElement) {
        super(locator, name != null ? name + " Table" : null, parentElement);
    }

    /**
     * @param index starts from 1
     * @return
     */
    public WebElementX getRow(int index) {
        String finalLocator = this.rowLocator + "[" + index + "]";
        return new WebElementX(By.xpath(finalLocator), null, this);
    }

    public ElementsList<WebElementX> getRows() {
        String finalLocator = this.rowLocator;
        return new ElementsList<>(By.xpath(finalLocator), null, this);
    }

    /**
     * @param row
     * @param colNum starts from 1
     * @return
     */
    public WebElementX getRowCell(WebElementX row, int colNum) {
        return row.element(By.xpath(".//td[" + colNum + "]"));
    }

    /**
     * @param rowNum starts from 1
     * @param colNum starts from 1
     * @return
     */
    public WebElementX getTableCell(int rowNum, int colNum) {
        logger.info("Getting Table Cell rowNum: " + rowNum + " colNum:" + colNum);
        return this.getRowCell(this.getRow(rowNum), colNum);
    }

    public int getRowsCount() {
        logger.info("Getting table rows count");
        return this.getRows().getCount();
    }

//    TODO
//    public int getAllRowsCount() {
//        logger.info("Getting all rows count");
//        int limit = 30;
//        int scrollHeight = 300;
//
//    }

    public int getColumnIndex(String colName) {
        int colIndex = headerRepeater.getText().indexOf(colName);
        logger.info("Column index:: " + colIndex);
        return colIndex;
    }

    public List<WebElementX> getColumnByField(String colName) {
        logger.info("Getting column by name [" + colName + ']');
        int colIndex = getColumnIndex(colName);
        return Helpers.range(getRowsCount()).stream().map(i ->
                this.getRowCell(this.getRow(i + 1), colIndex + 1)).collect(Collectors.toList());

    }

    /**
     * @param colName
     * @param rowIndex starts from 1
     * @return
     */
    public WebElementX getCellByField(String colName, int rowIndex) {
        logger.info("Getting cell by column name [" + colName + "] row: " + rowIndex);
        int colIndex = getColumnIndex(colName);
        return this.getRowCell(this.getRow(rowIndex), colIndex + 1);
    }


}
