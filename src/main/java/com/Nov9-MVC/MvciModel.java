class MvciModel {
	private final StringProperty number = new SimpleStringProperty("0");
	private final BooleanProperty moreAllowed = new SimpleBooleanProperty(true);
	
	String getNumber()
	{
		return number.get();
	}
	
	StringProperty numberProperty() {
		return number;
	}
	
	void setNumber(String number)
	{
		this.number.set(number);
	}
	
	boolan isMoreAllowed() {
		return moreAllowed.get();
	}
	
	BooleanProperty moreAllowedProperty() {
		return moreAllowed;
	}
	
	void setMoreAllowed(boolean moreAllowed)
	{
		this.moreAllowed.set(moreAllowed);
	}
	
}
