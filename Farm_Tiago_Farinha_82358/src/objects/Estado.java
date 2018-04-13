package objects;

public enum Estado {
	SMALL("small_"), GROWN(""), RUINED("bad_");
	
	private String state;
	
	private Estado(String state) {
		this.state = state;
	}
	
	public String getPrefix() {
		return state;
	}
	
	

}
