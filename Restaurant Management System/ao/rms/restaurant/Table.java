package ao.rms.restaurant;

import ao.rms.employee.Server;

public class Table {

	private int tableNo;
	private int tableCapacity;
	private boolean tableOccupied;
	private static int tableCount = 0;
	private Server server;
	private Bill bill;
	
	public Table(int tableCapacity) {
		this.tableCapacity = tableCapacity;
		this.tableNo = tableCount++;
		this.tableOccupied = false;
		this.server = null;
		this.bill = null;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public int getTableCapacity() {
		return tableCapacity;
	}

	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}

	public boolean isTableOccupied() {
		return tableOccupied;
	}

	public void setTableOccupied(boolean tableOccupied) {
		this.tableOccupied = tableOccupied;
	}
	
	public Server getServer() {
		return this.server;
	}
	
	public void setServer(Server server) {
		this.server = server;
	}
	
}
