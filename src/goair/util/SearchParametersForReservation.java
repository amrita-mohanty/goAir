package goair.util;

import java.util.Date;

public class SearchParametersForReservation {
	public long pnr;
	public int customerId;
	public int flightId;
	public Date dateOfFlying;
	
	public long getPnr() {
		return pnr;
	}
	public void setPnr(long pnr) {
		this.pnr = pnr;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public Date getDateOfFlying() {
		return dateOfFlying;
	}
	public void setDateOfFlying(Date dateOfFlying) {
		this.dateOfFlying = dateOfFlying;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		return "SearchParametersForReservation [pnr=" + pnr + ", customerId="
				+ customerId + ", flightId=" + flightId + ", dateOfFlying="
				+ dateOfFlying + "]";
	}
	
	
	
}
