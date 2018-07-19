package tools.crawler;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class attraction {

	public static void main(String[] args) throws IOException, RowsExceededException, WriteException, ParseException {
		List<String> resName = new ArrayList<>();
		List resAddress = new ArrayList<>();
		String line;
		WritableWorkbook workbook = Workbook.createWorkbook(new File("items/attraction.xls"));
		WritableSheet sheet = workbook.createSheet("attraction", 0);
		URL url;
		InputStream is = null;
		BufferedReader br;

		String website = "https://tw.bizpo.net/local/hot/109/482/526?page=";
		int resCount = 1;
		int infoCount = 1;
		Label label = new Label(0, 0, "景點");
		Label label2 = new Label(1, 0, "地址");
		Label label3 = new Label(2, 0, "latitude");
		Label label4 = new Label(3, 0, "longitude");
		sheet.addCell(label);
		sheet.addCell(label2);
		sheet.addCell(label3);
		sheet.addCell(label4);
		for (int page = 0; page <= 4; page++) {
			Document doc = Jsoup.connect(website+page).validateTLSCertificates(false).get();

			Elements titles = doc.select(".field-item>h2>a");
			Elements addresses = doc.select("[itemprop='name']");
			// 寫入店家名稱
			for (Element title : titles) {
				resName.add(title.text());
				Label restLab = new Label(0, resCount, title.text());
				sheet.addCell(restLab);
				resCount++;
				System.out.println(title.text());
			}

			// 寫入地址經緯度
			for (Element address : addresses) {
				System.out.println(infoCount);
				String searchString = "http://maps.googleapis.com/maps/api/geocode/json?address="
						+ java.net.URLEncoder.encode(address.text(), "UTF-8") + "&sensor=false&language=zh-TW";

				url = new URL(searchString);
				is = url.openStream(); // throws an IOException
				StringBuilder jsonBuilder = new StringBuilder();
				br = new BufferedReader(new InputStreamReader(is));

				while ((line = br.readLine()) != null) {
					jsonBuilder.append(line);
				}
				// now parse
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(jsonBuilder.toString());

				// now read
				JSONArray jsonRs = (JSONArray) jsonObj.get("results");
				JSONObject jsonObject = (JSONObject) jsonRs.get(0);
				String formatted_address = (String) jsonObject.get("formatted_address");
				JSONObject geometry = (JSONObject) jsonObject.get("geometry");
				JSONObject location = (JSONObject) geometry.get("location");

				System.out.println(formatted_address);
				Label addLabel = new Label(1, infoCount, formatted_address);
				sheet.addCell(addLabel);

				System.out.println(location.get("lat").toString());
				Label latLabel = new Label(2, infoCount, location.get("lat").toString());
				sheet.addCell(latLabel);

				System.out.println(location.get("lng").toString());
				Label lonLabel = new Label(3, infoCount, location.get("lng").toString());
				sheet.addCell(lonLabel);

				infoCount++;
			}
		}
		is.close();
		workbook.write();
		workbook.close();

	}

}
