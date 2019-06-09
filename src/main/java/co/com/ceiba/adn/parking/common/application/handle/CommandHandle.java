package co.com.ceiba.adn.parking.common.application.handle;

import org.springframework.transaction.annotation.Transactional;

public interface CommandHandle<C> {

	@Transactional
	void exec(C command);
}
