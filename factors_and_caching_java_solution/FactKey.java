
public class FactKey {
	private Integer key;

	
	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	} 
	
	
	public FactKey(Integer key) {
		super();
		this.key = key;
	}

	
	@Override
	public boolean equals(Object obj) {
		FactKey paramKey = (FactKey)obj;
		return this.getKey()<paramKey.getKey()?false:true;
	}
}
