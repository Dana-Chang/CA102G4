package tools;

import java.util.regex.Pattern;

public class LocDistrict {
	Integer locNum;
	Pattern DistName;
	
	public LocDistrict(Integer locNum, Pattern distName) {
		super();
		setLocNum(locNum);
		setDistName(distName);
	}
	public Integer getLocNum() {
		return locNum;
	}
	public Pattern getDistName() {
		return DistName;
	}
	private void setLocNum(Integer locNum) {
		this.locNum = locNum;
	}
	private void setDistName(Pattern distName) {
		DistName = distName;
	}
}
