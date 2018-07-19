package tools.crawler;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class WriteToSql {

	public static void main(String[] args) throws BiffException, IOException, ClassNotFoundException, SQLException {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "AA107G5";
		String passwd = "AA107G5";

		final String INSERT_STMT = "INSERT INTO spot (spotNo, spotName, spotAddr,spotType, spotLng, spotLat, spotRate, "
				+ " spotOverview, spotPhoto,spotOwner,spotChk, spotHdl, spotIsBlocked, spotBlockedReason) values "
				+ "(spotNo_seq.NEXTVAL,?,?, ?, ?, ?, ?, ?, ?, 0, ?, ? ,?,?)";

		File file = new File("items/spot.xls");
		Workbook workbook = Workbook.getWorkbook(file);
		int sheetnum = workbook.getNumberOfSheets();

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, userid, passwd);
		PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);

		for (int i = 0; i < sheetnum; i++) {
			int rows = workbook.getSheet(i).getRows();
			int columns = workbook.getSheet(i).getColumns();

			for (int j = 1; j < rows; j++) {
				for (int k = 0; k < columns; k++) {
					Cell cell = workbook.getSheet(i).getCell(k, j);
					String contents = cell.getContents();
					if (k == 0) {
						pstmt.setString(1, contents);
					} else if (k == 1) {
						pstmt.setString(2, contents);
					} else if (k == 2) {
						pstmt.setString(5, contents);
					} else {
						pstmt.setString(4, contents);
					}
					System.out.println("i=" + i + "j=" + j + "k=" + k + ":" + contents);
				}
				pstmt.setString(3, Integer.toString(i));
				System.out.println(Integer.toString(i));
				pstmt.setDouble(6, 0.0);
				pstmt.setString(7, "");
				pstmt.setBytes(8, null);
				pstmt.setString(9, "0");
				pstmt.setString(10, "0");
				pstmt.setString(11, "0");
				pstmt.setString(12, "");

				pstmt.executeUpdate();
			}
		}
	}
}
