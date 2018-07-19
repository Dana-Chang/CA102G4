package tools;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.spot.model.*;
import com.spotCmnt.model.*;
import com.trip.model.*;
import com.tripCmnt.model.*;

public class ScheduleServlet extends HttpServlet{    
    Timer timer ; 
    Calendar cal ;
    DecimalFormat df = new DecimalFormat("##.0");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
   
    SpotService spotSvc = new SpotService();
    SpotCmntService spotCmntSvc = new  SpotCmntService();
    TripService tripSvc = new TripService();
    TripCmntService tripCmntSvc = new TripCmntService();;
    
    
    public void destroy(){
        timer.cancel();
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    }
            
    public void init(){        
        timer = new Timer();
        cal = new GregorianCalendar(); 
        
        TimerTask task = new TimerTask(){
        	
            public void run(){
            	
            	System.out.print("開始更新，現在時間"+ sdf.format(new Date()));
            	
            	//從評論來計算地點的星數
            	List<SpotVO> spotList = spotSvc.getAll();
				List<SpotCmntVO> spotCmntVOList =  spotCmntSvc.getAll();
				for(SpotVO spotVO:spotList){
					Integer spotCmntCount = 0;
					Integer spotRate = 0;
					Double finalRate=0.0;
					for(SpotCmntVO  spotCmntVO:spotCmntVOList){
						if(spotCmntVO.getSpotNo()==spotVO.getSpotNo()){
							spotCmntCount++;
							spotRate = spotRate + spotCmntVO.getRate();
//							System.out.println("美則評論給的分數:"+spotVO.getSpotNo());
//							System.out.println("分數總和:"+spotRate);
//							System.out.println(spotCmntCount);
							
						}
						try {
							finalRate = Double.parseDouble (df.format(spotRate.doubleValue()/spotCmntCount.doubleValue()));
						} catch (Exception e) {
							finalRate=0.0;
						}
					}
//					System.out.println(spotVO.getSpotName());
//					System.out.println(spotRate);
//					System.out.println(spotCmntCount);
//					System.out.println(finalRate);
					//更新評分
					spotSvc.updateSpot(spotVO.getSpotNo(), spotVO.getSpotName(), spotVO.getSpotAddr(), spotVO.getSpotType(), spotVO.getSpotLng(), spotVO.getSpotLat(), finalRate, spotVO.getSpotOverview(), spotVO.getSpotPhoto(), spotVO.getSpotOwner(), spotVO.getSpotChk(), spotVO.getSpotHdl(), spotVO.getSpotIsBlocked(), spotVO.getSpotBlockedReason());
//					System.out.println("更新更新");
				}

				


				//從評論來計算行程的分數
				List<TripCmntVO> tripCmntVOList = tripCmntSvc.getAll();
				List<TripVO> tripList = tripSvc.getAll();
				for(TripVO tripVO:tripList){
					Integer tripCmntCount = 0;
					Integer tripRate = 0;
					Double finalTripRate=0.0;
					for(TripCmntVO tripCmntVO:tripCmntVOList){
						if(tripCmntVO.getTripNo()==tripVO.getTripNo()){
							tripCmntCount++;
							tripRate= tripRate+tripCmntVO.getTripRate();
						}
						try {
							finalTripRate = Double.parseDouble(df.format(tripRate.doubleValue()/tripCmntCount.doubleValue()));
						} catch (Exception e) {
							finalTripRate = 0.0;
						}
					}
//					System.out.println(finalTripRate);
					//更新評分
					tripSvc.updateTrip(tripVO.getTripNo(), tripVO.getMemId(), tripVO.getTripName(), tripVO.getDepartTime(), tripVO.getTripType(), tripVO.getTripAddTime(), tripVO.getTripRmvTime(), finalTripRate, tripVO.getTripIsPublic(), tripVO.getTripPrice(), tripVO.getTripAdImg(), tripVO.getTripDesc(), tripVO.getTripContent());
				}
				
				System.out.println("評分已更新");
            }
        };
        timer.scheduleAtFixedRate(task, cal.getTime(), 60*1000); 
    }
}
