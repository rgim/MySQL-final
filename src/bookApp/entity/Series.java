package bookApp.entity;

public class Series {

	private int seriesId;
	private String seriesName;
		
	public Series(int seriesId, String name) {
		this.seriesId = seriesId;
		this.seriesName = name;
	}

	public int getSeriesId() {
		return seriesId;
	}

	public String getSeriesName() {
		return seriesName;
	}
}
