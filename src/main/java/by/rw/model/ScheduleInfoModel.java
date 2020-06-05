package by.rw.model;

import by.rw.util.TimeFormat;
import by.rw.util.TimeFormatter;

public class ScheduleInfoModel
{
	private String trainName;
	private String departureTime;
	private String inWayTime;

	public ScheduleInfoModel()
	{
	}

	public ScheduleInfoModel(String trainName, String departureTime, String inWayTime)
	{
		this.trainName = trainName;
		this.departureTime = departureTime;
		this.inWayTime = inWayTime;
	}

	public String getTrainName()
	{
		return trainName;
	}

	public void setTrainName(String trainName)
	{
		this.trainName = trainName;
	}

	public String getDepartureTime()
	{
		return departureTime;
	}

	public void setDepartureTime(String departureTime)
	{
		this.departureTime = departureTime;
	}

	public String getInWayTime()
	{
		return inWayTime;
	}

	public void setInWayTime(String inWayTime)
	{
		this.inWayTime = inWayTime;
	}

	public int getDepartureTimeInMinutes()
	{
		return departureTime != null ? TimeFormatter.getMinutes(TimeFormat.HH_COLON_MM, departureTime) : -1;
	}

	public int getInWayTimeInMinutes()
	{
		return inWayTime != null ? TimeFormatter.getMinutes(TimeFormat.HH_MM, inWayTime) : -1;
	}
}
