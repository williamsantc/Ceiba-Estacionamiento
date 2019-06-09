package co.com.ceiba.adn.parking.common.application.handle;

import org.springframework.transaction.annotation.Transactional;

public interface CommandHandleResponse<C, R> {

	@Transactional
	R exec(C command);
}
