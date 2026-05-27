package Indodax;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
// import javafx.scene.control.TextArea;

// import Indodax.SqlConnector;

public class IndodaxPaginationController{

    @FXML
    private Pagination pagination;

    private static final int ROWS_PER_PAGE = 10;
    private int totalRows = 0;

    @FXML
    public void initialize(){
        try {
            totalRows = getTotalRows();
            System.out.println(">> Total baris = " + totalRows);
        } catch (SQLException e) {
            System.out.println(">> Eror: " + e.getMessage());
        }
        setUpPagination();
        
    }

    private int getTotalRows() throws SQLException {
        String query = "SELECT COUNT(*) FROM indodax_tickers";
        try (Connection conn = SqlConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    private List<String> getPageData(int pageIndex){
        List<String> pageData = new ArrayList<>();
        String query = "SELECT * FROM indodax_tickers LIMIT " + ROWS_PER_PAGE + " OFFSET " + (pageIndex * ROWS_PER_PAGE);

        try(Connection conn = SqlConnector.getConnection();
        Statement stmt = conn.createStatement(); 
        ResultSet rs = stmt.executeQuery(query);){
            while (rs.next()){
                String row = String.format("%-6d %-15s %-20.2f %-20.2f %-20.2f %-30s",
                rs.getInt(1),
                rs.getString(2),
                rs.getBigDecimal(3),
                rs.getBigDecimal(4),
                rs.getBigDecimal(5),
                rs.getTimestamp(6).toString()
            );
            pageData.add(row);
            }
        } catch (SQLException e) {
        }
        return pageData;
    }

    private void setUpPagination(){
        pagination.setPageFactory(pageIndex -> {
            VBox box = new VBox(3);
    box.setPadding(new Insets(6));

    Label header = new Label(String.format("%-6s %-15s %-20s %-20s %-20s %-30s",
        "ID", "Pair", "Last Price", "High 24h", "Low 24h", "Created At"));
    header.setFont(Font.font("Courier New", FontWeight.BOLD, 12)); 
    header.setStyle("-fx-text-fill: #2a6fc7;");
    box.getChildren().add(header);

    box.getChildren().add(new Label("─".repeat(90)));

    for (String row : getPageData(pageIndex)) {
        Label lbl = new Label(row);
        lbl.setFont(Font.font("Courier New", 12));
        box.getChildren().add(lbl);
    }

    return box;
        });

        int totalPages = (int) Math.ceil((double) totalRows / ROWS_PER_PAGE);
        pagination.setPageCount(totalPages == 0 ? 1 : totalPages);
    }


}

