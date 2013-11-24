package goair.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchParametersForReservation {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public long pnr = 0;
	public long customerId = 0;
	public long flightId = 0;
	public Date dateOfFlying;
	
	public long getPnr() {
		return pnr;
	}
	public void setPnr(long pnr) {
		this.pnr = pnr;
	}
	public long getFlightId() {
		return flightId;
	}
	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}
	public Date getDateOfFlying() {
		return dateOfFlying;
	}
	public void setDateOfFlying(Date dateOfFlying) {
		this.dateOfFlying = dateOfFlying;
	}
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		return "SearchParametersForReservation [pnr=" + pnr + ", customerId="
				+ customerId + ", flightId=" + flightId + ", dateOfFlying="
				+ dateFormat.format(dateOfFlying) + "]";
	}
	
	
	
}
