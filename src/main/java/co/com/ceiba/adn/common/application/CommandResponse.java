package co.com.ceiba.adn.common.application;

public class CommandResponse<T> {

	private T value;
	
	public CommandResponse(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
